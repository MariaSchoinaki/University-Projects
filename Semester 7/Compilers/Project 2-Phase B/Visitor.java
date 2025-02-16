import SymTableClasses.SymFunction;
import SymTableClasses.SymItem;
import SymTableClasses.SymVariable;
import minipython.analysis.DepthFirstAdapter;
import minipython.node.*;
import java.util.*;

public class Visitor extends DepthFirstAdapter {

    private Hashtable<String, SymItem> methodTable;
    private Hashtable<String, SymItem> variableTable;
    private Stack<String> operatorStack = new Stack<>();
    //private Stack<HashMap<String,String>> stack = new Stack<>();
    private Stack<String> returnStackType = new Stack<>();
    private Stack<SymFunction> stackFunctions = new Stack<>();
    private int globalLine;
    int functionCallLine = 0;
   

    // Constructor for Visitor
    public Visitor(Hashtable<String, SymItem> methodTable, Hashtable<String, SymItem> variableTable) {
        this.methodTable = methodTable;
        this.variableTable = variableTable;
    }

    private boolean inFuncionCallStatement = false;
    private boolean inFunctionDef = false;
    private boolean inAssignStatement = false;
    private boolean isLeftSideIdentifier;
    private boolean inFunctionCallId = false;
    private boolean inFunctionStatement = false;
    private boolean activeLineTracker = true;

    //1. Check if a variable is defined before usage
    @Override
    public void inAAssignPlusStatement(AAssignPlusStatement node) {
        inAssignStatement = true;
        isLeftSideIdentifier = true;
    }
    @Override
    public void outAStatFunctionOrStatement(AStatFunctionOrStatement node){
        returnStackType.clear();
    }
    @Override
    public void outAAssignPlusStatement(AAssignPlusStatement node) {
        inAssignStatement = false;
    }

    @Override
    public void inAAssignMinusStatement(AAssignMinusStatement node) {
        inAssignStatement = true;
        isLeftSideIdentifier = true;
    }

    @Override
    public void outAAssignMinusStatement(AAssignMinusStatement node) {
        inAssignStatement = false;
    }

    @Override
    public void inAAssignMultStatement(AAssignMultStatement node) {
        inAssignStatement = true;
        isLeftSideIdentifier = true;
    }

    @Override
    public void outAAssignMultStatement(AAssignMultStatement node) {
        inAssignStatement = false;
    }

    @Override
    public void inAAssignDivStatement(AAssignDivStatement node) {
        inAssignStatement = true;
        isLeftSideIdentifier = true;
    }

    @Override
    public void outAAssignDivStatement(AAssignDivStatement node) {
        inAssignStatement = false;
    }

    @Override
    public void inAAssignEqStatement(AAssignEqStatement node) {
        inAssignStatement = true;
        isLeftSideIdentifier = true;
    }

    @Override
    public void outAAssignEqStatement(AAssignEqStatement node) {
        inAssignStatement = false;
    }

    @Override
    public void inAFunction(AFunction node) {
        inFunctionDef = true;
    }

    @Override
    public void outAFunction(AFunction node) {
        inFunctionDef = false;
    }

    @Override
    public void inAFunctionCall(AFunctionCall node) {
        inFunctionCallId = true;
        inFuncionCallStatement = true;
    }

    @Override
    public void outAFunctionCall(AFunctionCall node) {
        if (!stackFunctions.empty()) stackFunctions.pop();
        //stack.pop();
        if (stackFunctions.empty()){
        //if (stack.empty()){
            inFuncionCallStatement = false;
            activeLineTracker = true;
        }
    }
    
    @Override
    public void inAFCallStatement(AFCallStatement node) {
        inFunctionStatement =  true;
    }

    @Override
    public void outAFCallStatement(AFCallStatement node) {
        inFunctionStatement =  false;
    }

    @Override
    public void caseAFunction(AFunction node){
        inAFunction(node);
        outAFunction(node);
    }
    
    @Override
    public void inAPrintStatement(APrintStatement node) {
        int line = node.getPrint().getLine();
        if (activeLineTracker && line >= globalLine) globalLine = line;
        if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
    }

    @Override
    public void caseAIdentifier(AIdentifier node) {
        inAIdentifier(node);
        if (node.getId() != null) {
           node.getId().apply(this);
        }
  
        /**
         * isLefSideIdentifier: Checks if is an assignement -> z = whatever
         * inFuctionDef: Checks if we are inside a Function Definition
         * inFunctionCallId: Cheks if we are inside function call, but don't want to check funtion identifier.  --> x()
         */
        int line = node.getId().getLine();
        if (activeLineTracker && line >= globalLine) globalLine = line;
        if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
        // System.out.println("        WHO AM I ON LINE: "+line+"          ");
        // System.out.println("isLeftSideIdentifier: "+isLeftSideIdentifier);
        // System.out.println("inFunctionDef: "+inFunctionDef);
        // System.out.println("inFunctionCallId: "+inFunctionCallId);
        // System.out.println("---------------------------------------------");
    
        if ( !( inFunctionDef || inFunctionCallId )) {
        
            String varName = node.getId().toString().trim();
            if (ParserTest1.debug) System.out.println("Checking id: " + varName + ".");
            if (!this.variableTable.containsKey(varName.trim())) {
                
                System.out.println("\n!!! Error in line:" + line + ": Variable '" + varName + "' used before declaration.");
                ParserTest1.errors += 1;
                return;
            }
            
            if (ParserTest1.debug) {
                System.out.println("===========================");
                System.out.println("  TEST 1 PASSED FOR VAR : "+ varName);
                System.out.println("===========================");
            }
            inFunctionCallId = false;
            isLeftSideIdentifier = false;
        }
        outAIdentifier(node);
    }


    // Fill up the variables table
    @Override
    public void caseAAssignEqStatement(AAssignEqStatement node) {
        inAAssignEqStatement(node);
        String variableName = "";
        if (node.getIdentifier() != null) {

            variableName = getAId(node.getIdentifier()).getId().toString().trim();
            int line = getAId(node.getIdentifier()).getId().getLine();
            if (activeLineTracker && line >= globalLine) globalLine = line;
            if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
            
            // Δημιουργία μεταβλητής και αποθήκευση στον πίνακα συμβόλων
            if(!inFunctionDef){
                SymVariable variable = new SymVariable(variableName, line, tableFillVisitor.determineExpressionType(node.getExpression()));
                if (node.getExpression() != null && !(node.getExpression().toString()).contains(variableName)) {
                    if (ParserTest1.debug) System.out.println("VARIABLE TO STORE: "+variableName);
                    variableTable.put(variableName, variable);
                }
            }
        }
        
        // Έλεγχος της έκφρασης μετά το "="
        if (node.getExpression() != null) {
            if (ParserTest1.debug) System.out.println("Expression: "+(node.getExpression().toString()).contains(variableName));
            node.getExpression().apply(this);
        } else {
            ParserTest1.errors += 1;
            System.err.println("\n!!! Error in line:" + getAId(node.getIdentifier()).getId().getLine() + " after '=' ");
        }
    
        outAAssignEqStatement(node);
    }
    
    @Override
    public void caseAAssignPlusStatement(AAssignPlusStatement node) {
        inAAssignPlusStatement(node);
        String variableName = "";
        if (node.getIdentifier() != null) {

            variableName = getAId(node.getIdentifier()).getId().toString().trim();
            int line = getAId(node.getIdentifier()).getId().getLine();
            if (activeLineTracker && line >= globalLine) globalLine = line;
            if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
            
            // Δημιουργία μεταβλητής και αποθήκευση στον πίνακα συμβόλων
            if(!inFunctionDef){
                if ( variableTable.containsKey(variableName) ){
                    String leftType = variableTable.get(variableName).getType();
                    String rightType = tableFillVisitor.determineExpressionType(node.getExpression());
                    if ( leftType == rightType ) {
                        SymVariable variable = new SymVariable(variableName, line, rightType);
                        if (ParserTest1.debug) System.out.println("Updating variable: "+variableName);
                        variableTable.put(variableName, variable);
                    } else {
                        ParserTest1.errors += 1;
                        System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do += because left Type is " + leftType + " and right Type is " + rightType);
                    }
                   
                } else {
                    ParserTest1.errors += 1;
                    System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do += because variable " + variableName + " is not yet defined.");
                }
            }
        }
        
        // Έλεγχος της έκφρασης μετά το "+="
        if (node.getExpression() != null) {
            if (ParserTest1.debug) System.out.println("Expression: "+(node.getExpression().toString()).contains(variableName));
            node.getExpression().apply(this);
        } else {
            ParserTest1.errors += 1;
            System.err.println("\n!!! Error in line:" + getAId(node.getIdentifier()).getId().getLine() + " after '+=' ");
        }
    
        outAAssignPlusStatement(node);
    }

    @Override
    public void caseAAssignMinusStatement(AAssignMinusStatement node) {
        inAAssignMinusStatement(node);
        String variableName = "";
        if (node.getIdentifier() != null) {

            variableName = getAId(node.getIdentifier()).getId().toString().trim();
            int line = getAId(node.getIdentifier()).getId().getLine();
            if (activeLineTracker && line >= globalLine) globalLine = line;
            if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
            // Δημιουργία μεταβλητής και αποθήκευση στον πίνακα συμβόλων
            if(!inFunctionDef){
                if ( variableTable.containsKey(variableName) ){
                    String leftType = variableTable.get(variableName).getType();
                    String rightType = tableFillVisitor.determineExpressionType(node.getExpression());
                    if ( leftType == rightType ) {
                        SymVariable variable = new SymVariable(variableName, line, rightType);
                        if (ParserTest1.debug) System.out.println("Updating variable: "+variableName);
                        variableTable.put(variableName, variable);
                    } else {
                        ParserTest1.errors += 1;
                        System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do -= because left Type is " + leftType + " and right Type is " + rightType);
                    }
                   
                } else {
                    ParserTest1.errors += 1;
                    System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do -= because variable " + variableName + " is not yet defined.");
                }
            }
        }
        
        // Έλεγχος της έκφρασης μετά το "+="
        if (node.getExpression() != null) {
            if (ParserTest1.debug) System.out.println("Expression: "+(node.getExpression().toString()).contains(variableName));
            node.getExpression().apply(this);
        } else {
            ParserTest1.errors += 1;
            System.err.println("\n!!! Error in line:" + getAId(node.getIdentifier()).getId().getLine() + " after '-=' ");
        }
    
        outAAssignMinusStatement(node);
    }
    
    @Override
    public void caseAAssignDivStatement(AAssignDivStatement node) {
        inAAssignDivStatement(node);
        String variableName = "";
        if (node.getIdentifier() != null) {

            variableName = getAId(node.getIdentifier()).getId().toString().trim();
            int line = getAId(node.getIdentifier()).getId().getLine();
            if (activeLineTracker && line >= globalLine) globalLine = line;
            if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
            // Δημιουργία μεταβλητής και αποθήκευση στον πίνακα συμβόλων
            if(!inFunctionDef){
                if ( variableTable.containsKey(variableName) ){
                    String leftType = variableTable.get(variableName).getType();
                    String rightType = tableFillVisitor.determineExpressionType(node.getExpression());
                    if ( leftType == rightType ) {
                        SymVariable variable = new SymVariable(variableName, line, rightType);
                        if (ParserTest1.debug) System.out.println("Updating variable: "+variableName);
                        variableTable.put(variableName, variable);
                    } else {
                        ParserTest1.errors += 1;
                        System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do /= because left Type is " + leftType + " and right Type is " + rightType);
                    }
                   
                } else {
                    ParserTest1.errors += 1;
                    System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do /= because variable " + variableName + " is not yet defined.");
                }
            }
        }
        
        // Έλεγχος της έκφρασης μετά το "+="
        if (node.getExpression() != null) {
            if (ParserTest1.debug) System.out.println("Expression: "+(node.getExpression().toString()).contains(variableName));
            node.getExpression().apply(this);
        } else {
            ParserTest1.errors += 1;
            System.err.println("\n!!! Error in line:" + getAId(node.getIdentifier()).getId().getLine() + " after '/=' ");
        }
    
        outAAssignDivStatement(node);
    }
 
    @Override
    public void caseAAssignMultStatement(AAssignMultStatement node) {
        inAAssignMultStatement(node);
        String variableName = "";
        if (node.getIdentifier() != null) {

            variableName = getAId(node.getIdentifier()).getId().toString().trim();
            int line = getAId(node.getIdentifier()).getId().getLine();
            if (activeLineTracker && line >= globalLine) globalLine = line;
            if (ParserTest1.debug) System.out.println("\nGlobal Line: " + globalLine +
                                                    "\nLocal Line: " + line +
                                                    "\ninFunctionDef: " + inFunctionDef);
            // Δημιουργία μεταβλητής και αποθήκευση στον πίνακα συμβόλων
            if(!inFunctionDef){
                if ( variableTable.containsKey(variableName) ){
                    String leftType = variableTable.get(variableName).getType();
                    String rightType = tableFillVisitor.determineExpressionType(node.getExpression());
                    if ( leftType == rightType || 
                        (leftType == "String" && rightType == "Number") || 
                        (leftType == "Number" && rightType == "String")) 
                        {
                        SymVariable variable = new SymVariable(variableName, line, rightType);
                        if (ParserTest1.debug) System.out.println("Updating variable: "+variableName);
                        variableTable.put(variableName, variable);
                    } else {
                        ParserTest1.errors += 1;
                        System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do /= because left Type is " + leftType + " and right Type is " + rightType);
                    }
                   
                } else {
                    ParserTest1.errors += 1;
                    System.err.println("\n!!! Error in line " + getAId(node.getIdentifier()).getId().getLine() 
                                    + ": Can't do *= because variable " + variableName + " is not yet defined.");
                }
            }
        }
        
        // Έλεγχος της έκφρασης μετά το "*="
        if (node.getExpression() != null) {
            if (ParserTest1.debug) System.out.println("Expression: "+(node.getExpression().toString()).contains(variableName));
            node.getExpression().apply(this);
        } else {
            ParserTest1.errors += 1;
            System.err.println("\n!!! Error in line:" + getAId(node.getIdentifier()).getId().getLine() + " after '*=' ");
        }
    
        outAAssignMultStatement(node);
    }

   

    @SuppressWarnings("rawtypes")
    public void caseAFunctionCall(AFunctionCall node)
    {
        inAFunctionCall(node);
        String matchedKey = null;
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        
            activeLineTracker = false;
            String calledFunctionName = getAId(node.getIdentifier()).getId().toString().trim();       
            int line = getAId(node.getIdentifier()).getId().getLine();
            if( line >= globalLine) globalLine = line;
            LinkedList arglist = node.getArglist();

            int calledFunctionArgs= 0;
            
            if (arglist.size() != 0) {
                calledFunctionArgs+= 1;
                calledFunctionArgs+= ((AArglist)node.getArglist().get(0)).getAdditionalExpression().size();
            }

            //Check if function arguments are defined before proceeding
            for (Object arg : arglist) {
                if (arg instanceof AArglist) {
                    AArglist aArglist = (AArglist) arg;

                    // Check the main expression
                    PExpression expr = aArglist.getExpression();
                    checkIfExpressionIsDefined(expr, line);

                    // Check additional expressions
                    for (Object additionalExprObj : aArglist.getAdditionalExpression()) {
                        AAdditionalExpression additionalExpr = (AAdditionalExpression) additionalExprObj;
                        checkIfExpressionIsDefined(additionalExpr.getExpression(), line);
                    }
                }
            }


            String functionNameWithArguments = calledFunctionArgs + calledFunctionName;
            // System.out.println("CHECKING FUNTION WITH: " + calledFunctionArgs + " ARGUMENTS.");
           
            boolean foundMatch = true;
            if (methodTable.containsKey(functionNameWithArguments)) {
            // Check for exact match 2add(2,3) == 2add(x,y) 

                // System.out.println("ALL IS GOOD. RELEVANT FUNCTION DEFINITION FOUND.");
                matchedKey = functionNameWithArguments;

            } else {
            // If not found then we have to search for a function def that has default values.
            // These are stored in this format <minArguments><FunctionName>.
            // So add(x,y,z=3) would be stored as 2add whilst add(x,y,z,p) would be 4add

                ArrayList<String> possibleMatches = new ArrayList<String>(); // We will add possible matches here.
                Set<String> setOfKeys = methodTable.keySet();

                for (String key : setOfKeys) {
                    
                    String possibleFunction = key.substring(1);  // Holds the name without the number
                    int minArguments = Character.getNumericValue(key.charAt(0)); // Holds the number

                    // System.out.println("\nChecking " + key + " and name is :" + possibleFunction + " and min is " + minArguments);
                    // If the name mathces and the number of arguments is more than the minimum args of the defined function
                    if ( possibleFunction.equals(calledFunctionName) && ( minArguments < calledFunctionArgs) ) {   
                        possibleMatches.add(key);   // add it to possible matches
                    }
                }

                // If the list has available matches.
                if ( possibleMatches.size() > 0 ) {

                    for ( String possibleKey : possibleMatches) {

                        int methodArguments = 0;
                        int defaultArguments = 0;
        
                        SymFunction possibleMatch = (SymFunction)methodTable.get(possibleKey); 
                        LinkedList<AArgument> args = possibleMatch.getArguments();
        
                        if (args.size() != 0) {
                            AArgument arguments = args.get(0);
                            methodArguments += 1;
        
                            if (arguments.getArgumentAssign().size() != 0) {
                                defaultArguments += 1;
                            }
        
                            methodArguments += arguments.getArgumentAdditionalAssign().size();
                            ListIterator it = arguments.getArgumentAdditionalAssign().listIterator();
                            while ( it.hasNext() ) {
                                AArgumentAdditionalAssign arg = (AArgumentAdditionalAssign)it.next();
                                if (arg.getArgumentAssign().size() != 0) {
                                    defaultArguments += 1;
                                }
                            }
                        } 
                        if (ParserTest1.debug) {
                            System.out.println("\nExisting function arguments: " + methodArguments);
                            System.out.println("Existing function default arguments: " + defaultArguments);
                        }
                        
                        if ( (calledFunctionArgs > methodArguments) || (calledFunctionArgs < methodArguments - defaultArguments) ) {
                            ParserTest1.errors +=1;
                            System.out.println("\n!!!! Error in line " + line + ": " +" Function Call " 
                            + calledFunctionName + " doesn't match any defined function\n" 
                            + "Check function definition " + calledFunctionName + " in line " + possibleMatch.getDefLine());
                            foundMatch = false;
                            continue;
                        } 
        
                        matchedKey = possibleKey;

                        // Check for return statement.
                        if ( ((SymFunction)methodTable.get(matchedKey)).isReturns() && inFunctionStatement ) {
                            ParserTest1.errors +=1;
                            System.out.println("\n!!!! Error in line " + line + ": " +" Function " 
                            + calledFunctionName + " returns a value but is not used anywhere!\n");
                        }


                    }

                } else {
                // No possible matches were found!
                    
                    foundMatch = false;
                    ParserTest1.errors +=1;
                    System.out.println("\n!!!! Error in line " + line + ": " +" Function " 
                    + calledFunctionName + " with " + calledFunctionArgs + " arguments isn't defined!\n");
                }

            }

            if (matchedKey != null){
                if (ParserTest1.debug) System.out.println("\nmatchedKey" + matchedKey);
                // Ανάκτηση της συνάρτησης από τον πίνακα με τις μεθόδους
                SymFunction function = (SymFunction) methodTable.get(matchedKey);

                // Δημιουργία HashMap για την αντιστοίχιση ορισμάτων της συνάρτησης και τιμών κλήσης
                HashMap<String, String> defArgsCallArgs = new HashMap<>();
                
                // Ανάκτηση της λίστας των ορισμάτων της δήλωσης της συνάρτησης
                LinkedList<AArgument> defArgs = function.getArguments();
                LinkedList<String> defArgsList = new LinkedList<>();
                HashMap<String, String> defaultValues = new HashMap<>();
                if(!defArgs.isEmpty()){
                    String argName = defArgs.getFirst().getIdentifier().toString().trim();
                    defArgsList.add(argName);
                    
                    // Αν υπάρχει προκαθορισμένη τιμή στο πρώτο όρισμα
                    if (!defArgs.getFirst().getArgumentAssign().isEmpty()) {
                        AArgumentAssign assign = (AArgumentAssign) defArgs.getFirst().getArgumentAssign().get(0);
                        PValue assingValue = (PValue) assign.getValue(); 
                        // Προσδιορισμός του τύπου της προκαθορισμένης τιμής
                        String defaultValue = "Unknown";
                        if (assingValue instanceof ANumberValue) {
                            defaultValue = "Number";
                        } else if (assingValue instanceof ASqStringValue || assingValue instanceof ADqStringValue) {
                            defaultValue = "String";
                        } else if (assingValue instanceof ANoneValue) {
                            defaultValue = "None";
                        }
                        defaultValues.put(argName, defaultValue);
                    }
                    // Έλεγχος για προκαθορισμένες τιμές στα ορίσματα της συνάρτησης       
                    // Αν υπάρχουν επιπλέον ορίσματα με προκαθορισμένες τιμές
                    if (!defArgs.getFirst().getArgumentAdditionalAssign().isEmpty()){
                        LinkedList<AArgumentAdditionalAssign> addArgAssings = defArgs.getFirst().getArgumentAdditionalAssign();
                        for (Object addAssing : addArgAssings) {
                            AArgumentAdditionalAssign addAssingArgs = (AArgumentAdditionalAssign) addAssing;
                            argName = addAssingArgs.getIdentifier().toString().trim();
                            defArgsList.add(argName);

                            if (!addAssingArgs.getArgumentAssign().isEmpty()){
                                AArgumentAssign defaulAddAssing = (AArgumentAssign) addAssingArgs.getArgumentAssign().get(0);
                                
                                PValue assingAddValue = (PValue) defaulAddAssing.getValue(); 
                                String defaultAddValue = "Unknown";
                                if (assingAddValue instanceof ANumberValue) {
                                    defaultAddValue = "Number";
                                } else if (assingAddValue instanceof ASqStringValue || assingAddValue instanceof ADqStringValue) {
                                    defaultAddValue = "String";
                                } else if (assingAddValue instanceof ANoneValue) {
                                    defaultAddValue = "None";
                                }
                                defaultValues.put(argName, defaultAddValue);
                            }
                        }
                    }
                    // Αντιγραφή των προκαθορισμένων τιμών στον χάρτη αντιστοίχισης
                    defArgsCallArgs.putAll(defaultValues);
                    
                    // Αν η λίστα των ορισμάτων κλήσης δεν είναι κενή
                    if (!arglist.isEmpty()) {
                        AArglist callArgs = (AArglist) (Object) arglist.get(0);
                        PExpression callarg1Ex = (PExpression) callArgs.getExpression();
                        
                        LinkedList<PExpression> callArgsList = new LinkedList<>();
                        callArgsList.add(callarg1Ex);
                        
                        // Ανάκτηση των υπόλοιπων ορισμάτων κλήσης
                        for (Object arg : callArgs.getAdditionalExpression()) {
                            AAdditionalExpression addEx = (AAdditionalExpression) arg;
                            PExpression ex = (PExpression) addEx.getExpression();
                            callArgsList.add(ex);
                        }
                        Iterator<String> defArgsIterator = defArgsList.iterator();
                        Iterator<PExpression> callArgsIterator = callArgsList.iterator();

                        while (defArgsIterator.hasNext() && callArgsIterator.hasNext()) {
                            String defArgName = defArgsIterator.next();
                            PExpression callArg = callArgsIterator.next();
                            
                            callArg.apply(this);
                            
                            // Προσδιορισμός του τύπου του ορίσματος κλήσης
                            String valueType = tableFillVisitor.determineExpressionType(callArg);
                            defArgsCallArgs.put(defArgName, valueType);
                            function.setMap(defArgsCallArgs);
                        }
                    }
                    //stack.add(defArgsCallArgs);
                    stackFunctions.add(function);
                }
                // Ανάκτηση του τύπου της έκφρασης επιστροφής της συνάρτησης
                function.getStatement().apply(this);
            }
            
        outAFunctionCall(node);
    }    

    //function to check if an argument is defined in the variables table
    private void checkIfExpressionIsDefined(PExpression exp, int line){
        if(exp instanceof AIdentifierExpression){
            AIdentifier id = (AIdentifier) ((AIdentifierExpression) exp).getIdentifier();
            String argName = id.getId().toString().trim();
            
            if(!variableTable.containsKey(argName)){
                System.err.println("!!! Error in line " + line + ": Variable: '" + argName + "' used before declaration.");
                ParserTest1.errors+=1;
            }
        }

    }
   
    @Override
    public void caseAAdditionExpression(AAdditionExpression node) {
        boolean hasFCallExpressions = false;
        if (!inFunctionDef) {
            inAAdditionExpression(node);
            operatorStack.push("Addition");

            if((node.getE1() != null ) && (node.getE2() != null)) 
            {
                // Επίσκεψη στην αριστερή και δεξιά πλευρά του τελεστή '+'
                if( ParserTest1.debug) System.out.println("\n\n***************************\n");
                PExpression leftExpr = node.getE1();  // Αριστερή πλευρά
                PExpression rightExpr = node.getE2(); // Δεξιά πλευρά
                leftExpr.apply(this);
                rightExpr.apply(this);

                if (!returnStackType.empty()) hasFCallExpressions = true;
                if( ParserTest1.debug) System.out.println("Return Types: " + returnStackType);
                if( ParserTest1.debug) System.out.println("inFunctionCallStatement: " + inFuncionCallStatement);
                String leftType = null;
                String rightType = null;

                if (!stackFunctions.isEmpty()){
                    SymFunction function = stackFunctions.peek();
                    HashMap<String, String> defArgsCallArgs = function.getMap();

                    leftType = determineExpressionType(leftExpr, defArgsCallArgs);
                    rightType = determineExpressionType(rightExpr, defArgsCallArgs);
                    if( ParserTest1.debug) System.out.println("IN HERE");
                }else{
                // Λήψη των τύπων των εκφράσεων
                    leftType = tableFillVisitor.determineExpressionType(leftExpr);
                    rightType = tableFillVisitor.determineExpressionType(rightExpr);
                }

                if( ParserTest1.debug) System.out.println("inFunctionCallStatement: " + inFuncionCallStatement);

                if( ParserTest1.debug) {
                    System.out.println(" set type.");
                    System.out.println("rightType: "+ rightType);
                    System.out.println("leftType: "+ leftType);
                }
                if (!returnStackType.isEmpty()){
                    if ((rightType.equals("Unknown") || (rightType.equals("Void")))){
                        rightType =  returnStackType.pop();

                        if( ParserTest1.debug) System.out.println("rightType: "+ rightType);

                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(rightExpr.toString().trim(), rightType);
                        }
                    }
                    if ((leftType.equals("Unknown") || (leftType.equals("Void")))){
                        leftType =  returnStackType.pop();

                        if( ParserTest1.debug) System.out.println("leftType: "+ leftType);

                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(leftExpr.toString().trim(), leftType);
                        }
                    }
                }
                // Έλεγχος αν οι τύποι είναι οι ίδιοι
                if (!( ((leftType.equals("Number") && rightType.equals("Double"))) || 
                ((leftType.equals("Double") && rightType.equals("Number"))) ) &&
                (!leftType.equals(rightType))) // Οι τύποι είναι διαφορετικοί. αλλα όχι αριμθοί.
                {
                    System.err.println("\n!!! Error in line "+ globalLine + ": Mismatched types in addition expression. Left side is "
                            + leftType + " and right side is " + rightType);
                    ParserTest1.errors += 1;
                } else {
                    returnStackType.push(leftType);
                    if (!stackFunctions.isEmpty()){
                        SymFunction function = stackFunctions.peek();
                        function.setType(leftType);
                    }
                }
                
                if (leftType.equals("None") || rightType.equals("None")) {
                    System.err.println("\n!!! Error in line " + globalLine + ": Cannot perform addition with None.");
                    ParserTest1.errors += 1;
                }
            }


            operatorStack.pop();
            outAAdditionExpression(node);
            } else { // If inside function defintion
                inAAdditionExpression(node);
                if(node.getE1() != null)
                {
                    node.getE1().apply(this);
                }
                if(node.getE2() != null)
                {
                    node.getE2().apply(this);
                }
                outAAdditionExpression(node);
        }
    }

    @Override
    public void caseASubtractionExpression(ASubtractionExpression node) {
        if (!inFunctionDef) {
            inASubtractionExpression(node);
            operatorStack.push("Subtraction");
            
            if((node.getE1() != null ) && (node.getE2() != null)) 
            {
    
                // Επίσκεψη στην αριστερή και δεξιά πλευρά του τελεστή '-'
                PExpression leftExpr = node.getE1();  // Αριστερή πλευρά
                PExpression rightExpr = node.getE2(); // Δεξιά πλευρά
                String leftType = null;
                String rightType = null;
                leftExpr.apply(this);
                rightExpr.apply(this);

                if (!stackFunctions.isEmpty()){
                    SymFunction function = stackFunctions.peek();
                    HashMap<String, String> defArgsCallArgs = function.getMap();
                    leftType = determineExpressionType(leftExpr, defArgsCallArgs);
                    rightType = determineExpressionType(rightExpr, defArgsCallArgs);
                }else{
                // Λήψη των τύπων των εκφράσεων
                
                    leftType = tableFillVisitor.determineExpressionType(leftExpr);
                    rightType = tableFillVisitor.determineExpressionType(rightExpr);
                }
            
                if (!returnStackType.isEmpty()){
                    if ((rightType.equals("Unknown") || (rightType.equals("Void")))){
                        rightType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(rightExpr.toString().trim(), rightType);
                        }
                    }
                    if ((leftType.equals("Unknown") || (leftType.equals("Void")))){
                        leftType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(leftExpr.toString().trim(), leftType);
                        }
                    }
                }
                   // Η πράξη είναι λάθος όταν:
                if ((!(((leftType.equals("Number") && rightType.equals("Double"))) ||  // Αν ένας είναι Number και ο άλλος Double, είναι αποδεκτό
                ((leftType.equals("Double") && rightType.equals("Number")))) && // Αν ένας είναι Double και ο άλλος Number, είναι αποδεκτό
                (!leftType.equals(rightType)))  ||  // Αν οι τύποι είναι διαφορετικοί και δεν είναι αριθμοί, απόρριψη.
                (leftType.equals(rightType) && (!leftType.equals("Number")) && (!leftType.equals("Double")))) // Οι τύποι είναι διαφορετικοί.
                {
                    ParserTest1.errors += 1;
                    System.err.println("!!! Error in line "+ globalLine + " Mismatched types in subtraction expression. Left side is "
                            + leftType + " and right side is " + rightType);
                } else {
                    returnStackType.push(leftType);
                    if (!stackFunctions.isEmpty()){
                        SymFunction function = stackFunctions.peek();
                        function.setType(leftType);
                    }
                }
                
                if (leftType.equals("None") || rightType.equals("None")) {
                    System.err.println("\n!!! Error in line " + globalLine + ": Cannot perform subtraction with None.");
                    ParserTest1.errors += 1;
                }
            }
    
            operatorStack.pop();
            outASubtractionExpression(node);

        } else { // If inside function defintion
            inASubtractionExpression(node);
            if(node.getE1() != null)
            {
                node.getE1().apply(this);
            }
            if(node.getE2() != null)
            {
                node.getE2().apply(this);
            }
            outASubtractionExpression(node);
        }
            
    }

    @Override
    public void caseAMultExpression(AMultExpression node) {
        if(!inFunctionDef) {
            inAMultExpression(node);
            operatorStack.push("Multiplication");

            if((node.getE1() != null ) && (node.getE2() != null)) 
            {
                // Επίσκεψη στην αριστερή και δεξιά πλευρά του τελεστή '*'

                PExpression leftExpr = node.getE1();  // Αριστερή πλευρά
                PExpression rightExpr = node.getE2(); // Δεξιά πλευρά
                String leftType = null;
                String rightType = null;
                leftExpr.apply(this);
                rightExpr.apply(this);

                if (!stackFunctions.isEmpty()){
                    SymFunction function = stackFunctions.peek();
                    //HashMap<String, String> defArgsCallArgs = stack.peek();
                    HashMap<String, String> defArgsCallArgs = function.getMap();
                    leftType = determineExpressionType(leftExpr, defArgsCallArgs);
                    rightType = determineExpressionType(rightExpr, defArgsCallArgs);
                }else{
                // Λήψη των τύπων των εκφράσεων
                
                    leftType = tableFillVisitor.determineExpressionType(leftExpr);
                    rightType = tableFillVisitor.determineExpressionType(rightExpr);
                }
    

                if (!returnStackType.isEmpty()){
                    if ((rightType.equals("Unknown") || (rightType.equals("Void")))){
                        rightType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(rightExpr.toString().trim(), rightType);
                        }
                    }
                    if ((leftType.equals("Unknown") || (leftType.equals("Void")))){
                        leftType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(leftExpr.toString().trim(), leftType);
                        }
                    }

                    
                }

                // Έλεγχος αν οι τύποι είναι οι ίδιοι
                if  ((!((leftType.equals("Number") && rightType.equals("Double")) ||  // Αν ένας είναι Number και ο άλλος Double, είναι αποδεκτό
                (leftType.equals("Double") && rightType.equals("Number")) ||
                (leftType.equals("Number") && rightType.equals("String")) ||
                (leftType.equals("String") && rightType.equals("Number"))) && // Αν ένας είναι Double και ο άλλος Number, είναι αποδεκτό
                (!leftType.equals(rightType))) 
                
                ||  // Αν οι τύποι είναι διαφορετικοί και δεν είναι αριθμοί, απόρριψη.
                (leftType.equals(rightType) && (!leftType.equals("Number")) && (!leftType.equals("Double")))) // Οι τύποι είναι διαφορετικοί.
                {
                    ParserTest1.errors += 1;
                    System.err.println("!!! Error in line "+ globalLine + " Mismatched types in multiplication expression. Left side is "
                    + leftType + " and right side is " + rightType);
                } else {
                    returnStackType.push(leftType);
                    if (!stackFunctions.isEmpty()){
                        SymFunction function = stackFunctions.peek();
                        function.setType(leftType);
                    }
                }

                if (leftType.equals("None") || rightType.equals("None")) {
                    System.err.println("\n!!! Error in line " + globalLine + ": Cannot perform multiplication with None.");
                    ParserTest1.errors += 1;
                }

            }

            operatorStack.pop();
            outAMultExpression(node);
        } else { // If inside function defintion
            inAMultExpression(node);
            if(node.getE1() != null)
            {
                node.getE1().apply(this);
            }
            if(node.getE2() != null)
            {
                node.getE2().apply(this);
            }
            outAMultExpression(node);
        }
        
    }

    @Override
    public void caseADivExpression(ADivExpression node) {

        if (!inFunctionDef) {
            inADivExpression(node);
            operatorStack.push("Division");

            if((node.getE1() != null ) && (node.getE2() != null)) 
            {
                // Επίσκεψη στην αριστερή και δεξιά πλευρά του τελεστή '/'
                PExpression leftExpr = node.getE1();  // Αριστερή πλευρά
                PExpression rightExpr = node.getE2(); // Δεξιά πλευρά
                String leftType = null;
                String rightType = null;
                leftExpr.apply(this);
                rightExpr.apply(this);

                if (!stackFunctions.isEmpty()){
                    SymFunction function = stackFunctions.peek();
                    HashMap<String, String> defArgsCallArgs = function.getMap();
                    leftType = determineExpressionType(leftExpr, defArgsCallArgs);
                    rightType = determineExpressionType(rightExpr, defArgsCallArgs);
                }else{
                // Λήψη των τύπων των εκφράσεων
                    leftType = tableFillVisitor.determineExpressionType(leftExpr);
                    rightType = tableFillVisitor.determineExpressionType(rightExpr);
                }
    
                if (!returnStackType.isEmpty()){
                    if ((rightType.equals("Unknown") || (rightType.equals("Void")))){
                        rightType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(rightExpr.toString().trim(), rightType);
                        }
                    }
                    if ((leftType.equals("Unknown") || (leftType.equals("Void")))){
                        leftType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(leftExpr.toString().trim(), leftType);
                        }
                    }
                }

                // Έλεγχος αν οι τύποι είναι αριθμοί.
                if((!(((leftType.equals("Number") && rightType.equals("Double"))) ||  // Αν ένας είναι Number και ο άλλος Double, είναι αποδεκτό
                ((leftType.equals("Double") && rightType.equals("Number")))) && // Αν ένας είναι Double και ο άλλος Number, είναι αποδεκτό
                (!leftType.equals(rightType)))  ||  // Αν οι τύποι είναι διαφορετικοί και δεν είναι αριθμοί, απόρριψη.
                (leftType.equals(rightType) && (!leftType.equals("Number")) && (!leftType.equals("Double")))) 
                {
                    ParserTest1.errors += 1;
                    System.err.println("!!! Error in line "+ globalLine + " Mismatched types in division expression. Left side is "
                            + leftType + " and right side is " + rightType);
                } else {
                    returnStackType.push(leftType);
                    if (!stackFunctions.isEmpty()){
                        SymFunction function = stackFunctions.peek();
                        function.setType(leftType);
                    }
                }
                if (leftType.equals("None") || rightType.equals("None")) {
                    System.err.println("\n!!! Error in line " + globalLine + ": Cannot perform division with None.");
                    ParserTest1.errors += 1;
                }
            }

            operatorStack.pop();
            outADivExpression(node);
        } else { // If inside function defintion
            inADivExpression(node);
            if(node.getE1() != null)
            {
                node.getE1().apply(this);
            }
            if(node.getE2() != null)
            {
                node.getE2().apply(this);
            }
            outADivExpression(node);
        }
        
    }

    @Override
    public void caseAExponentExpression(AExponentExpression node) {

        if (!inFunctionDef) {
            inAExponentExpression(node);
            operatorStack.push("Exponent");

            if((node.getE1() != null ) && (node.getE2() != null))
            {
                // Επίσκεψη στην αριστερή και δεξιά πλευρά του τελεστή '^'
                PExpression leftExpr = node.getE1();  // Αριστερή πλευρά
                PExpression rightExpr = node.getE2(); // Δεξιά πλευρά
                String leftType = null;
                String rightType = null;
                leftExpr.apply(this);
                rightExpr.apply(this);

                if (!stackFunctions.isEmpty()){
                    SymFunction function = stackFunctions.peek();
                    HashMap<String, String> defArgsCallArgs = function.getMap();
                    leftType = determineExpressionType(leftExpr, defArgsCallArgs);
                    rightType = determineExpressionType(rightExpr, defArgsCallArgs);
                }else{
                // Λήψη των τύπων των εκφράσεων
                
                    leftType = tableFillVisitor.determineExpressionType(leftExpr);
                    rightType = tableFillVisitor.determineExpressionType(rightExpr);
                }
                if (!returnStackType.isEmpty()){
                    if ((rightType.equals("Unknown") || (rightType.equals("Void")))){
                        rightType =  returnStackType.pop();
                        System.out.println("/rightType" + rightType);

                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(rightExpr.toString().trim(), rightType);
                        }
                    }
                    if ((leftType.equals("Unknown") || (leftType.equals("Void")))){
                        leftType =  returnStackType.pop();
                        System.out.println("/leftType" + leftType);
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(leftExpr.toString().trim(), leftType);
                        }
                    }
                }
                // Έλεγχος αν οι τύποι είναι αριθμοί.
                if ((!(((leftType.equals("Number") && rightType.equals("Double"))) ||  // Αν ένας είναι Number και ο άλλος Double, είναι αποδεκτό
                ((leftType.equals("Double") && rightType.equals("Number")))) && // Αν ένας είναι Double και ο άλλος Number, είναι αποδεκτό
                (!leftType.equals(rightType)))  ||  // Αν οι τύποι είναι διαφορετικοί και δεν είναι αριθμοί, απόρριψη.
                (leftType.equals(rightType) && (!leftType.equals("Number")) && (!leftType.equals("Double"))))  // Οι τύποι είναι διαφορετικοί.
                {
                    System.err.println("!!! Error in line "+ globalLine + " Mismatched types in exponentiation expression. Left side is "
                            + leftType + " and right side is " + rightType);
                } else {
                    ParserTest1.errors += 1;
                    returnStackType.push(leftType);
                    if (!stackFunctions.isEmpty()){
                        SymFunction function = stackFunctions.peek();
                        function.setType(leftType);
                    }
                }

                if (leftType.equals("None") || rightType.equals("None")) {
                    System.err.println("\n!!! Error in line " + globalLine + ": Cannot perform exponent with None.");
                    ParserTest1.errors += 1;
                }
            }

            operatorStack.pop();
            outAExponentExpression(node);
        } else { // If inside function defintion
            inAExponentExpression(node);
            if(node.getE1() != null)
            {
                node.getE1().apply(this);
            }
            if(node.getE2() != null)
            {
                node.getE2().apply(this);
            }
            outAExponentExpression(node);
        }
        
    }

    @Override 
    public void caseAModExpression(AModExpression node){

        if (!inFunctionDef) {
            inAModExpression(node);
            operatorStack.push("Mod");

            if((node.getE1() != null ) && (node.getE2() != null))
            {
                // Επίσκεψη στην αριστερή και δεξιά πλευρά του τελεστή '%'
                PExpression leftExpr = node.getE1();  // Αριστερή πλευρά
                PExpression rightExpr = node.getE2(); // Δεξιά πλευρά
                String leftType = null;
                String rightType = null;
                leftExpr.apply(this);
                rightExpr.apply(this);
                if (!stackFunctions.isEmpty()){
                    SymFunction function = stackFunctions.peek();
                    //HashMap<String, String> defArgsCallArgs = stack.peek();
                    HashMap<String, String> defArgsCallArgs = function.getMap();
                    leftType = determineExpressionType(leftExpr, defArgsCallArgs);
                    rightType = determineExpressionType(rightExpr, defArgsCallArgs);
                }else{
                // Λήψη των τύπων των εκφράσεων
                
                    leftType = tableFillVisitor.determineExpressionType(leftExpr);
                    rightType = tableFillVisitor.determineExpressionType(rightExpr);
                }
                if (!returnStackType.isEmpty()){
                    if ((rightType.equals("Unknown") || (rightType.equals("Void")))){
                        rightType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(rightExpr.toString().trim(), rightType);
                        }
                    }
                    if ((leftType.equals("Unknown") || (leftType.equals("Void")))){
                        leftType =  returnStackType.pop();
                        if (!stackFunctions.isEmpty()){
                            SymFunction function = stackFunctions.peek();
                            HashMap<String, String> defArgsCallArgs = function.getMap();
                            defArgsCallArgs.put(leftExpr.toString().trim(), leftType);
                        }
                    }
                }
                if ((!leftType.equals(rightType)) || ((leftType.equals(rightType)) && (!leftType.equals("Number")))){
                    ParserTest1.errors += 1;
                    System.err.println("!!! Error in line "+ globalLine + " Mismatched types in modulus expression. Left side is "
                            + leftType + " and right side is " + rightType);
                } else {
                    returnStackType.push(leftType);
                    if (!stackFunctions.isEmpty()){
                        SymFunction function = stackFunctions.peek();
                        function.setType(leftType);
                    }
                }

                if (leftType.equals("None") || rightType.equals("None")) {
                    System.err.println("\n!!! Error in line " + globalLine + ": Cannot perform mod with None.");
                    ParserTest1.errors += 1;
                }
            }

            operatorStack.pop();
            outAModExpression(node);
        } else { // If inside function defintion
            inAModExpression(node);
            if(node.getE1() != null)
            {
                node.getE1().apply(this);
            }
            if(node.getE2() != null)
            {
                node.getE2().apply(this);
            }
            outAModExpression(node);
        }
    }

    @Override
    public void caseANoneValue(ANoneValue node) {
        inANoneValue(node);
    
        int line = node.getNone().getLine();
        if (!operatorStack.isEmpty()) {
            String currentOperator = operatorStack.peek();
            System.err.println("\n!!! Error in line "+ line + ": Found 'None' used in " + currentOperator.toLowerCase());
            ParserTest1.errors += 1;
        } else {
            System.err.println("! Warning: 'None' used outside an operation at line: " + line);
        }
    
        outANoneValue(node);
    }


    // Determine the type of an expression (extended from your previous function)  
    private String determineExpressionType(PExpression value, HashMap<String, String> defArgsCallArgs) {
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
        } else if (value instanceof AArrayExpression) {
            return "List";
        } else if (value instanceof AIdentifierExpression) {
            AIdentifier id = (AIdentifier) ((AIdentifierExpression) value).getIdentifier();
            String idName = id.toString().trim();
    
            // **Έλεγχος αν υπάρχει στο Map**
            if (defArgsCallArgs.containsKey(idName)) {
                return defArgsCallArgs.get(idName);
            }
            return tableFillVisitor.getVariableType(value);
        } else if (value instanceof ALengthExpression) {
            return "Number"; 
        } else if (value instanceof AAsciiExpression) {
            return "Number";
        } else if (value instanceof AMaxExpression || value instanceof AMinExpression) {
            return determineExpressionType(((AParenthExpression) value).getExpression(), defArgsCallArgs);
        } else if (value instanceof AParenthExpression) {
            return determineExpressionType(((AParenthExpression) value).getExpression(), defArgsCallArgs);
        }
        return "Unknown";
    }
    
    /**
     * Returns the AIdentifier version of a PIdentifier object.
     * @param id PIdentifier object
     * @return AIdentifier object
     */
    public AIdentifier getAId(PIdentifier id) {
        return (AIdentifier) id;
    }
}
