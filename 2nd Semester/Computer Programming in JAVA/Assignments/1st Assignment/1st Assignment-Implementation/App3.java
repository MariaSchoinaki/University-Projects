/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

import java.util.Scanner;

class App3 {

    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        System.out.println("This program reads three numbers and returns the solution" + '\n' +
        "of the quadratic equation with these numbers as coefficients");
        System.out.print("Enter the first number: ");
        float a = input.nextFloat();
        System.out.print("Enter the second number: ");
        float b = input.nextFloat();
        System.out.print("Enter the third number: ");
        float c = input.nextFloat();
        double D = b * b - 4 * a * c;
        if (D >= 0){
            double x1 = (-b + Math.sqrt(D))/(2 * a);
            double x2 = (-b - Math.sqrt(D))/(2 * a);
            System.out.printf("The first solution is : %11.3f", x1);
            System.out.printf('\n' + "The second solution is: %11.3f", x2);
        }
        else{
            System.out.print("There are no real values for the quadratic equation.");
        }
    }

}

