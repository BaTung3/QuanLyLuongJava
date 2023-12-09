package dao;

import connection.MyConnection;
import entity.HeSoLuong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeSoLuong_Dao {

    private Connection con;

    public HeSoLuong_Dao() {
        con = MyConnection.getInstance().getConnection();
    }
    public ResultSet getResultSet(String StoreName)throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName +"}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }
    public List<HeSoLuong> getLS() {
        List<HeSoLuong> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_HSL");
            while(rs.next()){
                HeSoLuong heSoLuong =new HeSoLuong(rs.getString(1),rs.getDouble(2));
                ds.add(heSoLuong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public HeSoLuong TimKiemMa(String ma){
        HeSoLuong heSoLuong = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HESOLUONG where MAHSL = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                heSoLuong = new HeSoLuong(rs.getString(1),rs.getDouble(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return heSoLuong;
    }

    public HeSoLuong TimKiemHeSo(double hsl){
        HeSoLuong heSoLuong = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from HESOLUONG where HESOLUONG = ?");
            stmt.setDouble(1,hsl);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                heSoLuong = new HeSoLuong(rs.getString(1),rs.getDouble(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return heSoLuong;
    }
}
