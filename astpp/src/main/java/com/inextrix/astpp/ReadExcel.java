package com.inextrix.astpp;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static List<String> read() {
		List<String> list = new ArrayList<String>();

		try {

			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\resources\\com\\inextrix\\astpp\\files\\samplefile1.xlsx"));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			int cols = 0; // No of columns
			int tmp = 0;

			// This trick ensures that we get the data properly even if it doesn't start
			// from first few rows
			for (int i = 0; i < 10 || i < rows; i++) {
				row = sheet.getRow(i);
				if (row != null) {
					tmp = sheet.getRow(i).getPhysicalNumberOfCells();
					if (tmp > cols)
						cols = tmp;
				}
			}

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					for (int c = 0; c < 4; c++) {
						cell = row.getCell((short) c);
						if (cell != null) {
							// System.out.println(cell.toString());
							list.add(cell.toString());
						}
					}
				}
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return list;
	}
}
