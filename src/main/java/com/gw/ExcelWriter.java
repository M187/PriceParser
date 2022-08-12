package com.gw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelWriter {

	public void writeToExcel(List<InputData> inputDatas, List<ResultData> resultDatas) {
		try {
			File myObj = new File("policiesPriceComparison.xls");
			FileOutputStream fileOutputStream = null;
			try {
				myObj.createNewFile();
				fileOutputStream = new FileOutputStream(myObj);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("PoliciesComparison" + new SimpleDateFormat("dd.MM.yyyy").format(new Date()));

			int rownum = 0;
			for (int i = 0; i < inputDatas.size();i++) {
				ResultData resultData = resultDatas.get(i);
				InputData inputData = inputDatas.get(i);
				sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,0,resultData.getCompanyName().size()));
				Row rowOverview = sheet.createRow(rownum++);
				rowOverview.createCell(0).setCellValue(inputData.string());

				Object[][] arrayOfEntries = {
						resultData.getCompanyName().toArray(),
						resultData.getInsurancePrice().toArray(),
						resultData.getLiecebneVylohy().toArray(),
						resultData.getUrazovePojisteni().toArray(),
						resultData.getPojistOdpovednosti().toArray()
				};

				for (Object[] entry : arrayOfEntries) {
					Row row = sheet.createRow(rownum++);
					int colnum = 0;
					for (Object value : entry) {
						Cell cell = row.createCell(colnum++);
						if (value instanceof String) {
							cell.setCellValue((String) value);
						} else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						}
					}
				}
				for (int n = 0; n < resultData.getCompanyName().size(); n++) sheet.autoSizeColumn(n);
			}
			workbook.write(fileOutputStream);
			workbook.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}