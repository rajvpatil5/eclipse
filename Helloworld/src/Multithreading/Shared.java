package Multithreading;

public class Shared 
{
	public void justDoIt(String name) throws InterruptedException
	{
		for(int i=0; i<10; i++)
		{
			System.out.println(name);
			Thread.sleep(500);
		}
	}
}
