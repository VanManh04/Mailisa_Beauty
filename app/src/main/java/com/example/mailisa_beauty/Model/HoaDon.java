package com.example.mailisa_beauty.Model;

import java.util.Date;

public class HoaDon {
    private int maHD,maLKH;
    private Date ngayTT;
    private String ghiChu;

    public HoaDon(int maHD, int maLKH, Date ngayTT, String ghiChu) {
        this.maHD = maHD;
        this.maLKH = maLKH;
        this.ngayTT = ngayTT;
        this.ghiChu = ghiChu;
    }

    public Date getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(Date ngayTT) {
        this.ngayTT = ngayTT;
    }

    public HoaDon() {
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaLKH() {
        return maLKH;
    }

    public void setMaLKH(int maLKH) {
        this.maLKH = maLKH;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
