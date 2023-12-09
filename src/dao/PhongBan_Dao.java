package dao;

import connection.MyConnection;
import entity.Phongban;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongBan_Dao {
    private Connection con;

    public PhongBan_Dao() {
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
    public List<Phongban> getLS() {
        List<Phongban> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_PB");
            while(rs.next()){
                Phongban phongban =new Phongban(rs.getString(1),rs.getString(2));
                ds.add(phongban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public Phongban TimKiemMa(String ma){
        Phongban phongban = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from PHONGBAN where MAPB = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                phongban = new Phongban(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return phongban;
    }

    public Phongban TimKiemTen(String pb){
        Phongban phongban = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from PHONGBAN where TENPHONGBAN = ?");
            stmt.setString(1,pb);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                phongban = new Phongban(rs.getString(1),rs.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return phongban;
    }
}

