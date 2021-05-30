package com.automationproject;

import org.testng.annotations.Test;

public class loginFromExcel {
	
@Test
	
	public static void openTest() throws Exception {
		
		ExcelUtils.setExcelFile("./Input/InputData.xlsx");
			
		for(int counter =0;counter<ExcelUtils.getLastRownNo("UserDetails");counter++)
		{
			System.out.println(ExcelUtils.getCellData("UserDetails", counter, 0));
			System.out.println(ExcelUtils.getCellData("UserDetails", counter, 1));			
		}				
	}
}