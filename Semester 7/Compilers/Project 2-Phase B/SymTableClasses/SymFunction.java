package SymTableClasses;

import java.util.HashMap;
import java.util.LinkedList;
import minipython.node.AArgument;
import minipython.node.PStatement;

public class SymFunction extends SymItem {
    
    private LinkedList<AArgument> arguments;
    private PStatement statement;
    private boolean returns;
    private String type;
    private HashMap<String, String> defArgsCallArgs;

    public SymFunction() {

    }

    public SymFunction(int defLine, LinkedList<AArgument> arguments, PStatement statement, boolean returns, String type, HashMap<String, String> defArgsCallArgs) {
        this.defLine = defLine;
        this.arguments = arguments;
        this.statement = statement;
        this.returns = returns;
        this.type = type;
        this.defArgsCallArgs = defArgsCallArgs;

    }

    public void setMap(HashMap<String, String> defArgsCallArgs){
        this.defArgsCallArgs = defArgsCallArgs;
    }

    public HashMap<String, String> getMap(){
        return defArgsCallArgs;
    }
    
    public PStatement getStatement() {
        return statement;
    }

    public boolean isReturns() {
        return returns;
    }

    public void setReturns(boolean returns) {
         this.returns = returns;
    }
    @Override
    public String getType(){
        return type;
    }

    @Override
    public String setType(String type) {
        return this.type = type;
    }

    @Override
    public String toString() {
        return "SymFunction{" +
                "line=" + defLine +
                ", arguments=" + arguments +
                ", PStatement=" + statement +
                ", returns=" + returns +
                ", type=" + type + 
                '}';
    }

    public LinkedList<AArgument> getArguments() {
        return arguments;
    }

    public void setArguments(LinkedList<AArgument> arguments) {
        this.arguments = arguments;
    }
}
