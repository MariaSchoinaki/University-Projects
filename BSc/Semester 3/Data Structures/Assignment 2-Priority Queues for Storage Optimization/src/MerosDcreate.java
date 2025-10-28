import java.io.*;
public class MerosDcreate {

    // MerosDcreate: Xrhsimopoieitai gia th dhmiourgia twn 10 txt arxeiwn

    public static void main(String []args){
        int n = 1000;                                                   // n : o arithmos twn fakelwn pou theloume
        String fakelos = "n1000/";                                      // To onoma tou fakelou pou theloume na apothikeutoun ta txt arxeia
        String name = "input";                                          // To onoma tou kathe txt arxeiou (p.x input1.txt , input2.txt , ....)
        int num = 1;                                                    // num : Metrhths arxikopoieitai me 1
        FileWriter writer = null;                                       // writer : FileWriter arxikopoeitai me null
        try{
            while(num<=10){                                             // Oso o metrhths einai <=10
                int temp = 0;                                           // Arxikopoioume mia temp metblhth me 0
                writer = new FileWriter(fakelos +name + num++ + ".txt");// Arxikopoieitai o FileWriter(Ftiaxnetai to String kai auksanetai to num)
                for(int i=0; i<n; i++){                     // Gia i apo 0 mexri n-1 ( n fakeloi )
                    temp = (int)(Math.random()*1000000);    // To temp ginetai enas tuxaios int arithmos sto [0,1000000]
                    writer.write(Integer.toString(temp));   // Grafoume ton arithmo sto txt arxeio
                    if(i<n-1){                              // An o arithmos pou grapsame den einai o teleutaios
                        writer.write("\n");            // Kanoume allagh grammhs
                    }
                }
                writer.close();                             // Kleinoume ton FileWriter
            }
        }
        catch (Exception e){                                // Se periptwsh pou dhmiourgithei kapoio exception
            System.out.println("Yphrkse sfalma. To arxeio den dhmiourghthike. Sigoureuteitai oti exei dhmiourghthei o fakelos " + fakelos + " ston opoio tha topothetithoun ta arxeia txt");     // Emfanizoume antistoixo munhma
        }
    }
}
