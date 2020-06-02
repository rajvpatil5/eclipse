package Multithreading;

public class Thread02 {

	public static void main(String[] args)
	{
		new YieldDemo();
		for (int i=0; i<20; i++)
		{
			System.out.println("main thread");
		}
	}

}
