package Multithreading;

public class SetPriorityC extends Thread
{
	public void run()
	{
		for(int i=0; i<15; i++)
		{
			System.out.println("class C");
		}
	}

}
