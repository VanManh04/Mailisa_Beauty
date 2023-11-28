package com.example.mailisa_beauty.Model;

import java.util.Date;

public class LichKhachHang {
    private int maLKH,maTK,maDV;
    private Date ngayDat;
    private String gioDat,PTTT,trangThai,feedBack,ghiChu;

    public LichKhachHang() {
    }

    public LichKhachHang(int maLKH, int maTK, int maDV, Date ngayDat, String gioDat, String PTTT, String trangThai, String feedBack, String ghiChu) {
        this.maLKH = maLKH;
        this.maTK = maTK;
        this.maDV = maDV;
        this.ngayDat = ngayDat;
        this.gioDat = gioDat;
        this.PTTT = PTTT;
        this.trangThai = trangThai;
        this.feedBack = feedBack;
        this.ghiChu = ghiChu;
    }

    public int getMaLKH() {
        return maLKH;
    }

    public void setMaLKH(int maLKH) {
        this.maLKH = maLKH;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGioDat() {
        return gioDat;
    }

    public void setGioDat(String gioDat) {
        this.gioDat = gioDat;
    }

    public String getPTTT() {
        return PTTT;
    }

    public void setPTTT(String PTTT) {
        this.PTTT = PTTT;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
