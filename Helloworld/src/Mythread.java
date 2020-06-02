public class Mythread extends Thread
{
	public void run()
	{
		for(int i=0; i<10; i++)
		{
			System.out.println("myThread");
			try 
				{
					Thread.sleep(1000);
				}
			catch(Exception e)
			{
				System.out.println("I got interrupted");
			}
		}
		
	}	
}
