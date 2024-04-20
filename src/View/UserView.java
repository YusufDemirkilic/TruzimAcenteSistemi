package View;

import Core.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame{
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JButton btn_login;

    public UserView(){
        add(container);
        setSize(400,450);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        setVisible(true);


    }
}
