package Multithreading;

public class SetPriorityB extends Thread
{
	public void run()
	{
		for(int i=0; i<15; i++)
		{
			System.out.println("class B");
		}
	}

}
