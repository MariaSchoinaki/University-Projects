import java.util.*;
public class Pwlhths {
    private ArrayList <Pwlhsh> pwlhseis = new ArrayList<Pwlhsh>();                          // Array list me tis pwlhseis tou kathe pwlhth
    private int code_pol;                                                                   // O kwdikos tou pwlhth
    private String AFM;                                                                     // To afm tou pwlhth
    private String lastname;                                                                // To epitheto tou pwlhth
    private String firstname;                                                               // To onoma tou pwlhth
    private int key;                                                                        // To kleidi tou pwlhth (monadikos arithmos)
    private static int i=1;                                                                 // metablhth pou metra posa antikeimena pwlhth exoun dhmiourghthei
    Pwlhths(int code, String AFM, String lastname, String firstname){
        this.code_pol = code;                                                               // O constructor tou pwlhth me eisodous ton kwdiko,to afm,to epitheto kai
        this.AFM = AFM;                                                                     // to onoma tou pwlhth. Arxikopoiei ths metablhtes, orizei to kleidi tou
        this.lastname = lastname;                                                           // pwlhth kai auksanei ton metrhth
        this.firstname = firstname;
        key=i;
        i++;
    }
    
    int getCode(){
        return code_pol;                                                                    // H methodos getKey epistrefei ton kwdiko tou pwlhth
    }
    
    String getAfm(){
        return AFM;                                                                         // H methodos getAfm epistrefei to afm tou pwlhth
    }
    
    String getLastname(){
        return lastname;                                                                    //H methodos getLastname epistrefei to epitheto tou pwlhth
    }
   
    String getFirstName(){
        return firstname;                                                                   // H methodos getFirstName epistrefei to onoma tou pwlhth
    }
    
    void addPwlhsh(Pwlhsh p){
        pwlhseis.add(p);                                                                    //H methodos addPwlhsh prosthetei ena antikeimeno pwlhsh sto arraylist me
    }                                                                                       // tis pwlhseis tou pwlhth

    public String toString(){
        return "Kwdikos pwlhth: " + code_pol + "    Afm pwlhth: " + AFM + "    Onoma pwlhth: " + firstname + "    Epitheto pwlhth: " + lastname;
    }

    boolean isInList(int code){
        for (Pwlhsh p : pwlhseis){                                                      // H methodos isInList dexetai ws eisodo ton kwdiko enos proiontos                            
            if (p.getCodePr()==code){                                                   // kai elegxei an afto brisketai sth lista twn pwlhsewn enos pwlhth
                return true;
            }
        }
        return false;
    }

    int getKey(){
        return key;                                                                     // H methodos getKey epistrefei to kleidi tou pwlhth
    }

    ArrayList <Pwlhsh> getList(){                                                       // h methodos getList epistrefei to arraylist twn pwlhsewn enos pwlhth
        return pwlhseis;
    }

    


}

