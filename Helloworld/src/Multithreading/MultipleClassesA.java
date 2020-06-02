package Multithreading;

public class MultipleClassesA extends Thread
{
	public void run()
	{
		for(int i=0; i<15; i++)
		{
			System.out.println("MultipleClassesA");
		}
	}

}
