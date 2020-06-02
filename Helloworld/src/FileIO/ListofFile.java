package FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ListofFile {

	public static void main(String[] args) throws IOException 
	{
		File f = new File("D:\\JAVA\\A\\");
		String[] listoffile=f.list();
		System.out.println(listoffile[0]);
		BufferedReader br =null;
		PrintWriter pw = new PrintWriter("D:\\JAVA\\B\\output");
		
		for(int i=0; i<listoffile.length; i++)
		{
			FileReader fr = new FileReader("D:\\JAVA\\A\\"+listoffile[i]);
			br = new BufferedReader(fr);
			String text=br.readLine();
			while(text!=null) 
			{
				System.out.println(text);
				text=br.readLine();
				pw.println(text);
			}
		}
		
		
		pw.flush();
		br.close();
		pw.close();
	}
}
