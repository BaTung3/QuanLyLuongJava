package entity;

public class LuongCongNhan {
    protected String maLuong;
    protected CongNhan congNhan;
    protected int thang;
    protected int nam;
    protected double luong;

    public LuongCongNhan() {
    }

    public LuongCongNhan(String maLuong, int thang, int nam, double luong) {
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

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
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
}
