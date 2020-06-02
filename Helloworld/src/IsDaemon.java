
public class IsDaemon 
{
	static 
	{
		Thread.currentThread().setDaemon(true);
	}
	public static void main(String[] args) 
	{
	}

}


/*


As we can not change main thread to non daemon thread this program throws, 
Exception in thread "main" java.lang.ExceptionInInitializerError
Caused by: java.lang.IllegalThreadStateException

ExceptionInInitializerError - While executing, the static block, static variable initialization, if any exception comes then it is ExceptionInInitializerError.

*/