package View;

import Core.Helper;

import javax.swing.*;

public class AdminView extends JFrame{
    private JTextField fld_pass;
    private JTextField fld_username;
    private JPanel container;
    private JComboBox cmb_role;
    private JTable table1;
    private JButton btn_finduser;
    private JButton btn_adduser;
    private JTextField fld_userfind;

    public AdminView(){
        add(this.container);
        setSize(450,500);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        setVisible(true);
    }
}
