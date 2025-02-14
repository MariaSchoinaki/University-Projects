public class Pwlhsh {
    private int code_pr;                               // O kodikos tou proiontos
    private int code_pol;                              // O kodikos tou polhth
    private String aitiologia;                         // H aitiologia ths pwlhshs
    Pwlhsh(int code, int code_pol, String aitiologia){
        this.code_pr = code;                                          // O constructor ths Pwlhshs o opoios dextai san eisodo ton kodiko tou proiontos,
        this.code_pol = code_pol;                                     // ton kodiko tou pwlhth kai thn aitiologia ths pwlhshs kai arxikopoiei tis antistoixes metablhtes
        this.aitiologia = aitiologia;
    } 

    int getCodePr(){                                                // H methodos getCodePr epistrefei ton kwdiko tou proiontos
        return code_pr;                                             
    }
    
    int getCodePol(){                                               // H methodos getCodePol epistrefei ton kodiko tou pwlhth
        return code_pol;
    }

    String getAitiologia(){
        return aitiologia;                                         // h methodos getAitiologia epistrefei thn aitiologia ths pwlhshs
    }

    
}