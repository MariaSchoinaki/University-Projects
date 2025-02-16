import SymTableClasses.SymFunction;
import SymTableClasses.SymItem;
import SymTableClasses.SymVariable;
import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import minipython.lexer.Lexer;
import minipython.node.Start;
import minipython.parser.Parser;

public class ParserTest1
{

    public static int errors = 0;
    public static boolean debug = false;

  public static void main(String[] args)
  {
    try
    {
      Parser parser =
        new Parser(
        new Lexer(
        new PushbackReader(
        new FileReader(args[0].toString()), 1024)));

        Hashtable<String, SymItem>symFTable = new Hashtable<String, SymItem>();
        Hashtable<String, SymItem>symVTable = new Hashtable<String, SymItem>();

        Start ast = parser.parse();
      
        ast.apply(new ASTPrinter());

        if (debug) ast.apply(new ASTPrinter());
        System.out.println("\n *** Commencing Checks! *** \n");
        ast.apply(new tableFillVisitor(symFTable, symVTable));
        if ( ParserTest1.errors > 0 ) {
            System.out.println("\n *** " + ParserTest1.errors + " errors found in 1st Visit. Exiting . . .\n");
        } else {
            System.out.println(" *** No errors found in 1st Visit. Proceeding . . . ***\n");
            ast.apply(new Visitor(symFTable, symVTable));
            System.out.println("\n *** All visitors completed. *** \n");
            if ( ParserTest1.errors != 0 ) {
                System.out.println(" *** Found " + ParserTest1.errors + " errors on visitor2 ***\n");
            } else {
                System.out.println("\n *** No errors were found ***\n");
            }
        }
        
        if (debug) {
            printMethodTable(symFTable);
            printVariableTable(symVTable);
        }
    }
    catch (Exception e)
    {
        System.err.println(e);
    }
  }





  public static void printMethodTable( Hashtable<String, SymItem> methodTable) {
    for (Map.Entry<String, SymItem> entry : methodTable.entrySet()) {
        System.out.println("\n**************");
        System.out.println("Key: " + entry.getKey());
        System.out.println("Returns: " + ((SymFunction)entry.getValue()).isReturns());
        System.out.println("**************");
    }
  }

  public static void printVariableTable (Hashtable<String, SymItem> variableTable) {
    for (Map.Entry<String, SymItem> entry : variableTable.entrySet()) {
        System.out.println("\n**************");
        System.out.println("Key: " + entry.getKey());
        System.out.println("Def Line: " + ((SymVariable)entry.getValue()).getDefLine());
    }
  }
}
