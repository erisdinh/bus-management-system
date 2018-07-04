package quynh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectSQLServer {
    
    private static String DTB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=BusManagementDtb;"
            + "integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "12345678";
    
    public static void main(String args[]) {
        try {
            
            // Connect to BusManagementDatabase
            Connection connection = getConnection(DTB_URL,USER_NAME, PASSWORD);
            
            // Create statement
            Statement statement = connection.createStatement();
            
            // Get data from tables
            //ResultSet resultSet = statement.executeQuery("select * from "+ tableName);
            
            // Show data
//            while(resultSet.next()){
//                System.out.println(resultSet.getString());
//            }

            // Close connection
            connection.close();
           
        } catch (Exception e) {
            System.out.println("Connection Failure!");
        }
    }
    
    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection connection = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("Connect Successfully!");
        } catch (Exception e) {
            System.out.println("Connect Unsuccessfully!");
        }
        
        return connection;
    }
    
}
