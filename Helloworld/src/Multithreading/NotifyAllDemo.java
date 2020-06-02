package Multithreading;

public class NotifyAllDemo 
{
	
	public static void main(String[] args) throws InterruptedException
	{
		
		MyThread3 m = new MyThread3();
		m.start();
		Thread.sleep(10);
		synchronized(m)
		{
			m.wait(100);
		}
		System.out.println(m.total);
	}
}
