package table_model;

import entity.NhanVienHanhChinh;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class NhanVien_Table extends AbstractTableModel {

    private List<NhanVienHanhChinh> ds;
    String [] headers = {"Mã NV", "Họ Tên", "CMND","Ngày Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại","Lương Cơ Bản", "Phụ Cấp", "Phòng Ban", "Hệ Số Lương"};

    public NhanVien_Table(List<NhanVienHanhChinh> ds){
        super();
        this.ds = ds;
    }
    public String getColumnName(int column){
        return headers[column];
    }
    @Override
    public int getRowCount() {
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
         if(columnIndex == 7){
            return Double.class;
        }
        else{
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        } // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NhanVienHanhChinh nhanVienHanhChinh = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return nhanVienHanhChinh.getMaNV();
            case 1:
                return nhanVienHanhChinh.getHoTen();
            case 2:
                return nhanVienHanhChinh.getCmnd();
            case 3:
                return nhanVienHanhChinh.getNgaySinh();
            case 4:
                return nhanVienHanhChinh.getGioiTinh();
            case 5:
                return nhanVienHanhChinh.getDiaChi();
            case 6:
                return nhanVienHanhChinh.getSoDienThoai();
            case 7:
                return nhanVienHanhChinh.getLuongCoBan();
            case 8:
                return nhanVienHanhChinh.getPhuCap();
            case 9:
                return nhanVienHanhChinh.getPhongban().getTenPB();
            case 10:
                return nhanVienHanhChinh.getHeSoLuong().getHeSoLuong();
            default:
                return nhanVienHanhChinh;
        }


    }
}

