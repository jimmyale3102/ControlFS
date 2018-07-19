package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Alejandro
 */
public class ConnectionDB {
    Connection cnt;
    String dataBase = "controlfsdb";
    String url = "jdbc:mysql://localhost:3306/" + dataBase;
    //String url = "http://localhost/phpmyadmin/db_structure.php?server=1&db=" + dataBase;
    String user = "root";
    String pass = "j3102965378";
    
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnt = (Connection)DriverManager.getConnection(url, user, pass);
            System.out.println("Connection successful.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed connection");
        }
        return cnt;
    }
    
    public void disconnect(){
        try {
            cnt.close();
            System.out.println("Disconnected");
        } catch (SQLException ex) {
            System.out.println("Could not disconnect");
        }
    }
}
