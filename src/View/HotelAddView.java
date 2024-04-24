package View;

import Controller.HotelManager;
import Core.Helper;
import Entity.Hotel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelAddView extends  JFrame{

    private JPanel container;
    private JTextField fld_address;
    private JTextField fld_mail;
    private JTextField fld_phoneNumber;
    private JComboBox cmb_star;
    private JRadioButton rd_ultraAll;
    private JRadioButton rd_all;
    private JRadioButton rd_roomBreakfast;
    private JRadioButton rd_fullPans;
    private JRadioButton rd_halfPans;
    private JRadioButton rd_bed;
    private JRadioButton rd_notAlcohol;
    private JRadioButton rd_carPark;
    private JRadioButton rd_wifi;
    private JRadioButton rd_pool;
    private JRadioButton rd_gym;
    private JRadioButton rd_concierge;
    private JRadioButton rd_spa;
    private JRadioButton rd_roomServices;
    private JTextField fld_hotelName;
    private JRadioButton rd_summer;
    private JRadioButton rd_winter;
    private JButton btn_register;
    HotelManager hotelManager=new HotelManager();
    public HotelAddView(){
        add(container);
        setSize(1000,600);
        setLocation( Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setVisible(true);
        cmb_star.addItem(1);
        cmb_star.addItem(2);
        cmb_star.addItem(3);
        cmb_star.addItem(4);
        cmb_star.addItem(5);


        btn_register.addActionListener(e -> {
                JTextField[] checkField ={fld_hotelName,fld_address,fld_mail,fld_phoneNumber};

                if (Helper.isFieldListEmpty(checkField)){
                    Helper.showMsg("fill");
                }else {
                    String sesonCheck="";
                    Hotel hotelNew=new Hotel();
                    hotelNew.setHotelName(fld_hotelName.getText()); // fld_hotelName
                    hotelNew.setAddress(fld_address.getText()); // fld_address
                    hotelNew.setEmail(fld_mail.getText()); // fld_mail
                    hotelNew.setPhone(fld_phoneNumber.getText()); // fld_phoneNumber
                    hotelNew.setStar(cmb_star.getItemCount());
                    hotelNew.setPansiyon(checkHostel());
                    hotelNew.setTesis(checkFacility());
                    if (rd_summer.isSelected()){
                        sesonCheck += rd_summer.getText();
                    }
                    if (rd_winter.isSelected()){
                         sesonCheck += rd_winter.getText();
                    }
                    hotelNew.setSeson(sesonCheck);
                    if (hotelNew.getId() == 0) {
                        try {
                            this.hotelManager.saveHotel(hotelNew);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        Helper.showMsg("done");
                        dispose();

                    }
                }
        });
    }
    public String checkHostel(){
        JRadioButton[] checkRadioButton={rd_ultraAll,rd_all,rd_roomBreakfast,rd_fullPans,rd_halfPans,rd_bed,rd_notAlcohol};
        String radioString="";
        for (JRadioButton rd_check:checkRadioButton){
                if (rd_check.isSelected()){
                    radioString += rd_check.getText()+",\n";
                }
        }
        return radioString;
    }
    public String checkFacility(){
        JRadioButton[] checkRadioButton={rd_carPark,rd_wifi,rd_pool,rd_gym,rd_concierge,rd_spa,rd_roomServices};
        String radioString="";
        for (JRadioButton rd_check:checkRadioButton){
            if (rd_check.isSelected()){
                radioString += rd_check.getText()+",\n";
            }
        }
        return radioString;
    }

}
