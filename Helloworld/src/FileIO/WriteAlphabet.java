package FileIO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteAlphabet {

	public static void main(String[] args) throws FileNotFoundException 
	{
		PrintWriter pw = new PrintWriter("D:\\JAVA\\A\\alphabets.txt");
		for(int i=97; i<=122; i++)
		{
			pw.println((char)i);	
		}
		for(int i=65; i<=90; i++)
		{
			pw.println((char)i);
		}				
		pw.flush();
		pw.close();				
	}

}
