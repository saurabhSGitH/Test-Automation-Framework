package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName)  {

		// to read XLSX file use XSSF workbook

		File xlsxFile = new File(System.getProperty("user.dir") + "//testData//LoginData.xlsx");
		XSSFWorkbook xssfWorkbook = null;
		List<User> userList = null;
		XSSFSheet xssfSheet;
		Iterator<Row> rowIterator;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			
			userList = new ArrayList<User>();
			
			xssfSheet = xssfWorkbook.getSheet("LoginTestData");
			rowIterator = xssfSheet.iterator();
			rowIterator.next();//skipping the col name!!
			Row row;
			User user;
			
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				Cell EmailAddressCell = row.getCell(0);
				Cell PasswordCell = row.getCell(1);

				user = new User(EmailAddressCell.toString(), PasswordCell.toString());
				userList.add(user);
				xssfWorkbook.close();

			}
			} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//D:\NewEclipseWorkspace\RestAssured\StudentManagementSystem\automation-assignment\testData\LoginData.xlsx
		return userList.iterator();
}
}
