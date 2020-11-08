public class Test 
{
    public static void main(String args[])
    {
        ATM atm1 = new ATM("State Bank of India", "SBIN", 50000, 30000);
        atm1.getDatabase().loadDatabase();


        ATM atm2 = new ATM("ICICI Bank", "ICIC", 60000, 20000);
        atm2.getDatabase().loadDatabase();
        //ATM_User user = new ATM_User(atm);
        //user.run();
        //atm.getDatabase().loadDatabase();
        //atm.addDefaultData();
        
        //ATM_User u = new ATM_User(atm1);
        //u.main_menu();
        ATM_User_GUI u = new ATM_User_GUI(atm1, atm2);

        //atm1.getDatabase().updateDatabase();
        //atm2.getDatabase().updateDatabase();
    }
    
}
