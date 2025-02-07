/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

import java.util.Scanner;

class App1_2 {
	// Fill your code here (Factorial Method)
	public static int Factorial(int num){
		int fact = 1;
		for(int i = 1; i <= num; i++){
			fact = fact * i;
		}
		return(fact);	
	}
	public static void main(String args[]){
	// Fill your code here
	Scanner input = new Scanner(System.in);
	System.out.println("Enter a number");
	int num = input.nextInt(); 
	System.out.println(Factorial(num));
   	}

}





