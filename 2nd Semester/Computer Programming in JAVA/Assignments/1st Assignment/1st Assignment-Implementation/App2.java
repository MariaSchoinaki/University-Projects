/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

import java.util.Scanner;

class App2 {
	
	public static void main(String args[]){
		// Fill your code here
		int num;
		int Items = 0;
		int Sum = 0;
		int Negative = 0;
		int Positive = 0;
		int Max = -10000000;
		int Min = 10000000;
		do{
			Scanner input = new Scanner(System.in);
			System.out.print("Give a number: ");
			num = input.nextInt();
			if (num != 0){
				Items += 1;
				Sum += num;
				if (num > 0) {
					Positive += 1;	
				}
				else{
					Negative += 1;
				}
				if (num > Max){
					Max = num;
				}
				if (num < Min){
					Min = num;
				}
			}
		}while(num != 0);
		System.out.printf("----------------------------------------" + '\n' +
		                  "Items   : %7d", Items);
		System.out.printf('\n' + "Average : %11.3f", (double)(Sum)/Items);
		System.out.printf('\n' + "Negative: %7d", Negative);
		System.out.printf('\n' + "Positive: %7d", Positive);
		System.out.printf('\n' + "Max     : %7d", Max);
		System.out.printf('\n' + "Min     : %7d", Min);
		System.out.println('\n' + "----------------------------------------");
	}
}

