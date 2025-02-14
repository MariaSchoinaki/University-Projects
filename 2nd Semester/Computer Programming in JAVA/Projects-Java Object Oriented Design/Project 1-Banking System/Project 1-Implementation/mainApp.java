import java.util.*;
public class mainApp {
    public static void main(String args[]) {
        //Arxikopoihsh twn zhtoumenw
        ListaProduct products = new ListaProduct();
        ListaPwlhths pwlhtes = new ListaPwlhths();
        ArrayList <Double> promhtheies = new ArrayList<Double>();
        Pwlhths Maria = new Pwlhths(1, "3210191", "Schoinaki", "Maria");
        Pwlhths George = new Pwlhths(2, "3210183", "Sorogkas", "Giorgos");
        Pwlhths Chris = new Pwlhths(3, "3210188", "Stamoulos", "Xristos");
        pwlhtes.addPwlhths(Maria);
        pwlhtes.addPwlhths(George);
        pwlhtes.addPwlhths(Chris);
        products.addProduct(new Loan(123,10,"4832743123",30000,0.015));        
        products.addProduct(new Loan(124,11,"4832743124",40000,0.015));         
        products.addProduct(new Card(125,12,"4832743127",0.015,1000,100000));                                                                                /**123 temp */
        products.addProduct(new Card(126,10,"4832743123",0.015,1000,100000));                                                                                   /**123 temp */
        products.addProduct(new Loan(127,13,"4832743125",50000,0.015));  
        products.addProduct(new Card(128,14,"4832743126",0.015,80000,100000));                                                       /**123 temp */
        Maria.addPwlhsh(new Pwlhsh(125,1,"Epixeirhsiakh"));
        Maria.addPwlhsh(new Pwlhsh(123, 1, "Epixeirhmatiko"));
        George.addPwlhsh(new Pwlhsh(126,2,"Epixeirhsiakh"));
        George.addPwlhsh(new Pwlhsh(124, 2, "Stegastiko"));
        Chris.addPwlhsh(new Pwlhsh(128,3,"Epixeirgsiakh"));
        Chris.addPwlhsh(new Pwlhsh(127,3,"Autokinhtou"));
        products.addTransaction(2, new Transaction(125, 20, "Psuxagwgia"));
        products.addTransaction(2, new Transaction(125, 40, "Psonia"));
        products.addTransaction(2, new Transaction(125, 70, "Estiatorio"));
        products.addTransaction(2, new Transaction(125, 100, "Ygeia"));
        
        products.addTransaction(3, new Transaction(126, 60,"Psonia"));
        products.addTransaction(3, new Transaction(126, 50, "Ygeia"));
        products.addTransaction(3, new Transaction(126, 80,"Estiatorio"));
        products.addTransaction(3, new Transaction(126, 100,"Psyxagwgia"));

        products.addTransaction(5, new Transaction(128, 70,"Ygeia"));
        products.addTransaction(5, new Transaction(128, 80,"Psonia"));
        products.addTransaction(5, new Transaction(128, 10,"Psyxagwgia"));
        products.addTransaction(5, new Transaction(128, 20,"Estiatorio"));
       
        // Telos arxikopoihshs
        
        // Dhlwsh metablhtwn
        int kwdikosPwlhth,arithmosPelath,kwdikosProiontos,EpiloghProiontos,EpiloghPwlhth,EpiloghKartas,answer,answer1;
        String OnomaPwlhth,EpithetoPwlhth,AfmPwlhth,AfmPelath,aitiologia;
        double posoDaneiou=0d,epitokioDaneiou=0d,megistoEthsioPoso=0d,megistoHmerisioPoso=0d,promhtheiaKartas=0d,aksiakinhshs;
        boolean uper,eksodos=false,emfanish;

        Scanner input = new Scanner(System.in); 
        
        // Enarksh menu
        for(;;){
            emfanish=true;                                                  // metablhth pou orizei an tha emfanistoun ta stoixeia kai oi promhtheies
            if (eksodos==true){
                break;                                                      // elegxos gia ton an tha ginei eksodos apo thn metablhth
            }
            String Menu [] = {
            "1. Eisagwgi neou pwliti",
            "2. Eisagwgi neou trapezikoy proiontos",
            "3. Eisagwgi neas pwlisis trapezikoy proiontos",
            "4. Eisagwgi neas kinisis pistwtikis kartas",
            "5. Emfanisi twn daneiwn",                                    
            "6. Ypologismos tis promitheias enos pwliti",
            "7. Emfanisi twn kinisewn pistwtikwn kartwn pou sxetizontai me ena pwliti",
            "8. Ypologismos tis promitheias olwn twn pwlitwn tis trapezas",
            "9. Emfanisi tou telikoy posoy promitheias olwn twn pwlitwn",
            "10. Eksodos"
            };      //Menu
            for (int j=0;j<=9;j++){    
                System.out.println(Menu[j]);            //Ektupwsh Menu
            }
            do{   
                System.out.print("Dialekse enan arithmo: ");
                answer = input.nextInt();   
            }while(answer>10 || answer<1);
            System.out.println("Dialeksate " + Menu[answer - 1]);
            do{
                System.out.println("Eiste sigouros/h gia thn epilogh sas");
                System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                answer1 = input.nextInt();
            }while(answer1<1 || answer1>2);
            if (answer1 == 2){
                continue;
            }
            switch(answer) {
                case 1 :                                                                                    // Epilogh 1 tou menu
                    do{
                        System.out.println("Dwste ton kwdiko tou pwlhth: ");                             
                        kwdikosPwlhth=input.nextInt();
                        System.out.println("Dwste to afm tou pwlhth: ");
                        AfmPwlhth=input.next();
                        System.out.println("Dwste to onoma tou pwlhth: ");                              //Eisagwgh stoixeiwn apo ton xrhsth
                        OnomaPwlhth=input.next();
                        System.out.println("Dwste to epitheto tou pwlhth: ");
                        EpithetoPwlhth=input.next();
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    pwlhtes.addPwlhths(new Pwlhths(kwdikosPwlhth, AfmPwlhth, EpithetoPwlhth, OnomaPwlhth));
                    break;
                case 2 :                                                                                        // Epilogh 2 tou menu
                    do{
                        System.out.println("Dwste ton kwdiko tou proiontos: ");                             
                        kwdikosProiontos=input.nextInt();
                        System.out.println("Dwste ton arithmo tou pelath: ");                           
                        arithmosPelath=input.nextInt();
                        System.out.println("Dwste to Afm tou pelath: ");                                    //Eisagwgh stoixeiwn apo ton xrhsth
                        AfmPelath=input.next();
                        do{   
                            System.out.println("Ti proion epithimeite?");
                            System.out.println("1. Daneio/ 2. Karta (Epilekste 1 h 2)");
                        EpiloghProiontos=input.nextInt();
                        }while(EpiloghProiontos<0 || EpiloghProiontos>2);
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    do{
                        if (EpiloghProiontos==1){
                            System.out.println("Dwste to poso tou daneiou: ");
                            posoDaneiou=input.nextDouble();                                                             
                            do{
                                System.out.println("Dwste to epitokio tou daneiou(se dekadikh morfh p.x. 0.15): ");
                                epitokioDaneiou=input.nextDouble();
                            }while(epitokioDaneiou<=0 || epitokioDaneiou>1);
                        }
                        else{
                            System.out.println("Dwste thn promhtheia ths kartas: ");                            //Eisagwgh stoixeiwn apo ton xrhsth
                            promhtheiaKartas=input.nextDouble();
                            System.out.println("Dwste to megisto hmerisio poso: ");
                            megistoHmerisioPoso=input.nextDouble();
                            System.out.println("Dwste to megisto ethsio poso: ");
                            megistoEthsioPoso=input.nextDouble();
                        }
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    if (EpiloghProiontos==1) {
                        products.addProduct(new Loan(kwdikosProiontos, arithmosPelath, AfmPelath, posoDaneiou, epitokioDaneiou));
                    }
                    else if(EpiloghProiontos==2){
                        products.addProduct(new Card(kwdikosProiontos,arithmosPelath,AfmPelath,promhtheiaKartas,megistoHmerisioPoso,megistoEthsioPoso));
                    }
                    break;
                case 3 :                                                            // Epilogh 3 tou menu
                    do{    
                        pwlhtes.printPwlhtes();                                     // Emfanish pwlhtwn
                        do{    
                            do{
                                System.out.println("Dwste ton arithmo tou pwlhth pou epithumeite: ");           //Epilogh pwlhth apo ton xrhsth
                                EpiloghPwlhth=input.nextInt();
                            }while(EpiloghPwlhth>(pwlhtes.getSize()) || EpiloghPwlhth<0);
                            do{
                                System.out.println("Eiste sigouros/h gia thn epilogh sas");
                                System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                                answer1 = input.nextInt();
                            }while(answer1<1 || answer1>2);
                        }while(answer1==2);
                        products.printProducts();                                                                   // Emfanish proiontwn
                        do{    
                            do{
                                System.out.println("Dwste ton arithmo tou proiontos pou epithumeite: ");            // Epilogh proiontos apo to xrhsth
                                EpiloghProiontos=input.nextInt();
                            }while(EpiloghProiontos>products.getSize() || EpiloghProiontos<0);
                            do{
                                System.out.println("Eiste sigouros/h gia thn epilogh sas");
                                System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                                answer1 = input.nextInt();
                            }while(answer1<1 || answer1>2);
                        }while(answer1==2);
                        System.out.println("Dwste thn aitiologia: ");
                        aitiologia=input.next();                                                    //Eisagwgh stoixeiwn apo ton xrhsth
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    pwlhtes.addPwlhsh(EpiloghPwlhth, products.getProduct(EpiloghProiontos), aitiologia);
                    break;
                case 4:                                                                             //Epilogh 4 tou menu
                    int indexCard;
                    do{    
                        products.printKartes();                                                   // Emfanish kartwn
                        do{
                            System.out.println("Dwste ton arithmo ths kartas pou epithumeite: ");    //Epilogh kartas apo ton xrhsth
                            EpiloghKartas=input.nextInt();
                        }while(EpiloghKartas>(Card.i-1) || EpiloghKartas<0);                  
                        System.out.println("Dwste thn aitiologia ths kinhshs: ");
                        aitiologia=input.next();
                        do{    
                            System.out.println("Dwste thn aksia ths kinhshs: ");                            //Eisagwgh stoixeiwn apo ton xrhsth
                            aksiakinhshs=input.nextDouble();
                        }while(aksiakinhshs<=0);
                        indexCard=products.findCard(EpiloghKartas);                                       //Euresh ths theshs kartas sto arraylist
                        do{
                            uper=true;
                            if ((aksiakinhshs>products.getMaxPoso(indexCard)) || aksiakinhshs+(products.getTempPoso(indexCard))>products.getYearPoso(indexCard)){
                                System.out.println("To poso pou epileksate uperbainei to orio ths kartas");
                                System.out.printf("Sas upenthimizoume oti to megisto poso kinhshs ths kartas einai: %.2f kai to megisto ethsio: %.2f",products.getMaxPoso(indexCard),products.getYearPoso(indexCard));
                                System.out.println("\nParakalisthe na eisagete neo poso");
                                aksiakinhshs=input.nextDouble();
                                uper=false;
                            }
                        }while(uper==false);
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    products.addTransaction(indexCard,new Transaction(products.getCardCode(indexCard), aksiakinhshs, aitiologia));
                    break;
                case 5:                                                                 // Epilogh 5 tou menu
                    products.printDaneia();                                             // Emfanish twn daneiwn
                    break;
                case 6:                                                                 // Epilogh 6 tou menu
                    do{
                        do{
                            pwlhtes.printPwlhtes();                                                             //Emfanish pwlhtwn
                            System.out.println("Dwste ton arithmo tou pwlhth pou epithumeite: ");
                            EpiloghPwlhth=input.nextInt();                                                      //Epilogh pwlhth
                        }while(EpiloghPwlhth>(pwlhtes.getSize()) || EpiloghPwlhth<0);
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    double promhtheiaDaneiwn = pwlhtes.promhtheiaDaneiwn(products.getList(), EpiloghPwlhth,emfanish);
                    System.out.printf("Promhtheia Daneiwn: %.2f%n",promhtheiaDaneiwn);
                    double promhtheiaKartwn = pwlhtes.promhtheiaKartwn(EpiloghPwlhth, products.getList(),emfanish);
                    System.out.printf("H promhtheia tou pwlhth pou epileksate einai: %.2f%n",(promhtheiaKartwn+promhtheiaDaneiwn));
                    break;
                case 7:                                                         // Epilogh 7 tou menu
                   do{
                    do{
                        pwlhtes.printPwlhtes();                                                             //Emfanish pwlhtwn
                        System.out.println("Dwste ton arithmo tou pwlhth pou epithumeite: ");             //Epilogh pwlhth
                        EpiloghPwlhth=input.nextInt();
                    }while(EpiloghPwlhth>(pwlhtes.getSize()) || EpiloghPwlhth<0);
                        do{
                            System.out.println("Eiste sigouros/h gia thn epilogh sas");
                            System.out.println("1. NAI/ 2. OXI (Epilekste 1 h 2)");
                            answer1 = input.nextInt();
                        }while(answer1<1 || answer1>2);
                    }while(answer1==2);
                    pwlhtes.printTransactions(EpiloghPwlhth, products.getList());                           //Emfanish kinhsewn twn kartwn enws pwlhth
                    break;
                case 8:                                                                 //Epilogh tou 8
                case 9:                                                                 // Epilogh 9 tou menu
                    promhtheies.clear();                                                // Katharismos tou array list me tis promhtheies
                    double sum=0;
                    if(answer==8){
                        emfanish=false;
                    }
                    for(int i=1;i<=pwlhtes.getSize();i++){
                        double promhtheiaDaneiwn1 = pwlhtes.promhtheiaDaneiwn(products.getList(), i,emfanish);
                        double promhtheiaKartwn1 = pwlhtes.promhtheiaKartwn(i, products.getList(),emfanish);     // ypologismos promththeias
                        promhtheies.add(promhtheiaDaneiwn1+promhtheiaKartwn1);                                                    // prosthikh promhtheias sto array list promhtheiwn
                        if(answer==9){
                            System.out.println(pwlhtes.getPwlhths(i-1) + String.format("  Promhtheia: %.2f%n" , promhtheies.get(i-1)));
                            sum+=promhtheies.get(i-1);
                        }
                    }
                    if(answer==9){
                        System.out.printf("Sunolikh Promhtheia Pwlhtwn: %.2f%n",sum);
                    }
                    break;
                case 10:                                                        // Eksodos apo thn efarmogh
                    eksodos=true;
                    break;
            }   
        }
    }
}