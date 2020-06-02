
public class RunInstance implements Run1{

	public RunInstance(Instance i) 
	{
		
	}

	public static void main(String[] args) 
	{
		Instance i = new Instance();
		System.out.println(i.num);
		i.m1();
		i.m2();
		RunInstance r = new RunInstance(i);
		r.run();
		i.run();
		
		
		
	}
	@override
	public void run() 
	{
		System.out.println("runinstance run method");
	}
}