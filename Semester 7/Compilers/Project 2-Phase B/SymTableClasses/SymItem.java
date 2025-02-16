package SymTableClasses;

public class SymItem {
    protected String name; // Όνομα συμβόλου
    protected int defLine; // Γραμμή ορισμού
    protected String type; // Τύπος 

    public SymItem() {
    }

    public SymItem(String name, int defLine, String type) {
        this.name = name;
        this.defLine = defLine;
        this.type = type;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String getName() {
        return name;
    }

    public int setDefLine(int defLine) {
        return this.defLine = defLine;
    }

    public int getDefLine() {
        return defLine;
    }

    public String getDetails() {
        return "Symbol: " + name + ", Defined at line: " + defLine;
    }

    public String setType(String type) {
        return this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "SymItem{" +
                "name='" + name + '\'' +
                ", defLine='" + defLine + '\'' + 
                ", type=" + type + 
                '}';
    }
}
