/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

import java.util.Scanner;

class StudentApp {

	public static void main (String args[]) {
		
		StudentList Lesson = new StudentList();

		for (;;) {
			System.out.println ("1. Insert Student");
			System.out.println ("2. Lookup Student");
			System.out.println ("3. Display List");
			System.out.println ("0. Exit");
			Scanner input = new Scanner(System.in);
			String op = input.nextLine();
			int op1 = Integer.parseInt(op);
			if(op1 == 1){
				System.out.println("Enter student name ");
				String name = input.nextLine();
				System.out.println("Enter register number ");
				String rn = input.nextLine();
				System.out.println("Enter student's grade ");
				int grade = input.nextInt();
				Student in1 = new Student(name, rn, grade);
				Lesson.InsertStudent(in1);	
			}
			else if(op1 == 2){
				System.out.println("Enter register number ");
				String rn = input.nextLine();
				Lesson.LookupStudent(rn);
			}
			else if(op1 == 3){
				Lesson.DisplayList();
			}
			else{
				break;
			}
		}// for
		
	} //main
	
}//StudentApp
