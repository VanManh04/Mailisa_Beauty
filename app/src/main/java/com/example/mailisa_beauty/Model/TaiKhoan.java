package com.example.mailisa_beauty.Model;

public class TaiKhoan {
    private int ma_TK;
    private String sdt,hoTen,matKhau,chucVu;

    public TaiKhoan() {
    }

    public TaiKhoan(int ma_TK, String sdt, String hoTen, String matKhau, String chucVu) {
        this.ma_TK = ma_TK;
        this.sdt = sdt;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public int getMa_TK() {
        return ma_TK;
    }

    public void setMa_TK(int ma_TK) {
        this.ma_TK = ma_TK;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
