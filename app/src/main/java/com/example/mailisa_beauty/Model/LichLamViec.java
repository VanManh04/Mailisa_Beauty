package com.example.mailisa_beauty.Model;

import java.util.Date;

public class LichLamViec {
    private int maLLV,maTK;
    private Date ngayBatDau;
    private int ca;
    private String ghiChu;

    public LichLamViec() {
    }

    public LichLamViec(int maLLV, int maTK, Date ngayBatDau, int ca, String ghiChu) {
        this.maLLV = maLLV;
        this.maTK = maTK;
        this.ngayBatDau = ngayBatDau;
        this.ca = ca;
        this.ghiChu = ghiChu;
    }

    public int getMaLLV() {
        return maLLV;
    }

    public void setMaLLV(int maLLV) {
        this.maLLV = maLLV;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
