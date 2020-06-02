package Multithreading;

public class JoinIsAlive implements Runnable
{
	String name;
	Thread t;
	JoinIsAlive(String name)
	{
		t=new Thread(this,name);
		t.start();
	}
	public void run()
	{
		for(int i=0; i<15; i++)
		{
			System.out.println(name + " = " + i);
			try 
				{
					Thread.sleep(500);
				}
			catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
		}
	}
}
