package View;

import Controller.UserManager;
import Core.Helper;
import Entity.User;

import javax.swing.*;

public class LoginView extends JFrame{
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JButton btn_login;
    private UserManager userManager;

    public LoginView(){
        add(container);
        setSize(400,450);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        setVisible(true);

        this.userManager=new UserManager();
        btn_login.addActionListener(e -> {
                JTextField[] checkFieldList = {this.fld_username,this.fld_pass};
                if (Helper.isFieldListEmpty(checkFieldList)){
                    Helper.showMsg("fill");
                }
                else {
                    User loginUser=this.userManager.findByLogin(this.fld_username.getText(),this.fld_pass.getText());
                    if (loginUser==null){
                        Helper.showMsg("notFound");
                    } else  {
                        String role= loginUser.getRole();
                        if (role.equals("admin")){
                            AdminView adminView=new AdminView();
                            adminView.setVisible(true);
                        }
                    }

                }
        });
    }


}
