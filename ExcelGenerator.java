package com.wcs.app.main.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wcs.app.main.model.ProductStockMaintain;

public class ExcelGenerator {
	
	public static ByteArrayInputStream productsToExcel(List<ProductStockMaintain> products) throws IOException {
		String[] COLUMNs = {"id", "product_code", "product_name","product_quantity","hsn_num","updated_date","product_buying_price_with_gst", 
				"product_selling_price","pro_sell_price_with_gst","supplier","category","block","gsttaxtype","vehicleModel","vehicleType","systemUsers","status"};
		try(
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		){
			CreationHelper createHelper = workbook.getCreationHelper();
	 
			Sheet sheet = workbook.createSheet("s");
	 
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());
	 
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
	 
			// Row for Header
			Row headerRow = sheet.createRow(0);
	 
			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}
	 
			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));
	 
			int rowIdx = 1;
			for (ProductStockMaintain product : products) {
				Row row = sheet.createRow(rowIdx++);
	 
				row.createCell(0).setCellValue(product.getId());
				row.createCell(1).setCellValue(product.getProduct_code());
				row.createCell(2).setCellValue(product.getProduct_name());
				row.createCell(3).setCellValue(product.getProduct_quantity());
				row.createCell(4).setCellValue(product.getHsn_num());
				row.createCell(5).setCellValue(product.getUpdated_date());
				row.createCell(6).setCellValue(product.getProduct_buying_price_with_gst());
				row.createCell(7).setCellValue(product.getProduct_selling_price());
				row.createCell(8).setCellValue(product.getPro_sell_price_with_gst());
				row.createCell(9).setCellValue(product.getSupplier().getId());
				row.createCell(10).setCellValue(product.getCategory().getId());
				row.createCell(11).setCellValue(product.getBlock().getId());
				row.createCell(12).setCellValue(product.getGsttaxtype().getId());
				row.createCell(13).setCellValue(product.getVehicleModel().getId());
				row.createCell(14).setCellValue(product.getVehicleType().getId());
				row.createCell(15).setCellValue(product.getSystemUsers().getId());
				row.createCell(16).setCellValue(product.getStatus());
				
				
						}
	 
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}