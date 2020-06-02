
public class Sleep {

	public static void main(String[] args) 
	{
		try
		{
		for(int i=0; i<10; i++)
		{
			System.out.println("I am lazy thread - "+i);
			Thread.sleep(1000);
		}
		}
		catch(Exception e)
		{
			System.out.println("I am active thread");
		}
	}

}
