package Core;

import java.awt.*;

public class Helper {

    public static int getLocationPoint(String type, Dimension size){
        return switch (type){
                case "x" ->(Toolkit.getDefaultToolkit().getScreenSize().width-size.width)/2;
                case "y" ->(Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
            default -> 0;
        };
    }

}
