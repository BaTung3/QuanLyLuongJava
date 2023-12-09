package entity;

public class Phongban {
    protected String maPB;
    protected String tenPB;

    public Phongban() {
    }

    public Phongban(String maPB, String tenPB) {
        this.maPB = maPB;
        this.tenPB = tenPB;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }
}
