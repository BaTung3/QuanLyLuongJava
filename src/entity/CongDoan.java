package entity;

public class CongDoan {
    protected String maCD;
    protected String tenCongDoan;
    protected double giaCongDoan;
    protected int soLuong;
    protected SanPham sanPham;
    protected CongDoan congDoanYeuCau;

    public CongDoan() {
    }

    public CongDoan(String maCD, String tenCongDoan, double giaCongDoan, int soLuong) {
        this.maCD = maCD;
        this.tenCongDoan = tenCongDoan;
        this.giaCongDoan = giaCongDoan;
        this.soLuong = soLuong;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCongDoan() {
        return tenCongDoan;
    }

    public void setTenCongDoan(String tenCongDoan) {
        this.tenCongDoan = tenCongDoan;
    }

    public double getGiaCongDoan() {
        return giaCongDoan;
    }

    public void setGiaCongDoan(double giaCongDoan) {
        this.giaCongDoan = giaCongDoan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public CongDoan getCongDoanYeuCau() {
        return congDoanYeuCau;
    }

    public void setCongDoanYeuCau(CongDoan congDoanYeuCau) {
        this.congDoanYeuCau = congDoanYeuCau;
    }
}
