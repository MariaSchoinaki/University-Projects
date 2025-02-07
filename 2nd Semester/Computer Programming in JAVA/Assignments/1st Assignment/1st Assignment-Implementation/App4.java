/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

class App4 {
	
	public static void main(String args[]){
		// Fill your code here
		int x = Integer.parseInt(args[0]);
		int last = 1;
		int first = 0;
		if (x < 0){
			System.out.print(x + "is not a fibonacci number");
		}
		else{
			if (x == 0){
				System.out.print("0 is a fibonacci number");
			}
			else{
				for(int i = 1; i <= x; i++){
					if (last + first == i){
						System.out.println("Fibonacci number = " + i);
						first = last;
						last = i;
					}
				}
				if (last == x){
					System.out.print(x + " is a fibonacci number");
				}
				else{
					System.out.println("Fibonacci number = " + (first + last));
					System.out.print(x + " is not a fibonacci number");
				}		
			}
		}		
	}
}

