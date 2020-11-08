
public class Customer 
{
    private String name;
    private int AccNo;
    private String PIN;
    private double balance;
    private boolean loginStatus;
    private String IFSC;
    private String PhoneNo;
    private int time;
    private boolean blockStatus;
    private Time endTime;

    Customer(String n, int acc, String pin, double b, String ifsc, String ph, int t)
    {
        name = n;
        AccNo = acc;
        PIN = BCrypt.hashpw(pin, BCrypt.gensalt());
        balance = b;
        loginStatus = false;
        IFSC = ifsc;
        PhoneNo = ph;
        time = t;
        blockStatus = true;
        endTime = new Time();
    }
    Customer(String n, int acc, String pin, double b, boolean status, String ifsc, String ph, int t, boolean bs, Time end)
    {
        name = n;
        AccNo = acc;
        PIN = BCrypt.hashpw(pin, BCrypt.gensalt());
        balance = b;
        loginStatus = status;
        IFSC = ifsc;
        PhoneNo = ph;
        time = t;
        blockStatus = bs;
        endTime = end;
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
    String getPIN()
    {
        return PIN;
    }
    void setPIN(String pin)
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
    boolean getBlockStatus()
    {
        return blockStatus;
    }
    void setBlockStatus(boolean status)
    {
        blockStatus = status;
    }
    Time getEndTime()
    {
        return endTime;
    }
    void setEndTime(Time t)
    {
        endTime = t;
    }
    void blockCard()
    {
        Time t = new Time();
        endTime = t.add(time);
        blockStatus=false;
    }
}
