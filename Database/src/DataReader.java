import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

	static List<Object> column1 = new ArrayList<Object>();
	static List<Object> column2 = new ArrayList<Object>();
	static List<Object> column3 = new ArrayList<Object>();
	static List<Object> column4 = new ArrayList<Object>();

	public static void main(String[] args) throws Exception {

		File file = new File("D:\\Database\\DataDump\\dump.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int lastRowNum = sheet.getLastRowNum();

		for (int i = 0; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			int lastColNum = row.getLastCellNum();

			for (int j = 0; j < lastColNum; j++) {
				Cell cellValue = row.getCell(j);
				String update = cellValue.toString().trim().replaceAll(" +", " ");
				System.out.println(update);
				String[] arr = update.split(" ");

				for (int k = 0; k < 1; k++) {
					column1.add(arr[0]);
					column2.add(arr[1]);
					column3.add(arr[2]);
					column4.add(arr[3]);

				}
			}
		}
		System.out.println(column1);
		System.out.println(column2);
		System.out.println(column3);
		System.out.println(column4);

		DataWriter.setData(column1, column2, column3, column4);
		workbook.close();
		fis.close();

	}
}
