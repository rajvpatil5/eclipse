package Jy.Youtube;

import java.io.File;

public class DeleteFiles {

	public static void main(String[] args) {
		String path = "Recycle Bin";
		File file = new File(path);
		File[] files = file.listFiles();
		System.out.println(files[0].toString());
		for (File f : files) {
			if (f.isFile() && f.exists()) {
				f.delete();
				System.out.println("successfully deleted");
			} else {
				System.out.println("cant delete a file due to open or error");
			}
		}

	}

}
