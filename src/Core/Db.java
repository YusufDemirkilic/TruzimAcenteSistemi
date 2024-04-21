package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static  Db instance =null;
    private Connection connection=null;
    private  final String DB_URL="jdbc:postgresql://localhost:5432/TruzimAcentaSistem";
    private final String DB_USERNAME="postgres";
    private final String DB_PASS="postgre";
    public Db(){
        try{
            this.connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/TruzimAcentaSistem","postgres","postgre");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public static Connection getInstance(){
        try{
            if(instance==null || instance.getConnection().isClosed()){
                instance=new Db();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }

}
