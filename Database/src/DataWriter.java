import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataWriter {
	static List<List<Object>> data = new ArrayList<List<Object>>();

	public static void setData(List<Object> column1, List<Object> column2)
			throws IOException {
		data.add(column1);
		data.add(column2);
	//	data.add(column3);
	//	data.add(column4);

		DataWriter.write();
	}

	public static void write() throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		int rowCount = 0;
		for (int i = 0; i < data.get(0).size(); i++) {
			Row row = sheet.createRow(rowCount++);
			System.out.println(rowCount);
			int columnCount = 0;
			for (int j = 0; j < data.size(); j++) {
				Cell cell = row.createCell(columnCount++);
				cell.setCellValue(data.get(j).get(i).toString());
				System.out.println(data.get(j).get(i));
			}
		}
		File file = new File("D:\\Database\\DataDump\\writer.xlsx");
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			workbook.write(outputStream);
			System.out.println("Printing Done");
			workbook.close();
		}
	}

}
