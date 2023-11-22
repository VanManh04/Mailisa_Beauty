package com.example.mailisa_beauty.Model;

public class DichVu {
    private int maDV;
    private String hinhAnh,tenDV;
    private double giaDV;
    private String loaiDV,trangThai,ghiChu;

    public DichVu() {
    }

    public DichVu(int maDV, String hinhAnh, String tenDV, double giaDV, String loaiDV, String trangThai, String ghiChu) {
        this.maDV = maDV;
        this.hinhAnh = hinhAnh;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.loaiDV = loaiDV;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(double giaDV) {
        this.giaDV = giaDV;
    }

    public String getLoaiDV() {
        return loaiDV;
    }

    public void setLoaiDV(String loaiDV) {
        this.loaiDV = loaiDV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
