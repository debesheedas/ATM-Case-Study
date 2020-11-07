
public class Customer 
{
    private String name;
    private int AccNo;
    private int PIN;
    private double balance;
    private boolean loginStatus;
    private String IFSC;
    private String PhoneNo;
    private int time;

    Customer(String n, int acc, int pin, double b, String ifsc, String ph, int t)
    {
        name = n;
        AccNo = acc;
        PIN = pin;
        balance = b;
        loginStatus = false;
        IFSC = ifsc;
        PhoneNo = ph;
        time = t;
    }
    Customer(String n, int acc, int pin, double b, boolean status, String ifsc, String ph, int t)
    {
        name = n;
        AccNo = acc;
        PIN = pin;
        balance = b;
        loginStatus = status;
        IFSC = ifsc;
        PhoneNo = ph;
        time = t;
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
    String getIFSC()
    {
        return IFSC;
    }
    void setIFSC(String ifsc)
    {
        IFSC = ifsc;
    }
    String getPhoneNo()
    {
        return PhoneNo;
    }
    void setPhoneNo(String ph)
    {
        PhoneNo = ph;
    }
    int getTime()
    {
        return time;
    }
    void setTime(int t)
    {
        time = t;
    }
    
}
