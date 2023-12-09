package entity;

public class LuongNhanVien {
    protected String maLuong;
    protected int thang;
    protected int nam;
    protected double luong;
    protected NhanVienHanhChinh nhanVienHanhChinh;

    public LuongNhanVien() {
    }

    public LuongNhanVien(String maLuong, int thang, int nam, double luong) {
        this.maLuong = maLuong;
        this.thang = thang;
        this.nam = nam;
        this.luong = luong;
    }

    public String getMaLuong() {
        return maLuong;
    }

    public void setMaLuong(String maLuong) {
        this.maLuong = maLuong;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    public NhanVienHanhChinh getNhanVienHanhChinh() {
        return nhanVienHanhChinh;
    }

    public void setNhanVienHanhChinh(NhanVienHanhChinh nhanVienHanhChinh) {
        this.nhanVienHanhChinh = nhanVienHanhChinh;
    }
}
