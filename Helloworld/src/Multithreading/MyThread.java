package Multithreading;

public class MyThread extends Thread
{
	String name;
	Shared s;
	public MyThread(Shared s,String name)
	{
		this.name=name;
		this.s=s;
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

