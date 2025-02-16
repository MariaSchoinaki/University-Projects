package SymTableClasses;


public class SymVariable extends SymItem {

    public SymVariable(String name, int defLine, String type) {
        super(name, defLine, type);
    }

    public String getType() {
        return type;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Type: " + type;
    }

    @Override
    public String toString() {
        return "SymVariable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", defLine=" + defLine +
                '}';
    }
}
