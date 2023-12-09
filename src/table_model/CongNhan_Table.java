package table_model;

import entity.CongNhan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CongNhan_Table extends AbstractTableModel {

    private List<CongNhan> ds;
    String [] headers = {"Mã CN", "Họ Tên", "CMND","Ngày Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại","Tay Nghề", "Trợ Cấp", "Phòng Ban"};

    public CongNhan_Table(List<CongNhan> ds){
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
        CongNhan congNhan = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return congNhan.getMaCN();
            case 1:
                return congNhan.getHoTen();
            case 2:
                return congNhan.getCmnd();
            case 3:
                return congNhan.getNgaySinh();
            case 4:
                return congNhan.getGioiTinh();
            case 5:
                return congNhan.getDiaChi();
            case 6:
                return congNhan.getSodienThoai();
            case 7:
                return congNhan.getTayNghe();
            case 8:
                return congNhan.getPhuCap();
            case 9:
                return congNhan.getPhongban().getTenPB();
            default:
                return congNhan;
        }


    }
}

