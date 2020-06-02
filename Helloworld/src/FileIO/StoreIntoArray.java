package FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoreIntoArray {

	public static void main(String[] args) throws IOException 
	{
		FileReader file = new FileReader("D:\\JAVA\\A\\badminton.txt");
		BufferedReader br = new BufferedReader(file);
		ArrayList<String> li = new ArrayList<String>();
		String text=br.readLine();
		while(text!=null)
		{
			li.add(text);
			text=br.readLine();
		}
		System.out.println(li);
	}
		

}
