package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Lexicographically {

	public static void main(String[] args) throws IOException 
	{
		FileReader f = new FileReader("D:\\JAVA\\A\\football.txt"); 
		BufferedReader br = new BufferedReader(f);
		FileReader f1 = new FileReader("D:\\JAVA\\A\\formula one.txt"); 
		BufferedReader br1 = new BufferedReader(f1);
		String text=br.readLine();
		String text1=br1.readLine();
		while(text!=null)
		{
			System.out.println(text.compareTo(text1));
			text=br.readLine();
			text1=br1.readLine();
		}
		
		
		
	}

}
