package com.ly.test.helloWorld.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class XlsxProcessor implements Runnable {

	private Sheet sheet;
	
	//public static int SHEET_MAX_LINE = 1048576;
	public static int SHEET_MAX_LINE = 104;
	
	public XlsxProcessor(Sheet sheet) {
		this.sheet = sheet;
	}
	
	@Override
	public void run() {
		for (int i=0; i < SHEET_MAX_LINE; i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue("row" + (i+1) + "cell1");
			row.createCell(1).setCellValue("row" + (i+1) + "cell2");
			row.createCell(2).setCellValue("row" + (i+1) + "cell3");
			row.createCell(3).setCellValue("row" + (i+1) + "cell4");
			System.out.println(sheet.toString() + ": " + i);
		}
		ExcelService.onSheetComplete(sheet);
	}

}
