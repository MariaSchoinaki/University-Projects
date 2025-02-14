import java.io.*;
import java.util.*;
public class Loan extends Product{
    private double poso;                                                                    // To poso tou daneiou
    private double epitokio;                                                                // To epitokio tou daneiou 
    private int key;                                                                        // To kleidi kathe daneiou (monadikos arithmos)
    static int i=1;                                                                         // Arithmos pou deinxei posa antikeimena loan exoun dimiourghthei
    Loan(){
        poso=50000;
        epitokio=0.015;
        key=i;
        i++;
    }
    Loan(int code, int arithmos, String AFM, double poso, double epitokio){                 // Constructor tou loan me eisodo ton kwdiko tou proiontos,ton arithmo
        super(code, arithmos, AFM);                                                         // kai to afm tou pelath, to poso kai to epitokio tou daneiou.
        this.poso = poso;                                                                   // Arxikopoiei ta stoixei tou daneiou kai epeita orizei kai to kleidi tou 
        this.epitokio = epitokio;                                                           // auksanontas tautoxrona ton arithmo twn daneiwn pou exoun dimiourghthei.
        key=i;
        i++;
    } 
    double getPoso(){                                                                       // H methodos getPoso epistrefei to poso tou daneiou (double)
        return poso;
    }   
    double getEpitokio(){                                                                   // H methodos getEpitokio epistrefei to epitokio tou daneiou (double)
        return epitokio;
    }

    public String toString(){
        return super.toString() + String.format("  Poso Daneiou: %.2f  Epitokio Daneiou: %.2f%%",poso,epitokio*100);
    }

    int getKey(){                                                                          // H methodos getKey epistrefei to kleidi tou daneiou
        return key;
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
                    else if (token.equals("EPITOKIO")){
                        token = lineTokens.nextToken(); 
                        epitokio = Double.parseDouble(token)/100;
                    }
                    else if (token.equals("POSO")){
                        token = lineTokens.nextToken(); 
                        poso = Double.parseDouble(token);
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
