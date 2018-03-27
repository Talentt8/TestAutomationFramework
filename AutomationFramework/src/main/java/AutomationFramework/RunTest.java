package AutomationFramework;

public class RunTest extends BaseClass {

	public static int firstRow = 2;
	
	public static void getRows(){
		red.sheet = red.workbook.getSheet("TestData");
		System.out.println("Rows : " + red.sheet.getLastRowNum());
	}
}