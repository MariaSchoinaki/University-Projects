/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

import java.util.concurrent.TimeUnit;

class AMPMClockApp {
	
	public static void main (String args[]) throws Exception{
		AMPMClock clock = new AMPMClock();
		clock.setHour(23);
		clock.setMin(59);
		clock.setSec(58);
		clock.setAMPM(true);
		for (int i = 1; i <= 181; i++){
			System.out.println(clock);
			clock.tick();
			TimeUnit.SECONDS.sleep(1); 
		}
	}
}



