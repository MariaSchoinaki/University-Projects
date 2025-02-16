import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ListIterator;

import SymTableClasses.SymFunction;
import SymTableClasses.SymVariable;
import SymTableClasses.SymItem;
import java.util.HashMap;
import java.util.logging.Handler;
import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;

public class tableFillVisitor extends DepthFirstAdapter {

    
    private static Hashtable<String, SymItem> methodTable;
    private static Hashtable<String, SymItem> variableTable;

    /**
     * tableFillVisitor Constructor
     * @param symtable empty Symbols Table
     */
    tableFillVisitor(Hashtable<String, SymItem> methodTable, Hashtable<String, SymItem> variableTable) {
        tableFillVisitor.methodTable = methodTable;
        tableFillVisitor.variableTable = variableTable;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void caseAFunction(AFunction node) {
        boolean passedThefirstStage = false;
        boolean passedTheSecondStage = false;
        
        inAFunction(node);
        if(getAId(node.getIdentifier()) != null)
        {
            getAId(node.getIdentifier()).apply(this);
        }

        Object temp[] = node.getArgument().toArray();
        
        
        String fName = getAId(node.getIdentifier()).toString().trim();
        LinkedList<AArgument> parameters = node.getArgument();

        int line = getAId(node.getIdentifier()).getId().getLine();
        String arguments ="";
        AArgument incomingArgs = null;

        if(parameters.size()>0){
            arguments = ((AArgument) parameters.get(0)).toString();
            incomingArgs = ((AArgument) parameters.get(0));
        }
         
        String[] argumentList  = arguments.split(" ");

        argumentList = java.util.Arrays.stream(argumentList)
        .filter(s -> !s.isEmpty())
        .toArray(String[]::new);
        
        int incaditionalCount = 0;
        int exaditionalCount = 0;

        if(incomingArgs!=null){
            LinkedList<AArgument> addArgs = incomingArgs.getArgumentAssign();
            if(addArgs.size()==1){
                incaditionalCount =1;
            }
            ListIterator it = incomingArgs.getArgumentAdditionalAssign().listIterator();
            while ( it.hasNext() ) {
                AArgumentAdditionalAssign arg = (AArgumentAdditionalAssign)it.next();
                if (arg.getArgumentAssign().size() != 0) {
                    incaditionalCount++; 
                    if (ParserTest1.debug) System.out.println("increment");
                }
            }
        } else {
                if (ParserTest1.debug) System.out.println("eimai null incoming");
        }

        String signature = (argumentList.length-2*incaditionalCount)+fName;

        boolean returns = false;
        HashMap<String, String> map = new HashMap<>();
        SymFunction exFunction = new SymFunction(line, parameters ,node.getStatement(),returns, "Void", map);
        if(node.getStatement().getClass().getName() == "minipython.node.AReturnStatement") {
                    returns = true;
                    exFunction = new SymFunction(line, parameters ,node.getStatement(),returns, determineExpressionType(((AReturnStatement) node.getStatement()).getExpression()), map);
        }

       
         
        SymItem queryItem = isOnMethodTable(fName, argumentList.length, incaditionalCount);
        if (queryItem!= null) {
            SymFunction exFunc = (SymFunction) queryItem;
            
            LinkedList<AArgument> exFuncArgList = exFunc.getArguments();
          
            String exArguments ="";
            AArgument extracted = null;
            
            if(exFuncArgList.size()>0){
                exArguments = exFuncArgList.get(0).toString();
                extracted = exFuncArgList.get(0);
                
            }

            String[] exArgumentList = exArguments.split(" ");

            exArgumentList = java.util.Arrays.stream(exArgumentList)
                                    .filter(s -> !s.isEmpty())
                                    .toArray(String[]::new);
                 
            if(extracted!=null){
                LinkedList<AArgument> addArgs = extracted.getArgumentAssign();
                if(addArgs.size()==1){
                    exaditionalCount=1;
                }
                ListIterator it2 = extracted.getArgumentAdditionalAssign().listIterator();
                while ( it2.hasNext() ) {
                    AArgumentAdditionalAssign arg2 = (AArgumentAdditionalAssign)it2.next();
                    if (arg2.getArgumentAssign().size() != 0) {
                        exaditionalCount++;
                    }
                }
            } else {
                if (ParserTest1.debug) System.out.println("eimai null extracted");
            }
            
            if((argumentList.length-incaditionalCount!=exArgumentList.length-exaditionalCount) ){
                passedThefirstStage = true;
            }
        
            //-----------------------phase 2---------------------------------------
            if(incaditionalCount == 0 && exaditionalCount == 0){

                passedTheSecondStage =true;

            }else if (incaditionalCount>0 || exaditionalCount > 0) {

                if(HasIdenticalCalling(argumentList.length-2*incaditionalCount, argumentList.length-incaditionalCount, exArgumentList.length-2*exaditionalCount, exArgumentList.length-exaditionalCount)){
                    passedTheSecondStage=false;
                }else{
                    passedTheSecondStage=true;
                }
            
            }
            if (ParserTest1.debug) {
                System.out.println("===========STATUS===========");
                System.out.println("FIRST STAGE :"+ passedThefirstStage);
                System.out.println("SECOND STAGE :"+ passedTheSecondStage);
                System.out.println("============================");
            }

            if(passedTheSecondStage && passedThefirstStage ){
                if (ParserTest1.debug) System.out.println(signature);
                methodTable.put(signature, exFunction);
                if (ParserTest1.debug) {
                    System.out.println("============================");
                    System.out.println("No errors found : Proceeding");
                    System.out.println("============================");
                }
            }else{
                System.out.println("==============FAIL=============");
                System.out.println("\n!!! Error in Line " + line + ": " + " Function " + fName + " is already defined");
                System.out.println("============================");
                ParserTest1.errors += 1;
                return;
            }
                            
            //------------------------------------------------------------------------------------

        } else {
            
            methodTable.put(signature.trim(), exFunction);
            if (ParserTest1.debug) {
                System.out.println(methodTable.toString());
                System.out.println();
            }

        }

        for(int i = 0; i < temp.length; i++)
        {
            ((PArgument) temp[i]).apply(this);
        }

        if(node.getStatement() != null)
        {
            node.getStatement().apply(this);
        }
        outAFunction(node);
    }

    public SymItem isOnMethodTable(String fname,int numberOfParams, int numberOfAssigns){
        int notDeafault = numberOfParams-2*numberOfAssigns;
        if (ParserTest1.debug) {
            System.out.println("\nName: "+fname);
            System.out.println("numberOfParams: "+numberOfParams);
            System.out.println("Real arguments: "+notDeafault);
            System.out.println("Default arguments: "+numberOfAssigns);
        }
        int max = notDeafault + numberOfAssigns;
        ArrayList<String> possibleVersions = new ArrayList<String>();
        for ( int i = max ; i >=0 ; i-- ) {
            possibleVersions.add(i+fname);
        }
        
        for(String possibleVersion : possibleVersions){

            if (ParserTest1.debug) System.out.println("possibleVersion: "+possibleVersion);
       
            if(methodTable.containsKey(possibleVersion.trim())){
                if (ParserTest1.debug) {
                    System.out.println("================================");
                    System.out.println("SELECTED VERSION: "+possibleVersion.trim());
                    System.out.println("================================");
                }
                SymItem finding = methodTable.get(possibleVersion.trim());
                return finding;
            }
        
        }
        return null;
    }

    boolean HasIdenticalCalling(int incMin, int incMax, int exMin, int exMax) {
        if (ParserTest1.debug) {
            System.out.println("\n\nIncoming Min: " + incMin);
            System.out.println("Incoming Max: " + incMax);
            System.out.println("Existing Min: " + exMin);
            System.out.println("Existing Max: " + exMax);
        }

        //return (incMax <= exMin || exMax <= incMin);
        boolean Overlaps = !(incMax < exMin || exMax < incMin);

        if (incMin <= exMin && incMax >= exMax) {
            return true; 
        }

        if (exMin <= incMin && exMax >= incMax) {
            return true; 
        }

        return Overlaps;
    }

/**************************************************************/
/**************************************************************/
/*                                                            */
/*                  SUPPORTING FUNCTIONS                      */
/*                                                            */
/**************************************************************/
/**************************************************************/

    public static String determineExpressionType(PExpression value) {
        
        if (value instanceof AValueExpression) {
            PValue innerValue = ((AValueExpression) value).getValue();
            if (innerValue instanceof ANumberValue) {
                PNumber number = (PNumber) ((ANumberValue) innerValue ).getNumber();
                if (number instanceof  AIntegerNumber){
                    return "Number";
                }else if (number instanceof ADoubleNumber) {
                    return "Double";
                }
            } else if (innerValue instanceof ASqStringValue || innerValue instanceof ADqStringValue) {
                return "String";
            } else if (innerValue instanceof ANoneValue) {
                return "None";
            }
        } else if (value instanceof AArrayExpression) { // Δεν ελέγχεται σωστά
            return "List";
        } else if (value instanceof AFCallExpression) {

            return getFunctionReturnType((AFunctionCall) ((AFCallExpression) value).getFunctionCall());
            
        } else if (value instanceof AIdentifierExpression) {
            return getVariableType(value);
        } else if (value instanceof AAdditionExpression) {
            PExpression left = ((AAdditionExpression) value).getE1();
            PExpression right = ((AAdditionExpression) value).getE2();
            String leftType = determineExpressionType(left);
            String rightType = determineExpressionType(right);

            if (ParserTest1.debug) System.out.println(leftType + " " +rightType);
            // Αν ένας από τους όρους είναι String, επιτρέπουμε τη συμβολοσειρά (π.χ. "abc" + "def")
            if ("String".equals(leftType) && "String".equals(rightType)) {
                return "String";
            }
            if ("Number".equals(leftType) && "Number".equals(rightType)) {
                return "Number";
            }
            if ("Unknown".equals(leftType) && "Unknown".equals(rightType)) {
                return "Unknown";
            }
            if ("Number".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Double".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            return "Unknown"; // Άγνωστος συνδυασμός τύπων για πρόσθεση
        
        } else if (value instanceof ASubtractionExpression) {
            PExpression left = ((ASubtractionExpression) value).getE1();
            PExpression right = ((ASubtractionExpression) value).getE2();
            String leftType = determineExpressionType(left);
            String rightType = determineExpressionType(right);
        
            if ("Number".equals(leftType) && "Number".equals(rightType)) {
                return "Number";
            }
            if ("Number".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Double".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Unknown".equals(leftType) && "Unknown".equals(rightType)) {
                return "Unknown";
            }
            return "Unknown"; // Δεν επιτρέπεται αφαίρεση με μη αριθμητικά δεδομένα
        
        } else if (value instanceof AMultExpression) {
            PExpression left = ((AMultExpression) value).getE1();
            PExpression right = ((AMultExpression) value).getE2();
            String leftType = determineExpressionType(left);
            String rightType = determineExpressionType(right);
        
            if ("Number".equals(leftType) && "Number".equals(rightType)) {
                return "Number";
            }
            if ("Number".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Double".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if (("String".equals(leftType) && "Number".equals(rightType)) || 
                ("Number".equals(leftType) && "String".equals(rightType))) {
                return "String"; // Επιτρέπεται πολλαπλασιασμός String με αριθμό (π.χ. "abc" * 3 -> "abcabcabc")
            }
            if ("Unknown".equals(leftType) && "Unknown".equals(rightType)) {
                return "Unknown";
            }
            return "Unknown";
        
        } else if (value instanceof ADivExpression) {
            PExpression left = ((ADivExpression) value).getE1();
            PExpression right = ((ADivExpression) value).getE2();
            String leftType = determineExpressionType(left);
            String rightType = determineExpressionType(right);
        
            if ("Number".equals(leftType) && "Number".equals(rightType)) {
                return "Number";
            }
            if ("Number".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Double".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Unknown".equals(leftType) && "Unknown".equals(rightType)) {
                return "Unknown";
            }
            return "Unknown"; // Δεν επιτρέπεται διαίρεση μη αριθμητικών τύπων
        
        } else if (value instanceof AModExpression) {
            PExpression left = ((AModExpression) value).getE1();
            PExpression right = ((AModExpression) value).getE2();
            String leftType = determineExpressionType(left);
            String rightType = determineExpressionType(right);
        
            if ("Number".equals(leftType) && "Number".equals(rightType)) {
                return "Number";
            }
            if ("Number".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Double".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Unknown".equals(leftType) && "Unknown".equals(rightType)) {
                return "Unknown";
            }
            return "Unknown"; // Δεν επιτρέπεται mod μεταξύ μη αριθμών
        
        } else if (value instanceof AExponentExpression) {
            PExpression left = ((AExponentExpression) value).getE1();
            PExpression right = ((AExponentExpression) value).getE2();
            String leftType = determineExpressionType(left);
            String rightType = determineExpressionType(right);
        
            if ("Number".equals(leftType) && "Number".equals(rightType)) {
                return "Number";
            }
            if ("Number".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Double".equals(leftType) && "Double".equals(rightType)) {
                return "Double";
            }
            if ("Unknown".equals(leftType) && "Unknown".equals(rightType)) {
                return "Unknown";
            }
            return "Unknown"; // Δεν επιτρέπεται ύψωση σε δύναμη αν δεν είναι και οι δύο αριθμοί
        }
            else if (value instanceof ALengthExpression) {
            return "Number"; 
        } else if (value instanceof AAsciiExpression) {
            return "Number";
        } else if (value instanceof AMaxExpression || value instanceof AMinExpression) {
            return determineExpressionType(((AParenthExpression) value).getExpression()); // Δεν έχει ελεγχεί σωστά.
        } else if (value instanceof AParenthExpression) {
            return determineExpressionType(((AParenthExpression) value).getExpression());
        }
        return "Unknown";
    }   

    private static String getFunctionReturnType(AFunctionCall funcCall) {


        String calledFunctionName = getAId(funcCall.getIdentifier()).getId().toString().trim();       
            int line = getAId(funcCall.getIdentifier()).getId().getLine();
            LinkedList arglist = funcCall.getArglist();
    
            int calledFunctionArgs= 0;
               
            if (arglist.size() != 0) {
                calledFunctionArgs+= 1;
                calledFunctionArgs+= ((AArglist)funcCall.getArglist().get(0)).getAdditionalExpression().size();
            }
            String functionNameWithArguments = calledFunctionArgs + calledFunctionName;
    
        
            for (String key : methodTable.keySet()){
            if (key.trim().equals(functionNameWithArguments)) {
                SymFunction function = (SymFunction) methodTable.get(functionNameWithArguments);

                return function.getType();
            }
        }
        return "Unknown";
    }

    public static String getVariableType(PExpression variable) {
        String varName = variable.toString().trim();
        for (String key : variableTable.keySet()) {
            if (key.trim().equals(varName.trim())) {
                SymVariable var = (SymVariable) variableTable.get(varName);
                return var.getType();
            }
        }
        return "Unknown";
    }


    /**
     * Returns the AIdentifier version of a PIdentifier object.
     * @param id PIdentifier object
     * @return AIdentifier object
     */
    public static AIdentifier getAId(PIdentifier id) {
        return (AIdentifier)id;
    }

}
