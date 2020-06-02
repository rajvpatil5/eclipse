package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileExtraction {

	public static void main(String[] args) throws IOException 
	{
		FileReader fr = new FileReader("D:\\JAVA\\A\\extractionInput.txt");
		PrintWriter pw = new PrintWriter("D:\\JAVA\\B\\extractionOutput.txt");
		FileReader fr1 = null;
		BufferedReader br = new BufferedReader(fr);
		BufferedReader br1 = null;
		String text = br.readLine();
		
		while(text!=null)
		{
			
			Boolean avail=false;
			fr1 = new FileReader("D:\\JAVA\\A\\extractionData.txt");
			br1 = new BufferedReader(fr1);
			String text1 = br1.readLine();			
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
				System.out.println(text);
				pw.println(text);
			}
				text=br.readLine();
			
			}
		pw.flush();
		pw.close();
		fr.close();
		fr1.close();
		
		
	}

}
