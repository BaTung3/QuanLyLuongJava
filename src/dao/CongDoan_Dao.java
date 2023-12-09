package dao;

import connection.MyConnection;
import entity.CongDoan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CongDoan_Dao {
    private Connection con;
    private SanPham_Dao sanPham_dao;

    public CongDoan_Dao() {
        con = MyConnection.getInstance().getConnection();
        sanPham_dao = new SanPham_Dao();
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
    public List<CongDoan> getLS() {
        List<CongDoan> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_CD");
            while(rs.next()){
                CongDoan congDoan =new CongDoan(rs.getString(1),rs.getString(2),rs.getDouble(3),
                        rs.getInt(4));
                congDoan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(5)));
                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
                ds.add(congDoan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addCongDoan(CongDoan congDoan) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO CongDoan ([TENCONGDOAN],[GIACONGDOAN],[SOLUONG]" +
                    ",[MASP],[MACDYC]) VALUES(?,?,?,?,?)");
            cnAdd.setString(1,congDoan.getTenCongDoan());
            cnAdd.setDouble(2,congDoan.getGiaCongDoan());
            cnAdd.setInt(3,congDoan.getSoLuong());
            cnAdd.setString(4,congDoan.getSanPham().getMaSP());
            cnAdd.setString(5,congDoan.getMaCD());

            int n = cnAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteCongDoan(String maCD) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from CongDoan where MACD = ?");
            stmt.setString(1, maCD);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateCongDoan(CongDoan congDoan) {
        if (congDoan.getCongDoanYeuCau() != null) {
            String sql = "update CongDoan set TenCongDoan = ?, GiaCongDoan = ?, SoLuong = ?, MaCDYC = ? where MACD = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, congDoan.getTenCongDoan());
                stmt.setDouble(2, congDoan.getGiaCongDoan());
                stmt.setInt(3, congDoan.getSoLuong());
                stmt.setString(4, congDoan.getCongDoanYeuCau().getMaCD());
                stmt.setString(5, congDoan.getMaCD());

                int n = stmt.executeUpdate();
                if (n > 0)
                    return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "update CongDoan set TenCongDoan = ?, GiaCongDoan = ?, SoLuong = ?, MaCDYC = ? where MACD = ?";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, congDoan.getTenCongDoan());
                stmt.setDouble(2, congDoan.getGiaCongDoan());
                stmt.setInt(3, congDoan.getSoLuong());
                stmt.setString(4, "");
                stmt.setString(5, congDoan.getMaCD());

                int n = stmt.executeUpdate();
                if (n > 0)
                    return true;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    public CongDoan TimKiemMa(String ma){
        CongDoan congDoan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MACD = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                congDoan =new CongDoan(rs.getString(1),rs.getString(2),rs.getDouble(3),
                        rs.getInt(4));
                congDoan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(5)));
                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congDoan;
    }

    public CongDoan TimKiemTen(String ten){
        CongDoan congDoan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where TENCONGDOAN = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                congDoan =new CongDoan(rs.getString(1),rs.getString(2),rs.getDouble(3),
                        rs.getInt(4));
                congDoan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(5)));
                congDoan.setCongDoanYeuCau(TimKiemMa(rs.getString(6)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congDoan;
    }
    public List<CongDoan> TimKiemMaSP(String ma){
        List<CongDoan> congDoanList = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MASP = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CongDoan congDoan =new CongDoan(rs.getString(1),rs.getString(2),rs.getDouble(3),
                        rs.getInt(4));
                congDoan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(5)));
                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
                congDoanList.add(congDoan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congDoanList;
    }

    public CongDoan TimKiemTenCDSP(String ten, String ma){
        CongDoan congDoan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MASP = ? AND TENCONGDOAN = ?");
            stmt.setString(1,ma);
            stmt.setString(2,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                congDoan =new CongDoan(rs.getString(1),rs.getString(2),rs.getDouble(3),
                        rs.getInt(4));
                congDoan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(5)));
                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congDoan;
    }

    public CongDoan TimKiemMaCDYC(String ma){
        CongDoan congDoan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MACDYC = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                congDoan =new CongDoan(rs.getString(1),rs.getString(2),rs.getDouble(3),
                        rs.getInt(4));
                congDoan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(5)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congDoan;
    }

}
