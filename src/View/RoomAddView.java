package View;

import Controller.HotelManager;
import Controller.RoomManager;
import Core.Helper;
import Entity.Hotel;
import Entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static View.EmployeeView.modelRoom;
import static View.EmployeeView.showRoomTable;

public class RoomAddView extends JFrame{
    private JPanel container;
    private JTextField room_size;
    private JComboBox room_hotel;
    private JComboBox room_type;
    private JTextField room_price;
    private JCheckBox televizyonCheckBox;
    private JCheckBox minibarCheckBox;
    private JCheckBox konsolCheckBox;
    private JCheckBox kasaCheckBox;
    private JCheckBox projeksiyonCheckBox;
    private JButton btn_add;
    private HotelManager hotelManager = new HotelManager();
    private RoomManager roomManager = new RoomManager();
    RoomAddView(){
        add(container);
        setSize(400,450);
        this.setLocation(Helper.getLocationPoint("x",this.getSize()),Helper.getLocationPoint("y",this.getSize()));
        setVisible(true);
        fillHotelCombobox();
        fillRoomTypeCombobox();

        btn_add.addActionListener(e -> {
            List<Hotel> hotelList = hotelManager.findAll();
            Room room = new Room();
            // hotel ismini comboboxdan alır seçime göre
            String hotelName = room_hotel.getItemAt(room_hotel.getSelectedIndex()).toString();
            //seçilen hotel isminin id'sini database'den bulur
            int hotelId = hotelList.stream()
                    .filter(x -> x.getHotelName().equals(hotelName))
                    .findFirst()
                    .get()
                    .getId();

            // bulunan hotel id'sini room tablosuna kaydeder
            room.setHotelId(hotelId);
            room.setRoomSize(room_size.getText());
            room.setTelevision(televizyonCheckBox.isSelected());
            room.setMiniBar(minibarCheckBox.isSelected());
            room.setGameConsole(konsolCheckBox.isSelected());
            room.setSafe(kasaCheckBox.isSelected());
            room.setProjection(projeksiyonCheckBox.isSelected());
            // comboboxdan seçilen oda tipine göre yatak sayısını method ile bulur ve sayı döner
            room.setBedNumber(getBedNumberFromRoomType((String) room_type.getItemAt(room_type.getSelectedIndex())));
            room.setRoomType((String) room_type.getItemAt(room_type.getSelectedIndex()));
            room.setPrice(room_price.getText());
            room.setAvailability(true);
            try {
                roomManager.saveRoom(room);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // static bir şekilde empolyeeview classındaki aynı hashcode'lu objenin içindeki verileri sıfırlar
            modelRoom.setRowCount(0);
            // static bir şekilde empolyeeview classındaki methodu aynı verilerle çağırır ve güncelleme işlemi yapar tabloda
            showRoomTable();
            this.dispose();

        });
        //Sadece sayı girilmesi
        room_size.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    room_size.setEditable(true);
                } else {
                    room_size.setText("");
                }
            }
        });

        //Sadece sayı girilmesi
        room_price.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
                    room_price.setEditable(true);
                } else {
                    room_price.setText("");
                }
            }
        });
    }
    // Hotel combobox dolduruyor isimleri
    public void fillHotelCombobox() {
        List<Hotel> hotelList = hotelManager.findAll();
        Set<String> hotelNameList = new HashSet<>();
        hotelList.forEach(x -> hotelNameList.add(x.getHotelName()));
        hotelNameList.forEach(x -> room_hotel.addItem(x));

    }
    // oda tipi combobox doldurması
    public void fillRoomTypeCombobox() {
        room_type.addItem("Single Room");
        room_type.addItem("Double Room");
        room_type.addItem("Junior Suit Room");
        room_type.addItem("Suit Room");
    }
    // oda tipi kombobox'ından gelen tipe göre yatak sayısı dönüyor
    public int getBedNumberFromRoomType(String type) {
        return switch (type) {
            case "Single Room" -> 1;
            case "Double Room" -> 2;
            case "Junior Suit Room" -> 4;
            case "Suit Room" -> 6;
            default -> 0;
        };
    }
}
