import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class MainMenuGUI implements ActionListener
{
    private ATM atm1, atm2;
    private Customer c;
    private static JFrame frame;
	private static JPanel panel;
    private static JLabel welcome;
    private static JLabel label;
	private static JTextField value;
	private static int mode;
	private static JButton deposit;
    private static JButton withdraw;
    private static JButton mini;
    private static JButton exit;
    private static JButton confirm;
    private static JLabel success;
    private static int count;
   

    MainMenuGUI(ATM a, ATM b, Customer d)
    {
        atm1=a;
        atm2=b;
        c=d;
        mode=0;
        count=atm1.getAllTransactions().size();
        run();
        
    }

    void run()
    {
        frame = new JFrame();
		panel = new JPanel();
		
		frame.setSize(1440,1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.add(panel);
		frame.setTitle("Welcome - Main Menu");
        panel.setLayout(null);
        
        welcome = new JLabel("Welcome to "+atm1.getName()+" ATM");//add name of bank here
		welcome.setBounds(540, 20, 400, 30);
		panel.add(welcome);
		
		label = new JLabel("Enter Amount");
		label.setBounds(480, 100, 300, 25);
		panel.add(label);
		
		value = new JTextField();
		value.setBounds(670, 100, 150, 25);
        panel.add(value);
		
		deposit= new JButton("DEPOSIT");
		deposit.setBounds(60, 100, 200, 40);
		deposit.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(deposit);
        
        withdraw= new JButton("WITHDRAWAL");
		withdraw.setBounds(60, 160, 200, 40);
		withdraw.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(withdraw);
        
        mini= new JButton("MINI STATEMENT");
		mini.setBounds(60, 220, 200, 40);
		mini.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(mini);
        
        exit= new JButton("EXIT");
		exit.setBounds(60, 280, 200, 40);
		exit.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(exit);
        
        exit= new JButton("CONFIRM");
		exit.setBounds(480, 180, 200, 40);
		exit.addActionListener(new MainMenuGUI(atm1, atm2, c));
		panel.add(exit);


        success = new JLabel("Transaction successful");
		success.setBounds(480, 280, 800, 25);
		panel.add(success);
        frame.setVisible(true);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            if(e.getSource().equals(deposit))
            {
                setLeftVisible(false);
                setRightVisible(true);
                mode=1;

            }
            else if(e.getSource().equals(withdraw))
            {
                setLeftVisible(false);
                setRightVisible(true);
                mode=-1;
            }
            else if(e.getSource().equals(mini))
            {
                setLeftVisible(false);
                success.setText("Your mini statement has been printed on the console");
                printStatement();
                success.setVisible(true);
            }
            else if(e.getSource().equals(exit))
            {
                setLeftVisible(false);
                success.setText("Logged out successfully");
                success.setVisible(true);
                if(c!=null)
                c.setLoginStatus(false);
                frame.setVisible(false);
                frame.dispose();
                AuthenticateGUI u = new AuthenticateGUI(atm1, atm2);
            }
            else if(e.getSource().equals(confirm))
            {
                double amt = Double.parseDouble(value.getText());
                if(mode==1)
                {
                    atm1.setCashBalance(atm1.getCashBalance()+amt);
                    c.setBalance(c.getBalance()+amt);
                    Transaction t = new Transaction(++count, c.getAccNo(), "DEPOSIT", amt, c.getBalance());
                    atm1.addTransaction(t);
                    success.setText("Transaction successful");
                }
                else
                {
                    if(atm1.getCashBalance()>amt && c.getBalance()>amt)
                    {
                        atm1.setCashBalance(atm1.getCashBalance()-amt);
                        c.setBalance(c.getBalance()-amt);
                        success.setText("Transaction successful");
                        
                        Transaction t = new Transaction(++count, c.getAccNo(), "WITHDRAWAL", -1*amt, c.getBalance());
                        atm1.addTransaction(t);
                    }
                    else
                    {
                        success.setText("Transaction unsuccessful");
                        
                    }
                }
                setLeftVisible(true);
                setRightVisible(false);
                success.setVisible(true);
            }
            atm1.getDatabase().updateDatabase();
            atm2.getDatabase().updateDatabase();

        }
        catch(Exception error)
        {
            System.out.println(error);
        }

    }
    void setRightVisible(boolean status)
    {
        success.setVisible(status);
        value.setVisible(status);
        confirm.setVisible(status);
        label.setVisible(status);
    }
    void setLeftVisible(boolean status)
    {
        deposit.setVisible(status);
        withdraw.setVisible(status);
        mini.setVisible(status);
        exit.setVisible(status);
    }
    void printStatement()
    {
        ArrayList<Transaction> a = new ArrayList<Transaction>();
        a = atm1.returnTransactionsByAccNo(c.getAccNo());
        System.out.println("Account No: "+c.getAccNo());
            System.out.println("Name: "+c.getName());
            System.out.println("IFSC Code: "+c.getIFSC());
            System.out.println("Transactions");
        int slno=1;
        for(Transaction t: a)
        {
            System.out.println(slno++ +" "+t.getType()+" "+t.getValue()+" "+t.getBalance());
        }
    }
}