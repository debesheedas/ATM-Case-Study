public class Test 
{
    public static void main(String args[])
    {
        ATM atm = new ATM();
        //ATM_User user = new ATM_User(atm);
        //user.run();
        atm.getDatabase().loadDatabase();
        //atm.addDefaultData();
        for(Customer c: atm.getAllCustomers())
        {
            //System.out.println(c.getName()+" "+c.getAccNo()+" "+c.getPIN()+" "+c.getBalance()+" "+c.getLoginStatus());
        }
        ATM_User u = new ATM_User(atm);
        u.main_menu();
        atm.getDatabase().updateDatabase();
    }
    
}
