package com.maomao.ssm.controller.other;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.service.admin.ExportService;

import sun.misc.BASE64Encoder;

/**
 * @author:wzy
 * @descrption:导出excel
 * @version:
 * @date:2018年4月13日
 */
@Controller
public class ExportController {
	@Autowired
	private ExportService exportService;
	/**
	 * 导出excel 1:导出授信管理 2:导出提现申请 3:导出贷款月结
	 */
	@RequestMapping("/export.action")
	@ResponseBody
	public JsonData exportXLS(HttpServletRequest request, HttpServletResponse response, Byte type) {
		List<Map<String, Object>> list = exportService.exportXLS(type);
		if (type != null && (type == 1 || type == 2 || type == 3 || type == 4 || type == 5)&& list !=null && list.size()>0) {
			String fileName = null;
			String mimeType = null;

			// 创建一个workbook 对应一个excel文件
			Workbook workbook = new XSSFWorkbook();
			if (type == 1 && list != null && list.size() > 0) {
				
					// 在workbook中添加一个sheet
					Sheet sheet = workbook.createSheet("授信管理");
					CellRangeAddress range = new CellRangeAddress(0, 0, 0, 5);
					sheet.addMergedRegion(range);
					RegionUtil.setBorderBottom(1, range, sheet);

					Row row = sheet.createRow(0);
					row.setHeightInPoints(41);
					// 创建单元格,设置表头 ,设置表头居中
					CellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中格式
					cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
					// 设置字体
					Font font = workbook.createFont();
					font.setFontHeightInPoints((short) 14);// 字体大小
					font.setFontName("宋体");
					font.setBold(true);
					cellStyle.setFont(font);

					Cell cell = row.createCell(0);
					cell.setCellValue("茂茂商城B端用户信息表");
					cell.setCellStyle(cellStyle);

					Row row2 = sheet.createRow(1);
					row2.createCell(0).setCellValue("序号");
					row2.createCell(1).setCellValue("姓名");
					row2.createCell(2).setCellValue("身份证号");
					row2.createCell(3).setCellValue("联系电话");
					row2.createCell(4).setCellValue("商城登录账号");
					row2.createCell(5).setCellValue("备注");

					for (int i = 0; i < list.size(); i++) {
						Row row3 = sheet.createRow(i + 2);
						row3.createCell(0).setCellValue(list.get(i).get("xuhao")==null?"":list.get(i).get("xuhao").toString());
						row3.createCell(1).setCellValue(list.get(i).get("name")==null?"":list.get(i).get("name").toString());
						row3.createCell(2).setCellValue(list.get(i).get("idCard") == null? "":list.get(i).get("idCard").toString());
						row3.createCell(3).setCellValue(list.get(i).get("mobile")==null?"":list.get(i).get("mobile").toString());
						row3.createCell(4).setCellValue(list.get(i).get("account")==null?"":list.get(i).get("account").toString());
						row3.createCell(5).setCellValue("");
					}
					fileName = "茂茂商城B端用户信息表.xlsx";

			} else if (type == 2 && list != null && list.size() > 0) {

				// 在workbook中添加一个sheet
				Sheet sheet = workbook.createSheet("提现申请");
				CellRangeAddress range = new CellRangeAddress(0, 0, 0, 9);
				sheet.addMergedRegion(range);
				RegionUtil.setBorderBottom(1, range, sheet);

				Row row = sheet.createRow(0);
				row.setHeightInPoints(41);
				// 创建单元格,设置表头 ,设置表头居中
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中格式
				cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
				// 设置字体
				Font font = workbook.createFont();
				font.setFontHeightInPoints((short) 14);// 字体大小
				font.setFontName("宋体");
				font.setBold(true);
				cellStyle.setFont(font);

				Cell cell = row.createCell(0);
				cell.setCellValue("茂茂商城B端用户提现申请表");
				cell.setCellStyle(cellStyle);

				Row row2 = sheet.createRow(1);
				row2.createCell(0).setCellValue("序号");
				row2.createCell(1).setCellValue("姓名");
				row2.createCell(2).setCellValue("联系电话");
				row2.createCell(3).setCellValue("提现金额(元)");
				row2.createCell(4).setCellValue("开户行");
				row2.createCell(5).setCellValue("户名");
				row2.createCell(6).setCellValue("账号");
				row2.createCell(7).setCellValue("申请时间");
				row2.createCell(8).setCellValue("打款状态");
				row2.createCell(9).setCellValue("备注");

				for (int i = 0; i < list.size(); i++) {
					Row row3 = sheet.createRow(i + 2);
					row3.createCell(0).setCellValue(list.get(i).get("xuhao").toString());
					row3.createCell(1).setCellValue(list.get(i).get("name").toString());
					row3.createCell(2).setCellValue(list.get(i).get("mobile").toString());
					row3.createCell(3).setCellValue(list.get(i).get("money").toString());
					row3.createCell(4).setCellValue(list.get(i).get("bankName").toString());
					row3.createCell(5).setCellValue(list.get(i).get("bankUserName").toString());
					row3.createCell(6).setCellValue(list.get(i).get("account")==null?"":list.get(i).get("account").toString());
					row3.createCell(7).setCellValue(list.get(i).get("time").toString());
					row3.createCell(8).setCellValue(list.get(i).get("status").toString());
					row3.createCell(9).setCellValue("");
				}
				fileName = "茂茂商城B端用户提现申请表.xlsx";
			} else if (type == 3 && list != null && list.size() > 0) {
			
				//在workbook中添加一个sheet
				Sheet sheet = workbook.createSheet("贷款月结");
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
				
				Calendar c = Calendar.getInstance();
				int month = c.get(Calendar.MONTH)+1;
				Cell cell = row.createCell(0);
				cell.setCellValue("茂茂商城"+month+"月贷款结算清单");
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
				
				for (int i = 0;i<list.size();i++) {
					Row row3 = sheet.createRow(i+3);
					row3.createCell(0).setCellValue(list.get(i).get("xuhao").toString());
					row3.createCell(1).setCellValue(list.get(i).get("name").toString());
					row3.createCell(2).setCellValue(list.get(i).get("idCard").toString());
					row3.createCell(3).setCellValue(list.get(i).get("huankuanMoney").toString());
					row3.createCell(4).setCellValue(list.get(i).get("bankName01").toString());
					row3.createCell(5).setCellValue(list.get(i).get("account01").toString());
					row3.createCell(6).setCellValue(list.get(i).get("accountName01").toString());
					row3.createCell(7).setCellValue(list.get(i).get("dakuanMoney").toString());
					row3.createCell(8).setCellValue(list.get(i).get("bankName02").toString());
					row3.createCell(9).setCellValue(list.get(i).get("account02").toString());
					row3.createCell(10).setCellValue(list.get(i).get("accountName02").toString());
					row3.createCell(11).setCellValue(list.get(i).get("status").toString());
					row3.createCell(12).setCellValue("");
				}
				fileName = "茂茂商城"+month+"月贷款结算清单.xlsx";
			}else if (type == 5 && list != null && list.size() > 0) {
				

				// 在workbook中添加一个sheet
				Sheet sheet = workbook.createSheet("销售金额统计");
				CellRangeAddress range = new CellRangeAddress(0, 0, 0, 5);
				sheet.addMergedRegion(range);
				RegionUtil.setBorderBottom(1, range, sheet);

				Row row = sheet.createRow(0);
				row.setHeightInPoints(41);
				// 创建单元格,设置表头 ,设置表头居中
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中格式
				cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
				// 设置字体
				Font font = workbook.createFont();
				font.setFontHeightInPoints((short) 14);// 字体大小
				font.setFontName("宋体");
				font.setBold(true);
				cellStyle.setFont(font);

				Cell cell = row.createCell(0);
				cell.setCellValue("销售金额统计");
				cell.setCellStyle(cellStyle);

				Row row2 = sheet.createRow(1);
				row2.createCell(0).setCellValue("序号");
				row2.createCell(1).setCellValue("日期");
				row2.createCell(2).setCellValue("支付金额");
				row2.createCell(3).setCellValue("退款金额");
				row2.createCell(4).setCellValue("优惠券发放金额");
				row2.createCell(5).setCellValue("优惠券使用金额");
				row2.createCell(5).setCellValue("优惠券过期金额");

				for (int i = 0; i < list.size(); i++) {
					Row row3 = sheet.createRow(i + 2);
					row3.createCell(0).setCellValue(list.get(i).get("xuhao")==null?"":list.get(i).get("xuhao").toString());
					row3.createCell(1).setCellValue(list.get(i).get("time")==null?"":list.get(i).get("time").toString());
					row3.createCell(2).setCellValue(list.get(i).get("orderPayMoneyTotal") == null? "":list.get(i).get("orderPayMoneyTotal").toString());
					row3.createCell(3).setCellValue(list.get(i).get("orderRefundMoneyTotal")==null?"":list.get(i).get("orderRefundMoneyTotal").toString());
					row3.createCell(4).setCellValue(list.get(i).get("couponGetMoneyTotal")==null?"":list.get(i).get("couponGetMoneyTotal").toString());
					row3.createCell(4).setCellValue(list.get(i).get("couponUseMoneyTotal")==null?"":list.get(i).get("couponUseMoneyTotal").toString());
					row3.createCell(4).setCellValue(list.get(i).get("couponExpireMoneyTotal")==null?"":list.get(i).get("couponExpireMoneyTotal").toString());
					row3.createCell(5).setCellValue("");
				}
				
				
				
				fileName = "销售金额统计.xlsx";
			}


			// 解决下载文件名乱码
			String agent = request.getHeader("User-Agent");
			fileName = encodeDownloadFilename(fileName, agent);
			response.setContentType(mimeType);
			response.setHeader("content-disposition", "attachment;filename=" + fileName);
			ServletOutputStream out = null;
			try {
				out = response.getOutputStream();
				workbook.write(out);
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (workbook != null) {
					try {
						workbook.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return JsonData.setSuccessMessage();
		}
		return JsonData.setErrorMessage("数据为空,导出失败");
	}

	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @return 编码后的下载附件名
	 */
	private String encodeDownloadFilename(String filename, String agent) {
		try {
			if (agent.contains("Firefox")) { // 火狐浏览器
				filename = "=?UTF-8?B?" + new BASE64Encoder().encode(filename.getBytes("utf-8")) + "?=";
				filename = filename.replaceAll("\r\n", "");
			} else { // IE及其他浏览器

				filename = URLEncoder.encode(filename, "utf-8");
				filename = filename.replace("+", " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filename;
	}
	
//	@RequestMapping("test.action")
//	@ResponseBody
//	public JsonData test() {
//		return exportService.getList();
//	}
}
















