
public class Threadname {

	public static void main(String[] args) 
	{
		System.out.println(Thread.currentThread().getName());
		Mythread t = new Mythread();
		System.out.println(t.getName());
	}

}
