package com.example.mailisa_beauty.Model;

import android.net.Uri;

public class DichVu {
    private int maDV;
    private Uri hinhAnh;
    private String tenDV;

    private String loaiDV,trangThai;
    private int giaDV,giaSALE;
    private String ghiChu;

    public DichVu() {
    }

    public DichVu(int maDV, Uri hinhAnh, String tenDV, String loaiDV, String trangThai, int giaDV, int giaSALE, String ghiChu) {
        this.maDV = maDV;
        this.hinhAnh = hinhAnh;
        this.tenDV = tenDV;
        this.loaiDV = loaiDV;
        this.trangThai = trangThai;
        this.giaDV = giaDV;
        this.giaSALE = giaSALE;
        this.ghiChu = ghiChu;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public Uri getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Uri hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
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

    public int getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    public int getGiaSALE() {
        return giaSALE;
    }

    public void setGiaSALE(int giaSALE) {
        this.giaSALE = giaSALE;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
