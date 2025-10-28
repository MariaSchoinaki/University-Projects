/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

class AMPMClock extends Clock {
	boolean time;
	int h1;
	int h2 = -1;
	void setAMPM(boolean yes){
		time = yes;
		h1 = super.h;
		if(time){
			if(h1 >= 12){
				super.h -= 12;
			}
			h2 = h1;
		}	
		else{
			if(h2 >= 12){
				super.h += 12;
				h1 = super.h;
			}	
		}
	}

	public String toString() {
		if(time){
			if(h1 < 12){
				return(super.toString() + " am");
			}
			else{
				return(super.toString() + " pm");
			}
		}
		else{
			return(super.toString());
		}
	}	
}
