package table_model;

import entity.CongDoan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CongDoan_Table extends AbstractTableModel {

    private List<CongDoan> ds;
    String [] headers = {"Mã CĐ", "Tên CĐ", "Giá CĐ","Mã Sản Phẩm","Tên Sản Phẩm","Số Lượng","Công Đoạn yêu Cầu"};

    public CongDoan_Table(List<CongDoan> ds){
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
        CongDoan congDoan = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return congDoan.getMaCD();
            case 1:
                return congDoan.getTenCongDoan();
            case 2:
                return congDoan.getGiaCongDoan();
            case 3:
                return congDoan.getSanPham().getMaSP();
            case 4:
                return congDoan.getSanPham().getTenSanPham();
            case 5:
                return congDoan.getSoLuong();
            case 6:
                if (congDoan.getCongDoanYeuCau() != null)
                    return congDoan.getCongDoanYeuCau().getTenCongDoan();
                else
                    return "";
            default:
                return congDoan;
        }
    }
}
