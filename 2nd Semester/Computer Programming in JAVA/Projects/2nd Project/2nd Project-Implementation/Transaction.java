public class Transaction {
    int code_pr;                                                // O kwdikos tou proiontos
    double aksia;                                               // H aksia ths kinhshs
    String aitiologia;                                          // h aitiologia ths kinhshs
    Transaction(int code, double aksia, String aitiologia){     
        this.code_pr = code;                                    // O constructor tou Transaction o opoios dexetai san eisodo ton kwdiko, thn aksia kai thn aitiologia
        this.aksia = aksia;                                     // kai arxikopoiei ths metablhtes
        this.aitiologia = aitiologia;
    }
    int getCode(){
        return code_pr;                                         // H methodos getCode epistrefei ton kwdiko tou proiontos
    }       

    double getAksia(){  
        return aksia;                                           //H methodos getAksia epistrefei thn aksia ths kinhshs
    }  
    
    String getAitiologia(){                                     //H methodos getAitiologia epistrefei thn aitiologia ths kinhshs
        return aitiologia;
    }

    public String toString(){
        return String.format("Kwdikos proiontos: %d Aksia kinhshs: %.2f Aitiologia: %s ",code_pr,aksia,aitiologia);
    }
}
