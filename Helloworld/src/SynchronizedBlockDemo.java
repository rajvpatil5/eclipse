
public class SynchronizedBlockDemo {

	public static void main(String[] args) 
	{
		Display2 d = new Display2();
		Mythread2 t1 = new Mythread2(d, "Dhoni");
		Mythread2 t2 = new Mythread2(d, "Virat");
		Mythread2 t3 = new Mythread2(d, "Sachin");
		Mythread2 t4 = new Mythread2(d, "Kapil");
		Mythread2 t5 = new Mythread2(d, "Saurabh");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
