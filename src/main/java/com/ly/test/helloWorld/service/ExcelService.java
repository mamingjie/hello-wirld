package com.ly.test.helloWorld.service;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

	public static String filePath = "/Users/mamingjie/Documents/";
	
	private static Workbook workbook;
	private static int sheetCount = 0;
	private static int sheetComplete = 0;
	
	public void saveFile() {
		workbook = new XSSFWorkbook();
		sheetCount = 4;
		sheetComplete = 0;
		for (int i=0; i < sheetCount; i++) {
			Sheet sheet = workbook.createSheet("sheet" + (i+1));
			XlsxProcessor processor = new XlsxProcessor(sheet);
			Thread thread = new Thread(processor);
			thread.start();
		}
	}
	
	public static void onSheetComplete(Sheet sheet) {
		sheetComplete++;
		if (sheetCount == sheetComplete) {
			try {
				FileOutputStream fos = new FileOutputStream(filePath + "0000.xlsx");
				workbook.write(fos);
				fos.close();
				workbook.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
