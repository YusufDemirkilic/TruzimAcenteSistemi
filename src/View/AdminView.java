package View;

import Controller.UserManager;
import Core.Helper;
import Entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminView extends JFrame {
    private JPanel container;
    private JComboBox cmb_filter;
    private JTable table1;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JComboBox cmb_role;
    private JButton btn_delete;
    private JButton btn_update;
    private JButton btn_register;
    private JButton btn_logaout_admin;
    private JButton btn_help;
    private DefaultTableModel model = new DefaultTableModel();
    UserManager userManager = new UserManager();

    public AdminView() {
        add(this.container);
        setSize(450, 500);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        showTable();
        setVisible(true);
        cmb_role.addItem("admin");// admin ve employe rolleri eklme
        cmb_role.addItem("employee");

        btn_update.addActionListener(e -> {
            // Seçilen kullanıcıdaki veri değişikliği güncelleme ve database'e kaydetme
            for (int i = 0; i < model.getRowCount(); i++) {
                User user = new User();
                user.setId(Integer.parseInt(model.getValueAt(i, 0).toString()));
                user.setUsername(model.getValueAt(i, 1).toString());
                user.setRole(model.getValueAt(i, 2).toString());

                try {
                    userManager.updateUser(user);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });

        cmb_filter.addActionListener(e -> {// kullanıcı filtreleme için combobox veri atama işlemi
            String role = cmb_filter.getItemAt(cmb_filter.getSelectedIndex()).toString();
            model.setRowCount(0);

            if (!role.equals("All")) {
                userManager.findByRole(role).stream().forEach(user -> {
                    model.addRow(new Object[]{user.getId(), user.getUsername(), user.getRole()});
                });
            } else {
                fillTableWithUser();
            }

        });
        btn_delete.addActionListener(e -> {// seçilen indisi silme işlemi
            User user = new User();
            user.setId(Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()).toString()));
            try {
                userManager.deleteUser(user);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            model.setRowCount(0);
            fillTableWithUser();
        });

        btn_register.addActionListener(e -> {// sgirilen verileri database kaydetme işlemi
            User userNew = new User();
            userNew.setUsername(fld_username.getText());
            userNew.setPassword(fld_pass.getText());
            userNew.setRole(cmb_role.getItemAt(cmb_role.getSelectedIndex()).toString());
            try {
                userManager.saveUser(userNew);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            model.setRowCount(0);

            //Tabloyu doldurma methodu
            fillTableWithUser();

            fld_username.setText("");
            fld_pass.setText("");
        });


        btn_logaout_admin.addActionListener(e -> {
            this.dispose();
        });
        btn_help.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Kullanıcı silmek için, kullanıcı numarasına" +
                    " tıklayın ve Sil butonuna basın. Kullanıcı güncellemek için, güncellemek istediğiniz alana" +
                    " çift tıklayın, değişikliğinizi yapın ve güncelle butonuna basın.");
        });
    }

    public void showTable() {
        List<User> userList = userManager.findAll(); // user list
        Set<String> roleList = new HashSet<>();// unique olarak rol tutan liste

        model.addColumn("ID");
        model.addColumn("Username");
        model.addColumn("Password");

        userList.forEach(user -> {
            model.addRow(new Object[]{user.getId(), user.getUsername(), user.getRole()});// db den userı tabloya basma
            roleList.add(user.getRole());
        });

        roleList.add("All");
        roleList.forEach(x -> cmb_filter.addItem(x));
        table1.setModel(model);
    }

    private void fillTableWithUser() {//tabloyu yeniden yazdırma işlemi
        List<User> userList = userManager.findAll();
        userList.forEach(x -> {
            model.addRow(new Object[]{x.getId(), x.getUsername(), x.getRole()});
        });
    }
}
