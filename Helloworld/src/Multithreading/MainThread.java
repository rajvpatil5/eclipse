package Multithreading;

public class MainThread {

	public static void main(String[] args)
	{
		System.out.println("Thread name is - " +Thread.currentThread().getName());
		System.out.println("Thread priority is - "+Thread.currentThread().getPriority());
	}

}
