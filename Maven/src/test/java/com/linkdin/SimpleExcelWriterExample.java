package com.linkdin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A very simple program that writes some data to an Excel file using the Apache
 * POI library.
 * 
 * @author www.codejava.net
 *
 */
public class SimpleExcelWriterExample {
	static List<List<Object>> data = new ArrayList<List<Object>>();

	public static void setData(List<Object> name, List<Object> position, List<Object> linkdinUrl) throws IOException {
		data.add(ListDummy.firstNameMofidy(name));
		data.add(ListDummy.lastNameMofidy(name));
		data.add(position);
		data.add(linkdinUrl);
		SimpleExcelWriterExample.write();
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
		File file = new File("D:\\linedin\\Ja.xlsx");
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			workbook.write(outputStream);
			System.out.println("Printing Done");
		}
	}
}