
public class Transaction
{
    private int refID;
    private int AccNo;
    private String type;
    private double value;
    private double balance;

    Transaction(int id, int no, String t, double v, double b)
    {
        refID = id;
        AccNo = no;
        type = t;
        value = v;
        balance = b;
    }

    int getRefID()
    {
        return refID;
    }
    void setRefID(int id)
    {
        refID = id;
    }
    int getAccNo()
    {
        return AccNo;
    }
    void setAccNo(int no)
    {
        AccNo = no;
    }
    String getType()
    {
        return type;
    }
    void setType(String t)
    {
        type = t;
    }
    double getValue()
    {
        return value;
    }
    void setValue(double v)
    {
        value= v;
    }
    double getBalance()
    {
        return balance;
    }
    void setBalance(double b)
    {
        balance = b;
    }
}