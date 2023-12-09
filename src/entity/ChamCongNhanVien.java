package entity;

import java.sql.Date;

public class ChamCongNhanVien {
    protected String maCong;
    protected Date ngayCham;
    protected CaLamViec caLamViec;
    protected NhanVienHanhChinh nhanVienHanhChinh;
    protected int trangThai; //0 or 1: 0 - nghỉ \\ 1 - có mặt
    protected int nghiPhep; //0 or 1: 0 - không \\ 1 - có
    

    public ChamCongNhanVien() {
    }

    public ChamCongNhanVien(String maCong, Date ngayCham, int trangThai, int nghiPhep) {
        this.maCong = maCong;
        this.ngayCham = ngayCham;
        this.trangThai = trangThai;
        this.nghiPhep = nghiPhep;
    }

    public String getMaCong() {
        return maCong;
    }

    public void setMaCong(String maCong) {
        this.maCong = maCong;
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

    public NhanVienHanhChinh getNhanVienHanhChinh() {
        return nhanVienHanhChinh;
    }

    public void setNhanVienHanhChinh(NhanVienHanhChinh nhanVienHanhChinh) {
        this.nhanVienHanhChinh = nhanVienHanhChinh;
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
}
