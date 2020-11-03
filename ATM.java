import java.util.*;

public class ATM
{
    private Database db = new Database(this);
    private double cashBalance;
    private ArrayList<Customer> allCustomers= new ArrayList<Customer>();



    Database getDatabase()
    {
        return db;
    }
    double getCashBalance()
    {
        return cashBalance;
    }
    void setCashBalance(double c)
    {
        cashBalance = c;
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
    Customer searchAccountAndPIN(int acc, int pin)
    {
        for(Customer c: allCustomers)
        {
            if(c.getAccNo()==acc && c.getPIN()==pin)
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

    void addDefaultData()
    {
        cashBalance = 50000;
        this.addCustomer(new Customer("Ada Lovelace", 12345, 23232, 20100));
        this.addCustomer(new Customer("Frances Allen", 54321, 23232, 30100));
        this.addCustomer(new Customer("Ada Lovelace", 20001, 23232, 4100));
        this.addCustomer(new Customer("Ada Lovelace", 12001, 23232, 5000));
    }
}