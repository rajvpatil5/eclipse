package Multithreading;

public class Thread03 {

	public static void main(String[] args) 
	{
		B b = new B();
		Thread t1 = new Thread(b);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(b);
		
		t1.start();
		t2.start();
		t3.start();
		
		for(int i=0; i<15; i++)
		{
			System.out.println(Thread.currentThread().getName());
		}
	}

}
