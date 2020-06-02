
public class Test 
{

	public static void main(String[] args) throws InterruptedException 
	{
		Mythread t = new Mythread();
		t.start();
		t.interrupt();
		for(int i=0; i<10; i++)
		{
			System.out.println("Test"); 
		}
	}
}
