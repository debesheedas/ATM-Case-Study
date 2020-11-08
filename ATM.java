import java.util.*;

public class ATM
{
    private String name;
    private String code;
    private Database db;
    private double cashBalance;
    private double refreshAmount;
    private ArrayList<Customer> allCustomers= new ArrayList<Customer>();
    private ArrayList<Transaction> allTransactions= new ArrayList<Transaction>();

    ATM(String n, String c, double b, double r)
    {
        name=n;
        code = c;
        db = new Database(this);
        cashBalance = b;
        refreshAmount = r;
    }

    Database getDatabase()
    {
        return db;
    }
    String getName()
    {
        return name;
    }
    void setName(String n)
    {
        name = n;
    }
    String getCode()
    {
        return code;
    }
    void setCode(String c)
    {
        code = c;
    }
    double getCashBalance()
    {
        return cashBalance;
    }
    void setCashBalance(double c)
    {
        cashBalance = c;
    }
    double getRefreshAmount()
    {
        return refreshAmount;
    }
    void setRefreshAmount(double r)
    {
        refreshAmount = r;
    }
    void setDetails(Vector<Object> v)
    {
        //System.out.println(v.size());
        if(v==null || v.size()<4)
        {
            System.out.println("v is not ready");
        }
        else
        {
            name = v.get(0).toString();
        code = v.get(1).toString();
        cashBalance = Double.parseDouble(v.get(2).toString());
        refreshAmount = Double.parseDouble(v.get(3).toString());
        }
        
    }
    Customer searchByAccNo(int no)
    {
        for(Customer c: allCustomers)
        {
            if(c.getAccNo()==no)
            {
                return c;
            }
        }
        return null;
    }
    Customer searchAccountAndPIN(int acc, String pin)
    {
        for(Customer c: allCustomers)
        {
            if(c.getAccNo()==acc && c.getPIN().equals(pin))
            {
                return c;
            }
        }
        return null;
    }
    boolean addCustomer(Customer c)
    {
        if(this.searchByAccNo(c.getAccNo())==null)
        {
            allCustomers.add(c);
            return true;
        }
        return false;
    }
    boolean removeCustomer(Customer c)
    {
        if(this.searchByAccNo(c.getAccNo())!=null)
        {
            allCustomers.remove(c);
            return true;
        }
        return false;
    }
    void setCustomers(ArrayList<Customer> c)
    {
        allCustomers = c;
    }
    ArrayList<Customer> getAllCustomers()
    {
        return allCustomers;
    }
    Transaction searchByRefID(int no)
    {
        for(Transaction c: allTransactions)
        {
            if(c.getRefID()==no)
            {
                return c;
            }
        }
        return null;
    }
    boolean addTransaction(Transaction c)
    {
        if(this.searchByRefID(c.getRefID())==null)
        {
            allTransactions.add(c);
            return true;
        }
        return false;
    }
    boolean removeTransaction(Transaction c)
    {
        if(this.searchByRefID(c.getRefID())!=null)
        {
            allTransactions.remove(c);
            return true;
        }
        return false;
    }
    void setTransactions(ArrayList<Transaction> c)
    {
        allTransactions = c;
    }
    ArrayList<Transaction> getAllTransactions()
    {
        return allTransactions;
    }
    ArrayList<Transaction> returnTransactionsByAccNo(int no)
    {
        ArrayList<Transaction> a = new ArrayList<Transaction>();
        for(Transaction t: allTransactions)
        {
            if(t.getAccNo()==no)
            {
                a.add(t);
            }
        }
        return a;
    }
    /*void addDefaultData()//use this for initialising data of SBI Bank
    {
        this.addCustomer(new Customer("Ada Lovelace", 12345, "23232", 20100, code+"0005943", "1234554321", 1));
        this.addCustomer(new Customer("Frances Allen", 54321, "23232", 30100, code+"1234567", "0987655555", 2));
        this.addCustomer(new Customer("Frances Spence", 20001, "23232", 4100, code+"1234567", "0987654320", 2));
        this.addCustomer(new Customer("Judy Clapp", 12001, "23232", 5000, code+"1234567", "0987654323", 2));
    }*/
    
    void addDefaultData()//use this for initialising data of ICICI Bank
    {
        this.addCustomer(new Customer("Grace Hopper", 11221, "32323", 20100, code+"0005943", "1234554320", 1));
        this.addCustomer(new Customer("Betty Jean", 50400, "32323", 30100, code+"1234567", "4040654311", 2));
        this.addCustomer(new Customer("Radia Pearlman", 23331, "32323", 4100, code+"1234567", "0987654333", 2));
        this.addCustomer(new Customer("Karen Jones", 67890, "32323", 5000, code+"1234567", "0987654322", 2));
    }
}