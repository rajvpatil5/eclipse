package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ThreeLinesofFile {
	
	public static void main(String[] args) throws IOException
	{
		FileReader file = new FileReader("D:\\JAVA\\B\\extractionOutput.txt");
		BufferedReader br = new BufferedReader(file);
		String text=br.readLine();
		for(int i=0; i<3; i++)
		{
			System.out.println(text);
			text=br.readLine();
		}

	}

}
