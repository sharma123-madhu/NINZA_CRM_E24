package com.ninza.crm.generic.fileulity;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
public String toReadTheDataFromExcel(String sheet,int rowNum, int CellNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fs=new FileInputStream("./src/main/resources/exceldata.xlsx");
		Workbook wb=WorkbookFactory.create(fs);
		String data = wb.getSheet(sheet).getRow(rowNum).getCell(CellNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	public int ToGetTheRowCount(String Sheet) throws EncryptedDocumentException, IOException {
		FileInputStream fs=new FileInputStream("./src/main/resources/exceldata.xlsx");
		Workbook wb=WorkbookFactory.create(fs);
		int  lastRowNum = wb.getSheet(Sheet).getLastRowNum();
		return lastRowNum;
		
		
	}
	

 
public String ToReadTheMultipleDataFromExcel(String sheet, int rowNum, int CellNum) throws EncryptedDocumentException, IOException {

	FileInputStream fs=new FileInputStream("./src/main/resources/exceldata.xlsx");
	Workbook wb=WorkbookFactory.create(fs);
	

	int lastrowNum = 1;
	for (int i = 1; i <=lastrowNum; i++) {
		String data = wb.getSheet(sheet).getRow(rowNum).getCell(CellNum).getStringCellValue();
		
		
	return data;
	
	}
	return sheet;
}
	


}
