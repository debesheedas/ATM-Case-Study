import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticateGUI implements ActionListener
{
	//private ATM atm1,
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
    //private String username;
    //private String password;

    public static void main(String args[])
    {
        run();
    }
    public static void run()
    {
        frame = new JFrame();
		panel = new JPanel();
		
		frame.setSize(1440,1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.add(panel);
		frame.setTitle("Welcome - Authenticate");
        panel.setLayout(null);
        
        welcome = new JLabel("Welcome to ATM");//add name of bank here
		welcome.setBounds(20, 5, 100, 25);
		panel.add(welcome);
		
		label = new JLabel("Account Number");
		label.setBounds(20, 20, 80, 25);
		panel.add(label);
		
		AccNo = new JTextField();
		AccNo.setBounds(100, 20, 165, 25);
		panel.add(AccNo);
		
		PINLabel = new JLabel("Enter PIN");
		PINLabel.setBounds(20, 50, 165, 25);
		panel.add(PINLabel);
		
		PIN = new JPasswordField();
		PIN.setBounds(100, 50, 165, 25);
		panel.add(PIN);
		
		sendOTP= new JButton("SEND OTP");
		sendOTP.setBounds(100, 80, 80, 25);
		sendOTP.addActionListener(new AuthenticateGUI());
		panel.add(sendOTP);
		
	    otpSent = new JLabel();
		otpSent.setBounds(20, 110, 300, 25);
		panel.add(otpSent);
		//set Text if credentials are correct		
        

        authenticate = new JButton("AUTHENTICATE");
		authenticate.setBounds(100, 140, 80, 25);
		authenticate.addActionListener(new AuthenticateGUI());
        panel.add(authenticate);

        success = new JLabel();
		success.setBounds(20, 170, 300, 25);
		panel.add(success);
        frame.setVisible(true);
    
    }

    @Override
	public void actionPerformed(ActionEvent e) {
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
			success.setText("Login Successful!");
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

