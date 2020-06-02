package Multithreading;

public class MyThread2 implements Runnable
{
	Shared2 s;
	String name;
	MyThread2(Shared2 s,String name)
	{
		this.s=s;
		this.name=name;
		Thread t = new Thread(this);
		t.start();
	}
	public void run()
	{
		try {
			synchronized (s){
			s.justDoIt(name);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
