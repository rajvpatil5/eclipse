package Multithreading;

public class B implements Runnable
{
	public void run()
	{
		for (int i=0; i<15; i++)
		{
			System.out.println(Thread.currentThread().getName());
		}
	}

}
