package com.ly.test.helloWorld.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.ly.test.helloWorld.model.ExcelStorage;

public class XlsxProcessor implements Runnable {

	
	private ExcelStorage excelStorage;
	private Sheet sheet;
	
	public XlsxProcessor(ExcelStorage excelStorage, Sheet sheet) {
		this.sheet = sheet;
		this.excelStorage = excelStorage;
	}
	
	@Override
	public void run() {
		int total = excelStorage.getTotal();
		for (int i=0; i < total; i++) {
			Row row = sheet.createRow(i);
			row.createCell(0).setCellValue(i);
			row.createCell(1).setCellValue("" + System.currentTimeMillis());
			
			excelStorage.setProgress(i+1);
		}
	}

}
