package FileIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteNumber 
{

	public static void main(String[] args) throws IOException 
	{
		PrintWriter pw = new PrintWriter("D:\\JAVA\\A\\numbers.txt");
		for(int i=1; i<=100; i++)
		{
			pw.println(i);
		}
		pw.flush();
		pw.close();
		
		
		
	}
}
