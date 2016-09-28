package com.ly.test.helloWorld.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ly.test.helloWorld.model.ExcelStorage;

@Service
public class ExcelService {
	
	public static final Map<String, ExcelStorage> EXCEL_MAP = new HashMap<>();
	
	public void generatorExcel(int total, String key) {
		ExcelStorage excelStorage = new ExcelStorage();
		Workbook workbook = new XSSFWorkbook();
		
		excelStorage.setTotal(total);
		excelStorage.setProgress(0);
		excelStorage.setWorkbook(workbook);
		EXCEL_MAP.put(key, excelStorage);
		
		Sheet sheet = workbook.createSheet("sheet example");
		XlsxProcessor processor = new XlsxProcessor(excelStorage, sheet);
		new Thread(processor).start();
	}
	
}
