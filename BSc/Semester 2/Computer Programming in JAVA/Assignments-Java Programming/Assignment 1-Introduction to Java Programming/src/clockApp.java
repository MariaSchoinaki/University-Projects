/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/


import java.util.concurrent.TimeUnit;

public class clockApp {
	
	public static void main (String args[]) throws Exception{
		Clock out = new Clock();
		out.setHour(16);
		out.setMin(28);
		out.setSec(58);
		for (int i = 1; i <= 181; i++){
			System.out.println(out);
			TimeUnit.SECONDS.sleep(1); 
		}		
	}
}



