package Multithreading;

public class MyThread1 extends Thread
{
	String name;
	Shared1 s;
	public MyThread1(Shared1 s,String name)
	{
		this.name=name;
		this.s=s;
		start();
	}
	public void run()
	{
		try {
			s.justDoIt(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
