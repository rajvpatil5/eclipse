package Multithreading;

public class MyThread3 extends Thread
{
	int total=1;
	public void run()
	{
		for(int i=1; i<30; i++)
		{
			total=total*2;
		}
		synchronized(this)
		{
			this.notifyAll();
		}
	}
}