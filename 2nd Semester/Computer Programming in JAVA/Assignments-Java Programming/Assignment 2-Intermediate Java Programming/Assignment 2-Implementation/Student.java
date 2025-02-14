/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

class Student {
	
	private String Name;
	private String RN;
	private int Grade;
	
	Student(String Name, String RN, int Grade){
		this.Name = Name;
		this.RN = RN;
		this.Grade = Grade;
	}
	public String getRN(){
		return(RN);
	}
	public int getGrade(){
		return(Grade);
	}
	public String toString(){
		return("Name: " + Name + "\n" + "RN: " + RN + "\n" + "Grade: " + Grade);
	}
}// Student
