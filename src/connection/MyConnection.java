package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static MyConnection instance;
    private Connection connection;

    private MyConnection() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyLuongSanPham;integratedSecurity=true";
        try {
           // connection = DriverManager.getConnection(url , "DESKTOP-HKFENFR\\ADMIN", "");
           
           connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
