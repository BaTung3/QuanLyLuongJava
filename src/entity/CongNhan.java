package entity;

import java.sql.Date;

public class CongNhan {
    protected String maCN;
    protected String hoTen;
    protected int cmnd;
    protected Date ngaySinh;
    protected String gioiTinh;
    protected String tayNghe;
    protected String sodienThoai;
    protected String diaChi;
    protected double phuCap;
    protected Phongban phongban;

    public CongNhan() {
    }

    public CongNhan(String maCN, String hoTen, int cmnd, Date ngaySinh, String gioiTinh, String tayNghe, String sodienThoai, String diaChi, double phuCap) {
        this.maCN = maCN;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.tayNghe = tayNghe;
        this.sodienThoai = sodienThoai;
        this.diaChi = diaChi;
        this.phuCap = phuCap;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
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

    public String getTayNghe() {
        return tayNghe;
    }

    public void setTayNghe(String tayNghe) {
        this.tayNghe = tayNghe;
    }

    public String getSodienThoai() {
        return sodienThoai;
    }

    public void setSodienThoai(String sodienThoai) {
        this.sodienThoai = sodienThoai;
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

    public Phongban getPhongban() {
        return phongban;
    }

    public void setPhongban(Phongban phongban) {
        this.phongban = phongban;
    }
}
