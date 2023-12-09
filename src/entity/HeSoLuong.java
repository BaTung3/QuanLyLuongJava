package entity;

public class HeSoLuong {
    protected String maHSL;
    protected double heSoLuong;

    public HeSoLuong() {
    }

    public HeSoLuong(String maHSL, double heSoLuong) {
        this.maHSL = maHSL;
        this.heSoLuong = heSoLuong;
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
}
