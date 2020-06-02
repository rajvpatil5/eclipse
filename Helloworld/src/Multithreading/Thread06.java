package Multithreading;

public class Thread06 {

	public static void main(String[] args) throws InterruptedException
	{
		JoinIsAlive j = new JoinIsAlive("hello");
		j.t.join();
		for(int i=0; i<15; i++)
		{
			System.out.println(Thread.currentThread().getName()+" - "+i);
		}
	}

}
