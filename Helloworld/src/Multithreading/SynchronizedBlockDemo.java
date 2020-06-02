package Multithreading;

public class SynchronizedBlockDemo {

	public static void main(String[] args)
	{
		Shared2 s = new Shared2();
		MyThread2 mt1 = new MyThread2(s,"Thread-01");
		MyThread2 mt2 = new MyThread2(s,"Thread-02");
		MyThread2 mt3 = new MyThread2(s,"Thread-03");
		MyThread2 mt4 = new MyThread2(s,"Thread-04");
	}
}
