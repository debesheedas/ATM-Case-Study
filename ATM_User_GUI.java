import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ATM_User_GUI implements userInterface, ActionListener
{
    ATM atm;
    Customer c;
    ATM_User_GUI(ATM a)
    {
        atm=a;
        //c=null;
    }
}



