package com.automationproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

private static XSSFSheet ExcelWSheet;
private static XSSFWorkbook ExcelWBook; 
private static XSSFCell Cell;
private static XSSFRow Row;

public static void setExcelFile(String Path) throws Exception {

try {

  // Open the Excel file

	FileInputStream ExcelFile = new FileInputStream(Path);

	// Access the required test data sheet

	ExcelWBook = new XSSFWorkbook(ExcelFile);

	//ExcelWSheet = ExcelWBook.getSheet(SheetName);

} catch (Exception e){

throw (e);

}

}

public static int getLastRownNo(final String SheetName) {
    ExcelUtils.ExcelWSheet = ExcelUtils.ExcelWBook.getSheet(SheetName);
    return ExcelUtils.ExcelWSheet.getLastRowNum();
}

public static int getCellIntValue(String SheetName,int RowNum, int ColNum) throws Exception{


try{

	//Access the required test data sheet

	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	 
	 int CellData = (int) ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();

  return CellData;
}
catch(Exception e)
{
	 try{
	 if (ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
	        return (int) ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
	    } else if (ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {
	    	  int value=(int) ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
	    	 return value;
	    } 
	    else {
	        return ColNum;
	    }
	 }
	 catch(Exception e2)
	 {
		 return ColNum;
	 }
}

}

public static String getCellData(String SheetName,int RowNum, int ColNum) throws Exception{


try{

	//Access the required test data sheet

	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	 
	 String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue().toString();

  return CellData;
}
catch(Exception e)
{
	 try{
	 if (ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
	        return ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
	    } else if (ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {
	    	  int value=(int) ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
	    	 return value+"";
	    } else if (ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN) {
	        return ExcelWSheet.getRow(RowNum).getCell(ColNum).getBooleanCellValue() + "";
	    }else if(ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK){
	        return ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();
	    }else if(ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType() == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR){
	        return ExcelWSheet.getRow(RowNum).getCell(ColNum).getErrorCellValue() + "";
	    } 
	    else {
	        return "";
	    }
	 }
	 catch(Exception e2)
	 {
		 return "";
	 }
}
}

//@SuppressWarnings({ "deprecation", "static-access" })
public static void setCellData(String SheetName,String Result,int RowNum, int ColNum) throws Exception	{


try{
	
	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	//Access the required test data sheet

  Row  = ExcelWSheet.getRow(RowNum);

 // Cell = Row.getCell(ColNum, Row.r);

if (Cell == null) {
	
Cell = Row.createCell(ColNum);

Cell.setCellValue(Result);

}
 else {
Cell = Row.createCell(ColNum);
Cell.setCellValue(Result);
}
XSSFCellStyle style = ExcelWBook.createCellStyle();
XSSFFont font = ExcelWBook.createFont();
//font.setBold(true);
//font.setBold(color);
style.setFont(font);
//style.setWrapText(true);
style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
style.setBorderTop(HSSFCellStyle.BORDER_THIN);
style.setBorderRight(HSSFCellStyle.BORDER_THIN);
style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
Cell.setCellStyle(style);
 // Constant variables Test Data path and Test Data file name

FileOutputStream  fileOut = new FileOutputStream("");
ExcelWBook.write(fileOut);
 
fileOut.flush();

fileOut.close();
}catch(Exception e)
{
System.out.println("Not able to write data into Excel"+e.getMessage());
//e.printStackTrace();

}

}

//@SuppressWarnings("deprecation")
public static void setCellData(String SheetName,String Result,int RowNum, int ColNum,short color) throws Exception	{

	
try{
	

	//Access the required test data sheet

	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	Row  = ExcelWSheet.getRow(RowNum);
	Cell = Row.getCell(ColNum);

if (Cell == null) {	
Cell = Row.createCell(ColNum);

Cell.setCellValue(Result);

} else {
Cell.setCellValue(Result);

}

XSSFCellStyle style = ExcelWBook.createCellStyle();
XSSFFont font = ExcelWBook.createFont();
Cell.setCellStyle(style);
font.setColor(color);
//font.setBold(color);
//style.setWrapText(true);
style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
style.setBorderTop(HSSFCellStyle.BORDER_THIN);
style.setBorderRight(HSSFCellStyle.BORDER_THIN);
style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
style.setFont(font);

 // Constant variables Test Data path and Test Data file name

FileOutputStream  fileOut = new FileOutputStream("");
ExcelWBook.write(fileOut);
  fileOut.flush();
fileOut.close();

}catch(Exception e)
{
	System.out.println("Not able to write data into Excel"+e.getMessage());
	//	e.printStackTrace();

}
}
}