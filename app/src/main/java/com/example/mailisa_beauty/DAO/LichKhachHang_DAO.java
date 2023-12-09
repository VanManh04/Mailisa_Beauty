package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.LichLamViec;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LichKhachHang_DAO {
    private SQLiteDatabase db;
    public LichKhachHang_DAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public long insert(LichKhachHang Lkh) {
        ContentValues values = new ContentValues();
//        int maLKH, int maTK, int maDV, Date ngayDat, String gioDat, String PTTT, String trangThat, String feedBack, String ghiChu
        values.put("maTK", Lkh.getMaTK());
        values.put("maDV", Lkh.getMaDV());
        values.put("ngayDat", sdf.format(Lkh.getNgayDat()));
        values.put("gioDat", Lkh.getGioDat());
        values.put("PTTT", Lkh.getPTTT());
        values.put("trangThat", Lkh.getTrangThai());
        values.put("feedBack", Lkh.getFeedBack());
        values.put("ghiChu", Lkh.getGhiChu());

        return db.insert("LichKhachHang", null, values);
    }

    public int update(LichKhachHang Lkh) {
        ContentValues values = new ContentValues();
        values.put("maTK", Lkh.getMaTK());
        values.put("maDV", Lkh.getMaDV());
        values.put("ngayDat", sdf.format(Lkh.getNgayDat()));
        values.put("gioDat", Lkh.getGioDat());
        values.put("PTTT", Lkh.getPTTT());
        values.put("trangThat", Lkh.getTrangThai());
        values.put("feedBack", Lkh.getFeedBack());
        values.put("ghiChu", Lkh.getGhiChu());
        return db.update("LichKhachHang", values, "maLKH = ?", new String[]{String.valueOf(Lkh.getMaLKH())});
    }

    public int delete(int ma_LKH) {
        return db.delete("LichKhachHang", "maLKH = ?", new String[]{String.valueOf(ma_LKH)});
    }
    @SuppressLint("Range")
    private List<LichKhachHang> getData(String sql, String... selectionArgs) {
        List<LichKhachHang> list = new ArrayList<LichKhachHang>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LichKhachHang obj = new LichKhachHang();
            obj.setMaLKH(cursor.getInt(cursor.getColumnIndex("maLKH")));
            obj.setMaTK(cursor.getInt(cursor.getColumnIndex("maTK")));
            obj.setMaDV(cursor.getInt(cursor.getColumnIndex("maDV")));
            try {
                obj.setNgayDat(sdf.parse(cursor.getString(cursor.getColumnIndex("ngayDat"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setGioDat(cursor.getString(cursor.getColumnIndex("gioDat")));
            obj.setPTTT(cursor.getString(cursor.getColumnIndex("PTTT")));
            obj.setTrangThai(cursor.getString(cursor.getColumnIndex("trangThat")));
            obj.setFeedBack(cursor.getString(cursor.getColumnIndex("feedBack")));
            obj.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            list.add(obj);
        }
        return list;
    }
    public int deleteAllByMaTK(int maTK) {
        return db.delete("LichKhachHang", "maTK = ?", new String[]{String.valueOf(maTK)});
    }


    //GET ALL
    public List<LichKhachHang> getAll(){
        String sql = "SELECT * FROM LichKhachHang";
        return getData(sql);
    }

    //GET by maTK
    public List<LichKhachHang> getByMaTK(int maTK) {
        String sql = "SELECT * FROM LichKhachHang WHERE maTK = ?";
        String[] selectionArgs = {String.valueOf(maTK)};
        return getData(sql, selectionArgs);
    }
    public List<LichKhachHang> getALLByMaLKH(int maLKH) {
        String sql = "SELECT * FROM LichKhachHang WHERE maLKH = ?";
        String[] selectionArgs = {String.valueOf(maLKH)};
        return getData(sql, selectionArgs);
    }

    public LichKhachHang getByMaLKH(int maLKH) {
        String sql = "SELECT * FROM LichKhachHang WHERE maLKH=?";
        List<LichKhachHang> list = getData(sql, String.valueOf(maLKH));
        return list.isEmpty() ? null : list.get(0);
    }
    public List<LichKhachHang> getAllByTrangThai(String trangThai) {
        String sql = "SELECT * FROM LichKhachHang WHERE trangThat = ?";
        String[] selectionArgs = {trangThai};
        return getData(sql, selectionArgs);
    }
    public List<LichKhachHang> getByMaTKAndTrangThai(int maTK, String trangThai) {
        String sql = "SELECT * FROM LichKhachHang WHERE maTK = ? AND trangThat = ?";
        String[] selectionArgs = {String.valueOf(maTK), trangThai};
        return getData(sql, selectionArgs);
    }
}
