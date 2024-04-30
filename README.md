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

[Kullanım Videosu](https://youtu.be/pILPJu-pMSQ)

**Proje Göreselleri**

- Kullanıcı girişi

<img width="855" alt="Ekran Resmi 2024-04-30 12 14 50" src="https://github.com/YusufDemirkilic/TruzimAcenteSistemi/assets/81050891/6b387cba-86ff-4120-885e-ca2aec6a9122">

- Otel Listeleme
  
<img width="1440" alt="Ekran Resmi 2024-04-30 12 17 20" src="https://github.com/YusufDemirkilic/TruzimAcenteSistemi/assets/81050891/8f51edcd-242e-4a14-b536-c5a720a2c1de">

- Oda Listeleme

<img width="1440" alt="Ekran Resmi 2024-04-30 12 17 40" src="https://github.com/YusufDemirkilic/TruzimAcenteSistemi/assets/81050891/4c4b2e8c-5ed4-4b93-b7bf-4be9ff9c6614">


- Rezervasyon, oda arama ve fiyat hesaplama

<img width="1440" alt="Ekran Resmi 2024-04-30 12 18 39" src="https://github.com/YusufDemirkilic/TruzimAcenteSistemi/assets/81050891/a412e6ce-0db8-4202-8841-faeae5083c8d">

