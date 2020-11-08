import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private static JButton change;
    private static JButton confirm;
    private static JLabel pinLabel;
    private static JPasswordField pin;
    private static JLabel phoneLabel;
    private static JTextField phone;
    private static JLabel timeLabel;
    private static JTextField time;
    private static JLabel success;
    private static int count;
   

    MainMenuGUI(ATM a, ATM b, Customer d)
    {
        atm1=a;
        atm2=b;
        c=d;
        mode=0;
        count=atm1.getAllTransactions().size();
        //run();
        
    }

    void run()
    {
        frame = new JFrame();
		panel = new JPanel();
		
		frame.setSize(1440,800);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		frame.add(panel);
		frame.setTitle("Welcome - Main Menu");
        panel.setLayout(null);
        
        welcome = new JLabel("Welcome to "+atm1.getName()+" ATM");//add name of bank here
		welcome.setBounds(540, 20, 400, 30);
		panel.add(welcome);
		
		label = new JLabel("Enter Amount");
		label.setBounds(480, 100, 300, 25);
        panel.add(label);
        label.setVisible(false);
		
		value = new JTextField();
		value.setBounds(670, 100, 150, 25);
        panel.add(value);
        value.setVisible(false);

        pinLabel = new JLabel("Enter new Pin");
		pinLabel.setBounds(480, 160, 300, 25);
        panel.add(pinLabel);
        pinLabel.setVisible(false);
		
		pin = new JPasswordField();
		pin.setBounds(670, 160, 150, 25);
        panel.add(pin);
        pin.setVisible(false);

        phoneLabel = new JLabel("Enter Phone Number");
		phoneLabel.setBounds(480, 200, 300, 25);
        panel.add(phoneLabel);
        phoneLabel.setVisible(false);
		
		phone = new JTextField();
		phone.setBounds(670, 200, 150, 25);
        panel.add(phone);
        phone.setVisible(false);

        timeLabel = new JLabel("Enter Block Time in Minutes");
		timeLabel.setBounds(480, 240, 300, 25);
        panel.add(timeLabel);
        timeLabel.setVisible(false);
		
		time = new JTextField();
		time.setBounds(670, 240, 150, 25);
        panel.add(time);
        time.setVisible(false);
		
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

        change= new JButton("CHANGE DETAILS");
		change.setBounds(60, 280, 200, 40);
		change.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(change);
        
        exit= new JButton("EXIT");
		exit.setBounds(60, 340, 200, 40);
		exit.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(exit);
        
        confirm= new JButton("CONFIRM");
		confirm.setBounds(480, 360, 200, 40);
		confirm.addActionListener(new MainMenuGUI(atm1, atm2, c));
        panel.add(confirm);
        confirm.setVisible(false);


        success = new JLabel("Transaction successful");
		success.setBounds(480, 460, 800, 25);
        panel.add(success);
        success.setVisible(false);
        frame.setVisible(true);
    
    
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            if (JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to close this window?", "Close Window?", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    if(c!=null)
                    {
                        c.setLoginStatus(false);
                    }
                    //log out any logged in customer for security purposes
                    atm1.getDatabase().updateDatabase();
                    atm2.getDatabase().updateDatabase(); 
                System.exit(0);
            }
        }
    });
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
                success.setVisible(false);
                mode=1;

            }
            else if(e.getSource().equals(withdraw))
            {
                setLeftVisible(false);
                setRightVisible(true);
                success.setVisible(false);
                mode=-1;
            }
            else if(e.getSource().equals(mini))
            {
                //setLeftVisible(false);
                setRightVisible(false);
                success.setText("Your mini statement has been printed on the console");
                printStatement();
                success.setVisible(true);
            }
            else if(e.getSource().equals(change))
            {
                //
                setLeftVisible(false);
                setRightVisible(false);
                setChangesVisible(true);
                confirm.setVisible(true);
                success.setVisible(false);
                mode=0;
            }
            else if(e.getSource().equals(exit))
            {
                setLeftVisible(false);
                setRightVisible(false);
                success.setText("Logged out successfully");
                success.setVisible(true);
                if(c!=null)
                c.setLoginStatus(false);
                frame.setVisible(false);
                frame.dispose();
                AuthenticateGUI u = new AuthenticateGUI(atm1, atm2);
                u.run();
            }
            else if(e.getSource().equals(confirm))
            {
                double processingFee=0;
                if(!c.getIFSC().substring(0,4).equals(atm1.getCode()))
                {
                    processingFee=20.0;
                }
                System.out.println("Processing Fee:"+processingFee);
                double amt = Double.parseDouble("0"+value.getText());
                if(mode==1)
                {
                    atm1.setCashBalance(atm1.getCashBalance()+amt);
                    amt=amt-processingFee;
                    c.setBalance(c.getBalance()+amt);
                    Transaction t = new Transaction(++count, c.getAccNo(), "DEPOSIT", amt, c.getBalance());
                    atm1.addTransaction(t);
                    success.setText("Transaction successful");
                }
                else if(mode==-1)
                {
                    amt=amt+processingFee;
                    if(atm1.getCashBalance()>amt && c.getBalance()>amt)
                    {
                        
                        c.setBalance(c.getBalance()-amt);
                        success.setText("Transaction successful");
                        amt=amt-processingFee;
                        atm1.setCashBalance(atm1.getCashBalance()-amt);
                        Transaction t = new Transaction(++count, c.getAccNo(), "WITHDRAWAL", -1*amt, c.getBalance());
                        atm1.addTransaction(t);
                    }
                    else
                    {
                        success.setText("Transaction unsuccessful");
                        
                    }
                }
                else
                {
                    if(changeSettings())
                    {
                        setLeftVisible(true);
                        setChangesVisible(false);
                        setRightVisible(false);
                        success.setVisible(true);
                    }
                    //change settings
                }
                setLeftVisible(true);
                setRightVisible(false);
                setChangesVisible(false);
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
        change.setVisible(status);
        exit.setVisible(status);
    }
    void setChangesVisible(boolean status)
    {
        pinLabel.setVisible(status);
        pin.setVisible(status);
        phoneLabel.setVisible(status);
        phone.setVisible(status);
        timeLabel.setVisible(status);
        time.setVisible(status);

    }
    void printStatement()
    {
        ArrayList<Transaction> a = new ArrayList<Transaction>();
        a = atm1.returnTransactionsByAccNo(c.getAccNo());
        System.out.println();
        System.out.println("Account No: "+c.getAccNo());
            System.out.println("Name: "+c.getName());
            System.out.println("IFSC Code: "+c.getIFSC());
            System.out.println("Transactions");
        int slno=1;
        for(Transaction t: a)
        {
            System.out.println(slno++ +" "+t.getType()+" "+t.getValue()+" "+t.getBalance());
        }
        System.out.println();
    }
    boolean changeSettings()
    {
      int n;
      try
      {
        String input = new String(pin.getPassword());
        n = Integer.parseInt(input);
        if(input.length()==5)
        {
            String p = BCrypt.hashpw(input, BCrypt.gensalt());
            c.setPIN(p);
        }
        else
        {
            success.setText("Invalid Input");
            return false;
        }
        String ph = phone.getText();
        n = Integer.parseInt(ph);
        if(ph.length()==10)
        {
            c.setPhoneNo(ph);
        }
        else
        {
            success.setText("Invalid Input");
            return false;
        }
        String t = time.getText();
        n = Integer.parseInt(t);
        c.setTime(n);
        success.setText("Changes saved successfully");

        success.setVisible(true);
        return true;

      }  

      catch(Exception error)
    {
        success.setText("Invalid Input");
        success.setVisible(true);
        System.out.println("Invalid Input");
        System.out.println(error);
        return false;
    }
        
    }
    
}