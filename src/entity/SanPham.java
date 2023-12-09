package entity;

public class SanPham {
    protected String maSP;
    protected String tenSanPham;
    protected String kieuDang;
    protected String chatLieu;
    protected int soLuong;

    public SanPham(String maSP, String tenSanPham, String kieuDang, String chatLieu, int soLuong) {
        this.maSP = maSP;
        this.tenSanPham = tenSanPham;
        this.kieuDang = kieuDang;
        this.chatLieu = chatLieu;
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(String kieuDang) {
        this.kieuDang = kieuDang;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
