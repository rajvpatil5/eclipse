package Multithreading;

public class CurrentThread {

	public static void main(String[] args) 
	{
		Thread t = Thread.currentThread();
		System.out.println("Default name of Thread is - "+t);
		
		t.setName("MyNameIsMainThread");
		System.out.println("After changing name of thread - "+t);
	}

}
