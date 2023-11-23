package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.DichVu;

import java.util.ArrayList;
import java.util.List;

public class DichVuDAO {
    private SQLiteDatabase db;

    public DichVuDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(DichVu dv) {
            ContentValues values = new ContentValues();
//            values.put("maDV", );
            values.put("hinhAnh", dv.getHinhAnh());
            values.put("tenDV", dv.getTenDV());
            values.put("giaDV", dv.getGiaDV());
            values.put("loaiDV", dv.getLoaiDV());
            values.put("trangThai", dv.getTrangThai());
            values.put("ghiChu", dv.getGhiChu());
            return db.insert("DichVu", null, values);
    }

    public int update(DichVu dv) {
        ContentValues values = new ContentValues();
        values.put("hinhAnh", dv.getHinhAnh());
        values.put("tenDV", dv.getTenDV());
        values.put("giaDV", dv.getGiaDV());
        values.put("loaiDV", dv.getLoaiDV());
        values.put("trangThai", dv.getTrangThai());
        values.put("ghiChu", dv.getGhiChu());

        return db.update("DichVu", values, "maDV = ?", new String[]{String.valueOf(dv.getMaDV())});
    }

    public int delete(int ma_dv) {
        return db.delete("DichVu", "maDV = ?", new String[]{String.valueOf(ma_dv)});
    }
    @SuppressLint("Range")
    private List<DichVu> getData(String sql, String... selectionArgs) {
        List<DichVu> list = new ArrayList<DichVu>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DichVu obj = new DichVu();
            obj.setMaDV(cursor.getInt(cursor.getColumnIndex("maDV")));
            obj.setHinhAnh(cursor.getString(cursor.getColumnIndex("hinhAnh")));
            obj.setTenDV(cursor.getString(cursor.getColumnIndex("tenDV")));
            obj.setGiaDV(cursor.getString(cursor.getColumnIndex("giaDV")));
            obj.setLoaiDV(cursor.getString(cursor.getColumnIndex("loaiDV")));
            obj.setTrangThai(cursor.getString(cursor.getColumnIndex("trangThai")));
            obj.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            list.add(obj);
        }
        return list;
    }

    //GET ALL
    public List<DichVu> getAll(){
        String sql = "SELECT * FROM DichVu";
        return getData(sql);
    }
    //GET ID
    public DichVu getID(String ma_DV) {
        String sql = "SELECT * FROM DichVu WHERE maDV=?";
        List<DichVu> list = getData(sql, ma_DV);
        return list.get(0);
    }
}
