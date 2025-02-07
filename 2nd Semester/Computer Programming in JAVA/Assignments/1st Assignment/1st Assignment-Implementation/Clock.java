/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/


class Clock {
	
	//Data
	
	private int h;
	private int m;
	private int s;
	
	// Methods
    void setHour(int i) {
		this.h = i;
	}

	void setMin(int j) {
		this.m = j;
	}
	
	void setSec(int k) {
		this.s = k;
	}

	void tick() {
		if (s < 59){
			s++;
		}
		else{
			s = 0;
			if (m < 59){
				m++;
			}
			else{
				m = 0;
				if (h < 23){
					h++;
				}
				else{
					h = 0;
				}
			}
		} 
	}
	public String toString() {
		String first = h + ":";
		String second = m + ":";
		String third = s + "";
		if (h < 10){
			first = "0" + first;
		}
		if (m < 10){
			second = "0" + second;
		}
		if (s < 10){
			third = "0" + third;
		}
		tick();
		return(first + second + third);
	}	
}


