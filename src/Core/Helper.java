package Core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static int getLocationPoint(String type, Dimension size){
        return switch (type){
                case "x" ->(Toolkit.getDefaultToolkit().getScreenSize().width-size.width)/2;
                case "y" ->(Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
            default -> 0;
        };
    }
    public static void showMsg(String str){
        optionPayneTR();
        String msg;
        String title;
        switch(str){ // hata mesajları
            case"fill"->{
                msg="Lütfen tüm alanları doldurunuz !";
                title="Hata!";
            }
            case "done"->{
                msg = "İşlem Başarılı !";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt bulunamadı !";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı işlem yaptınız !";
                title = "Hata!";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);// hata mesajları gönderme
    }
    public static boolean isFieldEmpty(JTextField field){  // field boş olup olmadığını kotrol etme
        return field.getText().trim().isEmpty();
    }
    public static boolean isFieldListEmpty(JTextField[] fieldList){ // field list boş olup olmadığını kotrol etme
        for (JTextField field:fieldList)
            if (isFieldEmpty(field)) {
                return true;
            }
        return false;
    }


    public static void optionPayneTR(){     // hata mesajları türkçe çeviri
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

}
