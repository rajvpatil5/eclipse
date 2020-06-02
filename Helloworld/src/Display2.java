
public class Display2 
{
	public void wish(String name)
	{
		for(int i=0; i<10; i++)
		{
			System.out.println(i+" "+Thread.currentThread().getName());
		}
		synchronized (this)
		{
		for(int i=0; i<10; i++)
		{
			System.out.print("Good morning: ");
			System.out.println(name+" "+Thread.currentThread().getName());			
			}
		}
		for(int i=0; i<10; i++)
		{
			System.out.println("Hi "+Thread.currentThread().getName());
		}
	}
}