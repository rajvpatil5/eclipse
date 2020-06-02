package Multithreading;

public class Thread04 
{

	public static void main(String[] args) 
	{
		MultipleClassesA a = new MultipleClassesA();
		MultipleClassesB b = new MultipleClassesB();
		MultipleClassesC c = new MultipleClassesC();
		
		a.start();
		b.start();
		c.start();
		
		for(int i=0; i<15; i++)
		{
			System.out.println("main");
		}
		
	}
}