package com.ly.test.helloWorld.model;

import org.apache.poi.ss.usermodel.Workbook;

public class ExcelStorage {
	private int total;
	private int progress;
	private Workbook workbook;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public Workbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
}
