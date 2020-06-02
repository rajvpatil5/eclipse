package FileIO;

import java.io.File;

public class FileExists {

	public static void main(String[] args)
	{
		File f = new File("D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8"); 
		System.out.println(f.exists());
	}

}
