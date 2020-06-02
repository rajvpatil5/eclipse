import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDump {
	static List<Object> column1 = new ArrayList<Object>();
	// static List<Object> column2 = new ArrayList<Object>();
	// static List<Object> column3 = new ArrayList<Object>();
	// static List<Object> column4 = new ArrayList<Object>();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		List<Object> data = new ArrayList<Object>();
		data.add(column1);
		// data.add(column2);
		// data.add(column3);
		// data.add(column4);

		File file = new File("D:\\Database\\DataDump\\writer.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int lastRowNum = sheet.getLastRowNum();

		for (int i = 0; i < lastRowNum; i++) {
			Row row = sheet.getRow(i);
			int lastColNum = row.getLastCellNum();

			for (int j = 0; j < lastColNum; j++) {
				Cell cellValue = row.getCell(j);
				String update = cellValue.toString();
				((List<Object>) data.get(j)).add(update);

			}
		}
		Connection conn = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "1");
		Statement stmt = conn.createStatement();
		for (int i = 0; i < DataDump.column1.size(); i++) {
			int count = stmt.executeUpdate("insert into test values (   '" + column1.get(i) + "' )");
			System.out.println("Number of rows updated in database =  " + count);
		}
		System.out.println(column1);
		// System.out.println(column2);
		// System.out.println(column3);
		// System.out.println(column4);

		workbook.close();
		fis.close();

	}
}
