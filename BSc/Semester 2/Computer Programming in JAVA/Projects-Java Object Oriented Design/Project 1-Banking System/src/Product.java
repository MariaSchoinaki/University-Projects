/*Arithmos Omadas: 52
  Student numbers: 3210183
                   3210188
                   3210191
*/                   
import java.util.*;
public class Product{
    protected int code_pr;                                                     // O kwdikos tou proiontos
    protected int arithmos;                                                    // O arithmos tou pelath
    protected String AFM;                                                      // To afm tou pelath
    Product(int code, int arithmos, String AFM){                               // O Constructor ths Product me eisodo ton kwdiko tou proiontos,
        this.code_pr = code;                                                   // ton arithmo kai to afm tou pelath
        this.arithmos = arithmos;
        this.AFM = AFM;
    }
    int getCode(){                                                            // H methodos getCode epistrefei ton kwdiko tou proiontos (int)
        return code_pr;
    }
    int getArithmos(){                                                       // H methodos getArithmos epistrefeo ton arithmo tou pelath (int)
        return arithmos;
    }
    String getAfm(){                                                        // H methodos getAfm epistrefei to afm tou pelath (String)
        return AFM;
    }

    public String toString(){
        return "Kwdikos proiontos: " + code_pr + "   Arithmos Pelath: " + arithmos + "   Afm pelath: " + AFM;
    }

}

