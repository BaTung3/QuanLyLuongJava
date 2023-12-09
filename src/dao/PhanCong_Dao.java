package dao;

import connection.MyConnection;
import entity.CongDoan;
import entity.PhanCong;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhanCong_Dao {
    private Connection con;
    private CongNhan_Dao congNhan_dao;
    private CongDoan_Dao congDoan_dao;

    public PhanCong_Dao() {
        con = MyConnection.getInstance().getConnection();
        congNhan_dao = new CongNhan_Dao();
        congDoan_dao = new CongDoan_Dao();
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

    public List<PhanCong> getLS() {
        List<PhanCong> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_PCCN");
            while (rs.next()) {
                PhanCong phanCong = new PhanCong(rs.getString(1), rs.getInt(2));
                phanCong.setCongNhan(congNhan_dao.TimKiemMa(rs.getString(3)));
                phanCong.setCongDoan(congDoan_dao.TimKiemMa(rs.getString(4)));
                ds.add(phanCong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public boolean addPhanCong(PhanCong phanCong) {
        try {
            PreparedStatement cnAdd = con.prepareStatement("INSERT INTO PhanCong ([SOLUONGCANLAM],[MACN],[MACD]) " +
                    "VALUES(?,?,?)");
            cnAdd.setInt(1, phanCong.getSoLuongCanLam());
            cnAdd.setString(2, phanCong.getCongNhan().getMaCN());
            cnAdd.setString(3, phanCong.getCongDoan().getMaCD());

            int n = cnAdd.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deletePhanCong(String maPC) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from PhanCong where MAPC = ?");
            stmt.setString(1, maPC);
            int n = stmt.executeUpdate();
            if (n > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updatePhanCong(PhanCong phanCong) {
        String sql = "update PhanCong set SoLuongCanLam = ?, MaCD = ? where MAPC = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, phanCong.getSoLuongCanLam());
            stmt.setString(2, phanCong.getCongDoan().getMaCD());
            stmt.setString(5, phanCong.getMaPC());

            int n = stmt.executeUpdate();
            if (n > 0)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public PhanCong TimKiemMa(String ma) {
//        CongDoan congDoan = null;
//        try {
//            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MACD = ?");
//            stmt.setString(1, ma);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                congDoan = new CongDoan(rs.getString(1), rs.getString(2), rs.getDouble(3),
//                        rs.getInt(4));
//                congDoan.setSanPham(congNhan_dao.TimKiemMa(rs.getString(5)));
//                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return congDoan;
//    }
//
//    public CongDoan TimKiemTen(String ten) {
//        CongDoan congDoan = null;
//        try {
//            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where TENCONGDOAN = ?");
//            stmt.setString(1, ten);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                congDoan = new CongDoan(rs.getString(1), rs.getString(2), rs.getDouble(3),
//                        rs.getInt(4));
//                congDoan.setSanPham(congNhan_dao.TimKiemMa(rs.getString(5)));
//                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return congDoan;
//    }
//
//    public List<CongDoan> TimKiemMaSP(String ma) {
//        List<CongDoan> congDoanList = new ArrayList<>();
//        try {
//            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MASP = ?");
//            stmt.setString(1, ma);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                CongDoan congDoan = new CongDoan(rs.getString(1), rs.getString(2), rs.getDouble(3),
//                        rs.getInt(4));
//                congDoan.setSanPham(congNhan_dao.TimKiemMa(rs.getString(5)));
//                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
//                congDoanList.add(congDoan);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return congDoanList;
//    }
//
//    public CongDoan TimKiemMaCDYC(String ma) {
//        CongDoan congDoan = null;
//        try {
//            PreparedStatement stmt = con.prepareStatement("select * from CongDoan where MACDYC = ?");
//            stmt.setString(1, ma);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                congDoan = new CongDoan(rs.getString(1), rs.getString(2), rs.getDouble(3),
//                        rs.getInt(4));
//                congDoan.setSanPham(congNhan_dao.TimKiemMa(rs.getString(5)));
//                congDoan.setCongDoanYeuCau(this.TimKiemMa(rs.getString(6)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return congDoan;
//    }
}

