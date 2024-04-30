package View;

import Controller.HotelManager;
import Controller.ReservasionManager;
import Controller.RoomManager;
import Core.Helper;
import Entity.Hotel;
import Entity.Reservasion;
import Entity.Room;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static View.EmployeeView.*;


public class ReservationAddView extends JFrame {
    private JPanel container;
    private JComboBox cmb_city;
    private JComboBox cmb_hotel;
    private JComboBox cmb_room_type;
    private JTextField fld_name_surname;
    private JTextField fld_phone;
    private JTextField fld_mail;
    private JButton btn_register_reservation;
    private JButton btn_logout_reservation;
    private JLabel lbl_price;
    private DatePicker datePicker_entry;
    private DatePicker datePicker1_exit;
    private JTextField fld_people;
    private JTextField fld_child;
    private JButton btn_calculate;

    private HotelManager hotelManager = new HotelManager();
    private RoomManager roomManager = new RoomManager();
    private ReservasionManager reservasionManager = new ReservasionManager();

    ReservationAddView() {
        add(container);
        setSize(900, 750);
        adjustDatePickerSettings();
        fillCityCombobox();
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setVisible(true);
        cmb_city.addActionListener(e -> fillHotelCombobox());
        cmb_hotel.addActionListener(e -> fillRoomTypeCombobox());
        btn_register_reservation.addActionListener(e -> {

            Reservasion reservasion = new Reservasion();
            reservasion.setCity(cmb_city.getSelectedItem().toString());
            reservasion.setHotelName(cmb_hotel.getSelectedItem().toString());
            reservasion.setEntryDate(adjustDateFormat(datePicker_entry.getDate()));
            reservasion.setExitDate(adjustDateFormat(datePicker1_exit.getDate()));
            reservasion.setRoomType(cmb_room_type.getSelectedItem().toString());
            reservasion.setNameSurname(fld_name_surname.getText());
            reservasion.setPhoneNum(fld_phone.getText());
            reservasion.setMail(fld_mail.getText());
            reservasion.setPeopleNumber(Integer.parseInt(fld_people.getText()));
            if (fld_child.getText().isEmpty()) {
                fld_child.setText("0");
            }
            reservasion.setChildNumber(Integer.parseInt(fld_child.getText()));

            Room room = roomManager.findAll().stream().filter(x -> x.getRoomType().equals(cmb_room_type.getSelectedItem().toString()) && x.getHotelId() == getHotelIdFromHotelName()).findFirst().get();
            room.setStock(room.getStock() - 1);
            try {
                roomManager.update(room);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            reservasion.setSeasonChoice(getSeasonFromDate(datePicker_entry.getDate()));
            reservasion.setPrice(adjustPrice(reservasion, room));
            try {
                reservasionManager.save(reservasion);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Rezervasyon kaydedilirken hata: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
            modelReservasion.setRowCount(0);
            showReservasionTable();
            modelRoom.setRowCount(0);
            showRoomTable();
            JOptionPane.showMessageDialog(this, "Rezervasyon başarıyla kaydedildi.");
            dispose();
        });
        btn_calculate.addActionListener(e -> {
            if (null == cmb_room_type || cmb_city.getSelectedItem().toString() == null || cmb_hotel.getSelectedItem().toString() == null
                    || datePicker_entry.getDate() == null || datePicker1_exit.getDate() == null || fld_name_surname.getText() == null
                    || fld_phone.getText() == null || fld_mail.getText() == null || fld_people.getText() == null) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurunuz.");
            } else {
                Room room = roomManager.findAll().stream().filter(x -> x.getRoomType().equals(cmb_room_type.getSelectedItem().toString()) && x.getHotelId() == getHotelIdFromHotelName()).findFirst().get();
                Reservasion reservasion = new Reservasion();
                reservasion.setCity(cmb_city.getSelectedItem().toString());
                reservasion.setHotelName(cmb_hotel.getSelectedItem().toString());
                reservasion.setEntryDate(adjustDateFormat(datePicker_entry.getDate()));
                reservasion.setExitDate(adjustDateFormat(datePicker1_exit.getDate()));
                reservasion.setRoomType(cmb_room_type.getSelectedItem().toString());
                reservasion.setNameSurname(fld_name_surname.getText());
                reservasion.setPhoneNum(fld_phone.getText());
                reservasion.setMail(fld_mail.getText());
                reservasion.setPeopleNumber(Integer.parseInt(fld_people.getText()));
                if (fld_child.getText().isEmpty()) {
                    fld_child.setText("0");
                }
                reservasion.setChildNumber(Integer.parseInt(fld_child.getText()));
                lbl_price.setText(adjustPrice(reservasion, room));
            }
        });
        btn_logout_reservation.addActionListener(e -> dispose());
    }

    public void adjustDatePickerSettings() {
        DatePickerSettings datePickerSettingsForEntry = new DatePickerSettings();
        datePickerSettingsForEntry.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
        datePickerSettingsForEntry.setFormatForDatesCommonEra("dd.MM.yyyy");
        DatePickerSettings datePickerSettingsForExit = new DatePickerSettings();
        datePickerSettingsForExit.setFormatForDatesBeforeCommonEra("dd.MM.yyyy");
        datePickerSettingsForExit.setFormatForDatesCommonEra("dd.MM.yyyy");
        datePicker_entry.setSettings(datePickerSettingsForEntry);
        datePicker1_exit.setSettings(datePickerSettingsForExit);
    }

    public void fillCityCombobox() {
        List<Hotel> hotelList = hotelManager.findAll();
        Set<String> hotelNameList = new HashSet<>();
        hotelList.forEach(hotel -> {
            hotelNameList.add(hotel.getAddress());
        });
        hotelNameList.forEach(name -> cmb_city.addItem(name));
    }

    public void fillHotelCombobox() {
        cmb_hotel.removeAllItems();
        List<Hotel> hotelList = hotelManager.findAll();
        String cityName = cmb_city.getSelectedItem().toString();
        List<String> hotelNameList = hotelList.stream().filter(x -> x.getAddress().equals(cityName)).map(hotel -> hotel.getHotelName()).toList();
        hotelNameList.forEach(x -> cmb_hotel.addItem(x));
    }

    public void fillRoomTypeCombobox() {
        cmb_room_type.removeAllItems();
        List<Room> roomList = roomManager.findAll();
        List<Hotel> hotelList = hotelManager.findAll();
        Set<String> roomTypeList;
        if (cmb_hotel.getSelectedItem() != null) {
            Hotel selectedHotel = hotelList.stream().filter(x -> cmb_hotel.getSelectedItem().toString().equals(x.getHotelName())).findFirst().get();
            roomTypeList = roomList.stream().filter(x -> x.getHotelId() == selectedHotel.getId()).map(room -> room.getRoomType()).collect(Collectors.toSet());
            roomTypeList.forEach(x -> cmb_room_type.addItem(x));
        }
    }

    public String adjustDateFormat(LocalDate date) {
        return date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
    }

    public String getSeasonFromDate(LocalDate date) {
        if (date.getMonthValue() < 6) {
            return "Kış";
        } else {
            return "Yaz";
        }
    }

    public int getHotelIdFromHotelName() {
        List<Hotel> hotelList = hotelManager.findAll();
        // hotel ismini comboboxdan alır seçime göre
        String hotelName = cmb_hotel.getSelectedItem().toString();
        //seçilen hotel isminin id'sini database'den bulur
        int hotelId = hotelList.stream()
                .filter(x -> x.getHotelName().equals(hotelName))
                .findFirst()
                .get()
                .getId();
        return hotelId;
    }

    public String adjustPrice(Reservasion reservasion, Room room) {
        int peopleNumber = reservasion.getPeopleNumber();
        int childNumber = reservasion.getChildNumber();
        String roomPrice = room.getPrice();
        LocalDate entryDay = datePicker_entry.getDate();
        LocalDate exitDay = datePicker1_exit.getDate();
        long dayNumber = ChronoUnit.DAYS.between(entryDay, exitDay);
        double price = ((peopleNumber * Double.parseDouble(roomPrice)) + (childNumber * (Double.parseDouble(roomPrice) / 2))) * dayNumber;
        if (getSeasonFromDate(datePicker_entry.getDate()).equals("Yaz")) {
            price = price * 2;
        }
        return String.valueOf(price);
    }

}
