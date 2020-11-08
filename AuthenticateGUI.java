import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AuthenticateGUI implements ActionListener
{
    private ATM atm1, atm2;
    private static Customer c;
    private static int n;
    private static int temp;
    private static JFrame frame;
	private static JPanel panel;
    private static JLabel welcome;
    private static JLabel label;
	private static JTextField AccNo;
	private static JLabel PINLabel;
    private static JPasswordField PIN;
    private static JLabel OTPLabel;
	private static JPasswordField OTP;
	private static JButton sendOTP;
    private static JLabel otpSent;
    private static JButton authenticate;
    private static JLabel success;
    private static int tempOTP;
    //private String username;
    //private String password;

    AuthenticateGUI(ATM a, ATM b)
    {
        atm1=a;
        atm2=b;
        n=3;
    }

    void run()
    {
        frame = new JFrame();
		panel = new JPanel();
		
		frame.setSize(1440,800);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
		frame.add(panel);
		frame.setTitle("Welcome - Authenticate");
        panel.setLayout(null);
        
        welcome = new JLabel("Welcome to ATM of"+atm1.getName());//add name of bank here
		welcome.setBounds(540, 20, 400, 30);
		panel.add(welcome);
		
		label = new JLabel("Enter Account No");
		label.setBounds(60, 100, 300, 25);
		panel.add(label);
		
		AccNo = new JTextField();
		AccNo.setBounds(390, 100, 150, 25);
		panel.add(AccNo);
		
		PINLabel = new JLabel("Enter PIN");
		PINLabel.setBounds(60, 160, 300, 25);
		panel.add(PINLabel);
		
		PIN = new JPasswordField();
		PIN.setBounds(390, 160, 150, 25);
		panel.add(PIN);
		
		sendOTP= new JButton("SEND OTP");
		sendOTP.setBounds(60, 220, 200, 40);
		sendOTP.addActionListener(new AuthenticateGUI(atm1, atm2));
		panel.add(sendOTP);
		
	    otpSent = new JLabel();
		otpSent.setBounds(60, 280, 800, 25);
		panel.add(otpSent);
		//set Text if credentials are correct		
        
        OTPLabel = new JLabel("Enter OTP");
		OTPLabel.setBounds(60, 340, 300, 25);
		panel.add(OTPLabel);
		
		OTP = new JPasswordField();
		OTP.setBounds(390, 340, 150, 25);
		panel.add(OTP);

        authenticate = new JButton("AUTHENTICATE");
		authenticate.setBounds(60, 400, 200, 40);
		authenticate.addActionListener(new AuthenticateGUI(atm1, atm2));
        panel.add(authenticate);

        success = new JLabel("");
		success.setBounds(60, 460, 800, 25);
		panel.add(success);
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
            if(e.getSource().equals(sendOTP))
            {
                int no = Integer.parseInt(AccNo.getText());
                if(searchAccount(no)==null)
                {
                    otpSent.setText("Invalid Account Number. Try again");
                }
                else
                {
                    Customer x = searchAccount(no);
                
                    if(!checkBlockStatus(new Time(), x))
                    {
                        otpSent.setText("Your card is blocked");
                    }
                    else if(checkPIN(x, new String(PIN.getPassword())))
                    {
                        n=3;
                        generateOTP();
                        c=x;
                        otpSent.setText("OTP has been sent to your phone ending in XXXXXX"+x.getPhoneNo().substring(5));
                    }
                    else 
                    {
                        if(x.getAccNo()==temp)
                        {
                            n--;
                        }
                        else
                        {
                            n=3;
                        }
                        if(n==0)
                        {
                            x.blockCard();//block card
                            otpSent.setText("Your card has been blocked");
                            n=3;
                        }
                        else
                        otpSent.setText("Invalid PIN. "+n+" tries remaining");
                        temp=x.getAccNo();
                    }
                }
            //check if account no exists in both banks and check block status
            //notify which bank
            //if not exists  throw account number wrong
            //if exists check password
            //check increment count
            //if correct - generate and throw otp
            //if not correct increment count throw invalid password


            }
            else if(e.getSource().equals(authenticate))
            {
                //System.out.println("Authenticate clicked");
                if(Integer.toString(tempOTP).equals(new String(OTP.getPassword())))
                {
                    System.out.println("**");
                    success.setText("Login successful");
                    if(c!=null)
                    {
                        c.setLoginStatus(true);
                        System.out.println("Login successful");
                        System.out.println(c.getLoginStatus());
                    }
                    atm1.getDatabase().updateDatabase();
                    atm2.getDatabase().updateDatabase();
                    frame.setVisible(false);
                    frame.dispose();
                    MainMenuGUI m = new MainMenuGUI(atm1, atm2, c);
                    m.run();
                    //open up main menu
                }
                else
                {
                    success.setText("Incorrect OTP. Try again or Click Resend OTP");
                }
            //check if entered otp is valid
            //if not- show resend otp message
            //if yes = show success, change login status, open main menu, terminate this window, pass customer object

            }

        }
        catch(Exception er)
        {
            System.out.println("Exception occurred: "+er);
            System.out.println(er.getStackTrace());
        }
        
        
    }

    Customer searchAccount(int no)
    {
        if (atm1.searchByAccNo(no)!=null)
        {
            return atm1.searchByAccNo(no);
        }
        return atm2.searchByAccNo(no);
    }
    boolean checkPIN(Customer e, String s)
    {
        //System.out.println(e.getAccNo());
        //System.out.println(e.getPIN());
        //System.out.println(s);
        if (BCrypt.checkpw(s, e.getPIN()))
        {
            //System.out.println("true");
            return true;
        }
        return false;
    }
    boolean checkBlockStatus(Time t, Customer y)
    {
        //System.out.println("Checking block status");
        //System.out.println("End time"+ y.getEndTime().getTime());
        Time now = new Time();
        System.out.println("Current Time"+ now.getTime());
        if(t.isGreaterThan(y.getEndTime()))
        {
            y.setBlockStatus(true);
            return true;
        }
        return false;
    }
    void generateOTP()
    {
        int i = (int)(Math.random()*10000);
        tempOTP=i;
        System.out.println("Your OTP is: "+i);
        System.out.println("*");
    }
   
 
 
}


