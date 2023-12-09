package dao;

import connection.MyConnection;
import entity.CongNhan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CongNhan_Dao {
    private Connection con;
    private PhongBan_Dao phongBan_dao;

    public CongNhan_Dao() {
        con = MyConnection.getInstance().getConnection();
        phongBan_dao = new PhongBan_Dao();
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
    public List<CongNhan> getLS() {
        List<CongNhan> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_CN");
            while(rs.next()){
                CongNhan congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
                ds.add(congNhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addCongNhan(CongNhan congNhan) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO CongNhan ([HOTEN],[CMND],[NGAYSINH],[GIOITINH],[TAYNGHE]," +
                    "[SODIENTHOAI],[DIACHI],[PHUCAP],[MAPB]) VALUES(?,?,?,?,?,?,?,?,?)");
            cnAdd.setString(1,congNhan.getHoTen());
            cnAdd.setInt(2,congNhan.getCmnd());
            cnAdd.setDate(3,congNhan.getNgaySinh());
            cnAdd.setString(4,congNhan.getGioiTinh());
            cnAdd.setString(5,congNhan.getTayNghe());
            cnAdd.setString(6,congNhan.getSodienThoai());
            cnAdd.setString(7,congNhan.getDiaChi());
            cnAdd.setDouble(8,congNhan.getPhuCap());
            cnAdd.setString(9,congNhan.getPhongban().getMaPB());

            int n = cnAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteCN(String maNV) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from CongNhan where MACN = ?");
            stmt.setString(1, maNV);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateCongNhan(CongNhan congNhan) {
        String sql = "update CongNhan set HOTEN = ?, CMND = ? ,NGAYSINH = ? ,"
                + "GIOITINH = ?,TAYNGHE = ?,SODIENTHOAI = ? ,DIACHI = ?,PHUCAP = ?, MAPB = ? where MACN = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,congNhan.getHoTen());
            stmt.setInt(2,congNhan.getCmnd());
            stmt.setDate(3,congNhan.getNgaySinh());
            stmt.setString(4,congNhan.getGioiTinh());
            stmt.setString(5,congNhan.getTayNghe());
            stmt.setString(6,congNhan.getSodienThoai());
            stmt.setString(7,congNhan.getDiaChi());
            stmt.setDouble(8,congNhan.getPhuCap());
            stmt.setString(9,congNhan.getPhongban().getMaPB());
            stmt.setString(10, congNhan.getMaCN());

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public CongNhan TimKiemMa(String ma){
        CongNhan congNhan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CONGNHAN where MACN = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congNhan;
    }
    public CongNhan TimKiemTen(String ten){
        CongNhan congNhan = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CONGNHAN where HOTEN = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return congNhan;
    }
    public List<CongNhan> TimKiemTenTD(String ten){
        List<CongNhan> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CONGNHAN where HOTEN LIKE '%"+ten+"%'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CongNhan congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
                list.add(congNhan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<CongNhan> TimKiemCMTD(String sdt){
        List<CongNhan> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CONGNHAN where CMND LIKE '%"+sdt+"%'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CongNhan congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
                list.add(congNhan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<CongNhan> TimKiemDCTD(String diaChi){
        List<CongNhan> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CONGNHAN where DIACHI LIKE '%"+diaChi+"%'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CongNhan congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
                list.add(congNhan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<CongNhan> TimKiemPCTD(String maPB){
        List<CongNhan> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CONGNHAN where MAPB = ? ");
            stmt.setString(1,maPB);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CongNhan congNhan =new CongNhan(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getDouble(9));
                congNhan.setPhongban(phongBan_dao.TimKiemMa(rs.getString(10)));
                list.add(congNhan);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}


