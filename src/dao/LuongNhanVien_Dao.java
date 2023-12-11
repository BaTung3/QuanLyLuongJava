package dao;

import application.RunApplication;
import connection.MyConnection;
import entity.ChamCongNhanVien;
import entity.LuongNhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LuongNhanVien_Dao {
    private Connection con;
    private NhanVienHanhChinh_Dao nhanVienHanhChinh_dao;

    public LuongNhanVien_Dao() {
        con = MyConnection.getInstance().getConnection();
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
    public List<LuongNhanVien> getLS() {
        List<LuongNhanVien> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_LNV");
            while(rs.next()){
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                ds.add(luongNhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public boolean addLuongNhanVien(LuongNhanVien luongNhanVien) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO LuongNhanVien ([Thang],[Nam],[Luong]" +
                    ",[MANV]) VALUES(?,?,?,?)");
            cnAdd.setInt(1, luongNhanVien.getThang());
            cnAdd.setInt(2, luongNhanVien.getNam());
            cnAdd.setDouble(3, luongNhanVien.getLuong());
            cnAdd.setString(4, luongNhanVien.getNhanVienHanhChinh().getMaNV());

            int n = cnAdd.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteListLuongNV(ArrayList<String> maLuong) {
        try {
            
             System.out.println("senD : "+ maLuong.toString());
             
             String mau = "delete from LuongNhanVien where MALUONG IN (";
             int size =  maLuong.size();
             
             for (int i = 0; i < size; i++){
                 if(i!= size - 1){
                      mau += "'"+ maLuong.get(i) +"' , " ;
                      }
                      else{
                          mau += "'"+ maLuong.get(i) +"' )" ;
                      }
             }
             
             System.out.println("String mau : " + mau);
            
            PreparedStatement stmt = con.prepareStatement(mau);
            //stmt.setString(1, nhommaLuong);
            
            System.out.println("deleteListLuongNV : co chạy ");
            
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean deleteLuongNV(String maLuong) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from LuongNhanVien where MALUONG = ?");
            stmt.setString(1, maLuong);
            int n = stmt.executeUpdate();
            if(n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public LuongNhanVien TimKiemMa(String maCN){
        LuongNhanVien luongNhanVien = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongNhanVien where MANV = ?");
            stmt.setString(1,maCN);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return luongNhanVien;
    }

    public boolean TimKiem(String maNV, int thang, int nam){
        LuongNhanVien luongNhanVien = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongNhanVien where MANV = ? and thang = ? and nam = ?");
            stmt.setString(1,maNV);
            stmt.setInt(2,thang);
            stmt.setInt(3,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (luongNhanVien == null)
            return false;
        return true;
    }
    
    public List<LuongNhanVien>TimKiemThangNam(int thang, int nam){
        List<LuongNhanVien> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongNhanVien where thang = ? and nam = ?");
            stmt.setInt(1,thang);
            stmt.setInt(2,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                list.add(luongNhanVien);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<LuongNhanVien>TimKiemNam( int nam){
        List<LuongNhanVien> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongNhanVien where nam = ?");
            stmt.setInt(1,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                list.add(luongNhanVien);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
     public List<LuongNhanVien>TimKiemLuongCVThangNam(String hsl,int thang, int nam){
        List<LuongNhanVien> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select L.* from LuongNhanVien as L,NhanVien as N where L.MANV = N.MANV and N.MAHSL = ?  and L.thang = ? and L.nam = ?");
            stmt.setString(1,RunApplication.ChucvuToMaHesoluong(hsl));
            stmt.setInt(2,thang);
            stmt.setInt(3,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                list.add(luongNhanVien);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<LuongNhanVien>TimKiemLuongCVNam(String hsl, int nam){
        List<LuongNhanVien> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select L.* from LuongNhanVien as L,NhanVien as N where L.MANV = N.MANV and N.MAHSL = ?  and L.nam = ?");
            stmt.setString(1,RunApplication.ChucvuToMaHesoluong(hsl));
            stmt.setInt(2,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                list.add(luongNhanVien);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    
     public List<LuongNhanVien>TimKiemLuongThangNam(String maNV,int thang, int nam){
        List<LuongNhanVien> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongNhanVien where MANV = ? and thang = ? and nam = ?");
            stmt.setString(1,maNV);
            stmt.setInt(2,thang);
            stmt.setInt(3,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                list.add(luongNhanVien);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public List<LuongNhanVien>TimKiemLuongMaNam(String maNV, int nam){
        List<LuongNhanVien> list = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("select * from LuongNhanVien where MANV = ? and nam = ?");
            stmt.setString(1,maNV);
            stmt.setInt(2,nam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                LuongNhanVien luongNhanVien =new LuongNhanVien(rs.getString(1),rs.getInt(2),rs.getInt(3),
                        rs.getDouble(4));
                luongNhanVien.setNhanVienHanhChinh(nhanVienHanhChinh_dao.TimKiemMa(rs.getString(5)));
                list.add(luongNhanVien);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public float GetTongTruLuongCa(String maNV,int thang, int nam){
        ChamCongNhanVien chamCongNhanVien = null;
        double sum = 0;
        try{
            PreparedStatement stmt = con.prepareStatement("select NGAYCHAM, LUONG from ChamCongNhanVien where TrangThai = ? and MANV = ?");
            stmt.setInt(1,1);
            stmt.setString(2,maNV);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                String date = rs.getString(1);
                String month = date.substring(date.length() - 5, date.length()-3);
                
                String year = date.substring(0, date.length()-6);
              
                 try{
                    int nmonth = Integer.parseInt(month);
                    int nYear = Integer.parseInt(year);
                    System.out.println(nmonth+"and"+nYear); // output = 25
                    
                    if((thang == nmonth) && nYear == nam ) sum+= rs.getFloat(2);
                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
               
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (float) sum;
    }
    
}