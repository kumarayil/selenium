package apparelStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
//How to read excel files using Apache POI
public class testData {
	public static String alreadyUserEmailId;
	public static String newUserEmailId;
	public static String firstName;
	public static String lastName;
	public static String password;
	public static String companyName;
	public static String address1;
	public static String address2;
	public static String cityName;
	public static String zipcode;
	public static String invZip;
	public static String additionalInfo;
	public static String homePhone;
	public static String mobilePhone;
	
	public static String invalidUserName1;
	public static String invalidPassword1;
	public static String invalidUserName2;
	public static String invalidPassword2;
	
	public static String searchKeyWord;
	public static String facebookUserName;
	public static String facebookPassword;
	public static String googleUserName;
	public static String googlePassword;
	public static String pinterestUserName;
	public static String pinterestPassword;
	
	public static String reviewTitle;
	public static String reviewComments;
	
	
	@Test
	public static void main (String [] args) throws IOException{
                         
			FileInputStream fileLocation = new FileInputStream(System.getProperty("user.dir") + "/TestData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);
			XSSFSheet sheet = workbook.getSheetAt(0);//account
            
			alreadyUserEmailId = sheet.getRow(1).getCell(0).getStringCellValue();
			newUserEmailId = sheet.getRow(2).getCell(0).getStringCellValue();
			firstName = sheet.getRow(2).getCell(1).getStringCellValue();
			lastName = sheet.getRow(2).getCell(2).getStringCellValue();

			password = sheet.getRow(2).getCell(3).getStringCellValue();
			companyName = sheet.getRow(2).getCell(6).getStringCellValue();
			address1 = sheet.getRow(2).getCell(4).getStringCellValue();
			address2 = sheet.getRow(2).getCell(5).getStringCellValue();
			cityName = sheet.getRow(2).getCell(7).getStringCellValue();
			
			zipcode = sheet.getRow(2).getCell(9).getStringCellValue();
			invZip = sheet.getRow(2).getCell(14).getStringCellValue();
			additionalInfo = sheet.getRow(2).getCell(11).getStringCellValue();
			homePhone = sheet.getRow(2).getCell(12).getStringCellValue();
			mobilePhone = sheet.getRow(2).getCell(13).getStringCellValue();
			
			//Invalid Credential
			XSSFSheet sheet1 = workbook.getSheetAt(1);//invalid credential
			invalidUserName1 = sheet1.getRow(1).getCell(0).getStringCellValue();
			invalidPassword1 = sheet1.getRow(1).getCell(1).getStringCellValue();
			invalidUserName2 = sheet1.getRow(2).getCell(0).getStringCellValue();
			invalidPassword2 = sheet1.getRow(2).getCell(1).getStringCellValue();

			//Search and share page
			//Invalid Credential
			XSSFSheet sheet2 = workbook.getSheetAt(2);//Review
			searchKeyWord = sheet2.getRow(1).getCell(0).getStringCellValue();
			facebookUserName = sheet2.getRow(1).getCell(1).getStringCellValue();
			facebookPassword = sheet2.getRow(1).getCell(2).getStringCellValue();
			googleUserName = sheet2.getRow(1).getCell(3).getStringCellValue();
			googlePassword = sheet2.getRow(1).getCell(4).getStringCellValue();
			pinterestUserName = sheet2.getRow(1).getCell(5).getStringCellValue();
			pinterestPassword = sheet2.getRow(1).getCell(6).getStringCellValue();
			
			reviewTitle = sheet2.getRow(1).getCell(7).getStringCellValue();
			reviewComments = sheet2.getRow(1).getCell(8).getStringCellValue();
			
	}

	
}
