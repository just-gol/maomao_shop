package com.maomao.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/** 
* @author:wzy
* @descrption:
* @version:
* @date:2018年4月4日
*/

public class TestPOI {
	
	@Test
	public void fn1() throws Exception{
		//创建一个workbook 对应一个excel文件
		Workbook workbook = new XSSFWorkbook();
		
		
		
		//在workbook中添加一个sheet
		Sheet sheet = workbook.createSheet("授信管理");
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, 12);		
		sheet.addMergedRegion(range);
		CellRangeAddress range1 = new CellRangeAddress(1, 2, 0, 0);		
		sheet.addMergedRegion(range1);
		CellRangeAddress range2 = new CellRangeAddress(1, 2, 1, 1);		
		sheet.addMergedRegion(range2);
		CellRangeAddress range3 = new CellRangeAddress(1, 2, 2, 2);		
		sheet.addMergedRegion(range3);
		CellRangeAddress range4 = new CellRangeAddress(1, 2, 3, 3);		
		sheet.addMergedRegion(range4);
		
		CellRangeAddress range5 = new CellRangeAddress(1, 1, 4, 6);		
		sheet.addMergedRegion(range5);
		
		CellRangeAddress range6 = new CellRangeAddress(1, 2, 7, 7);		
		sheet.addMergedRegion(range6);
		
		CellRangeAddress range7 = new CellRangeAddress(1, 1, 8, 10);		
		sheet.addMergedRegion(range7);
		
		CellRangeAddress range8 = new CellRangeAddress(1, 2, 11, 11);		
		sheet.addMergedRegion(range8);
		
		CellRangeAddress range9 = new CellRangeAddress(1, 2, 12, 12);		
		sheet.addMergedRegion(range9);
	
	
		
		
		Row row = sheet.createRow(0);
		row.setHeightInPoints(41);
		//创建单元格,设置表头 ,设置表头居中
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中格式
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		//设置字体
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)14);//字体大小
		font.setFontName("宋体");
		font.setBold(true);
		cellStyle.setFont(font);
		
		
		Cell cell = row.createCell(0);
		cell.setCellValue("茂茂商城B端用户信息表");
		cell.setCellStyle(cellStyle);
		
		Row row2 = sheet.createRow(1);
		CellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(HorizontalAlignment.CENTER);//水平居中格式
		cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
		
		Cell cell2 = row2.createCell(0);
		cell2.setCellValue("序号");
		cell2.setCellStyle(cellStyle2);
		
		Cell cell3 = row2.createCell(1);
		cell3.setCellValue("姓名");
		cell3.setCellStyle(cellStyle2);
		
		Cell cell4 = row2.createCell(2);
		cell4.setCellValue("身份证号");
		cell4.setCellStyle(cellStyle2);
		
		Cell cell5 = row2.createCell(3);
		cell5.setCellValue("还款金额(元)");
		cell5.setCellStyle(cellStyle2);
		
		Cell cell6 = row2.createCell(4);
		cell6.setCellValue("还款账户");
		cell6.setCellStyle(cellStyle2);
		
		Cell cell7 = row2.createCell(7);
		cell7.setCellValue("打款金额(元)");
		cell7.setCellStyle(cellStyle2);
		
		Cell cell8 = row2.createCell(8);
		cell8.setCellValue("打款账户");
		cell8.setCellStyle(cellStyle2);
		
		Cell cell9 = row2.createCell(11);
		cell9.setCellValue("打款状态");
		cell9.setCellStyle(cellStyle2);
		Cell cell10 = row2.createCell(12);
		cell10.setCellValue("备注");
		cell10.setCellStyle(cellStyle2);
		
		Row titile = sheet.createRow(2);
		titile.createCell(4).setCellValue("开户行");
		titile.createCell(5).setCellValue("账户");
		titile.createCell(6).setCellValue("户名");
		titile.createCell(8).setCellValue("开户行");
		titile.createCell(9).setCellValue("账户");
		titile.createCell(10).setCellValue("户名");
		
		for (int i = 0;i<5;i++) {
			Row row3 = sheet.createRow(i+3);
			row3.createCell(0).setCellValue(12255);
			row3.createCell(1).setCellValue(575757);
			row3.createCell(2).setCellValue(757);
			row3.createCell(3).setCellValue(575);
			row3.createCell(4).setCellValue(5757);
			row3.createCell(5).setCellValue(5757);
			row3.createCell(6).setCellValue(5757);
			row3.createCell(7).setCellValue(5757);
			row3.createCell(8).setCellValue(5757);
			row3.createCell(9).setCellValue(5757);
			row3.createCell(10).setCellValue(5757);
			row3.createCell(11).setCellValue(5757);
		}
		String fileName = "茂茂商城B端用户信息表.xlsx";
		String mimeType = Files.probeContentType(Paths.get(fileName));
		System.out.println(mimeType);
		OutputStream out = new FileOutputStream("D:/t7.xlsx");
		workbook.write(out);
	}

	private void setCellValue(String string) {
	}
	
	@Test
	public void fn2()throws Exception{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(c.getTime());
		Date d = new Date();
		
		System.out.println();
	}
}





























