package Multithreading;

public class UnSynchronizedDemo {

	public static void main(String[] args) throws InterruptedException 
	{
		Shared s= new Shared();
		MyThread t1 = new MyThread(s,"Thread - 1");
		MyThread t2 = new MyThread(s,"Thread - 2");
		MyThread t3 = new MyThread(s,"Thread - 3");
		t1.start();
		t2.start();
		t3.start();
	}
}
