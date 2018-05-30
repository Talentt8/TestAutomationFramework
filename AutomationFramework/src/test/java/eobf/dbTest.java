package eobf;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.sql.*;
import java.sql.PreparedStatement;

import org.testng.annotations.Test;

public class dbTest {

	@Test
	 public void TestVerifyDB() throws SQLException{
		//con = DriverManager.getConnection("jdbc:sqlserver:wdbur03:50200","eobfdb","w3bEOB0z");
		
		String connUrl = "jdbc:db2://wdbur03:50200;" + "databaseName=eobfdb;" + "user=eobfdb;" + "password=w3bEOB0z";
		
		// Declare the JDBC objects.  
	      Connection con = null;  
	      PreparedStatement stmt = null;  
	      ResultSet rs = null;
	      //transient protected DBConnection = null;
	      
	      try {  
	          // Establish the connection.  	    	  
	          Class.forName("com.ibm.db2.jcc.DB2Driver");  
	          System.out.println("Driver loaded...");
	          con = DriverManager.getConnection("jdbc:db2://wdbur03:50200/eobfdb","eobfdb","w3bEOB0z");  
	          con.setAutoCommit(false);
	          System.out.println("Connection started...");

	          // Create and execute an SQL statement that returns some data.  
	          String SQL = "SELECT * FROM AVAFMAIN WHERE applicationNo = ?";  
	          stmt = con.prepareStatement(SQL);
	          stmt.setString(1, "188015869286029");
	          //System.out.println(stmt);
	          System.out.println(SQL);
	          System.out.println("Created JDBC Statement object");
	          rs = stmt.executeQuery();
	          System.out.println("Created JDBC ResultSet object");

	          // Iterate through the data in the result set and display it.  
	          while (rs.next()) {  
	             System.out.println(rs.getString(4) + " " + rs.getString(6));  
	          }  
	       }  

	       // Handle any errors that may have occurred.  
	       catch (Exception e) {  
	          e.printStackTrace();  
	       }  
	       finally {  
	         // if (rs != null) try { rs.close(); } catch(Exception e) {}  
	          if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
	          if (con != null) try { con.close(); } catch(Exception e) {}  
	       }  
	 }
}
