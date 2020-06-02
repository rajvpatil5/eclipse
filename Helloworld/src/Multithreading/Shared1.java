package Multithreading;

public class Shared1
{
	public synchronized void justDoIt(String name) throws InterruptedException
	{
		for(int i=0; i<10; i++)
		{
			System.out.println(name);
			Thread.sleep(500);
		}
	}

}
