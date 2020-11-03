
public class Customer 
{
    private String name;
    private int AccNo;
    private int PIN;
    private double balance;
    private boolean loginStatus;

    Customer(String n, int acc, int pin, double b)
    {
        name = n;
        AccNo = acc;
        PIN = pin;
        balance = b;
        loginStatus = false;
    }
    Customer(String n, int acc, int pin, double b, boolean status)
    {
        name = n;
        AccNo = acc;
        PIN = pin;
        balance = b;
        loginStatus = status;
    }

    String getName()
    {
        return name;
    }
    void setName(String n)
    {
        name = n;
    }
    int getAccNo()
    {
        return AccNo;
    }
    void setAccNo(int no)
    {
        AccNo = no;
    }
    int getPIN()
    {
        return PIN;
    }
    void setPIN(int pin)
    {
        PIN = pin;
    }
    double getBalance()
    {
        return balance;
    }
    void setBalance(double b)
    {
        balance = b;
    }
    boolean getLoginStatus()
    {
        return loginStatus;
    }
    void setLoginStatus(boolean status)
    {
        loginStatus = status;
    }

    
}
