import java.util.*;
class ListaPwlhths{
    private ArrayList <Pwlhths> pwlhtes = new ArrayList<Pwlhths>();                          // Array list me antikeimena Pwlhths

    void addPwlhths(Pwlhths p){                                                              // h methodos addPwlhths prosthetei enan Pwlhth (p) sto array list
        pwlhtes.add(p);
    }

    void printPwlhtes(){
        for(Pwlhths p : pwlhtes){                                                       // H methodos printPwlhtes ektupwnei tous pwlhtes pou briskontai sto arraylist
            System.out.println(p.getKey()+ ". " + p.toString());
        }
    }

    int getSize(){
        return pwlhtes.size();                                                  // H methodos getSize epistrefei to megethos tou arraylist pwlhtes
    }

    void addPwlhsh(int keyPwlhth,Product P,String aitiologia){
        for (Pwlhths p : pwlhtes){                                              // H methodos addPwlhsh prosthetei ena antikeimeno pwlhsh se ena sugkekrimeno pwlhths
            if(p.getKey()==keyPwlhth){                                          // pou brisketai sto arraylist pwlhtes analoga me thn eisodo(keyPwlhth)
                p.addPwlhsh(new Pwlhsh(P.getCode(),p.getCode(),aitiologia));    // pou exei dothei
                System.out.println(p.getKey()+". "+ p.toString());
            }
        }
    }

    double promhtheiaKartwn(int epilogh, ArrayList <Product> Products,boolean emfanish ){
        double sum=0;
        for(Pwlhths p : pwlhtes){                                               // upologizei kai epistrefei thn promhtheia twn kartwn enos sugkekrimenou pwlhth pou
            if(epilogh==p.getKey()){                                            // brisketai sto array list pwlhtes
                for(Pwlhsh pwl: p.getList()){
                    for(Product pr : Products){
                        if(pwl.getCodePr()==pr.getCode() && pr.getClass()==Card.class){
                            sum+=((Card) pr).promhtheia(emfanish);
                        }
                    }            
                }
            }
        }
        return sum;
    }

    double promhtheiaDaneiwn(ArrayList <Product> Products,int key,boolean emfanish){
        double sumDaneiwn=0;
        double sumTokwn=0;
        double tempPromhth;
        double sum;
        for(Pwlhths pwl : pwlhtes){
            if(pwl.getKey()==key){                                              // Ypologizei kai epistrefei thn promhtheia twn poswn twn daneiwn 
                for(Pwlhsh pwlhsh : pwl.getList()){                             // pou exei poulhseis enas pwlhths
                    for(Product pro : Products){
                        if(pro.getCode()==pwlhsh.getCodePr() && pro.getClass()==Loan.class){
                            sumDaneiwn+=((Loan) pro).getPoso();
                            sumTokwn+=((Loan)pro).getEpitokio()*((Loan) pro).getPoso();
                            if (emfanish==true)System.out.printf("Poso daneiou: %.2f%n" ,((Loan) pro).getPoso());
                        }
                    }
                }
            }
        }
        if(sumDaneiwn<500000){
            tempPromhth=0.01;                                                   
        }
        else if(sumDaneiwn<=2000000){                                                               // Elegxetai to athroisma ton daneiwn wste na kathoristei to pososto ths
            tempPromhth=0.02;                                                              // promhtheias sumfwna me ton pinaka promhtheiwn twn daneiwn
        }
        else{
            tempPromhth=0.025;
        }
        if (sumDaneiwn*tempPromhth>sumTokwn){                                               // Elegxei an to poso promhtheias tou daneiou einai megalutero apo tous
            sum=sumTokwn;                                                             // ethsious tokous tou daneiou
        }
        else{
           sum = sumDaneiwn*tempPromhth;
        }
        return sum;      
    }

    void printTransactions(int key,ArrayList <Product> Products){
        for(Pwlhths pwl : pwlhtes){                                                             // Ektupwnei oles tis kinhseis mia kartas pou exei poulhseis enas
            if(pwl.getKey()==key){                                                              // sugkekrimenos pwlhths
                for(Pwlhsh pwlhsh : pwl.getList() ){
                    for(Product product : Products){
                        if((product.getClass()==Card.class) && (product.getCode()==pwlhsh.getCodePr())){
                            for(Transaction cTran : ((Card) product).getList()){
                                System.out.println(cTran);
                            }
                        }
                    }
                }
            }
        }
    }

    ArrayList <Pwlhths> getList(){                                          //Epistrefei to arralist pwlhtes
        return pwlhtes;
    }

    Pwlhths getPwlhths(int i){                                             //Epistrefei antikeimeno Pwlhths to opoio brisketai sthn thesh
        return pwlhtes.get(i);                                             // i tou pinaka pwlhtes
    }

    void copyPwlhtesList(ArrayList<Pwlhths> Pwlhtes){
        for(Pwlhths p : Pwlhtes){
            pwlhtes.add(p);
        }
    }

    void addReadPwlhseis(ArrayList<Pwlhsh> Pwlhseis){
        for(Pwlhths p : pwlhtes){
            for(Pwlhsh pwl : Pwlhseis){
                if(p.getCode()==pwl.getCodePol()){
                    p.addPwlhsh(pwl);
                }
            }
        }
    }
}