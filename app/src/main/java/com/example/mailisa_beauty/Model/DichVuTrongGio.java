package com.example.mailisa_beauty.Model;

public class DichVuTrongGio {
    private int maDVTG,maTK,maDV,soLuong;
    private boolean isCheck;

    public DichVuTrongGio() {
    }

    public DichVuTrongGio(int maDVTG, int maTK, int maDV, int soLuong, boolean isCheck) {
        this.maDVTG = maDVTG;
        this.maTK = maTK;
        this.maDV = maDV;
        this.soLuong = soLuong;
        this.isCheck = isCheck;
    }

    public int getMaDVTG() {
        return maDVTG;
    }

    public void setMaDVTG(int maDVTG) {
        this.maDVTG = maDVTG;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
