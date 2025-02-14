/*
	Name: Maria Schoinaki
	Student Number: 3210191
*/

abstract class Animal {
	
	String name;
	static int animals = 0;
	
	Animal(String n) {
		name = n;
		animals++;
	}
	
	abstract void speak();
		static int numberOfAnimals() {
		return(animals++);
	}
}

class Dog extends Animal {
	static int dogs = 0;
	Dog(String name){
		super(name);
		dogs++;
	}
	String sound = "woof";
	static int numberOfDogs(){
		return(dogs++);
	}
	public void speak(){
		System.out.println(name + ": " + sound);
	}
}

class Cat extends Animal {
	static int cats = 0;
	Cat(String name){
		super(name);
		cats++;
	}	
	String sound = "miaou";
	static int numberOfCats(){
		return(cats++);
	}
	public void speak(){
		System.out.println(name + ": " + sound);
	}
}


class App4 {

	public static void main(String[] args) {
		Animal[] animal = {	new Cat("stella"), new Cat("ziggy"), new Dog("azor")};
		System.out.println("Cats: "+Cat.numberOfCats());
		System.out.println("Dogs: "+Dog.numberOfDogs());
		System.out.println("Animals: "+Animal.numberOfAnimals());
		for ( int i = 0; i < animal.length; i++ )
			animal[i].speak();
	}
}
