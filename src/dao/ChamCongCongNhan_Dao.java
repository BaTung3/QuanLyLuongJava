package dao;

import connection.MyConnection;
import entity.ChamCongCongNhan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChamCongCongNhan_Dao {
    private Connection con;
    private CaLamViec_Dao caLamViec_dao;
    private CongNhan_Dao congNhan_dao;
    private SanPham_Dao sanPham_dao;
    private CongDoan_Dao congDoan_dao;

    public ChamCongCongNhan_Dao() {
        con = MyConnection.getInstance().getConnection();
        caLamViec_dao = new CaLamViec_Dao();
        congNhan_dao = new CongNhan_Dao();
        congDoan_dao = new CongDoan_Dao();
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
    public List<ChamCongCongNhan> getLS() {
        List<ChamCongCongNhan> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_CCCN");
            while(rs.next()){
                ChamCongCongNhan chamCongCongNhan =new ChamCongCongNhan(rs.getString(1),rs.getDate(2),rs.getInt(3),
                        rs.getInt(4), rs.getInt(5));
                chamCongCongNhan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(6)));
                chamCongCongNhan.setCongDoan(congDoan_dao.TimKiemMa(rs.getString(7)));
                chamCongCongNhan.setCaLamViec(caLamViec_dao.TimKiemMa(rs.getString(8)));
                chamCongCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(9)));
                ds.add(chamCongCongNhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addChamCongCN(ChamCongCongNhan chamCongCongNhan) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO ChamCongCongNhan ([NGAYCHAM],[SOSANPHAM],[TRANGTHAI],[NGHIPHEP]," +
                    "[MASP],[MACD],[MACA],[MACN]) VALUES(?,?,?,?,?,?,?,?)");
            cnAdd.setDate(1,chamCongCongNhan.getNgayCham());
            cnAdd.setInt(2,chamCongCongNhan.getSoSanPham());
            cnAdd.setInt(3,chamCongCongNhan.getTrangThai());
            cnAdd.setInt(4,chamCongCongNhan.getNghiPhep());
            cnAdd.setString(5,chamCongCongNhan.getSanPham().getMaSP());
            cnAdd.setString(6,chamCongCongNhan.getCongDoan().getMaCD());
            cnAdd.setString(7,chamCongCongNhan.getCaLam().getMaCa());
            cnAdd.setString(8,chamCongCongNhan.getCongNhan().getMaCN());

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
            PreparedStatement stmt = con.prepareStatement("delete from ChamCongCongNhan where MACONG = ?");
            stmt.setString(1, maCC);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateChamCongCN(ChamCongCongNhan chamCongCongNhan) {
        String sql = "update ChamCongCongNhan set SoSanPham = ?, TrangThai = ?, NghiPhep = ? ,MACA = ? where MACONG = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,chamCongCongNhan.getSoSanPham());
            stmt.setInt(2,chamCongCongNhan.getTrangThai());
            stmt.setInt(3,chamCongCongNhan.getNghiPhep());
            stmt.setString(4,chamCongCongNhan.getCaLamViec().getMaCa());
            stmt.setString(5,chamCongCongNhan.getMaCong());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public ChamCongCongNhan TimKiemMa(String ma){
        ChamCongCongNhan chamCongCongNhan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from ChamCongCongNhan where MACONG = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                chamCongCongNhan =new ChamCongCongNhan(rs.getString(1),rs.getDate(2),rs.getInt(3),
                        rs.getInt(4), rs.getInt(5));
                chamCongCongNhan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(6)));
                chamCongCongNhan.setCongDoan(congDoan_dao.TimKiemMa(rs.getString(7)));
                chamCongCongNhan.setCaLamViec(caLamViec_dao.TimKiemMa(rs.getString(8)));
                chamCongCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(9)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return chamCongCongNhan;
    }

    public List<ChamCongCongNhan> TimKiem(String maCN, int thang){
        List<ChamCongCongNhan> chamCongCongNhanList = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from ChamCongCongNhan cn where MACN = ? and MONTH(cn.NGAYCHAM) = ?");
            stmt.setString(1,maCN);
            stmt.setInt(2,thang);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ChamCongCongNhan chamCongCongNhan =new ChamCongCongNhan(rs.getString(1),rs.getDate(2),rs.getInt(3),
                        rs.getInt(4), rs.getInt(5));
                chamCongCongNhan.setSanPham(sanPham_dao.TimKiemMa(rs.getString(6)));
                chamCongCongNhan.setCongDoan(congDoan_dao.TimKiemMa(rs.getString(7)));
                chamCongCongNhan.setCaLamViec(caLamViec_dao.TimKiemMa(rs.getString(8)));
                chamCongCongNhan.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(9)));
                chamCongCongNhanList.add(chamCongCongNhan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return chamCongCongNhanList;
    }
}
