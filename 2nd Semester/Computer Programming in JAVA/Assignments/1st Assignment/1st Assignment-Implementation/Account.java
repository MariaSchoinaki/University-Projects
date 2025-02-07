/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

class Account {
	
	// Data
   	private final double RATE = 0.015;
	private String name;
   	private String acctNumber;
   	private double balance;
   	
	// Constructors
   	Account (String name1, String acctNumber1, double balance1) {
		this.name = name1;
		this.acctNumber = acctNumber1;
		this.balance = balance1;
   	}
	
	Account (String name2, String acctNumber2) {
		this.name = name2;
		this.acctNumber = acctNumber2;
		this.balance = 0;
   	}
	
	// Methods
   	double deposit (double amount){
		System.out.println('\n' + "Deposit @ Account " + getAccountNumber());
		System.out.println("Balance " + String.format("%.2f", getBalance()));
		System.out.println("Requested: " + amount);
		if (amount > 0){
			balance += amount;
		}
		else{
			System.out.println("Error: Deposit amount is invalid.");
		}
		System.out.println("New Balance " + String.format("%.2f", getBalance()));	
		return(this.balance);
	}

	double withdraw (double amount) {
		System.out.println('\n' + "withDraw @ Account " + getAccountNumber());
		System.out.println("Balance " + String.format("%.2f", getBalance()));
		System.out.println("Requested: " + amount);
		if (amount > 0){
			if(balance > amount){
				balance -= amount;
			}
			else{
				System.out.println("Error: Insufficient funds.");
			}
		}
		else{
			System.out.println("Error: Withdraw amount is invalid.");
		}
		System.out.println("New Balance " + String.format("%.2f", getBalance()));
		return(this.balance);
   	}

   	double addInterest (){
		balance += RATE * balance;
		return(this.balance);
   	}

   	double getBalance (){
		return(this.balance);
   	}

   	String getAccountNumber (){
		return(this.acctNumber);
   	}

   	public String toString(){
		return("Account Number: " + acctNumber + '\n' + 
		       "Name: " + name + '\n' +
			   "Balance: " + String.format("%.2f", balance));
   	}
}

