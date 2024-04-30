package View;

import Controller.HotelManager;
import Controller.ReservasionManager;
import Controller.RoomManager;
import Core.Helper;
import Entity.Hotel;
import Entity.Reservasion;
import Entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.SQLException;
import java.util.List;

public class EmployeeView extends JFrame {
    private JPanel container;
    private JTabbedPane hotel_management;
    private JTable tbl_hotel;
    private JButton btn_hoteladd;
    private JTable tbl_facility;
    private JTable tbl_hostel;
    private JScrollPane scrl_hotel;
    private JScrollPane scr_hostel;
    private JScrollPane scr_facility;
    private JTable tbl_room;
    private JButton btn_room;
    private JTable tbl_rsv;
    private JButton btn_reserve;
    private JButton btn_delete_reserve;
    private JButton btn_logout;
    private JButton btn_logout_otel;
    private JButton btn_resUpdate;
    private JButton btn_rezHelp;
    // roomaddview classında kullanmak için static tanımlı
    static HotelManager hotelManager = new HotelManager();
    // roomaddview classında kullanmak için static tanımlı
    static RoomManager roomManager = new RoomManager();
    static ReservasionManager reservasionManager = new ReservasionManager();
    static DefaultTableModel modelHotel = new DefaultTableModel();
    // roomaddview classında kullanmak için static tanımlı
    static DefaultTableModel modelRoom = new DefaultTableModel();
    static DefaultTableModel modelHostel = new DefaultTableModel();
    static DefaultTableModel modelReservasion = new DefaultTableModel();
    private DefaultTableModel modelFacility = new DefaultTableModel();

    EmployeeView() {
        add(container);
        setSize(900, 750);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setVisible(true);
        modelHotel.addColumn("Pansiyon");
        tbl_hostel.setModel(modelHotel);
        btn_hoteladd.addActionListener(e -> {// otel eklemeye basılırsa ekleme sayfasına geçme
            HotelAddView hotelAddView = new HotelAddView();
            hotelAddView.setVisible(true);
            modelHotel.setRowCount(0);
            fillTableWithHotel();
        });

        modelHostel.addColumn("Pansiyon");
        modelFacility.addColumn("Tesis");
        tbl_facility.setModel(modelFacility);//  modeli ayarla
        tbl_hostel.setModel(modelHostel);

        // tbl_otel'e ListSelectionListener ekleme
        tbl_hotel.getSelectionModel().addListSelectionListener(e -> {//otel tablosundan pansiyon verileri alıp harici tabloya atama
            if (!e.getValueIsAdjusting()) {
                // tbl_otel'dan seçilen satırı kontrol et
                int selectedRow = tbl_hotel.getSelectedRow();

                // Seçilen satır geçerli bir indeks ise
                if (selectedRow >= 0) {
                    // tbl_otel'dan pansiyon verilerini al
                    String pansiyon = (String) tbl_hotel.getValueAt(selectedRow, 6); // 6, pansiyon sütununun indeksi

                    // tbl_hostel'daki modelin satırlarını temizle
                    modelHostel.setRowCount(0);

                    // Pansiyon verilerini tbl_hostel'a ekleme
                    modelHostel.addRow(new Object[]{pansiyon});
                }
            }
        });
        tbl_hotel.getSelectionModel().addListSelectionListener(e -> {// otel tablosundan tesis özeliklerini seçip farklı bir taploya yazdrıma
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tbl_hotel.getSelectedRow();
                if (selectedRow >= 0) {
                    String facility = (String) tbl_hotel.getValueAt(selectedRow, 7);
                    modelFacility.setRowCount(0);
                    modelFacility.addRow(new Object[]{facility});
                }
            }
        });

        showTable();
        showRoomTable();
        showReservasionTable();
        tbl_rsv.setModel(modelReservasion);
        tbl_room.setModel(modelRoom);
        tbl_room.setAutoCreateRowSorter(true);

        tbl_facility.setAutoCreateRowSorter(true);
        tbl_hostel.setAutoCreateRowSorter(true);
        tbl_rsv.setAutoCreateRowSorter(true);
        tbl_hotel.setAutoCreateRowSorter(true);


        btn_room.addActionListener(e -> {
            RoomAddView roomAddView = new RoomAddView();
            roomAddView.setVisible(true);
        });

        btn_reserve.addActionListener(e -> {
            ReservationAddView reservationAddView = new ReservationAddView();
            reservationAddView.setVisible(true);
        });
        btn_logout.addActionListener(e -> {
            this.dispose();
        });
        btn_logout_otel.addActionListener(e -> {
            this.dispose();
        });

        btn_delete_reserve.addActionListener(e -> {
            Reservasion reservasion = new Reservasion();
            reservasion.setId(Integer.parseInt(tbl_rsv.getValueAt(tbl_rsv.getSelectedRow(), tbl_rsv.getSelectedColumn()).toString()));
            int selectedRow = tbl_rsv.getSelectedRow();

            List<Hotel> hotelList = hotelManager.findAll();
            // hotel ismini comboboxdan alır seçime göre
            String hotelName = tbl_rsv.getValueAt(selectedRow, 2).toString();
            //seçilen hotel isminin id'sini database'den bulur
            int hotelId = hotelList.stream()
                    .filter(x -> x.getHotelName().equals(hotelName))
                    .findFirst()
                    .get()
                    .getId();

            String roomType = tbl_rsv.getValueAt(selectedRow, 9).toString();

            RoomManager roomManager1 = new RoomManager();
            Room room = roomManager1.findAll().stream().filter(x -> x.getRoomType().equals(roomType) && x.getHotelId() == hotelId).findFirst().get();
            room.setStock(room.getStock() + 1);
            try {
                roomManager1.update(room);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            try {
                reservasionManager.delete(reservasion);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Rezervasyon kaldırılırken hata: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
            modelReservasion.setRowCount(0);
            modelRoom.setRowCount(0);
            showRoomTable();
            showReservasionTable();
            JOptionPane.showMessageDialog(this, "Rezervasyon başarıyla kaldırıldı.");
        });

        btn_resUpdate.addActionListener(e -> {
            for (int i = 0; i < modelReservasion.getRowCount(); i++) {
                Reservasion reservasion = new Reservasion();
                reservasion.setCity(modelReservasion.getValueAt(i, 1).toString());
                reservasion.setSeasonChoice(modelReservasion.getValueAt(i, 8).toString());
                reservasion.setRoomType(modelReservasion.getValueAt(i, 9).toString());
                reservasion.setNameSurname(modelReservasion.getValueAt(i, 3).toString());
                reservasion.setPhoneNum(modelReservasion.getValueAt(i, 5).toString());
                reservasion.setMail(modelReservasion.getValueAt(i, 4).toString());
                reservasion.setHotelName(modelReservasion.getValueAt(i, 2).toString());
                reservasion.setEntryDate(modelReservasion.getValueAt(i, 10).toString());
                reservasion.setExitDate(modelReservasion.getValueAt(i, 11).toString());
                reservasion.setPeopleNumber(Integer.parseInt(modelReservasion.getValueAt(i, 6).toString()));
                reservasion.setChildNumber(Integer.parseInt(modelReservasion.getValueAt(i, 7).toString()));
                reservasion.setPrice(modelReservasion.getValueAt(i, 12).toString());
                reservasion.setId(Integer.parseInt(modelReservasion.getValueAt(i, 0).toString()));
                try {
                    reservasionManager.update(reservasion);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Rezervasyon güncellenirken hata: " + ex.getMessage());
                    throw new RuntimeException(ex);
                }
            }
            JOptionPane.showMessageDialog(this, "Rezervasyon başarıyla güncellendi.");
        });

        btn_rezHelp.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Rezervasyon silmek için, en baştaki ID numarasına" +
                    " tıklayın ve Kaldır butonuna basın. Rezervasyon güncellemek için, güncellemek istediğiniz alana" +
                    " çift tıklayın, değişikliğinizi yapın ve o alandan ayrılın. Güncelle butonuna basın.");
        });
    }

    public void showTable() {// otel tablosu kolon oluştruma ve veri atama

        modelHotel.setRowCount(0);
        modelHotel.setColumnCount(0);
        modelHotel.addColumn("ID");
        modelHotel.addColumn("Hotel Name");
        modelHotel.addColumn("Address");
        modelHotel.addColumn("Email");
        modelHotel.addColumn("Phone");
        modelHotel.addColumn("Star");
        modelHotel.addColumn("Pansiyon");
        modelHotel.addColumn("Tesis");
        List<Hotel> hotelList = hotelManager.findAll();
        hotelList.forEach(hotel -> {
            modelHotel.addRow(new Object[]{hotel.getId(), hotel.getHotelName(), hotel.getAddress(), hotel.getEmail(),
                    hotel.getPhone(), hotel.getStar(), hotel.getPansiyon(), hotel.getTesis()});
        });

        tbl_hotel.setModel(modelHotel);

    }

    // roomaddview classında kullanmak için static tanımlı
    public static void showRoomTable() {
        modelRoom.setColumnCount(0);
        modelRoom.addColumn("ID");
        modelRoom.addColumn("Hotel Name");
        modelRoom.addColumn("Room Size");
        modelRoom.addColumn("Television");
        modelRoom.addColumn("Minibar");
        modelRoom.addColumn("Game Console");
        modelRoom.addColumn("Safe");
        modelRoom.addColumn("Projection");
        modelRoom.addColumn("Bed Number");
        modelRoom.addColumn("Room Type");
        modelRoom.addColumn("Price");
        modelRoom.addColumn("Stock");

        List<Room> roomList = roomManager.findAll();
        List<Hotel> hotelList = hotelManager.findAll();

        roomList.forEach(x -> {
            Hotel findedHotel = hotelList.stream().filter(y -> y.getId() == x.getHotelId()).findFirst().get();
            modelRoom.addRow(new Object[]{x.getId(), findedHotel.getHotelName(), x.getRoomSize(), x.isTelevision(), x.isMiniBar()
                    , x.isGameConsole(), x.isSafe(), x.isProjection(), x.getBedNumber(), x.getRoomType(), x.getPrice(), x.getStock()});
        });
    }

    public static void showReservasionTable() {
        modelReservasion.setColumnCount(0);
        modelReservasion.addColumn("ID");
        modelReservasion.addColumn("City");
        modelReservasion.addColumn("Hotel Name");
        modelReservasion.addColumn("Name Surname");
        modelReservasion.addColumn("Mail");
        modelReservasion.addColumn("Phone Number");
        modelReservasion.addColumn("People Number");
        modelReservasion.addColumn("Child Number");
        modelReservasion.addColumn("Season");
        modelReservasion.addColumn("Room Type");
        modelReservasion.addColumn("Entry Date");
        modelReservasion.addColumn("Exit Date");
        modelReservasion.addColumn("Price");

        List<Reservasion> reservasionsList = reservasionManager.findAll();
        reservasionsList.forEach(x -> {
            modelReservasion.addRow(new Object[]{x.getId(), x.getCity(), x.getHotelName(), x.getNameSurname(), x.getMail(),
                    x.getPhoneNum(), x.getPeopleNumber(), x.getChildNumber(), x.getSeasonChoice(), x.getRoomType(), x.getEntryDate(),
                    x.getExitDate(), x.getPrice()});
        });
    }


    private void adjustColumnWidths(JTable table) {
        // JTable'ın sütun modelini al
        TableColumnModel columnModel = table.getColumnModel();

        // Sütun genişliklerini ayarla
        // Örneğin, 0. sütun olan ID sütununu ayarlama
        columnModel.getColumn(0).setPreferredWidth(50); // ID sütununun genişliğini ayarlayın

        // Diğer sütunların genişliklerini ayarlama
        columnModel.getColumn(1).setPreferredWidth(100); // Hotel Name sütunu
        columnModel.getColumn(2).setPreferredWidth(350); // Address sütunu
        columnModel.getColumn(3).setPreferredWidth(150); // Email sütunu
        columnModel.getColumn(4).setPreferredWidth(100); // Phone sütunu
        columnModel.getColumn(5).setPreferredWidth(50); // Star sütunu
        columnModel.getColumn(6).setPreferredWidth(220); // Pansiyon sütunu
        columnModel.getColumn(7).setPreferredWidth(220); // Tesis sütunu
        // Sütun genişliklerini ayarlamak için diğer sütunları ekleyin
    }

    public static void fillTableWithHotel() {//tablo verilerini tekrar döndürmek için
        List<Hotel> hotelList = hotelManager.findAll();
        hotelList.forEach(x -> {
            modelHotel.addRow(new Object[]{x.getId(), x.getHotelName(), x.getAddress(), x.getEmail(), x.getPhone(),
                    x.getStar(), x.getPansiyon(), x.getTesis(), x.getSeson()});
        });
    }


}
