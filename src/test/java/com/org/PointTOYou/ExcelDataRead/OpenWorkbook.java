package com.org.PointTOYou.ExcelDataRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OpenWorkbook {
	public static void main(String[] args) throws IOException 
		{ 
		// Declare a variable 'filePath' with data type String to store path of file. 
		     String filePath = "\\Microsoft_Office_2007_Enterprise_Edition_2007.iso"; 
		// Create an object of the file class and pass "filePath" as a parameter to open xlsx based data file. 
		     File file = new File(filePath); 

		// Create an object of the FileInputStream class and pass the reference variable "file" as a parameter to read excel data file. 
		     FileInputStream fis = new FileInputStream(file); 

		// Declare a variable "fileName" with data type String and store the "DataSheet.xlsx" as a file name. 
		     String fileName = "DataSheet.xlsx"; 

		// Get the index position of first occurrence of specified substring. 
		     int index = fileName.indexOf("."); // Return type of this method is an Integer. 
		     System.out.println(index); 

		// Call substring() method to get the file extension name by splitting file name into substring. 
		     String extType = fileName.substring(index); 

		// The above three lines of code can be written in one step like this: 
		// String extType = fileName.substring(fileName.indexOf(".")); 

		// Use if statement and equals() method to check the condition whether the file is xlsx file. 
		     if(extType.equals(".xlsx"))
		    { 
		// If it is xlsx file, create the object of XSSFWorkbook class and pass the reference variable "fis" as a parameter. 
		      XSSFWorkbook wb = new XSSFWorkbook(fis); 
		      System.out.println("OpenFile is .xlsx file"); 
		      System.out.println("DataSheet.xlsx file opened successfully"); 
		 } 
		// Similarly, check the condition whether the file is xls file. 
		     if(extType.equals(".xls"))
		     { 
		// If the file is xls file, create the object of the HSSFWorkbook class and pass the reference variable "fis" as a parameter. 
		      HSSFWorkbook wb = new HSSFWorkbook(fis); 
		      System.out.println("OpenFile is .xls file"); 
		      System.out.println("DataSheet.xls file opened successfully"); 
		}


}

}
