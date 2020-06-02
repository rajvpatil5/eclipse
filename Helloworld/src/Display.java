public class Display 
{
	public void wish(String name)
	{
		for(int i=0; i<10; i++)
		{
			System.out.print("Good morning: ");
			System.out.println(name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}			
		}
	}

}
