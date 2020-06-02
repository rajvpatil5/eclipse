
public class Mythread1 extends Thread
{
	Display d;
	String name;
	
	Mythread1(Display d, String name)
	{
		this.d=d;
		this.name=name;
	}
	
	public void run()
	{
		d.wish(name);
	}
}
