package entity;

import java.sql.Date;

public class ChamCongCongNhan {
    protected String maCong;
    protected Date ngayCham;
    protected SanPham sanPham;
    protected CongDoan congDoan;
    protected CaLamViec caLamViec;
    protected CongNhan congNhan;
    protected int soSanPham;
    protected int trangThai;
    protected int nghiPhep;

    public ChamCongCongNhan() {
    }

    public ChamCongCongNhan(String maCong, Date ngayCham, int soSanPham, int trangThai, int nghiPhep) {
        this.maCong = maCong;
        this.ngayCham = ngayCham;
        this.soSanPham = soSanPham;
        this.trangThai = trangThai;
        this.nghiPhep = nghiPhep;
    }

    public String getMaCong() {
        return maCong;
    }

    public void setMaCong(String maCong) {
        this.maCong = maCong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public CongDoan getCongDoan() {
        return congDoan;
    }

    public void setCongDoan(CongDoan congDoan) {
        this.congDoan = congDoan;
    }

    public CaLamViec getCaLam() {
        return caLamViec;
    }

    public void setCaLam(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public int getSoSanPham() {
        return soSanPham;
    }

    public void setSoSanPham(int soSanPham) {
        this.soSanPham = soSanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getNghiPhep() {
        return nghiPhep;
    }

    public void setNghiPhep(int nghiPhep) {
        this.nghiPhep = nghiPhep;
    }

    public Date getNgayCham() {
        return ngayCham;
    }

    public void setNgayCham(Date ngayCham) {
        this.ngayCham = ngayCham;
    }

    public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
    }
}
