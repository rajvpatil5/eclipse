package FileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DuplicateElimination 
{
	public static void main(String[] args) throws IOException 
	{		
		FileReader fr = new FileReader("D:\\JAVA\\A\\duplicate.txt");
		PrintWriter pw = new PrintWriter("D:\\JAVA\\A\\originalDuplicate.txt");
		BufferedReader br = new BufferedReader(fr);
		String text=br.readLine();
		while(text!=null)
		{
			Boolean avail=false;
			FileReader fr1 = new FileReader("D:\\JAVA\\A\\originalDuplicate.txt");
			BufferedReader br1 = new BufferedReader(fr1);
			String text1=br1.readLine();
			while(text1!=null)
			{				
				if(text.equals(text1))
				{
					avail=true;					
					break;
				}
				text1=br1.readLine();
			}
			if(avail==false)
			{
				pw.println(text);
				pw.flush();
			}			
			text=br.readLine();
		}
		
		pw.close();
		br.close();
	}
}
