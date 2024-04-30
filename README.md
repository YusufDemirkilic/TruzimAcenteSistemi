**Truzim Acente Programı**

Truzim Acente Programı, hem admin hem de çalışan rollerine sahip bir acente işletme programıdır. Bu program, admin ve
çalışanlar arasında belirli yetki ayrımları yaparak acentenin işleyişini düzenler.

**Admin Yetkileri:**

- Acente çalışanı listeleme, ekleme, silme, güncelleme
- Kullanıcıların rollerine (admin, personel) göre filtreleme yapabilme

**Employee Yetkileri:**

- Otel Yönetimi: Otel listeleme, ekleme
- Oda Yönetimi: Oda listeleme, ekleme
- Dönem Yönetimi: Dönem listeleme, ekleme
- Fiyat Yönetimi
- Oda Arama
- Rezervasyon işlemleri: Rezervasyon listeleme, ekleme, silme, güncelleme

İstenilen müşteri için çalışan otel listeleme ve gerektiğinde otel verisi eklemek gibi özellikler bulunmaktadır. Ayrıca
istenilen tarihe ve lokasyona göre oda araması yapıp rezervasyon işlemleri gerçekleştirilebilir.

**Pansiyon Tipleri:**

- Ultra Her Şey Dahil
- Her Şey Dahil
- Oda Kahvaltı
- Tam Pansiyon
- Yarım Pansiyon
- Sadece Yatak
- Alkol Hariç Full Credit

**Tesis Özellikleri:**

- Ücretsiz Otopark
- Ücretsiz WiFi
- Yüzme Havuzu
- Fitness Center
- Hotel Concierge
- SPA
- 7/24 Oda Servisi

**Dönem Yönetimi:**
Yaz aylarında otel fiyatları daha yüksek iken, bu durum kış ayları için daha azdır. 
Fiyatlandırmalar turizm sektöründe dönemsel olarak yapılır. Dönemler iki tarih aralığı olarak tanımlanır
örnek dönem:

- 01/01/2021  31/05/2021
- 01/06/2021  01/12/2021

**Oda Yönetimi**

Acente çalışanı tarafından otel odaları listelenir, istenilmesi durumda otellere oda eklemesi yapılacak,
oda tipleri 4 tip olarak gösterilmiştir.

- Single room
- Double room
- junior suite oda
- suite oda

Oda eklenirken dönem seçimi ve oda özellikleri seçilebilir.

- Yatak Sayısı
- Metrekare
- Televizyon (Var, Yok)
- Minibar (Var ,Yok)
- Oyun Konsolu (Var, Yok)
- Kasa (Var, Yok)
- Projeksiyon (Var, Yok)

**Oda Arama ve Rezervasyon İşlemleri**

Acente çalışanı sistem üzerinden

- Girdiği tarih aralığına,
- Şehire,
- Otel adına

göre oda arayabilmelidir.

Filtrelenen odalardan seçilen oda için belirli tarih aralığı seçilir,
acente çalışanından müşteri iletişim bilgileri alınır.

**Fiyat hesaplama**

Seçilen oda kişi sayısı ve tarih ile yetişkin bilet fiyatı ve çocuk bilet fiyatı (%50 inidirimli) olarak hesapanarak.
Rezervasyon eklemesi tamamlanır.

**Kullanılan Teknolojiler:**

- Java Swing UI Designer
- Java LGoodDatePicker 11.2.1
- JDK 21
- Jav JDBC
- PostgreSQL
- Flow Diagram


NOT: SQL tablo oluşturma için TABLES.md dosyasındaki scriptleri kullanabilirsiniz.

[Flow Diagram için tıklayınız](https://lucid.app/lucidchart/c37eefb2-8413-4080-a697-53666739b8d3/edit?page=0_0&invitationId=inv_f04ab0ab-3b8e-40c4-96e5-7f18e1c7bab8)

**Proje Göreselleri**

- Kullanıcı girişi

![Ekran Resmi 2024-04-30 12.14.50.png](..%2F..%2FDesktop%2FEkran%20Resmi%202024-04-30%2012.14.50.png)

- Otel Listeleme
![Ekran Resmi 2024-04-30 12.17.20.png](..%2F..%2FDesktop%2FEkran%20Resmi%202024-04-30%2012.17.20.png)

- Oda Listeleme

![Ekran Resmi 2024-04-30 12.17.40.png](..%2F..%2FDesktop%2FEkran%20Resmi%202024-04-30%2012.17.40.png)


- Rezervasyon, oda arama ve fiyat hesaplama

![Ekran Resmi 2024-04-30 12.18.39.png](..%2F..%2FDesktop%2FEkran%20Resmi%202024-04-30%2012.18.39.png)