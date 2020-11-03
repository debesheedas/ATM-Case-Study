import java.sql.*;
import java.util.*;
import java.io.*;
// Driver path   -------->   ".;JDBC/sqlite-jdbc-3.7.2.jar"
//Driver path ----> For Non - Windows  ".:JDBC/sqlite-jdbc-3.7.2.jar"

public class Database 
{
    private String db_name = "ATM.db";
    private String url = "jdbc:sqlite:db/"+db_name; // url of the database
    private ATM atm;

    Database(ATM atm){
      this.atm = atm;;
      db_name = "ATM.db";
      url = "jdbc:sqlite:db/"+db_name;
   }

    private Connection connect() 
    {
        Connection conn = null;
        try 
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection try block");
        }
        catch (Exception e) 
        {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return conn;
    }
    public void setupDatabase(){

        //deleteAllTables();
        
        try(Connection c = this.connect();
           Statement s = c.createStatement();
        ){

           s.executeUpdate("create table if not exists Customers (Name text not null , AccNo int not null unique, PIN int not null , balance real not null , loginStatus text not null)");
           s.executeUpdate("create table if not exists cashBalance(balance real not null)");


        }catch(Exception e){
           System.out.println(e.getClass().getName() +" : "+e.getMessage());
        }
    }
    private ArrayList<Customer> fillCustomers(ArrayList<Customer> customers){
        customers.clear();
        try(Connection c = this.connect();
            Statement s = c.createStatement();)
            {

            ResultSet rs = s.executeQuery("select * from Customers");
            while(rs.next())
            {
               int no = rs.getInt("AccNo");
               if(no != 0)
               {
                  String name = rs.getString("Name");
                  int pin = rs.getInt("PIN");
                  double bal = rs.getDouble("balance");
                  String loginstatus = rs.getString("loginStatus");

                  Customer e = new Customer(name, no, pin, bal, loginstatus.equals("true"));
                  customers.add(e);

               }
               
            }
            return customers; 
         }
         catch(Exception e){
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
            return null;
         }
     }

    private double getCashBalance(){
   
        try(Connection c = this.connect();
            PreparedStatement ps = c.prepareStatement("select * from cashBalance ");)
            {
            ResultSet rs = ps.executeQuery();
  
            while(rs.next())
            {
                double balance = rs.getDouble("balance");
                return balance;
            }
            return 0;
  
        }catch(Exception e)
        {
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
            return 0;
        }
     }
    //loadDatabase();
    void loadDatabase(){

        File f = new File("db/ATM.db");  

        if(!(f.isFile()))// checking if db file exists
        {    
           System.out.println("Database has not been created, filling default data in instance variables with default data");
           this.setupDatabase();                        // if not create db and tables in it
           atm.addDefaultData();  
        }
        //else if()
        else
        {
            System.out.println("Loading into Instance variables");
            atm.setCustomers(fillCustomers(new ArrayList<Customer>()));
            atm.setCashBalance(getCashBalance());
        }
       
    
    }

    private void addCashBalance(double bal)
    {
        try(Connection c = this.connect();
           PreparedStatement ps = c.prepareStatement("delete from cashBalance");
        ){
           ps.executeUpdate();           
        }catch(Exception e){
           System.out.println(e.getClass().getName() +" : "+e.getMessage());
        }
        try(Connection c = this.connect();
           PreparedStatement ps = c.prepareStatement("insert into cashBalance values (?)");
        ){
           ps.setDouble(1, bal);
           ps.executeUpdate();
           
        }catch(Exception e){
           System.out.println(e.getClass().getName() +" : "+e.getMessage());
        }
    }
    private void addCustomer(String name , int no , int pin, double bal , String loginStatus )
    {
        try(Connection c = this.connect();
            PreparedStatement ps = c.prepareStatement("insert into Customers values (?,?,?,?,?)");
         ){
            ps.setString(1, name);
            ps.setInt(2, no);
            ps.setInt(3, pin);
            ps.setDouble(4, bal);
            ps.setString(5 , loginStatus);
  
            ps.executeUpdate();
            
         }catch(Exception e){
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
         }
  
    }
  

    void updateDatabase()
    {
        System.out.println("Updating Database");
        File f = new File("db/ATM.db");  

        if(!(f.isFile()))// checking if db file exists
        {    
           System.out.println("Database is being created for first time");
           try (Connection conn = DriverManager.getConnection(url)) 
           {
            if (conn != null) 
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                this.setupDatabase();                        // if not create db and tables in it
                //atm.addDefaultData();  
            }

            } 
            catch (SQLException e) 
            {
                System.out.println(e.getMessage());
            } 
        }
        try(Connection c = this.connect();	
            Statement s = c.createStatement();	        
            )
            {	
                //s.executeUpdate("drop table if exists 'employees' ");	         
                //s.executeUpdate("drop table if exists 'floors' ");
                s.executeUpdate("delete from 'Customers' ");	         
                s.executeUpdate("delete from 'cashBalance' ");	            
        	
            }
            catch(Exception e){	
                System.out.println(e.getClass().getName() +" : "+e.getMessage());	
            }	 
            ArrayList<Customer> customers = atm.getAllCustomers(); 
            for(Customer e: customers)
            {
                addCustomer(e.getName(), e.getAccNo(), e.getPIN(), e.getBalance() ,(String)((e.getLoginStatus()) ? "true" : "false" ));
            }
            addCashBalance(atm.getCashBalance());
        
    }
    /*
    public static void main(String args[])
    {
        System.out.println("Hello");
        Database DB = new Database();
        //DB.connect();
        DB.setupDatabase();
    }*/
    
}

    

