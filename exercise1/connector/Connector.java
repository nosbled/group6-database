package connector;
import java.sql.*;

public class Connector {
	public static Connection startConnection()  {  
		 Connection conn = null;
		     try{ 
		     
		          String connectionUrl = "jdbc:sqlserver://10.211.55.4:1433;" + 
                          				"Database=SWAGSCHOOL;" + 
                          				"user=sa;" + 
                          				"password=hejhej123"; 
		          
		          conn = DriverManager.getConnection(connectionUrl);
		          
		     }catch (Exception e){
		    	 System.out.println("Could not connect, see message: " + e);;
		     }return conn;
		     
	 }
	     
}

	

