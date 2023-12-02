package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("maLKH", hoaDon.getMaLKH());
        values.put("ngayTT",sdf.format(hoaDon.getNgayTT()));
        values.put("ghiChu", hoaDon.getGhiChu());
        return db.insert("HoaDon", null, values);
    }

    public int update(HoaDon hoaDon) {
        ContentValues values = new ContentValues();
        values.put("maLKH", hoaDon.getMaLKH());
        values.put("ngayTT",sdf.format(hoaDon.getNgayTT()));
        values.put("ghiChu", hoaDon.getGhiChu());
        return db.update("HoaDon", values, "maHD = ?", new String[]{String.valueOf(hoaDon.getMaHD())});
    }

    public int delete(int maHD) {
        return db.delete("HoaDon", "maHD = ?", new String[]{String.valueOf(maHD)});
    }

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHD(cursor.getInt(cursor.getColumnIndex("maHD")));
            obj.setMaLKH(cursor.getInt(cursor.getColumnIndex("maLKH")));
            try {
                obj.setNgayTT(sdf.parse(cursor.getString(cursor.getColumnIndex("ngayTT"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            list.add(obj);
        }
        cursor.close();
        return list;
    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }


}
