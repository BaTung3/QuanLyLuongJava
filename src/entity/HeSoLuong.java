package entity;

public class HeSoLuong {
    protected String maHSL;
    protected double heSoLuong;
     protected String chucVu;

    public HeSoLuong() {
    }

    public HeSoLuong(String maHSL, double heSoLuong) {
        this.maHSL = maHSL;
        this.heSoLuong = heSoLuong;
    }

    public HeSoLuong(String maHSL, double heSoLuong, String chucVu) {
        this.maHSL = maHSL;
        this.heSoLuong = heSoLuong;
        this.chucVu = chucVu;
    }
    
    

    public String getMaHSL() {
        return maHSL;
    }

    public void setMaHSL(String maHSL) {
        this.maHSL = maHSL;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
