package com.example.mailisa_beauty.Model;

public class FeedBack {
    private int maFB,maLKH;
    private float soSao;
    private String ghiChuKH,ghiChuQL;

    public FeedBack() {
    }

    public FeedBack(int maFB, int maLKH, float soSao, String ghiChuKH, String ghiChuQL) {
        this.maFB = maFB;
        this.maLKH = maLKH;
        this.soSao = soSao;
        this.ghiChuKH = ghiChuKH;
        this.ghiChuQL = ghiChuQL;
    }

    public int getMaFB() {
        return maFB;
    }

    public void setMaFB(int maFB) {
        this.maFB = maFB;
    }

    public int getMaLKH() {
        return maLKH;
    }

    public void setMaLKH(int maLKH) {
        this.maLKH = maLKH;
    }

    public float getSoSao() {
        return soSao;
    }

    public void setSoSao(float soSao) {
        this.soSao = soSao;
    }

    public String getGhiChuKH() {
        return ghiChuKH;
    }

    public void setGhiChuKH(String ghiChuKH) {
        this.ghiChuKH = ghiChuKH;
    }

    public String getGhiChuQL() {
        return ghiChuQL;
    }

    public void setGhiChuQL(String ghiChuQL) {
        this.ghiChuQL = ghiChuQL;
    }
}
