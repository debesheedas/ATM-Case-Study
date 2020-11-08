class ATM_User_GUI implements userInterface
{
    ATM atm1, atm2;
    Customer c;
    ATM_User_GUI(ATM a, ATM b)
    {
        atm1 = a;
        atm2 = b;
        authentication();
        //c=null;
    }
    public void authentication()
    {
        AuthenticateGUI a = new AuthenticateGUI(atm1, atm2);
        a.run();
    }
    public void main_menu(Customer c)
    {
        //not required - is dealt with in MainMenuGUI class
    }
}



