package FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileSize {

	public static void main(String[] args) throws IOException
	{
		File file = new File("G:\\Ad Astra (2019) 720p BluRay x264 Dual Audio [Hindi DD5.1 - English DD5.1] ESub - MoviePirate - Telly\\Ad Astra (2019) 720p BluRay x264 Dual Audio [Hindi DD5.1 - English DD5.1] ESub - MoviePirate - Telly.mkv");
		
		System.out.println(file.length());
		System.out.println(file.length()/1024);
		System.out.println(file.length()/(1024*1021));
		
		
	}

}
