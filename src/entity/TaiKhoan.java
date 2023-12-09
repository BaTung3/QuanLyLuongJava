package entity;

public class TaiKhoan {
    protected String maTK;
    protected String tenDN;
    protected String matKhau;

    public TaiKhoan() {
    }

    public TaiKhoan(String maTK, String tenDN, String matKhau) {
        this.maTK = maTK;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
