package FileIO;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileMerger {

	public static void main(String[] args) throws IOException 
	{
		PrintWriter pw = new PrintWriter("D:\\JAVA\\A\\mergeAlphNum.txt");
		
		
		FileReader fr1 = new FileReader("D:\\JAVA\\A\\alphabets.txt");
		BufferedReader br1 = new BufferedReader(fr1);
		String line1=br1.readLine();
		while(line1!=null)
		{
			pw.println(line1);			
			line1=br1.readLine();			
		}
		pw.println("===============================");
		FileReader fr = new FileReader("D:\\JAVA\\A\\numbers.txt");
		br1 = new BufferedReader(fr);
		String line=br1.readLine();
		while(line!=null)
		{
			pw.println(line);
			line=br1.readLine();
				
		}		
		pw.flush();
		br1.close();
		pw.close();
	}

}
