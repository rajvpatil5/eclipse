package FileIO;

import java.io.File;

public class FileOfSpecificName {

	public static void main(String[] args) 
	{
		File f = new File("D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8"); 
		String[] list=f.list();
		for(String li:list)
		{
			if(li.endsWith("xsd"))
			{
				System.out.println(li);
			}			
		}
	}
}
