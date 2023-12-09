package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.FeedBack;

import java.util.ArrayList;
import java.util.List;

public class FeedBackDAO {
    private SQLiteDatabase db;

    public FeedBackDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(FeedBack feedBack) {
        ContentValues values = new ContentValues();
        values.put("maLKH", feedBack.getMaLKH());
        values.put("soSao", feedBack.getSoSao());
        values.put("ghiChuKH", feedBack.getGhiChuKH());
        values.put("ghiChuQL", feedBack.getGhiChuQL());
        return db.insert("FeedBack", null, values);
    }

    public int update(FeedBack feedBack) {
        ContentValues values = new ContentValues();
        values.put("maLKH", feedBack.getMaLKH());
        values.put("soSao", feedBack.getSoSao());
        values.put("ghiChuKH", feedBack.getGhiChuKH());
        values.put("ghiChuQL", feedBack.getGhiChuQL());
        return db.update("FeedBack", values, "maFB = ?", new String[]{String.valueOf(feedBack.getMaFB())});
    }

    public int delete(int maFB) {
        return db.delete("FeedBack", "maFB = ?", new String[]{String.valueOf(maFB)});
    }

    @SuppressLint("Range")
    private List<FeedBack> getData(String sql, String... selectionArgs) {
        List<FeedBack> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            FeedBack obj = new FeedBack();
            obj.setMaFB(cursor.getInt(cursor.getColumnIndex("maFB")));
            obj.setMaLKH(cursor.getInt(cursor.getColumnIndex("maLKH")));
            obj.setSoSao(cursor.getFloat(cursor.getColumnIndex("soSao")));
            obj.setGhiChuKH(cursor.getString(cursor.getColumnIndex("ghiChuKH")));
            obj.setGhiChuQL(cursor.getString(cursor.getColumnIndex("ghiChuQL")));
            list.add(obj);
        }
        cursor.close();
        return list;
    }

    //GET ALL
    public List<FeedBack> getAll() {
        String sql = "SELECT * FROM FeedBack";
        return getData(sql);
    }

    public List<FeedBack> getAllByMaLKHList(List<Integer> maLKHList) {
        String sql = "SELECT * FROM FeedBack WHERE maLKH IN (" + TextUtils.join(",", maLKHList) + ")";
        return getData(sql);
    }
    public List<FeedBack> getAllByMaLKH(int maLKH) {
        String sql = "SELECT * FROM FeedBack WHERE maLKH = ?";
        return getData(sql, String.valueOf(maLKH));
    }
    public int deleteAllByMaLKHFB(int maLKH) {
        return db.delete("FeedBack", "maLKH = ?", new String[]{String.valueOf(maLKH)});
    }


}
