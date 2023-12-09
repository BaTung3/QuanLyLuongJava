package dao;

import connection.MyConnection;
import entity.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPham_Dao {
    private Connection con;

    public SanPham_Dao() {
        con = MyConnection.getInstance().getConnection();
    }

    public ResultSet getResultSet(String StoreName) throws Exception {
        ResultSet rs = null;
        try {
            String callStore;
            callStore = "{Call " + StoreName + "}";
            CallableStatement cs = this.con.prepareCall(callStore);
            cs.executeQuery();
            rs = cs.getResultSet();
        } catch (Exception e) {
            throw new Exception("Error get Store " + e.getMessage());
        }
        return rs;
    }

    public List<SanPham> getLS() {
        List<SanPham> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_SP");
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                ds.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public boolean addSanPham(SanPham sanPham) {
        try {
            PreparedStatement sanphamAdd = con.prepareStatement("INSERT INTO SanPham ([TENSANPHAM],[KIEUDANG],[CHATLIEU],[SOLUONG]) " +
                    "VALUES(?,?,?,?)");
            sanphamAdd.setString(1, sanPham.getTenSanPham());
            sanphamAdd.setString(2, sanPham.getKieuDang());
            sanphamAdd.setString(3, sanPham.getChatLieu());
            sanphamAdd.setInt(4, sanPham.getSoLuong());

            int n = sanphamAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSanPham(String maSanPham) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from SanPham where MASP = ?");
            stmt.setString(1, maSanPham);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSanPham(String maSanPham, SanPham sanPham) {
        String sql = "update SanPham set [TENSANPHAM] = ?,[KIEUDANG] = ?,[CHATLIEU] = ?,[SOLUONG] = ? where MASP = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, sanPham.getTenSanPham());
            stmt.setString(2, sanPham.getKieuDang());
            stmt.setString(3, sanPham.getChatLieu());
            stmt.setInt(4, sanPham.getSoLuong());
            stmt.setString(5, maSanPham);

            int n = stmt.executeUpdate();
            if (n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public SanPham TimKiemMa(String ma) {
        SanPham sanPham = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where MASP = ?");
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPham;
    }

    public SanPham TimKiemTen(String ten) {
        SanPham sanPham = null;
        try {
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where TENSANPHAM = ?");
            stmt.setString(1, ten);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
    public boolean updateSoLuong(String maSP,int soLuong) {
        String sql = "update SANPHAM set SOLUONG = ? where MASP = ?";
        try {
            System.out.println("OK1");
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,soLuong);
            stmt.setString(2,maSP);

            int n = stmt.executeUpdate();
            System.out.println("OK2");
            if(n > 0){
                System.out.println("OK3");
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public String getMa() throws SQLException {
        String ma = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT MAX([MASP]) FROM [dbo].[SanPham]");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ma = rs.getString(1);
        }
        return ma;
    }

    public List<SanPham> TimKiemTenTD(String ten) {
        List<SanPham> list = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where TENSANPHAM LIKE '%"+ten+"%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                list.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPham> TimKiemKDTD(String kd) {
        List<SanPham> list = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where KIEUDANG LIKE '%"+kd+"%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                list.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPham> TimKiemCLTD(String cl) {
        List<SanPham> list = new ArrayList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("select * from SanPham where CHATLIEU LIKE '%"+cl+"%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                list.add(sanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
