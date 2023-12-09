package table_model;

import entity.ChamCongCongNhan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ChamCongCN_Table extends AbstractTableModel {

    private List<ChamCongCongNhan> ds;
    String [] headers = {"Mã Công", "Tên Công Nhân", "Sản Phẩm","Công Đoạn","Ngày Chấm","Ca Làm",
                        "Giờ Làm", "Số SP", "Trạng Thái", "Nghỉ Phép"};

    public ChamCongCN_Table(List<ChamCongCongNhan> ds){
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
        ChamCongCongNhan chamCongCongNhan = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return chamCongCongNhan.getMaCong();
            case 1:
                return chamCongCongNhan.getCongNhan().getHoTen();
            case 2:
                return chamCongCongNhan.getSanPham().getTenSanPham();
            case 3:
                return chamCongCongNhan.getCongDoan().getTenCongDoan();
            case 4:
                return chamCongCongNhan.getNgayCham();
            case 5:
                return chamCongCongNhan.getCaLam().getTenCa();
            case 6:
                return chamCongCongNhan.getCaLam().getGioLam();
            case 7:
                return chamCongCongNhan.getSoSanPham();
            case 8:
                return chamCongCongNhan.getTrangThai();
            case 9:
                return chamCongCongNhan.getNghiPhep();
            default:
                return chamCongCongNhan;
        }
    }
}

