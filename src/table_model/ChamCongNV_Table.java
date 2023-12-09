package table_model;

import entity.ChamCongNhanVien;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ChamCongNV_Table extends AbstractTableModel {

    private List<ChamCongNhanVien> ds;
    String [] headers = {"Mã Công", "Tên Nhân Viên", "Ngày Chấm","Ca Làm",
            "Giờ Làm", "Trạng Thái", "Nghỉ Phép"};

    public ChamCongNV_Table(List<ChamCongNhanVien> ds){
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        ChamCongNhanVien chamCongNhanVien = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return chamCongNhanVien.getMaCong();
            case 1:
                return chamCongNhanVien.getNhanVienHanhChinh().getHoTen();
            case 2:
                return chamCongNhanVien.getNgayCham();
            case 3:
                return chamCongNhanVien.getCaLamViec().getTenCa();
            case 4:
                return chamCongNhanVien.getCaLamViec().getGioLam();
            case 5:
                return chamCongNhanVien.getTrangThai();
            case 6:
                return chamCongNhanVien.getNghiPhep();
            default:
                return chamCongNhanVien;
        }
    }
}