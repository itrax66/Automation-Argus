package DbConnection;

import FileManagers.RunProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static DbConnection connection;
    private Connection c = null;
    Statement stmt = null;

    //Defines the connection to the postgresql db inside docker
    private DbConnection(){
        try {
            c = DriverManager.getConnection("jdbc:postgresql://"+ RunProperties.getDbUrl()+":"+RunProperties.getDbPort()+
                    "/"+RunProperties.getDatabaseName()+"",RunProperties.getDbUser(),RunProperties.getDbPassword());
            System.out.println("successfully connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DbConnection getInstance(){
        if(connection == null){
            connection = new DbConnection();
        }
        return connection;
    }

    //Sends an up
    public void sendQuarry(String sQuarry){
        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sQuarry);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeDbConnection(){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
