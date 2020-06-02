package Multithreading;

public class UseSleepDemo implements Runnable
{
	public void newThread()
	{
		Thread t = new Thread(this,"Child Thread");
		t.start();
	}
	public void run()
	{
		for(int i=0; i<10; i++)
		{
			System.out.println("child thread"+i);
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
