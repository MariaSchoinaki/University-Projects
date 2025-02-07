import java.io.*;
import java.util.*;
public class Card extends Product {
    private ArrayList <Transaction> cTransaction = new ArrayList<Transaction>();    // Array list me ths kinhseis kathe pistwtikhs kartas
    private double pos_promhtheia;                                                  // To pososto promhtheias ths kartas
    private double maxposo;                                                         // To megisto poso kinhshs ths kartas
    private double maxyearposo;                                                     // To megisto ethsio poso ths kartas
    private double tempPoso=0d;                                                      // H metablhth tempPoso apothikeuei tis kinhseis pou exoun ginei sunolika
    private int key;                                                                // To kleidi kathe kartas (monadikos arithmos)
    static int i=1;                                                                 // Arithmos pou deinxei posa antikeimena loan exoun dimiourghthei
    Card(){
        pos_promhtheia=0.015;
        maxposo=1000;
        maxyearposo=40000;
        key=i;
        i++;
    }
    Card(int code, int arithmos, String AFM, double promhtheia, double maxposo, double maxyearposo){    
        super(code, arithmos, AFM);                                                  // Constructor ths kartas me eisodo ton kwdiko tou proiontos,ton arithmo
        this.pos_promhtheia = promhtheia;                                            // kai to afm tou pelath, to pososto promhtheias,to megisto hmerisio kai ethsio poso.
        this.maxposo = maxposo;                                                      // Arxikopoiei ta stoixei ths kartas kai epeita orizei kai to kleidi tou 
        this.maxyearposo = maxyearposo;                                             // auksanontas tautoxrona ton arithmo twn kartwn pou exoun dimiourghthei.
        key=i;
        i++;

        
    }
    double getPromhtheia(){
        return pos_promhtheia;                                                      // H methodos getPromhtheia epistrefei to pososto ths promhtheias
    }
    double getMaxPoso(){
        return maxposo;                                                            // H methodos getMaxPoso epistrefei to megisto hmerisio poso
    }
    double getMaxYearPoso(){
        return maxyearposo;                                                        // H methodos getMaxYearPoso epistrefei to megisto ethsio poso
    }
    void addTransaction(Transaction t){
        cTransaction.add(t);                                                        // H methodos addTransaction prosthetei ena antikeimeno kinhshs t sto array list
        tempPoso+=t.getAksia();                                                     // me tis kinhseis ths kartas kai auksanei to poso twn sunoliko kinhsewn me thn aksia
    }                                                                               // ths trexousas kinhshs

    double getTempPoso(){
        return tempPoso;                                                            // H methodos getTempPoso epistrefei thn aksia twn sunolikwn kinhsewn ths kartas
    }

    public String toString(){
        return super.toString() + String.format("  Pososto Promhtheias kartas: %.2f%%  Megisto poso sunallaghs: %.2f  Megisto ethsio poso: %.2f",pos_promhtheia*100,maxposo,maxyearposo) ;
    }
    
    int getKey(){
        return key;                                                               // H methodos getKey epistrefei to monadiko kleidi ths kartas
    }

    double promhtheia(boolean emfanish){
        double sum=0;                                     // H methodos promhtheia upologizei thn promhtheia ths kathe kartas prosthetontas tis aksies twn kinhsewn ths
        for(Transaction t : cTransaction){                // pollaplasiasmenes me to pososto promhtheias ths kartas. H boolean emfanish kathorizei an tha ektupothoun
            sum+=t.getAksia()*pos_promhtheia;             // ta stoixeia ths kartas kai h promhtheia
        }
        if(emfanish==true)System.out.println("Kwdikos Kartas: " + super.code_pr + String.format("  Promhtheia Kartas: %.2f",sum));
        return sum;
    }

    ArrayList <Transaction> getList(){
        return cTransaction;                             // H methodos getList epistrefei to array list me tis kinhseis ths kartas       
    }


    boolean parse(BufferedReader buff){
        try {
            buff.reset();
            StringTokenizer lineTokens;
            String token;
            String line;
            boolean codef=false;    
            line = buff.readLine();             
            while ( !(line.trim().equals("}")) ) {
                if(line.trim().length()>0){                           
                    lineTokens = new StringTokenizer(line);
                    token = lineTokens.nextToken();  
                    if (token.equals("CODE")) {
                        token = lineTokens.nextToken(); 
                        code_pr = Integer.parseInt(token);
                        codef=true;
                    }
                    else if (token.equals("AFM")) {
                        token = lineTokens.nextToken(); 
                        AFM = token;
                    }
                    else if (token.equals("ARITHMOS")) {
                        token = lineTokens.nextToken(); 
                        arithmos = Integer.parseInt(token);
                    }
                    else if (token.equals("PROMHTHEIA")){
                        token = lineTokens.nextToken(); 
                        pos_promhtheia = Double.parseDouble(token)/100;
                    }
                    else if (token.equals("MAXPOSO")){
                        token = lineTokens.nextToken(); 
                        maxposo = Double.parseDouble(token);
                    }
                    else if (token.equals("MAXYEARPOSO")){
                        token = lineTokens.nextToken(); 
                        maxyearposo = Double.parseDouble(token);
                    }                  
                }
                line = buff.readLine();                             
            } 
            if(codef==false){
                return false;
            }
            
        return true;   
        }catch(IOException e){
            System.out.println("Yphrkse problhma sto diabasma tou arxeiou");
            return false;
        }
    }

}
