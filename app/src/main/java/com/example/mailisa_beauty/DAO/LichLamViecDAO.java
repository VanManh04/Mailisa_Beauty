package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.LichLamViec;
import com.example.mailisa_beauty.Model.TaiKhoan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LichLamViecDAO {
    private SQLiteDatabase db;
    public LichLamViecDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public long insert(LichLamViec llv) {
        ContentValues values = new ContentValues();
//            values.put("maLLV");
        values.put("maTK", llv.getMaTK());
        values.put("ngayBatDau", sdf.format(llv.getNgayBatDau()));
        values.put("Ca", llv.getCa());
        values.put("ghiChu", llv.getGhiChu());

        return db.insert("LichLamViec", null, values);
    }

    public int update(LichLamViec llv) {
        ContentValues values = new ContentValues();
        values.put("maTK", llv.getMaTK());
        values.put("ngayBatDau", sdf.format(llv.getNgayBatDau()));
        values.put("Ca", llv.getCa());
        values.put("ghiChu", llv.getGhiChu());
        return db.update("LichLamViec", values, "maLLV = ?", new String[]{String.valueOf(llv.getMaLLV())});
    }

    public int delete(int ma_LLV) {
        return db.delete("LichLamViec", "maLLV = ?", new String[]{String.valueOf(ma_LLV)});
    }
    @SuppressLint("Range")
    private List<LichLamViec> getData(String sql, String... selectionArgs) {
        List<LichLamViec> list = new ArrayList<LichLamViec>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            LichLamViec obj = new LichLamViec();
            obj.setMaLLV(cursor.getInt(cursor.getColumnIndex("maLLV")));
            obj.setMaTK(cursor.getInt(cursor.getColumnIndex("maTK")));
            try {
                obj.setNgayBatDau(sdf.parse(cursor.getString(cursor.getColumnIndex("ngayBatDau"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.setCa(cursor.getInt(cursor.getColumnIndex("Ca")));
            obj.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            list.add(obj);
        }
        return list;
    }

    //GET ALL
    public List<LichLamViec> getAll(){
        String sql = "SELECT * FROM LichLamViec";
        return getData(sql);
    }
}
