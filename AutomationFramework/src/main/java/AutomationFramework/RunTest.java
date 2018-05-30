package AutomationFramework;

public class RunTest extends BaseClass {

	public static int firstRow = 2;
	
	public static void getRows(){
		red.sheet = red.workbook.getSheet("TestData");
		int numRows = red.sheet.getLastRowNum();
		int runTo = firstRow + numRows;
		System.out.println("Rows : " + numRows);
		for (int i = firstRow; i < runTo ; i++){
			if (red.getCellData("TestData", "ExecutionFlag", i).equalsIgnoreCase("yes")){
				System.out.println("Yes");
			}
		}
	}
}