import java.io.*;
import java.util.*;
public class ReadFile{
    ArrayList<Product> Proionta = new ArrayList<Product>();
    ArrayList<Pwlhths> Pwlhtes = new ArrayList<Pwlhths>();
    ArrayList<Pwlhsh> Pwlhseis = new ArrayList<Pwlhsh>();
    ArrayList<Transaction> Kinhseis = new ArrayList<Transaction>();
    void parseProductList(String FileName){
        try {
            FileReader fw = new FileReader(FileName);           
            BufferedReader buff=new BufferedReader(fw);
            StringTokenizer lineTokens;
            String token;
            String line;
            Product r=null;
            boolean eof = false;
            boolean ok;
            while (!eof) {
                Boolean typeF=false;
                line = buff.readLine();               
                if (line == null)
                    eof = true;
                else {
                   if (line.trim().equals("BANKITEM")) {
                      line = buff.readLine();  
                      if (line.trim().equals("{")) {
                        buff.mark(2048);
                        while ( !(line.trim().equals("}")) && (!eof) ) {
                            line = buff.readLine();
                            if(line.trim().length()>0){ 
                                lineTokens = new StringTokenizer(line);
                                token = lineTokens.nextToken();  
                                if (token.toUpperCase().equals("TYPE")) {
                                    typeF=true;
                                    token = lineTokens.nextToken(); 
                                    if (token.equals("Card")){
                                        r = new Card();
                                    }
                                    else if (token.equals("Loan")){ 
                                        r = new Loan();
                                    }                            
                                    if(token.equals("Loan")){
                                        ok=((Loan)r).parse(buff);
                                    }
                                    else{
                                        ok=((Card)r).parse(buff);
                                    }
                                    if (ok) 
                                        Proionta.add(r);  
                                    else{
                                        if (r.getClass()==Card.class){
                                            Card.i--;
                                        }
                                        else{
                                            Loan.i--;
                                        }
                                        r=null;
                                        System.out.println("Mia eggrafh typou Product paralhfthike logw elleipsis shmantikou stoixeiou");  
                                    }                              
                                    break;  
                                }                             
                           }
                        }
                        if(typeF==false){
                            System.out.println("Mia eggrafh typou Product paralhfthike logw elleipsis shmantikou stoixeiou");
                        }    
                      }
                    }
                }    
            }           
            
            buff.close();
        }catch(IOException e){
            System.out.println("Yphrkse problhma kata to diabasma tou arxeiou");
        }
    }

    void parseSalesmen(String FileName){
        try {
            FileReader fw = new FileReader(FileName);           
            BufferedReader buff=new BufferedReader(fw);
            StringTokenizer lineTokens;
            String token;
            String line;
            Pwlhths r=null;
            boolean eof = false;
            String Tafm="0000000000";
            while (!eof) {
                int Tcode=0;
                String Tname="",Tlastname="";
                boolean codef=false,namef=false,lastnamef=false;
                line = buff.readLine();               
                if (line == null)
                    eof = true;
                else {
                   if (line.trim().equals("SALESMAN")) {
                      line = buff.readLine();  
                      if (line.trim().equals("{")) {
                            while ( !(line.trim().equals("}"))){
                                line = buff.readLine();
                                if(line.trim().length()>0){
                                    lineTokens = new StringTokenizer(line);
                                    token = lineTokens.nextToken();  
                                    if (token.toUpperCase().equals("CODE")) {
                                        token = lineTokens.nextToken(); 
                                        Tcode = Integer.parseInt(token);
                                        codef=true;
                                    }
                                    else if(token.toUpperCase().equals("AFM")){
                                        token = lineTokens.nextToken(); 
                                        Tafm = token;
                                    }
                                    else if(token.toUpperCase().equals("SURNAME")){
                                        token = lineTokens.nextToken(); 
                                        Tlastname =token;
                                        lastnamef=true;
                                    }
                                    else if(token.toUpperCase().equals("FIRSTNAME")){
                                        token = lineTokens.nextToken(); 
                                        Tname =token;
                                        namef=true;
                                    }
                                }
                            }
                            if(codef && lastnamef && namef){
                                r = new Pwlhths(Tcode, Tafm, Tlastname, Tname);
                                Pwlhtes.add(r);
                            }
                            else{
                                System.out.println("Mia eggrafh typou Salesman paralhfthike logw elleipsis shmantikou stoixeiou");
                            }                           
                        }    
                    }
                }
            } 
            buff.close();       
        }catch(IOException e){
            System.out.println("Yphrkse problhma kata to diabasma tou arxeiou");
        }  
    }

    void parseSales(String FileName){
        try {
            FileReader fw = new FileReader(FileName);           
            BufferedReader buff=new BufferedReader(fw);
            StringTokenizer lineTokens;
            String token;
            String line;
            Pwlhsh r=null;
            boolean eof = false;
            String Tait=" ";
            while (!eof) {
                int Tcodepr=0,Tcodepwl=0;
                boolean codeprf=false,codepwlf=false;
                line = buff.readLine();               
                if (line == null)
                    eof = true;
                else {
                   if (line.trim().equals("SALES")) {
                      line = buff.readLine();  
                      if (line.trim().equals("{")) {
                            while ( !(line.trim().equals("}"))){
                                line = buff.readLine();
                                if(line.trim().length()>0){
                                    lineTokens = new StringTokenizer(line);
                                    token = lineTokens.nextToken();  
                                    if (token.toUpperCase().equals("SALESMAN_CODE")) {
                                        token = lineTokens.nextToken(); 
                                        Tcodepwl = Integer.parseInt(token);
                                        codepwlf=true;
                                    }
                                    else if(token.toUpperCase().equals("BANKITEM_CODE")){
                                        token = lineTokens.nextToken(); 
                                        Tcodepr = Integer.parseInt(token);
                                        codeprf=true;
                                    }
                                    else if(token.toUpperCase().equals("DESCR")){
                                        token = lineTokens.nextToken(); 
                                        Tait =token;
                                    }
                                }
                            }
                            if(codepwlf && codeprf){
                                r = new Pwlhsh(Tcodepr, Tcodepwl, Tait);
                                Pwlhseis.add(r);
                            }
                            else{
                                System.out.println("Mia eggrafh typou Sales paralhfthike logw elleipsis shmantikou stoixeiou");
                            }                           
                        }    
                    }
                }
            } 
            buff.close();       
        }catch(IOException e){
            System.out.println("Yphrkse problhma kata to diabasma tou arxeiou");
        }  
    }
    
    void parseTransactions(String FileName){
        try{    
            FileReader fw = new FileReader(FileName);           
            BufferedReader buff=new BufferedReader(fw);
            StringTokenizer lineTokens;
            String token;
            String line;
            Transaction r=null;
            boolean eof = false;
            while (!eof) {
                String Tait="";
                double Tvalue=0;
                int Tcodepr=0;
                boolean codeprf=false,valf=false,aitf=false;
                line = buff.readLine();               
                if (line == null)
                    eof = true;
                else {
                   if (line.trim().equals("TRN")) {
                      line = buff.readLine();  
                      if (line.trim().equals("{")) {
                            while ( !(line.trim().equals("}"))){
                                line = buff.readLine();
                                if(line.trim().length()>0){
                                    lineTokens = new StringTokenizer(line);
                                    token = lineTokens.nextToken();  
                                    if (token.toUpperCase().equals("BANKITEM_CODE")) {
                                        token = lineTokens.nextToken(); 
                                        Tcodepr = Integer.parseInt(token);
                                        codeprf=true;
                                    }
                                    else if(token.toUpperCase().equals("VAL")){
                                        token = lineTokens.nextToken(); 
                                        Tvalue = Double.parseDouble(token);
                                        valf=true;
                                    }
                                    else if(token.toUpperCase().equals("JUSTIFICATION")){
                                        token = lineTokens.nextToken(); 
                                        Tait =token;
                                        aitf=true;
                                    }
                                }
                            }
                            if(valf && codeprf && aitf){
                                r = new Transaction(Tcodepr,Tvalue,Tait);
                                Kinhseis.add(r);
                            }
                            else{
                                System.out.println("Mia eggrafh typou TRN paralhfthike logw elleipsis shmantikou stoixeiou");
                            }                           
                        }    
                    }
                }
            } 
            buff.close();       
        }catch(IOException e){
            System.out.println("Yphrkse problhma kata to diabasma tou arxeiou");
        }  
    }


    ArrayList<Product> getProductList(){
        return Proionta;
    }

    ArrayList<Pwlhths> getPwlhtesList(){
        return Pwlhtes;
    }

    ArrayList<Pwlhsh> getPwlhseisList(){
        return Pwlhseis;
    }

    ArrayList<Transaction> getTransactionList(){
        return Kinhseis;
    }
}