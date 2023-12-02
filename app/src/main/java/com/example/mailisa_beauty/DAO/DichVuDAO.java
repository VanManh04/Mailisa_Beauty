package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.TaiKhoan;

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
        values.put("hinhAnh", dv.getHinhAnh().toString());
        values.put("tenDV", dv.getTenDV());
        values.put("loaiDV", dv.getLoaiDV());
        values.put("trangThai", dv.getTrangThai());
        values.put("giaDV", dv.getGiaDV());
        values.put("giaSALE", dv.getGiaSALE());
        values.put("ghiChu", dv.getGhiChu());
        return db.insert("DichVu", null, values);
    }

    public int update(DichVu dv) {
        ContentValues values = new ContentValues();
//        values.put("hinhAnh", dv.getHinhAnh());
        values.put("tenDV", dv.getTenDV());
        values.put("loaiDV", dv.getLoaiDV());
        values.put("trangThai", dv.getTrangThai());
        values.put("giaDV", dv.getGiaDV());
        values.put("giaSALE", dv.getGiaSALE());
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
            //            obj.setHinhAnh(cursor.getString(cursor.getColumnIndex("hinhAnh")));

            String uriString = cursor.getString(cursor.getColumnIndex("hinhAnh"));
            if (uriString != null) {
                Uri uri = Uri.parse(uriString);
                obj.setHinhAnh(uri);
            }

            obj.setTenDV(cursor.getString(cursor.getColumnIndex("tenDV")));
            obj.setLoaiDV(cursor.getString(cursor.getColumnIndex("loaiDV")));
            obj.setTrangThai(cursor.getString(cursor.getColumnIndex("trangThai")));
            obj.setGiaDV(cursor.getInt(cursor.getColumnIndex("giaDV")));
            obj.setGiaSALE(cursor.getInt(cursor.getColumnIndex("giaSALE")));
            obj.setGhiChu(cursor.getString(cursor.getColumnIndex("ghiChu")));
            list.add(obj);
        }

        return list;
    }

    //GET ALL
    public List<DichVu> getAll() {
        String sql = "SELECT * FROM DichVu";
        return getData(sql);
    }

    //GET ID
    public DichVu getID(String ma_DV) {
        String sql = "SELECT * FROM DichVu WHERE maDV=?";
        List<DichVu> list = getData(sql, ma_DV);
        return list.get(0);
    }
    public List<DichVu> getAllSALE() {
        String sql = "SELECT * FROM DichVu WHERE trangThai='SALE' ";
        return getData(sql);
    }
    public List<DichVu> getAllNEW() {
        String sql = "SELECT * FROM DichVu WHERE trangThai='NEW' ";
        return getData(sql);
    }
    public List<DichVu> getAllById(int ma_DV) {
        String sql = "SELECT * FROM DichVu WHERE maDV=?";
        return getData(sql, String.valueOf(ma_DV));
    }
}
