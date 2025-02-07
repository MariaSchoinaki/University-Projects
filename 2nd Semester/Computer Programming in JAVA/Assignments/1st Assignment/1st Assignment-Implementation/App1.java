/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

import java.util.Scanner;

class App1 {
    // Fill your code here (Factorial Method)
	int fact = 1;
	public void Factorial(int num){
		for(int i = 1; i <= num; i++){
			this.fact = fact * i;
		}
	}
	
	public static void main(String args[]){
		// Fill your code here
		int num;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a number");
		num = input.nextInt();
		App1 out = new App1();
		out.Factorial(num);
		System.out.println(out.fact);
   	}

}

