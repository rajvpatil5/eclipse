package FileIO;

import java.io.File;

public class NamesOfFiles {

	public static void main(String[] args) 
	{
		File f = new File("G:\\Java\\Collection"); 
		String[] list=f.list();
		for(String li:list)
		{
			System.out.println(li);
		}
	}
}
