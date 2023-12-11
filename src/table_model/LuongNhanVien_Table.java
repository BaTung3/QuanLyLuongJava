package table_model;

import application.RunApplication;
import entity.LuongNhanVien;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LuongNhanVien_Table extends AbstractTableModel {

    private List<LuongNhanVien> ds;
    String [] headers = {"Mã Lương","Mã Nhân Viên", "Tên Nhân Viên", "CMND","Lương Cơ Bản",
            "Chức Vụ", "Phụ Cấp","Phòng Ban", "Tháng", "Năm", "Lương"};

    public LuongNhanVien_Table(List<LuongNhanVien> ds){
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
        if(columnIndex == 4||columnIndex == 10){
            return Double.class;
        }
        else{
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }
    }

    
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LuongNhanVien luongNhanVien = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return luongNhanVien.getMaLuong();
            case 1:
                return luongNhanVien.getNhanVienHanhChinh().getMaNV();
            case 2:
                return luongNhanVien.getNhanVienHanhChinh().getHoTen();
            case 3:
                return luongNhanVien.getNhanVienHanhChinh().getCmnd();
            case 4:
                return luongNhanVien.getNhanVienHanhChinh().getLuongCoBan();
            case 5:
                return RunApplication.HesoluongToChucvu(luongNhanVien.getNhanVienHanhChinh().getHeSoLuong().getHeSoLuong());
            case 6:
                return luongNhanVien.getNhanVienHanhChinh().getPhuCap();
            case 7:
                return luongNhanVien.getNhanVienHanhChinh().getPhongban().getTenPB();
            case 8:
                return luongNhanVien.getThang();
            case 9:
                return luongNhanVien.getNam();
            case 10:
                return  luongNhanVien.getLuong();
            default:
                return luongNhanVien;
        }
    }
}