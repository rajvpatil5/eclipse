package FileIO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LongestWord {

	public static void main(String[] args) throws FileNotFoundException
	{
		FileReader file = new FileReader("D:\\JAVA\\A\\badminton.txt");
		Scanner scan = new Scanner(file);
		String currentWord = scan.next();
		String longestWord="";
		while(scan.hasNext())
		{
			if(currentWord.length()>longestWord.length())
			{
				longestWord=currentWord;
			}
			currentWord = scan.next();
		}
		System.out.println(longestWord);
	}
}
