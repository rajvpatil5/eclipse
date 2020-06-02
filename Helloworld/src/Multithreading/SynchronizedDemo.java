package Multithreading;

public class SynchronizedDemo {

	public static void main(String[] args) 
	{
		Shared1 s= new Shared1();
		MyThread1 t1 = new MyThread1(s,"Thread - 1");
		MyThread1 t2 = new MyThread1(s,"Thread - 2");
		MyThread1 t3 = new MyThread1(s,"Thread - 3");
		/*t1.start();
		t2.start();
		t3.start();*/
	}

}
