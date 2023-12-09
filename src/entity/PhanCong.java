package entity;

public class PhanCong {
    protected String maPC;
    protected CongNhan congNhan;
    protected CongDoan congDoan;
    protected int soLuongCanLam;

    public PhanCong() {
    }

    public PhanCong(String maPC, int soLuongCanLam) {
        this.maPC = maPC;
        this.soLuongCanLam = soLuongCanLam;
    }

    public String getMaPC() {
        return maPC;
    }

    public void setMaPC(String maPC) {
        this.maPC = maPC;
    }

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
    }

    public CongDoan getCongDoan() {
        return congDoan;
    }

    public void setCongDoan(CongDoan congDoan) {
        this.congDoan = congDoan;
    }

    public int getSoLuongCanLam() {
        return soLuongCanLam;
    }

    public void setSoLuongCanLam(int soLuongCanLam) {
        this.soLuongCanLam = soLuongCanLam;
    }
}
