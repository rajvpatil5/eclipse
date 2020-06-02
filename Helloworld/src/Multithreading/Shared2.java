package Multithreading;

public class Shared2 
{

			
		public void justDoIt(String name) throws InterruptedException
		{
			synchronized(this) {
			for(int i=0; i<15; i++)
			{
				System.out.println(name);
				Thread.sleep(500);
			}		
		}
	}}

