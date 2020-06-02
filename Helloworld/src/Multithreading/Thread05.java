package Multithreading;

public class Thread05 
{
	public static void main(String[] args) throws InterruptedException 
	{
		UseSleepDemo u = new UseSleepDemo();
		u.newThread();
		for(int i=0; i<10; i++)
		{
			System.out.println("main thread"+i);
			Thread.sleep(500);			
		}
	}
}
