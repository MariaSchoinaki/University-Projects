import java.io.*;
import java.util.*;
public class CreateFile {

    
    void createProducts(ArrayList<Product> proionta){
        System.out.println(">> Pragmatopoieitai dhmiourgia arxeiou \"products.txt\"");
        FileWriter writer = null;
        try	{
			writer = new FileWriter(new File("products.txt"));
            writer.write("BANKITEM_LIST" + "\n{" );
            for (Product p : proionta ){
                if(p instanceof Loan){
                    
                    writer.write("\n\tBANKITEM" + "\n\t{" + "\n\t\tTYPE Loan" + "\n\t\tCODE " + p.getCode() + "\n\t\tDESCR \"DANEIO " 
                    +((Loan)p).getKey() + "\"" + "\n\t\tAFM " +p.getAfm() + "\n\t\tARITHMOS " + p.getArithmos() + "\n\t\tPOSO " + ((Loan) p).getPoso()
                    + "\n\t\tEPITOKIO " + ((Loan) p).getEpitokio()*100 + "\n\t}" );
                }
                else if(p instanceof Card){
                    writer.write("\n\tBANKITEM" + "\n\t{" + "\n\t\tTYPE Card" + "\n\t\tCODE " + p.getCode() + "\n\t\tDESCR \"CARD " 
                    +((Card)p).getKey() +"\"" + "\n\t\tAFM " +p.getAfm() + "\n\t\tARITHMOS " + p.getArithmos() + "\n\t\tPROMHTHEIA " 
                    + ((Card) p).getPromhtheia()*100 + "\n\t\tMAXPOSO " + ((Card) p).getMaxPoso() + "\n\t\tMAXYEARPOSO " + ((Card) p).getMaxYearPoso()
                    + "\n\t}" );
                }
            
            }
            writer.write("\n}");
            writer.close();
            System.out.println(">> To arxeio \"products.txt\" dhmiourghthike me epituxia");
        }
        catch(Exception e){
            System.out.println(">> Yphrkse problhma sthn eggrafh tou arxeiou");
        }
    }

    void createSalemans(ArrayList<Pwlhths> Pwlhtes){
        System.out.println(">> Pragmatopoieitai dhmiourgia arxeiou \"salesmen.txt\"");
        FileWriter writer = null;
        try	{
			writer = new FileWriter(new File("salesmen.txt"));
            writer.write("SALESMAN_LIST" + "\n{" );
            for(Pwlhths p : Pwlhtes){
                writer.write("\n\tSALESMAN" + "\n\t{" + "\n\t\tCODE " + p.getCode() + "\n\t\tSURNAME " 
                + p.getLastname().toUpperCase() + "\n\t\tFIRSTNAME " +p.getFirstName().toUpperCase()
                + "\n\t\tAFM " + p.getAfm() + "\n\t\tARITHMOS_PWLHTH " + p.getKey() + "\n\t}");
            }
            writer.write("\n}");
            writer.close();
            System.out.println(">> To arxeio \"salesmen.txt\" dhmiourghthike me epituxia");
        }
        catch(Exception e){
            System.out.println(">> Yphrkse problhma sthn eggrafh tou arxeiou");
        }
    }
    
    void createSales(ArrayList<Pwlhths> Pwlhtes,ArrayList<Product> proionta){
        System.out.println(">> Pragmatopoieitai dhmiourgia arxeiou \"sales.txt\"");
        FileWriter writer = null;
        try{
            writer = new FileWriter(new File("sales.txt"));
            writer.write("SALES_LIST" + "\n{" );
            for(Pwlhths p : Pwlhtes){
                for(Pwlhsh pwl : p.getList()){
                    String Type="";
                    for(Product pro : proionta){
                        if(pwl.getCodePr()==pro.getCode()){
                            if(pro.getClass()==Card.class){
                                Type = "Card";
                            }
                            else{
                                Type = "Loan";
                            }
                        }
                    }



                    writer.write("\n\tSALES" + "\n\t{" +  "\n\t\tSALESMAN_CODE " + pwl.getCodePol() + "\n\t\tBANKITEM_TYPE " 
                    + Type + "\n\t\tBANKITEM_CODE " + pwl.getCodePr() + "\n\t\tDESCR " + pwl.getAitiologia() + "\n\t}" );
                }
            }
            writer.write("\n}");
            writer.close();
            System.out.println(">> To arxeio \"sales.txt\" dhmiourghthike me epituxia");

        }catch(Exception e){
            System.out.println(">> Yphrkse problhma sthn eggrafh tou arxeiou");
        }
    }

    void createTransactions(ArrayList<Product> proionta,ArrayList<Pwlhths> pwlhtes){
        System.out.println(">> Pragmatopoieitai dhmiourgia arxeiou \"transactions.txt\"");
        FileWriter writer = null;
        try{
            writer = new FileWriter(new File("transactions.txt"));
            writer.write("TRN_LIST" + "\n{" );
            int kwdikosPwlhth=0;
            for(Product p : proionta){
                if (p.getClass()==Card.class){
                    for(Pwlhths pwlhths : pwlhtes){
                        for(Pwlhsh pwlhsh : pwlhths.getList()){
                            if(pwlhsh.getCodePr()==p.getCode()){
                                kwdikosPwlhth=pwlhsh.getCodePol();
                            }
                        }
                    }
                    
                    
                    
                    for(Transaction trn : ((Card)p).getList()){
                        writer.write("\n\tTRN" + "\n\t{" +  "\n\t\tEMPLOYEE_CODE " + kwdikosPwlhth + "\n\t\tBANKITEM_CODE " 
                        + p.getCode() + "\n\t\tVAL " + trn.getAksia() + "\n\t\tJUSTIFICATION " + trn.getAitiologia() + "\n\t}" );
                    }

                }
            }
            writer.write("\n}");
            writer.close();
            System.out.println(">> To arxeio \"transactions.txt\" dhmiourghthike me epituxia");
        }catch(Exception e){
            System.out.println(">> Yphrkse problhma sthn eggrafh tou arxeiou");
        }
    }
    
}
