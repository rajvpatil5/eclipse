package FileIO;

import java.io.File;

public class IsFileIsDirectory {

	public static void main(String[] args)
	{
		File f = new File("D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\meta.xml"); 
		File f1 = new File("D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8"); 
		if(f.isFile())
		{
			System.out.println("This is file");
		}
		else
		{
			System.out.println("Cant Say");
		}
		if(f1.isDirectory())
		{
			System.out.println("This is directory");
		}
		else
		{
			System.out.println("Cant Say");
		}
	}

}
