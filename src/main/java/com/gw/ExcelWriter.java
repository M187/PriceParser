package com.gw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gw.v1.InputData;
import com.gw.v1.ResultData;
import com.gw.v2.InputDataV2;
import com.gw.v2.ResultDataV2;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

@Slf4j
public class ExcelWriter {

	public void writeToExcelV2(List<InputDataV2> inputData, List<ResultDataV2> resultData) {
		log.info(" -- writing data into excel");
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

			//Style setting
			HSSFFont headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 12);

			HSSFFont boldFont = workbook.createFont();
			boldFont.setBold(true);

			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);

			HSSFCellStyle greenCellStyle = workbook.createCellStyle();
				greenCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				greenCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


			String[] columns = {"Ubezpieczyciel", "Cena polisy", "Koszty leczenia", "OC", "Ocena experta"};
			int rowNum = 0;
			for (int i = 0; i < inputData.size(); i++) {
				ResultDataV2 data = resultData.get(i);
				InputDataV2 input = inputData.get(i);
				//Merge cells in first row (from first cell to 20th column)
				sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 20));
				// Populate the first row with description
				Row rowOverview = sheet.createRow(rowNum++);
				rowOverview.createCell(0).setCellValue(input.getQuoteParameters());


				//create  a variable for the second row and populate the columns with headers, then increase rowNum
				Row headerRow = sheet.createRow(rowNum++);
				for (int y = 0; y < columns.length; y++) {
					Cell cell = headerRow.createCell(y);
					cell.setCellValue(columns[y]);
					cell.setCellStyle(headerCellStyle);
					sheet.autoSizeColumn(y);
				}


				//Add the results below each header, Colonnade related cells are bold
				for (int k = 0; k < data.getCompanyName().size(); k++) {
					Row row = sheet.createRow(rowNum++);
					String companyName = data.getCompanyName().get(k);
					String premium = data.getInsurancePremium().get(k);
					String medexSumInsured = data.getMedexSumInsured().get(k);
					String oc = data.getOc().get(k);
					String rate = data.getRate().get(k);

					Cell cell1 = row.createCell(0);
						cell1.setCellValue(companyName);
						setBoldIfColonnade(cell1, companyName, greenCellStyle);

					Cell cell2 = row.createCell(1);
						cell2.setCellValue(premium);
						setBoldIfColonnade(cell2, companyName, greenCellStyle);

					Cell cell3 = row.createCell(2);
						cell3.setCellValue(medexSumInsured);
						setBoldIfColonnade(cell3, companyName, greenCellStyle);

					Cell cell4 = row.createCell(3);
						cell4.setCellValue(oc);
						setBoldIfColonnade(cell4, companyName, greenCellStyle);

					Cell cell5 = row.createCell(4);
						cell5.setCellValue(rate);
						setBoldIfColonnade(cell5, companyName, greenCellStyle);

				}
				sheet.createRow(rowNum++);
			}


			workbook.write(fileOutputStream);
			workbook.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		log.info(" -- finished writing data into excel");
    }

	private void setBoldIfColonnade(Cell cell, String companyName, HSSFCellStyle greenStyle) {
		if(companyName.equals("colonnade")) {
			cell.setCellStyle(greenStyle);
		}
	}

	public void writeToExcelV1(List<InputData> inputDatas, List<ResultData> resultDatas) {
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
						resultData.getInsurancePremium().toArray(),
						resultData.getMedexSumInsured().toArray(),
						resultData.getOc().toArray(),
						resultData.getRate().toArray()
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
				rownum++;
			}
			workbook.write(fileOutputStream);
			workbook.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}