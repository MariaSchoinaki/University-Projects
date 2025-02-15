/*
	Name: Maria Schoinaki 
	Student Number: 3210191
*/

class StudentList {
	
	private Student [] myList = new Student[50];
	
	private int length = 0;

	void InsertStudent(Student newStudent){
		myList[length] = newStudent;
		length += 1;
	}//InsertStudent

	void LookupStudent(String RN) {
		int j = 0;
		for(int i=0; i<length; i++){
			if(myList[i].getRN().equals(RN)){
				System.out.println(myList[i].getGrade());
			}
			else{
				j += 1;
			}
		}
		if (j == myList.length){
			System.out.println("Studen not found");
		}
	}//LookupStudent

	void DisplayList() {
		for(int i=0; i<length; i++){
			System.out.println(myList[i]);
		}			
	}//DisplayList
	
}//StudentList	