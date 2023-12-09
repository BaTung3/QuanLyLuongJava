package entity;

import java.sql.Date;

public class NhanVienHanhChinh {
    protected String maNV;
    protected String hoTen;
    protected int cmnd;
    protected Date ngaySinh;
    protected String gioiTinh;
    protected double luongCoBan;
    protected String soDienThoai;
    protected String diaChi;
    protected double phuCap;
    protected HeSoLuong heSoLuong;
    protected Phongban phongban;

    public NhanVienHanhChinh(String maNV, String hoTen, int cmnd, Date ngaySinh, String gioiTinh,
                             double luongCoBan, String soDienThoai, String diaChi, double phuCap) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.luongCoBan = luongCoBan;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.phuCap = phuCap;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    public HeSoLuong getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(HeSoLuong heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public Phongban getPhongban() {
        return phongban;
    }

    public void setPhongban(Phongban phongban) {
        this.phongban = phongban;
    }
}
