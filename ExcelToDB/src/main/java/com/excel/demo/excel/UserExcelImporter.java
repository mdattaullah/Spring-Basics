package com.excel.demo.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.excel.demo.model.Student;


public class UserExcelImporter 
{
	InputStream inputStream;
	public UserExcelImporter(MultipartFile file) {
		try {
			this.inputStream=file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Student> excelImport(){
		List<Student> listEmployee=new ArrayList<>();
		int id=0;
		String name="";
		int age=0;
		
		//String excelFilePath="src/main/resources/file1.xlsx";
		
		//FileInputStream inputStream;
		try {
			//inputStream = new FileInputStream(excelFilePath);
			Workbook workbook=new XSSFWorkbook(inputStream);
			Sheet firstSheet=workbook.getSheetAt(0);
			Iterator<Row> rowIterator=firstSheet.iterator();
			rowIterator.next();
			
			while(rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator=nextRow.cellIterator();
				while(cellIterator.hasNext()) {
					Cell nextCell=cellIterator.next();
					int columnIndex=nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						id=(int)nextCell.getNumericCellValue();
						break;
					case 1:
						name=nextCell.getStringCellValue();
					
						break;
					case 2:
						age=(int)nextCell.getNumericCellValue();
						break;
					
					}
					listEmployee.add(new Student(id, name, age));
				}
			}
			
			workbook.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		return listEmployee;
	}
}
