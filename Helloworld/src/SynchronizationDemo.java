
public class SynchronizationDemo {

	public static void main(String[] args) 
	{
		Display d = new Display();
		Mythread1 t = new Mythread1(d, "Dhoni");
		Mythread1 t1 = new Mythread1(d, "Virat");
		Mythread1 t2 = new Mythread1(d, "Sachin");
		Mythread1 t3 = new Mythread1(d, "Kapil");
		Mythread1 t4 = new Mythread1(d, "Saurabh");
		t.start();
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
