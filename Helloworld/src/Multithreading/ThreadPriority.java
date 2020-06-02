package Multithreading;

public class ThreadPriority {

	public static void main(String[] args) 
	{
		SetPriorityA a = new SetPriorityA();
		SetPriorityB b = new SetPriorityB();
		SetPriorityC c = new SetPriorityC();
		
		a.setPriority(1);
		b.setPriority(5);
		c.setPriority(10);
		
		a.start();
		b.start();
		c.start();
		System.out.println("main thread");
	}

}
//priorities depends on jvm to jvm, some platforms does not support for priorities.
