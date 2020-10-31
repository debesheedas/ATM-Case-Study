import java.sql.*;
import java.util.*;

// Driver path   -------->   ".;JDBC/sqlite-jdbc-3.7.2.jar"
//Driver path ----> For Non - Windows  ".:JDBC/sqlite-jdbc-3.7.2.jar"

public class Database 
{
    private String db_name = "ATM.db";
    private String url = "jdbc:sqlite:db/"+db_name; // url of the database
    //private ATM atm;

    /*Database(ATM atm){
      this.atm = atm;;
      db_name = "ATM.db";
      url = "jdbc:sqlite:db/"+db_name;
   }*/

   private Connection connect() {
    Connection conn = null;
    try {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);
    } catch (Exception e) {
        System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return conn;
}

    public static void main(String args[])
    {
        System.out.println("Hello");
        Database DB = new Database();
        DB.connect();
    }
    
}

    

