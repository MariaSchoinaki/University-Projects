import java.io.BufferedReader;                                     // Xrhsimopoieitai mono gia to diabasma tou arxeiou txt
import java.io.FileReader;                                         // Xrhsimopoieitai mono gia to diabasma tou arxeiou txt
import java.io.IOException;                                        // Xrhsimopoieitai mono gia to diabasma tou arxeiou txt
import java.util.ArrayList;                                        // Xrhsimopoieitai mono gia to diabasma tou arxeiou txt
import java.util.List;                                             // Xrhsimopoieitai mono gia to diabasma tou arxeiou txt


public class Thiseas{
    public static void main(String []args) throws IOException{
        boolean lisi = false;                                       // Metablhth pou ginetai true an exei brethei h lush tou labyrinthou
        boolean dbnl = false;                                       // Metablhth pou ginetai true an uparxei adieksodos ston labyrintho
        int countE = 0;                                             // Metrhths twn shmeiwn eisodou tou labyrintou (gia elegxo lathwn)
        boolean falseDed = false;                                   // Metablhth pou ginetai true an exei brethei lathos sta dedomena
        boolean exit = false;                                       // Metablhth pou ginetai true an uparxei eksodos ston labyrintho

        String file = args[0];                                        // Onoma tou arxeiou (path)
        List<String> listOfStrings = new ArrayList<String>();            // Dhmiourgei listas pou leitourgei ws array(Gia diabasma apo to arxeio)
        BufferedReader bf = new BufferedReader(new FileReader(file));    // Dhmiourgia BufferedReader gia diabasma arxeiou
        String line = bf.readLine();                                     // Diabasma prwths grammhs
        while (line != null) {                                          // While loop gia to diabasma tou arxeiou
            listOfStrings.add(line);                                    // prosthiki grammhs sth lista
            line = bf.readLine();                                       // Diabasma epomenhs grammhs
        }
        bf.close();                                                     // Kleisimo buffer(arxeiou)
        String[] array = listOfStrings.toArray(new String[0]);          // Metatroph ths listas se array apo Strings
        String border = array[0];                                       // Apothikeush prwths grammhs tou arxeiou se metblhth String
        String[] borders = border.split(" ");                    // Diaspash ths prwths grammhs sto xarakthra " "
        int gram = Integer.parseInt(borders[0]);                        // Apothikeush tou arithmou grammwn(pou htan prin apo to " ")
        int sthl = Integer.parseInt(borders[1]);                        // Apothikeush tou arithmou sthlwn(pou htan meta apo to " ")
        border = array[1];                                  
        String[] sunt = border.split(" ");                      //-----------------------------------------------------------------
        int x_e = Integer.parseInt(sunt[0]);                          //        Idia diadikasia kai gia tis suntetagmenes ths eisodou
        int y_e = Integer.parseInt(sunt[1]);                          //------------------------------------------------------------------

        if(gram<=0 || sthl<=0 || x_e<0 || y_e<0){                    //-------------------------------------------------------------------
            falseDed=true;                                           // Elegxos gia to an exei dothei arnhtikh timh se grammes,sthles
        }                                                            // kai tis suntetagmenes --------------------------------------------

        if(array.length-2!=gram){                                  // Elegxos gia to an o pinakas pou exei dothei exei perissoteres grammes
            falseDed=true;                                         // array.length --> plithos grammwn txt arxeiou  
        }                                                          // array.length - 2 --> plithos grammwn pinaka

        for(int i=2;i<array.length;i++){                          //-----------------------------------------------------------------
            if(array[i].length()!=2*sthl-1){                      // Elegxos gia to an o pinakas pou exei dothei exei perissoteres sthles
                falseDed=true;                                    // se kathe grammh tou txt arxeiou ektos apo tis prwtes 2
            }                                                     //-----------------------------------------------------------------
        }

        if(falseDed==false){                                      // An den exei brethei lathos sta dedomena (Mexri twra)
            StringStackImpl<Point> PointStack = new StringStackImpl<Point>();   // Dhmiourgia Stack<Point> (Arxeio Point.java)
            PointStack.push(new Point(x_e, y_e));                               // Eisagwgh eisodou sth stoiba
            int pinakas[][] = new int[gram][sthl];           // Dhmiourgia int array (Pinakas opou tha ginetai h diadikasia eureshs eksodou)
            
            //Diadikasia metatrophs kathe grammhs tou txt arxeiou pou exoume diabasei se pinaka(oi grammes briskontai ston String pinaka array)
            
            for(int i=2; i<array.length;i++){   //for loop apo 2 (3h grammh tou txt arxizei o pinakas) mexri to size tou array
                int k = -1;                     // metrhths gia th swsth apothikeush (logw twn kenwn se kathe grammh)
                for(int j=0; j<array[i].length(); j+=2){    //for loop gia kathe stoixeio kathe grammhs
                    k++;                                    // aukshsh metrhth kata ena
                    if(array[i].charAt(j)=='E'){            // Elegxos an to stoixeio einai to 'E' (eisodos)
                        pinakas[i-2][j-k]=2;                // antistoixh apothikeush
                        countE++;                           // aukshsh metrhth twn eisodwn (Gia elegxo lathous sta dedomena)
                        if(i-2!=x_e || j-k!=y_e){           // elegxos an oi suntetagmenes pou brhkame to 'E' antistoixoun se
                            falseDed=true;                  // autes pou dothikan sto arxeio kai enhmerwsh ths metbalhths an den antistoixoun
                        }
                    }
                    else if(array[i].charAt(j)=='1'){       // Elegxos an to stoixeio einai to '1'
                        pinakas[i-2][j-k]=1;                // antistoixh apothikeush
                    }
                    else if(array[i].charAt(j)=='0'){       // Elegxos an to stoixeio einai to '0'
                        pinakas[i-2][j-k]=0;                // antistoixh apothikeush
                    }
                    else{                                   // Periptwsh pou exei dothei lathos xarakthras apo to arxeio
                        falseDed=true;                      // Enhmerwsh metablhths
                    }
                }
            }

            if(countE>1 || countE<=0){                      // Elegxos an exei dothei perissoteres apo mia eisodous h kamia eisodos
                falseDed=true;                              // Enhmerwsh metablhths
            }

            for(int i=0; i<sthl ; i++){                        // Elegxos an uparxei eksodos sthn prwth kai sth teleutaia grammhs
                if(pinakas[0][i]==0 || pinakas[gram-1][i]==0){  // An uparxei eksodos
                    exit = true;                                // Enhmerwsh metblhths
                }
            }

            for(int i=0; i<gram ; i++){                     // Elegxos an uparxei eksodos sthn prwth kai sth teleutaia sthlh
                if(pinakas[i][0]==0 || pinakas[i][sthl-1]==0){  // An uparxei eksodos
                    exit = true;                                // Enhmerwsh metblhths
                }
            }


            if(exit==false || falseDed==true){          // ekteleitai an den uparxei eksodos H exoun dothei lathos dedomena
                if(exit==false && falseDed==false){     // ekteleitai an den uparxei eksodos KAI den uparxei lathos sta dedomena
                    System.out.println("Den brethike eksodos ston labyrintho");     // Emfanish antistoixou munhmatos
                }
            }
            else{                              // Ekteleitai an uparxei eksodos kai den exoun dothei lathos dedomena
                while(lisi==false && dbnl==false){     // Loop mexri na brethei lisi H na apodeixthei oti den uparxei monopati gia thn eksodo
                    if(PointStack.peek().getX()-1>=0){ //An oi suntetagmenes tou shmeiou panw apo auto pou brisketai sth korufh ths stoibas einai >=0 (entos pinaka)
                        if(pinakas[PointStack.peek().getX()-1][PointStack.peek().getY()]==0){  // An to shmeio auto periexei to 0
                            PointStack.push(new Point(PointStack.peek().getX()-1,PointStack.peek().getY()));  //Eisagwgh sth stoiba
                            pinakas[PointStack.peek().getX()][PointStack.peek().getY()]=10;// Allagh apo 0 se 10 (Deixnei oti exoume perasei apo to shmeio)
                            if(PointStack.peek().getX()==0 || PointStack.peek().getX()==gram-1 || PointStack.peek().getY()==0 || PointStack.peek().getY()==sthl-1){ //Elegxos an brethike eksodos
                                System.out.println("Brethike eksodos ston labyrintho sth thesi: GRAMMH=" + PointStack.peek().getX()+ " STHLH=" + PointStack.peek().getY());// Emfanish munhmatos
                                lisi=true;  // Enhmerwsh metablhths
                                break;
                            }
                            continue;
                        }
                    }
                    if(PointStack.peek().getX()+1<=gram-1){//An oi suntetagmenes tou shmeiou katw apo auto pou brisketai sth korufh ths stoibas einai (entos pinaka)
                        if(pinakas[PointStack.peek().getX()+1][PointStack.peek().getY()]==0){ // An to shmeio auto periexei to 0
                            PointStack.push(new Point(PointStack.peek().getX()+1,PointStack.peek().getY()));//Eisagwgh sth stoiba
                            pinakas[PointStack.peek().getX()][PointStack.peek().getY()]=10;// Allagh apo 0 se 10 (Deixnei oti exoume perasei apo to shmeio)
                            if(PointStack.peek().getX()==0 || PointStack.peek().getX()==gram-1 || PointStack.peek().getY()==0 || PointStack.peek().getY()==sthl-1){//Elegxos an brethike eksodos
                                System.out.println("Brethike eksodos ston labyrintho sth thesi: GRAMMH=" + PointStack.peek().getX()+ " STHLH=" + PointStack.peek().getY());// Emfanish munhmatos
                                lisi=true;  // Enhmerwsh metablhths
                                break;
                            }
                            continue;
                        }
                    }
                    if(PointStack.peek().getY()-1>=0){//An oi suntetagmenes tou shmeiou aristera apo auto pou brisketai sth korufh ths stoibas einai (entos pinaka)
                        if(pinakas[PointStack.peek().getX()][PointStack.peek().getY()-1]==0){// An to shmeio auto periexei to 0
                            PointStack.push(new Point(PointStack.peek().getX(),PointStack.peek().getY()-1));//Eisagwgh sth stoiba
                            pinakas[PointStack.peek().getX()][PointStack.peek().getY()]=10;// Allagh apo 0 se 10 (Deixnei oti exoume perasei apo to shmeio)
                            if(PointStack.peek().getX()==0 || PointStack.peek().getX()==gram-1 || PointStack.peek().getY()==0 || PointStack.peek().getY()==sthl-1){//Elegxos an brethike eksodos
                                System.out.println("Brethike eksodos ston labyrintho sth thesi: GRAMMH=" + PointStack.peek().getX()+ " STHLH=" + PointStack.peek().getY());// Emfanish munhmatos
                                lisi=true;  // Enhmerwsh metablhths
                                break;
                            }
                            continue;
                        }
                    }
                    if(PointStack.peek().getY()+1<=sthl-1){//An oi suntetagmenes tou shmeiou deksia apo auto pou brisketai sth korufh ths stoibas einai (entos pinaka)
                        if(pinakas[PointStack.peek().getX()][PointStack.peek().getY()+1]==0){// An to shmeio auto periexei to 0
                            PointStack.push(new Point(PointStack.peek().getX(),PointStack.peek().getY()+1));//Eisagwgh sth stoiba
                            pinakas[PointStack.peek().getX()][PointStack.peek().getY()]=10;// Allagh apo 0 se 10 (Deixnei oti exoume perasei apo to shmeio)
                            if(PointStack.peek().getX()==0 || PointStack.peek().getX()==gram-1 || PointStack.peek().getY()==0 || PointStack.peek().getY()==sthl-1){//Elegxos an brethike eksodos
                                System.out.println("Brethike eksodos ston labyrintho sth thesi: GRAMMH=" + PointStack.peek().getX()+ " STHLH=" + PointStack.peek().getY());// Emfanish munhmatos
                                lisi=true;  // Enhmerwsh metablhths
                                break;
                            }
                            continue;
                        }
                    }
                    // Ekteleitai mono an uparxei adieksodos(dhladh ta shmeia gurw apo auto pou brisketai einai eite 1 eite 10 )
                    if(PointStack.peek().getX()==x_e && PointStack.peek().getY()==y_e){//Elegxos an to stoixeio pou brisketai sth stoiba einai h eisodos
                        dbnl=true;  // Enhmrwsh oti den mporei na brethei monopati gia thn eksodo
                    }
                    pinakas[PointStack.peek().getX()][PointStack.peek().getY()]=1;  //Enhmerwsh tou pinaka me 1 sto shmeio (backtracking)
                    PointStack.pop();                                               //afairesh shmeiou apo th stoiba
                }
            }
            if(dbnl==true){ // An den uparxei monopati pros thn eksodo
            System.out.println("Den uparxei monopati gia thn/tis eksodo/ous tou labyrinthou");  //Emfanish antistoixou munhmatos
            }

        }
        if(falseDed){// An uparxoun lathos dedomena
            System.out.println("Exound dothei lathos dedomena");    //Emfanish antistoixou munhmatos
        }
    }
}