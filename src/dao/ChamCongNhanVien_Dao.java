package dao;

import connection.MyConnection;
import entity.ChamCongNhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChamCongNhanVien_Dao {
    private Connection con;
    private CaLamViec_Dao caLamViec_dao;
    private NhanVienHanhChinh_Dao nhanVienHanhChinh_dao;
    

    public ChamCongNhanVien_Dao() {
        con = MyConnection.getInstance().getConnection();
        caLamViec_dao = new CaLamViec_Dao();
        nhanVienHanhChinh_dao = new NhanVienHanhChinh_Dao();
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
    public List<ChamCongNhanVien> getLS() {
        List<ChamCongNhanVien> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_CCNV");
            while(rs.next()){
                ChamCongNhanVien chamCongNhanVien =new ChamCongNhanVien(rs.getString(1),rs.getDate(2),rs.getInt(3),
                        rs.getInt(4));
                chamCongNhanVien.setCaLamViec(caLamViec_dao.TimKiemMa(rs.getString(5)));
                chamCongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(6)));
                ds.add(chamCongNhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addChamCongNV(ChamCongNhanVien chamCongNhanVien) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO ChamCongNhanVien ([NGAYCHAM],[TRANGTHAI],[NGHIPHEP]," +
                    "[MACA],[MANV],[LUONG]) VALUES(?,?,?,?,?,?)");
            cnAdd.setDate(1,chamCongNhanVien.getNgayCham());
            cnAdd.setInt(2,chamCongNhanVien.getTrangThai());
            cnAdd.setInt(3,chamCongNhanVien.getNghiPhep());
            cnAdd.setString(4,chamCongNhanVien.getCaLamViec().getMaCa());
            cnAdd.setString(5,chamCongNhanVien.getNhanVienHanhChinh().getMaNV());
            cnAdd.setDouble(6,chamCongNhanVien.getCaLamViec().getLuongCa());

            int n = cnAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteChamCong(String maCC) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from ChamCongNhanVien where MACONG = ?");
            stmt.setString(1, maCC);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateChamCongNV(ChamCongNhanVien chamCongNhanVien) {
        String sql = "update ChamCongNhanVien set TrangThai = ?, NghiPhep = ? ,MACA = ? where MACONG = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,chamCongNhanVien.getTrangThai());
            stmt.setInt(2,chamCongNhanVien.getNghiPhep());
            stmt.setString(3,chamCongNhanVien.getCaLamViec().getMaCa());
            stmt.setString(4,chamCongNhanVien.getMaCong());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public ChamCongNhanVien TimKiemMa(String ma){
        ChamCongNhanVien chamCongNhanVien = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from ChamCongNhanVien where MACONG = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                chamCongNhanVien =new ChamCongNhanVien(rs.getString(1),rs.getDate(2),rs.getInt(3),
                        rs.getInt(4));
                chamCongNhanVien.setCaLamViec(caLamViec_dao.TimKiemMa(rs.getString(5)));
                chamCongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(6)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return chamCongNhanVien;
    }
    
   
    
}