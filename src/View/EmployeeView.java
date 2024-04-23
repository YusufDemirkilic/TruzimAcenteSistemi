package View;

import Core.Helper;

import javax.swing.*;

public class EmployeeView extends JFrame{
    private JPanel container;
    private JTabbedPane hotel_management;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;

      EmployeeView(){
        add(container);
        setSize(600,700);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        setVisible(true);
    }
}
