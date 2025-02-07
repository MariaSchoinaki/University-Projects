/*
	Name: Maria Schoinaki
	Student Number: 3210191
*/

class App2 { 

	public static void main (String args[]){	
        final int a = 5;
		final int b = 3;
	 	int pin [] [] = new int [a] [b];

		pin[0][0]=182;
        pin[0][1]=41;
        pin[0][2]=202;

        pin[1][0]=145;
        pin[1][1]=85;
        pin[1][2]=325;

        pin[2][0]=195;
        pin[2][1]=15;
        pin[2][2]=115;

        pin[3][0]=110;
        pin[3][1]=24;
        pin[3][2]=407;

        pin[4][0]=255;
        pin[4][1]=11;
        pin[4][2]=357;
        System.out.println("___________________________________________________________");
        System.out.println("|  Eklogikh  |" + " " + "Ypopshfios A |" + " " + 
        "Ypopshfios B |" + " " + "Ypopshfios C |" + "\n" + "| Perifereia |" + 
        "              |              |              |");
        System.out.println("|____________|______________|______________|______________|");
        int j = 0;
        for (int i=0; i<a; i++){
            System.out.println("|     " + (i+1) + "      |     " + pin[i][j] + "      |      " + 
            pin[i][j+1] + "      |     " + pin[i][j+2] + "      |    ");
            System.out.println("|____________|______________|______________|______________|");    
        }
   		int Suma = 0;
        int Sumb = 0;
		int Sumc = 0;
        j = 0;
        for (int i=0; i<a; i++){
            Suma += pin[i][j];
            Sumb += pin[i][j+1];
            Sumc += pin[i][j+2];
        }
        int Sum = Suma + Sumb + Sumc;
        System.out.println("\n" + "Ypopshfios A: " + Suma + " pshfoi");
        System.out.println("Ypopshfios B: " + Sumb + " pshfoi");
        System.out.println("Ypopshfios C: " + Sumc + " pshfoi");

        System.out.println("\n" + "Ypopshfios A: " + (double)(Suma)/Sum * 100 + "%");
        System.out.println("Ypopshfios B: " + (double)(Sumb)/Sum * 100 + "%");
        System.out.println("Ypopshfios C: " + (double)(Sumc)/Sum * 100 + "%");
        double posa = (double)(Suma)/Sum * 100;
        double posb = (double)(Sumb)/Sum * 100;
        double posc = (double)(Sumc)/Sum * 100;
        if(posa > 50){
            System.out.println("\n" + "O Ypopshfios A kerdise tis ekloges ");
        }
        if(posb > 50){
            System.out.println("\n" + "O Ypopshfios B kerdise tis ekloges ");
        }
        if(posc > 50){
            System.out.println("\n" + "O Ypopshfios C kerdise tis ekloges ");
        }
        double max1 = 0;
        String maxn1 = "";
        double max2 = 0;
        String maxn2 = "";
        if((posa <= 50) & (posb <= 50) & (posc <= 50)){
            if ((posa > posb) & (posa > posc)){
                max1 = posa;
                maxn1 = "A";
                if(posb > posc){
                    max2 = posb;
                    maxn2 = "B";
                }
                else{
                    max2 = posc;
                    maxn2 = "C";
                }    
            }
            if ((posb > posa) & (posb > posc)){
                max1 = posb;
                maxn1 = "B";
                if(posa > posc){
                    max2 = posa;
                    maxn2 = "A";
                }
                else{
                    max2 = posc;
                    maxn2 = "C";
                }        
            }
            if ((posc > posa) & (posc > posb)){
                max1 = posc;
                maxn1 = "C";
                if(posa > posb){
                    max2 = posa;
                    maxn2 = "A";
                }
                else{
                    max2 = posb;
                    maxn2 = "B";
                }        
            }
            System.out.println("8a diejax8ei epanalhptikos gyros metaksy twn" + maxn1 + " kai" + maxn2 +
            "me pososta " + max1 + " " + max2 + " antistoixa");
        }    
    }                         	
}
