package table_model;

import entity.CongNhan;
import entity.SanPham;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SanPham_Table extends AbstractTableModel {

    private List<SanPham> ds;
    String [] headers = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Kiểu Dáng","Chất Liệu","Số Lượng"};

    public SanPham_Table(List<SanPham> ds){
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
        SanPham sanPham = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return sanPham.getMaSP();
            case 1:
                return sanPham.getTenSanPham();
            case 2:
                return sanPham.getKieuDang();
            case 3:
                return sanPham.getChatLieu();
            case 4:
                return sanPham.getSoLuong();
            default:
                return sanPham;
        }
    }
}
