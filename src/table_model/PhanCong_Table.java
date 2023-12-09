package table_model;

import dao.PhanCong_Dao;
import entity.CongDoan;
import entity.PhanCong;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PhanCong_Table extends AbstractTableModel {

    private List<PhanCong> ds;
    String [] headers = {"Mã Phân Công", "Tên Công Nhân", "Tên Sản Phẩm","Tên Công Đoạn","Số Lượng Cần Làm"};

    public PhanCong_Table(List<PhanCong> ds){
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
        PhanCong phanCong = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return phanCong.getMaPC();
            case 1:
                return phanCong.getCongNhan().getHoTen();
            case 2:
                return phanCong.getCongDoan().getSanPham().getTenSanPham();
            case 3:
                return phanCong.getCongDoan().getTenCongDoan();
            case 4:
                return phanCong.getSoLuongCanLam();
            default:
                return phanCong;
        }
    }
}

