import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticateGUI implements ActionListener
{
    private ATM atm1, atm2;
    private Customer c;
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
		
		frame.setSize(1440,1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.add(panel);
		frame.setTitle("Welcome - Authenticate");
        panel.setLayout(null);
        
        welcome = new JLabel("Welcome to ATM");//add name of bank here
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
		success.setBounds(60, 400, 800, 25);
		panel.add(success);
        frame.setVisible(true);
    
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
                if(tempOTP==(Integer.parseInt(authenticate.getText())))
                {
                    System.out.println("**");
                    success.setText("Login successful");
                    if(c!=null)
                    c.setLoginStatus(true);
                    atm1.getDatabase().updateDatabase();
                    atm2.getDatabase().updateDatabase();
                    frame.setVisible(false);
                    frame.dispose();
                    MainMenuGUI m = new MainMenuGUI(atm1, atm2, c);
                    //open up main menu
                }
                else
                {
                    success.setText("Incorrect OTP. Click Resend OTP");
                }
            //check if entered otp is valid
            //if not- show resend otp message
            //if yes = show success, change login status, open main menu, terminate this window, pass customer object

            }

        }
        catch(Exception er)
        {
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
        if (BCrypt.checkpw(s, e.getPIN()))
        {
            return true;
        }
        return false;
    }
    boolean checkBlockStatus(Time t, Customer y)
    {
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
    /*
    @Override
	public void actionPerformed(ActionEvent e) {

		//System.out.println("Button clicked");
		username = password1.getText();
		password = new String(password2.getPassword());
        //System.out.println(username + " "+ password);
        
        if(username.equals("admin")&&password.equals("password"))
		{
			success.setText("Login Successful!");100
			pl.getAdmin().setLoginStatus(true);
			pl.getDatabase().updateDatabase();

			//execute(true);
        }
        else if(pl.searchEmployeeByUsername(username, password))
        {
            success.setText("Login Successful!");
			Employee emp = pl.returnEmployeeByUsername(username, password);//method present in ParkingLot class
			
			emp.setLoginStatus(true);//setter method of Employee class
			pl.getDatabase().updateDatabase();
            //search for employee and set login status as true
        }
		else
		{
			success.setText("Login Unsuccessful, please try again");
		}	
        */
    
}

