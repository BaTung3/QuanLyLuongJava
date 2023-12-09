package entity;

public class CaLamViec {
    protected String maCa;
    protected String tenCa;
    protected String gioLam;
    protected String caLam;
    protected double luongCa;

    public CaLamViec() {
    }

    public CaLamViec(String maCa, String tenCa, String gioLam, String caLam, double luongCa) {
        this.maCa = maCa;
        this.tenCa = tenCa;
        this.gioLam = gioLam;
        this.caLam = caLam;
        this.luongCa = luongCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public void setGioLam(String gioLam) {
        this.gioLam = gioLam;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }

    public void setLuongCa(double luongCa) {
        this.luongCa = luongCa;
    }

    public String getMaCa() {
        return maCa;
    }

    public String getGioLam() {
        return gioLam;
    }

    public String getCaLam() {
        return caLam;
    }

    public double getLuongCa() {
        return luongCa;
    }

    public String getTenCa() {
        return tenCa;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }
}
