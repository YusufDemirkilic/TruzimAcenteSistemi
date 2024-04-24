package Entity;

public class Hotel {
    private int id; // Otelin benzersiz kimliği (primary key)
    private String hotelName; // Otel adı
    private String address; // Otel adresi
    private String email; // Otel e-posta adresi
    private String phone; // Otel telefon numarası
    private int star; // Otelin yıldız derecelendirmesi
    private String pansiyon; // Otelin pansiyon türü (belirtilen)
    private String tesis; // Otelin tesis özellikleri (belirtilen)
    private String seson;

    // Hotel sınıfının yapıcı metodu
    public Hotel(int id, String hotelName, String address, String email, String phone, int star, String pansiyon, String tesis,String seson) {
        this.id = id;
        this.hotelName = hotelName;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.star = star;
        this.pansiyon = pansiyon;
        this.tesis = tesis;
        this.seson=seson;
    }
    public Hotel(){

    }

    // Get ve set metotları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPansiyon() {
        return pansiyon;
    }

    public void setPansiyon(String pansiyon) {
        this.pansiyon = pansiyon;
    }

    public String getTesis() {
        return tesis;
    }

    public void setTesis(String tesis) {
        this.tesis = tesis;
    }

    public String getSeson() {
        return seson;
    }

    public void setSeson(String seson) {
        this.seson = seson;
    }

    // Otel bilgilerini döndüren toString metodu
    @Override
    public String toString() {
        return "Hotel{id=" + id + ", hotelName='" + hotelName + "', address='" + address + "', email='" + email + "', phone='" + phone + "', star=" + star + ", pansiyon='" + pansiyon + "', tesis='" + tesis + "'}";
    }
}
