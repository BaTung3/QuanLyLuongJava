package dao;

import connection.MyConnection;
import entity.LuongCongNhan;
import entity.LuongNhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LuongCongNhan_Dao {
    private Connection con;
    private CongNhan_Dao congNhan_dao;

    public LuongCongNhan_Dao() {
        con = MyConnection.getInstance().getConnection();
        congNhan_dao = new CongNhan_Dao();
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
    public List<LuongCongNhan> getLS() {
        List<LuongCongNhan> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_LCN");
            while(rs.next()){
                LuongCongNhan luongCongNhan =new LuongCongNhan(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(5)));
                ds.add(luongCongNhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addLuongCongNhan(LuongCongNhan luongCongNhan) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO LuongCongNhan ([Thang],[Nam],[Luong]" +
                    ",[MACN]) VALUES(?,?,?,?)");
            cnAdd.setInt(1,luongCongNhan.getThang());
            cnAdd.setInt(2,luongCongNhan.getNam());
            cnAdd.setDouble(3,luongCongNhan.getLuong());
            cnAdd.setString(4,luongCongNhan.getCongNhan().getMaCN());

            int n = cnAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteLuongCN(String maLuong) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LuongCongNhan where MALUONG = ?");
            stmt.setString(1, maLuong);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public LuongCongNhan TimKiemMa(String maCN){
        LuongCongNhan luongCongNhan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongCongNhan where MACN = ?");
            stmt.setString(1,maCN);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                luongCongNhan =new LuongCongNhan(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(5)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return luongCongNhan;
    }

    public boolean TimKiem(String maCN, int thang, int nam){
        LuongCongNhan luogCongNhan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongCongNhan where MACN = ? and thang = ? and nam = ?");
            stmt.setString(1,maCN);
            stmt.setInt(2,thang);
            stmt.setInt(3,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                luogCongNhan =new LuongCongNhan(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luogCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(5)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (luogCongNhan == null)
            return false;
        return true;
    }

    public List<LuongCongNhan>TimKiemThangNam(int thang, int nam){
        List<LuongCongNhan> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongCongNhan where thang = ? and nam = ?");
            stmt.setInt(1,thang);
            stmt.setInt(2,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongCongNhan luongCongNhan =new LuongCongNhan(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(5)));
                list.add(luongCongNhan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}

