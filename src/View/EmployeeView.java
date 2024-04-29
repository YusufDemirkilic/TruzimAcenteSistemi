package View;

import Controller.HotelManager;
import Controller.RoomManager;
import Core.Helper;
import Entity.Hotel;
import Entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
    private JButton button1;
    private JTable table1;
    private JComboBox comboBox1;
    // roomaddview classında kullanmak için static tanımlı
    private static HotelManager hotelManager = new HotelManager();
    // roomaddview classında kullanmak için static tanımlı
    private static RoomManager roomManager = new RoomManager();
    DefaultTableModel modelHotel = new DefaultTableModel();
    // roomaddview classında kullanmak için static tanımlı
    public static DefaultTableModel modelRoom = new DefaultTableModel();
    public static DefaultTableModel modelHostel= new DefaultTableModel();
    private DefaultTableModel modelFacility=new DefaultTableModel();

    EmployeeView() {
        add(container);
        setSize(900, 750);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        setVisible(true);
        modelHotel.addColumn("Pansiyon");
        tbl_hostel.setModel(modelHotel);
        btn_hoteladd.addActionListener(e -> {// otel eklemeye basılırsa ekleme sayfasına geçme
                HotelAddView hotelAddView=new HotelAddView();
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
        tbl_hotel.getSelectionModel().addListSelectionListener(e->{// otel tablosundan tesis özeliklerini seçip farklı bri taploya yazdrıma
            if (!e.getValueIsAdjusting()){
                int selectedRow= tbl_hotel.getSelectedRow();
                if (selectedRow>=0){
                    String facility=(String) tbl_hotel.getValueAt(selectedRow,7);
                    modelFacility.setRowCount(0);
                    modelFacility.addRow(new Object[]{facility});
                }
            }
        });

        showTable();
        showRoomTable();
        tbl_room.setModel(modelRoom);
        adjustColumnWidths(tbl_hotel);
        btn_room.addActionListener(e -> {
            RoomAddView roomAddView = new RoomAddView();
            roomAddView.setVisible(true);
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
            modelHotel.addRow(new Object[]{hotel.getId(), hotel.getHotelName(), hotel.getAddress(), hotel.getEmail(), hotel.getPhone(), hotel.getStar(), hotel.getPansiyon(), hotel.getTesis()});
        });

        tbl_hotel.setModel(modelHotel);

    }
    // roomaddview classında kullanmak için static tanımlı
    public static void showRoomTable(){
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
        modelRoom.addColumn("Availability");
        modelRoom.addColumn("Stock");

        List<Room> roomList = roomManager.findAll();
        List<Hotel> hotelList = hotelManager.findAll();

        roomList.forEach(x -> {
            Hotel findedHotel = hotelList.stream().filter(y -> y.getId() == x.getHotelId()).findFirst().get();
            modelRoom.addRow(new Object[]{x.getId(),findedHotel.getHotelName(),x.getRoomSize(),x.isTelevision(),x.isMiniBar()
                    ,x.isGameConsole(),x.isSafe(),x.isProjection(),x.getBedNumber(),x.getRoomType(),x.getPrice(), 1});
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
    private void fillTableWithHotel(){
        List<Hotel> hotelList =hotelManager.findAll();
        hotelList.forEach(x-> {
                modelHotel.addRow(new Object[]{x.getId(),x.getHotelName(),x.getAddress(),x.getEmail(),x.getPhone(),x.getStar(),x.getPansiyon(),x.getTesis(),x.getSeson()});
        });
    }



}
