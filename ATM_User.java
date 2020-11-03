import java.util.*;
interface userInterface
{
    boolean authentication();
    void main_menu();
}

class ATM_User implements userInterface
{
    ATM atm;
    Customer c;
    ATM_User(ATM a)
    {
        atm=a;
        //c=null;
    }
    public boolean authentication()
    {
        Scanner sc = new Scanner(System.in);
        try
            {
                System.out.println("Welcome to the ATM. Please enter your unique 5-digit Account Number:");
                int acc = Integer.parseInt(sc.nextLine());
                System.out.println("Please enter your 5-digit PIN:");
                int pin = Integer.parseInt(sc.nextLine());
                if(atm.searchAccountAndPIN(acc, pin)!=null)
                {
                    c = atm.searchAccountAndPIN(acc, pin);
                    c.setLoginStatus(true);
                    return true;
                }
                else
                {
                    System.out.println("Authentication failed. Please enter correct Account Number and PIN and try again");
                    return false;
                }
            }
            catch(Exception e)
            {
                System.out.println("Please enter a valid number");
                return false;
            }
            
    }
    public void main_menu()
    {
        Scanner sn = new Scanner(System.in);
        char choice = 'y';
        while(choice=='y')
        {
            //System.out.println(c);
            //if(c!=null)
            //System.out.println(c.getLoginStatus());
            System.out.println("*");
            if((c!=null && c.getLoginStatus()==true) || (this.authentication()==true))
            {
                //System.out.println(c);
                try
                {
                    System.out.println("Press 1: View my Balance\nPress 2: Withdraw Amount\nPress 3: Deposit Amount\nPress 4: Exit/Logout");
                    int n = Integer.parseInt(sn.nextLine());
                    switch(n)
                    {
                        case 1:
                            System.out.println("Displaying Balance and Details");
                            System.out.println("Name: "+c.getName()+"    Account Number: "+c.getAccNo());
                            System.out.println("Balance: "+c.getBalance());
                            break;
                        case 2:
                            System.out.println("Enter the amount to withdraw:");
                            double amount = Double.parseDouble(sn.nextLine());
                            if(amount>c.getBalance())
                            {
                                System.out.println("You do not have enough balance to withdraw this amount. Please enter a smaller number");
                            }
                            else if(atm.getCashBalance()<amount)
                            {
                                System.out.println("Sorry, transaction invalid because ATM machine is out of cash");
                            }
                            else if(amount>=0)
                            {
                                c.setBalance(c.getBalance()-amount);
                                atm.setCashBalance(atm.getCashBalance()-amount);
                                System.out.println("Transaction successful. Your current balance is "+c.getBalance());
                            }
                            break;
                        case 3:
                            System.out.println("Enter the amount to deposit:");
                            double dep = Double.parseDouble(sn.nextLine());
                            if(dep>=0)
                            {
                                c.setBalance(c.getBalance()+dep);
                                System.out.println("Transaction successful. Your current balance is "+c.getBalance());
                                atm.setCashBalance(atm.getCashBalance()+dep);
                            }
                            else
                            {
                                System.out.println("Please enter a valid amount to deposit");
                            }
                            break;
                        case 4:
                            choice = 'n';
                            break;
                        default:
                            System.out.println("Please enter a valid choice");
                    }

                }
                catch(Exception e)
                {
                    System.out.println("Please enter a valid number");
                } 
            } 
        }
        if(c!=null)//for security purposes, logout the user by default before exiting the process
        {
            atm.searchByAccNo(c.getAccNo()).setLoginStatus(false);
            System.out.println("You have been logged out successfully.");
        }
    }

    
}
