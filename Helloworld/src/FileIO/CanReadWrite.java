package FileIO;

import java.io.File;

public class CanReadWrite {

	public static void main(String[] args)
	{
		File f = new File("D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\meta.xml"); 
		if(f.canRead())
		{
			System.out.println("You can read to this file");
		}
		else
		{
			System.out.println("You can read to this file");
		}
		if(f.canWrite())
		{
			System.out.println("You can write to this file");
		}
		else
		{
			System.out.println("You can write to this file");
		}
	}
}
