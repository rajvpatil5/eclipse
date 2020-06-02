package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AlternateLineFileMerger {

	public static void main(String[] args) throws IOException 
	{
		PrintWriter pw = new PrintWriter("D:\\JAVA\\A\\alternateLineAlphNum.txt");
		
		
		FileReader fr1 = new FileReader("D:\\JAVA\\A\\alphabets.txt");
		BufferedReader br1 = new BufferedReader(fr1);
		FileReader fr = new FileReader("D:\\JAVA\\A\\numbers.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String line1=br1.readLine();
		String line=br.readLine();
		while(line1!=null || line!=null)
		{
			pw.println(line1);
			line1=br1.readLine();
			pw.println(line);			
			line=br.readLine();			
		}			
		pw.flush();
		br1.close();
		br.close();
		pw.close();
	}

}
