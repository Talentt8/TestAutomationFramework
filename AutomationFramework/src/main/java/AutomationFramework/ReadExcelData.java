package AutomationFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author      Mzwandile Mdladla <mzwandile.mdladla@absa.co.za>
 * @version     1.0                
 * @since       1.0          
 */
public class ReadExcelData {
	
	public XSSFSheet sheet;
	public XSSFWorkbook workbook;
	private XSSFRow row;
	private XSSFCell cell;
	private FileInputStream fis;
	private FileOutputStream fos;	
	private String path;
	
	/**
	 * ReadExcelData
	 * Reads excel data from the specified path. 
	 * <p>
	 * This constructor reads the excel file from the specified path
	 * it handles an IOException if the specified path is not found 
	 *
	 * @param  excelPath path of the excel file
	 * @return void     
	 * @see         
	 */
	public ReadExcelData(String excelPath){
		try {
			File src = new File(excelPath);
			fis = new FileInputStream(src);
			fos = null;
			path = excelPath;
			
			workbook = new XSSFWorkbook(fis);			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * getCellData
	 * Reads excel data from the specified sheet and column name. 
	 * <p>
	 * This method reads the excel data from the specified sheet and column name
	 * it handles an String numeric and date types
	 *
	 * @param  	sheetName the sheet to be read
	 * @param	colName the name of the column heading to be read from
	 * @param	rowNum the current row to be read
	 * @return void     
	 * @see         
	 */
	public String getCellData(String sheetName, String colName, int rowNum){
		try{
			int colNum = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			for (int i=0; i<row.getLastCellNum(); i++){
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)){
					colNum = i;
				}
			}
			
			row = sheet.getRow(rowNum-1);
			cell = row.getCell(colNum);
			
			if (cell.getCellTypeEnum() == CellType.STRING){
				String value = cell.getStringCellValue();
				//fis.close();
				return value;				
			}
			else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA){
				//cell.setCellType(CellType.STRING);
				String cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)){
					DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
					Date date = cell.getDateCellValue();
					cellValue = dt.format(date);
				}
				return cellValue;
			}
			else if (cell.getCellTypeEnum() == CellType.BLANK){
				return "";
			}
			else{
				return String.valueOf(cell.getBooleanCellValue());
			}
		}		
		
		catch(Exception e){
			e.printStackTrace();
			return "Data not found";
		}
	}
	
	public String setCellData(String sheetName, String colName, int rowNum, String value){
		try{
			int colNum = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			for (int i=0; i<row.getLastCellNum(); i++){
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)){
					colNum = i;
				}
			}
			
			row = sheet.getRow(rowNum-1);
			//if row does not exist create row
			if (row == null){
				row = sheet.createRow(rowNum-1);
			}
						
			cell = row.getCell(colNum);
			//if cell does not exist create cell
			if (cell == null){
				System.out.println("Cannot find column : " + colName);
				cell = row.createCell(colNum);
			}
			
			cell.setCellValue(value);
			fos = new FileOutputStream(path);
			System.out.println(path);
			workbook.write(fos);
			fos.close();
			return "True";
			
		}
		catch(Exception e){
			e.printStackTrace();
			return "Column not found";
		}
	}

}
