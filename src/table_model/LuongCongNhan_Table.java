package table_model;

import entity.LuongCongNhan;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LuongCongNhan_Table extends AbstractTableModel {

    private List<LuongCongNhan> ds;
    String [] headers = {"Mã Lương","Mã Công Nhân", "Tên Công Nhân", "CMND","Ngày Sinh",
            "Giới Tính", "Phòng Ban", "Trợ Cấp","Tháng", "Năm", "Lương"};

    public LuongCongNhan_Table(List<LuongCongNhan> ds){
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
        LuongCongNhan luongCongNhan = ds.get(rowIndex);
        switch (columnIndex){
            case 0:
                return luongCongNhan.getMaLuong();
            case 1:
                return luongCongNhan.getCongNhan().getMaCN();
            case 2:
                return luongCongNhan.getCongNhan().getHoTen();
            case 3:
                return luongCongNhan.getCongNhan().getCmnd();
            case 4:
                return luongCongNhan.getCongNhan().getNgaySinh();
            case 5:
                return luongCongNhan.getCongNhan().getGioiTinh();
            case 6:
                return luongCongNhan.getCongNhan().getPhongban().getTenPB();
            case 7:
                return luongCongNhan.getCongNhan().getPhuCap();
            case 8:
                return luongCongNhan.getThang();
            case 9:
                return luongCongNhan.getNam();
            case 10:
                return luongCongNhan.getLuong();
            default:
                return luongCongNhan;
        }
    }
}
