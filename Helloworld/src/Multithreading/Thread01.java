package Multithreading;

public class Thread01 {

	public static void main(String[] args) 
	{
		A a1 = new A("bigdata");
		A a2 = new A("darkdata");
		A a3 = new A("AI");
		
		a1.start();
		a2.start();
		a3.start();
		
		System.out.println("main thread");
	}

}
