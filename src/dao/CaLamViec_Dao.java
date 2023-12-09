package dao;

import connection.MyConnection;
import entity.CaLamViec;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaLamViec_Dao {

    private Connection con;

    public CaLamViec_Dao() {
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
    public List<CaLamViec> getLS() {
        List<CaLamViec> ds = new ArrayList<>();
        try {
            ResultSet rs = getResultSet("select_CLV");
            while(rs.next()){
                CaLamViec caLamViec =new CaLamViec(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDouble(5));
                ds.add(caLamViec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public CaLamViec TimKiemMa(String ma){
        CaLamViec caLamViec = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CALAMVIEC where MACA = ?");
            stmt.setString(1,ma);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                caLamViec =new CaLamViec(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDouble(5));            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return caLamViec;
    }

    public CaLamViec TimKiemCaLam(String caLam){
        CaLamViec caLamViec = null;
        try{
            PreparedStatement stmt = con.prepareStatement("select * from CALAMVIEC where CALAM = ?");
            stmt.setString(1,caLam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                caLamViec =new CaLamViec(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDouble(5));            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return caLamViec;
    }
}

