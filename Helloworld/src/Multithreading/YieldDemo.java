package Multithreading;

public class YieldDemo implements Runnable
{
	YieldDemo()
	{
		Thread t = new Thread(this);
		t.start();
	}
	public void run()
	{
		for (int i=0; i<10; i++)
		{
			System.out.println("Child thread");
			Thread.yield();
		}
	}
}
