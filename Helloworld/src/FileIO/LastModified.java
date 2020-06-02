package FileIO;

import java.io.File;
import java.util.Date;

public class LastModified {

	public static void main(String[] args) 
	{
		File f = new File("D:\\JAVA\\A\\football.txt");
		long date=f.lastModified();
		System.out.println(date);
		Date d = new Date(date);
		System.out.println(d);

	}

}
