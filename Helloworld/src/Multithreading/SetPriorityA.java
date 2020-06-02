package Multithreading;

public class SetPriorityA extends Thread
{
	public void run()
	{
		for(int i=0; i<15; i++)
		{
			System.out.println("class A");
		}
	}

}
