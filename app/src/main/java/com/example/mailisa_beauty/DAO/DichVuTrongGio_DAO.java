//package com.example.mailisa_beauty.DAO;
//
//public class DichVuTrongGio_DAO {
//}
package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.Model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class DichVuTrongGio_DAO {
    private SQLiteDatabase db;

    public DichVuTrongGio_DAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(DichVuTrongGio dvtg) {
        ContentValues values = new ContentValues();
        values.put("maTK", dvtg.getMaTK());
        values.put("maDV", dvtg.getMaDV());
        values.put("soLuong", dvtg.getSoLuong());
        values.put("isCheck", dvtg.isCheck());
        return db.insert("DichVuTrongGio", null, values);
    }

    public int update(DichVuTrongGio dvtg) {
        ContentValues values = new ContentValues();
        values.put("soLuong", dvtg.getSoLuong());
        values.put("isCheck", dvtg.isCheck());
        return db.update("DichVuTrongGio", values, "maDVTG= ?", new String[]{String.valueOf(dvtg.getMaDVTG())});
    }

    public int delete(int ma_dvtg) {
        return db.delete("DichVuTrongGio", "maDVTG = ?", new String[]{String.valueOf(ma_dvtg)});
    }



    @SuppressLint("Range")
    private List<DichVuTrongGio> getData(String sql, String... selectionArgs) {
        List<DichVuTrongGio> list = new ArrayList<DichVuTrongGio>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            DichVuTrongGio obj = new DichVuTrongGio();
            obj.setMaDVTG(cursor.getInt(cursor.getColumnIndex("maDVTG")));
            obj.setMaTK(cursor.getInt(cursor.getColumnIndex("maTK")));
            obj.setMaDV(cursor.getInt(cursor.getColumnIndex("maDV")));
            obj.setSoLuong(cursor.getInt(cursor.getColumnIndex("soLuong")));
            int check = cursor.getInt(cursor.getColumnIndex("isCheck"));
            if (check==1){
                obj.setCheck(true);
            }else {
                obj.setCheck(false);
            }

            list.add(obj);
        }

        return list;
    }

    //GET ALL
    public List<DichVuTrongGio> getAll() {
        String sql = "SELECT * FROM DichVuTrongGio";
        return getData(sql);
    }
}