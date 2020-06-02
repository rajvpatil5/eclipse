package Multithreading;

public class A extends Thread
{
	public A(String s)
	{
		super(s);
	}

	public void run()
	{
		for(int i=0; i<15; i++)
		{
			System.out.println(getName());
		}
	}
}
