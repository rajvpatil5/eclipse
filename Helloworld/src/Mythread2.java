public class Mythread2 extends Thread
{
	Display2 d;
	String name;
	
	Mythread2(Display2 d, String name)
	{
		this.d=d;
		this.name=name;
	}
	
	public void run()
	{
		d.wish(name);
	}
}
