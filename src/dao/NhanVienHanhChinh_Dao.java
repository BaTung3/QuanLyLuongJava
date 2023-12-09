package dao;

import connection.MyConnection;
import entity.CongNhan;
import entity.NhanVienHanhChinh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienHanhChinh_Dao {
    private Connection con;
    private HeSoLuong_Dao heSoLuong_dao;
    private PhongBan_Dao phongBan_dao;

    public NhanVienHanhChinh_Dao() {
        con = MyConnection.getInstance().getConnection();
        heSoLuong_dao = new HeSoLuong_Dao();
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
    public List<NhanVienHanhChinh> getLS() {
        List<NhanVienHanhChinh> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_NV");
            while(rs.next()){
                NhanVienHanhChinh nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
                ds.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addNhanVien(NhanVienHanhChinh nv) {
        try {
            PreparedStatement nvAdd = con.prepareStatement("INSERT INTO NhanVien ([HOTEN],[CMND],[NGAYSINH],[GIOITINH],[LUONGCOBAN]," +
                    "[SODIENTHOAI],[DIACHI],[PHUCAP],[MAHSL],[MAPB]) VALUES(?,?,?,?,?,?,?,?,?,?)");
            nvAdd.setString(1,nv.getHoTen());
            nvAdd.setInt(2,nv.getCmnd());
            nvAdd.setDate(3,nv.getNgaySinh());
            nvAdd.setString(4,nv.getGioiTinh());
            nvAdd.setDouble(5,nv.getLuongCoBan());
            nvAdd.setString(6,nv.getSoDienThoai());
            nvAdd.setString(7,nv.getDiaChi());
            nvAdd.setDouble(8,nv.getPhuCap());
            nvAdd.setString(9,nv.getHeSoLuong().getMaHSL());
            nvAdd.setString(10,nv.getPhongban().getMaPB());

            int n = nvAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean deleteNV(String maNV) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from NhanVien where MANV = ?");
            stmt.setString(1, maNV);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean updateNhanVien(String maNV, NhanVienHanhChinh nv) {
        String sql = "update NhanVien set HOTEN = ?, CMND = ? ,NGAYSINH = ? ,"
                + "GIOITINH = ?,LUONGCOBAN = ?,SODIENTHOAI = ? ,DIACHI = ?,PHUCAP = ?,MAHSL = ?, MAPB = ? where MANV = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nv.getHoTen());
            stmt.setInt(2,nv.getCmnd());
            stmt.setDate(3,nv.getNgaySinh());
            stmt.setString(4,nv.getGioiTinh());
            stmt.setDouble(5,nv.getLuongCoBan());
            stmt.setString(6,nv.getSoDienThoai());
            stmt.setString(7,nv.getDiaChi());
            stmt.setDouble(8,nv.getPhuCap());
            stmt.setString(9,nv.getHeSoLuong().getMaHSL());
            stmt.setString(10,nv.getPhongban().getMaPB());
            stmt.setString(11, maNV);

            int n = stmt.executeUpdate();
            if(n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public NhanVienHanhChinh TimKiemMa(String ma){
        NhanVienHanhChinh nv = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where MANV = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }
    public NhanVienHanhChinh TimKiemTen(String ten){
        NhanVienHanhChinh nv = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where HOTEN = ?");
            stmt.setString(1,ten);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }
    public NhanVienHanhChinh TimKiemSDT1(String soDT){
        String so = "%".concat(soDT).concat("%");
        System.out.println(so);
        NhanVienHanhChinh nv = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where SODIENTHOAI LIKE ?");
            stmt.setString(1,so);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nv;
    }

    public List<NhanVienHanhChinh> TimKiemTenTD(String ten){
        List<NhanVienHanhChinh> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where HOTEN LIKE '%"+ten+"%'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhanVienHanhChinh nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
                list.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVienHanhChinh> TimKiemDTTD(String dt){
        List<NhanVienHanhChinh> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where SODIENTHOAI LIKE '%"+dt+"%'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhanVienHanhChinh nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
                list.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVienHanhChinh> TimKiemDCTD(String dc){
        List<NhanVienHanhChinh> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where DIACHI LIKE '%"+dc+"%'");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhanVienHanhChinh nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
                list.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVienHanhChinh> TimKiemPBTD(String maPB){
        List<NhanVienHanhChinh> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from NHANVIEN where MAPB = ?");
            stmt.setString(1,maPB);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                NhanVienHanhChinh nv =new NhanVienHanhChinh(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getDate(4),
                        rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8), rs.getDouble(9));

                nv.setHeSoLuong(heSoLuong_dao.TimKiemMa(rs.getString(10)));
                nv.setPhongban(phongBan_dao.TimKiemMa(rs.getString(11)));
                list.add(nv);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}

