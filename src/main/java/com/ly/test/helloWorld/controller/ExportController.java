package com.ly.test.helloWorld.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ly.test.helloWorld.model.ExcelStorage;
import com.ly.test.helloWorld.service.ExcelService;

@Controller
public class ExportController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ExcelService excelService;
	
	@RequestMapping("/exportPre")
	public ModelAndView index2() {
		return new ModelAndView("export");
	}
	
	@RequestMapping("/export")
	@ResponseBody
	public Map<String, Object> export() {
		String totalReq = request.getParameter("total");
		String key = request.getParameter("key");
		String down = request.getParameter("down");
		Map<String, Object> result = new HashMap<>();
		try {
			if (key == null) {
				key = request.getRemoteAddr().replaceAll(":", "") + System.currentTimeMillis();
				excelService.generatorExcel(Integer.parseInt(totalReq), key);
			}
			
			ExcelStorage excelStorage = (ExcelStorage) ExcelService.EXCEL_MAP.get(key);
			int total = excelStorage.getTotal();
			int progress = excelStorage.getProgress();
			
			if (progress == total && "1".equals(down)) {
				response.setCharacterEncoding("utf-8");
				response.setContentType("multipart/form-data");
				response.setHeader("Content-Disposition", "attachment;fileName=file_" + System.currentTimeMillis() + ".xlsx");
				OutputStream os = response.getOutputStream();
				Workbook workbook = excelStorage.getWorkbook();
				workbook.write(os);
				
				os.close();
				workbook.close();
				ExcelService.EXCEL_MAP.remove(key);
				
				return null;
			} else {
				result.put("total", total);
				result.put("progress", progress);
				result.put("key", key);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("/exportStatus")
	@ResponseBody
	public Map<String, Object> status() {
		Map<String, Object> res = new HashMap<>();
		for (Map.Entry<String, ExcelStorage> entry: ExcelService.EXCEL_MAP.entrySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("total", entry.getValue().getTotal());
			map.put("progress", entry.getValue().getProgress());
			map.put("workbook", entry.getValue().getWorkbook().toString());
			res.put(entry.getKey(), map);
		}
		return res;
	}
}
