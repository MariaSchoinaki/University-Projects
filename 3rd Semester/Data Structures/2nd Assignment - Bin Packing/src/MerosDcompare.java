public class MerosDcompare {
    
    // MerosDcompare.java : Xrhsimopoieitai gia th sugkrish twn duo algorithmwn (Greedy - GreedyDecreasing) 

    public static void main(String []args){
        String arg[] = new String[10];                          // Dhmiourgoume ena array me strings
        String fakelos = "n1000/";   // Apothikeuoume to onoma tou fakelou pou exoume apothikeusei ta txt arxeia
        // (p.x. n1000 : periexei ta 10 txt arxeia me tous 1000 fakelous) (Antistoixa n500 - n100)
        String arxeio = "input";    // To onoma tou txt arxeiou (Sth periptwsh mas input1.txt, input2.txt, ... , input10.txt)
        for(int i = 0; i < 10; i++){
            arg[i] = fakelos + arxeio + (i+1) + ".txt"; // Ston pinaka arg eisagontai ta onomata twn 10 arxeiwn mazi me to fakelo ston opoio
            // periexontai
        }
        for(int i = 0; i<10; i++){          // Gia kathe ena apo ta deka arxeia
            Greedy.main(arg);               // Trexoume th Greedy   (H greedy kserei pio arxeio na treksei logo ths static metablhths iter)
            MerosDgreedydecreasing.main(arg); //Trexoume th MerosDgreedydecreasing (GreedyDecreasing)
            //(H MerosDgreedydecreasing (GreedyDecreasing) kserei pio arxeio na treksei logo ths static metablhths iter)
            // H kathe methodos ektupwnei ta apotelesmata gia kathe arxeio ksexwrista
        }
        System.out.println("--------------- Final Reports ---------------");    // Telos tuponontai ta telika reports
        System.out.println("Size of all folders : " + (double)Greedy.s_total_size/1000000 + "TB");  // Sunoliko megethos olwn twn fakelwn se TB
        System.out.println("Greedy.java used: " + Greedy.s_total_disk + " disks");//Tuponetai o sunolikos arithmwn twn diskwn pou xrhsimopoihse h Greedy
        System.out.println("Sort.java(Greedy-decreasing) used: " + MerosDgreedydecreasing.s_total_disk + " disks");
        //Tuponetai o sunolikos arithmwn twn diskwn pou xrhsimopoihse h Greedy-Decreasing
        System.out.println("M.O. Diskwn Greedy: " + (double)Greedy.s_total_disk/10);
        //Tuponetai o m.o. arithmwn twn diskwn pou xrhsimopoihse h Greedy
        System.out.println("M.O. Diskwn Sort(Greedy-Decreasing): " + (double)MerosDgreedydecreasing.s_total_disk/10);
        //Tuponetai o m.o. arithmwn twn diskwn pou xrhsimopoihse h Greedy-Decreasing
    }
}
