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

    Database(ATM atm)
    {
      this.atm = atm;
      db_name = atm.getCode()+"ATM.db";
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

           s.executeUpdate("create table if not exists Customers (Name text not null , AccNo int not null unique, PIN text not null , balance real not null , loginStatus text not null, IFSC text not null, PhoneNo text not null, BlockTime int not null, blockStatus text not null, endTime text not null)");
           s.executeUpdate("create table if not exists BankDetails(Name text not null, Code text not null, balance real not null, refresh real not null)");
           s.executeUpdate("create table if not exists Transactions (RefID int not null unique, AccNo int not null, type text not null, value real not null, balance real not null)");
          

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
                  String pin = rs.getString("PIN");
                  double bal = rs.getDouble("balance");
                  boolean loginstatus = rs.getString("loginStatus").equals("true");
                    String ifsc = rs.getString("IFSC");
                    String phone = rs.getString("PhoneNo");
                    int time = rs.getInt("BlockTime");
                    boolean bs = rs.getString("blockStatus").equals("true");
                    Time end = new Time(rs.getString("endTime"));
                  Customer e = new Customer(name, no, pin, bal, loginstatus, ifsc, phone, time, bs, end);
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

     private ArrayList<Transaction> fillTransactions(ArrayList<Transaction> transactions){
        transactions.clear();
        try(Connection c = this.connect();
            Statement s = c.createStatement();)
            {

            ResultSet rs = s.executeQuery("select * from Transactions");
            while(rs.next())
            {
               int id = rs.getInt("RefID");
               if(id!= 0)
               {
                   int no = rs.getInt("AccNo");
                   String t = rs.getString("type");
                   double v = rs.getDouble("value");
                   double b = rs.getDouble("balance");
                  
                  Transaction e = new Transaction(id, no, t, v, b);
                  transactions.add(e);

               }
               
            }
            return transactions; 
         }
         catch(Exception e){
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
            return null;
         }
     }

    private Vector<Object> getBankDetails(){
   
        try(Connection c = this.connect();
            PreparedStatement ps = c.prepareStatement("select * from BankDetails ");)
            {
            ResultSet rs = ps.executeQuery();
            Vector<Object> v = new Vector<Object>();
            v.add("3");
            System.out.println(v.get(0));
            System.out.println("**");
            System.out.println("**");
            System.out.println("rs contains"+rs);
            while(rs.next())
            {
                System.out.println("&");
                String name = rs.getString("Name");
                String code = rs.getString("Code");
                double balance = rs.getDouble("balance");
                double refresh = rs.getDouble("refresh");
                System.out.println("*");
                v.add(name);
                v.add(code);
                v.add(balance);
                v.add(refresh);
                //return v;
            }
            return v;
  
        }catch(Exception e)
        {
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
            return null;
        }
     }
    //loadDatabase();
    void loadDatabase(){
        String str = "db/"+db_name;
        File f = new File(str);  

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
            atm.setTransactions(fillTransactions(new ArrayList<Transaction>()));
            atm.setDetails(getBankDetails());
        }
       
    
    }

    private void addBankDetails(String name, String code, double bal, double r)
    {
        try(Connection c = this.connect();
           PreparedStatement ps = c.prepareStatement("delete from BankDetails");
        ){
           ps.executeUpdate();           
        }catch(Exception e){
           System.out.println(e.getClass().getName() +" : "+e.getMessage());
        }
        try(Connection c = this.connect();
           PreparedStatement ps = c.prepareStatement("insert into BankDetails values (?,?,?,?)");
        )
        {
            ps.setString(1, name);
            ps.setString(2, code);
            ps.setDouble(3, bal);
            ps.setDouble(4, r);
            ps.executeUpdate();
           
        }catch(Exception e){
           System.out.println(e.getClass().getName() +" : "+e.getMessage());
        }
    }
    private void addCustomer(String name , int no , String pin, double bal , String loginStatus, String ifsc, String phone, int t, String blockStatus, String end)
    {
        try(Connection c = this.connect();
            PreparedStatement ps = c.prepareStatement("insert into Customers values (?,?,?,?,?,?,?,?,?,?)");
         ){
            ps.setString(1, name);
            ps.setInt(2, no);
            ps.setString(3, pin);
            ps.setDouble(4, bal);
            ps.setString(5 , loginStatus);
            ps.setString(6 , ifsc);
            ps.setString(7 , phone);
            ps.setInt(8, t);
            ps.setString(9 , blockStatus);
            ps.setString(10 , end);
            ps.executeUpdate();
            
         }catch(Exception e){
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
         }
  
    }
  
    private void addTransaction(int id, int no , String t, double v, double b)
    {
        try(Connection c = this.connect();
            PreparedStatement ps = c.prepareStatement("insert into Transactions values (?,?,?,?,?)");
         ){
            ps.setInt(1, id);
            ps.setInt(2, no);
            ps.setString(3, t);
            ps.setDouble(4, v);
            ps.setDouble(5 , b);
            
            ps.executeUpdate();
            
         }catch(Exception e){
            System.out.println(e.getClass().getName() +" : "+e.getMessage());
         }
  
    }
  

    void updateDatabase()
    {
        System.out.println("Updating Database");
        String str = "db/"+db_name;
        File f = new File(str);  

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
                s.executeUpdate("delete from 'Customers' ");	         
                s.executeUpdate("delete from 'BankDetails' ");	
                s.executeUpdate("delete from 'Transactions' ");	            
        	
            }
            catch(Exception e){	
                System.out.println(e.getClass().getName() +" : "+e.getMessage());	
            }	 
            ArrayList<Customer> customers = atm.getAllCustomers(); 
            for(Customer e: customers)
            {
                addCustomer(e.getName(), e.getAccNo(), e.getPIN(), e.getBalance() ,(String)((e.getLoginStatus()) ? "true" : "false"), e.getIFSC(), e.getPhoneNo(), e.getTime(), (String)((e.getBlockStatus()) ? "true" : "false"), e.getEndTime().getTime());
            }
            addBankDetails(atm.getName(), atm.getCode(), atm.getCashBalance(), atm.getRefreshAmount());
            ArrayList<Transaction> transactions = atm.getAllTransactions(); 
            for(Transaction e: transactions)
            {
                addTransaction(e.getRefID(), e.getAccNo(), e.getType(), e.getValue(), e.getBalance());
            }
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

    

