package FileIO;

import java.io.File;
import java.io.IOException;

public class File1 {

	public static void main(String[] args) throws IOException 
	{
		for(int i=0; i<10; i++)
		{
		
		File f = new File("D:\\JAVA\\A\\abc"+i+".txt");
		f.createNewFile();
		System.out.println(f.exists());
		System.out.println(f.delete());
		}
	}

}
