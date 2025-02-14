/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/


public class bankApp  {

	public static void main(String args[]) {

    	Account acct1 = new Account("Togantzi Maria", "100-00", 188.46);
    	Account acct2 = new Account("Kalergis Christos", "100-01", 140.21);
    	Account acct3 = new Account("Maras Petros", "100-02", 0.00);

		System.out.println("New accounts");
		
		System.out.println('\n' + acct1.toString());
		System.out.println('\n' + acct2.toString());
		System.out.println('\n' + acct3.toString());
		
		//Deposit to 100-00
		acct1.deposit(-10.0);
		//Deposit to 100-01
		acct2.deposit(500.1);
		//Withdraw to 100-02
		acct3.withdraw(1420.75);
		//Withdraw to 100-02
		acct3.withdraw(-10.0);
		//Withdraw to 100-02
		acct3.withdraw(420.75);
		//Rate
		System.out.println ("\nadd interest ... ");
		
   		acct1.addInterest();
   		acct2.addInterest();
   		acct3.addInterest();

		System.out.println('\n' + acct1.toString());
		System.out.println('\n' + acct2.toString());
		System.out.println('\n' + acct3.toString());

   }

}

