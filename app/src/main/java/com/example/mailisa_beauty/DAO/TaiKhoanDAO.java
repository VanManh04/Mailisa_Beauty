package com.example.mailisa_beauty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mailisa_beauty.DataBase.DbHelper;
import com.example.mailisa_beauty.Model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
    private SQLiteDatabase db;

    public TaiKhoanDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    private boolean isPhoneNumberExists(String sdt) {
        String query = "SELECT COUNT(*) FROM TaiKhoan WHERE sdt=?";
        Cursor cursor = db.rawQuery(query, new String[]{sdt});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
    public long insert(TaiKhoan tk) {
        if (isPhoneNumberExists(tk.getSdt())) {
            return -1; // CHECK SDT ĐÃ TỒN TẠI
        }else {
            ContentValues values = new ContentValues();
//            values.put("maTK", tk.getMa_TK());
            values.put("sdt", tk.getSdt());
            values.put("hoTen", tk.getHoTen());
            values.put("matKhau", tk.getMatKhau());
            values.put("chucVu", tk.getChucVu());

            return db.insert("TaiKhoan", null, values);
        }
    }

    public int update(TaiKhoan tk) {
        ContentValues values = new ContentValues();
//        values.put("maTK", tk.getMa_TK());
        values.put("sdt", tk.getSdt());
        values.put("hoTen", tk.getHoTen());
        values.put("matKhau", tk.getMatKhau());
        values.put("chucVu", tk.getChucVu());

        return db.update("TaiKhoan", values, "maTK = ?", new String[]{String.valueOf(tk.getMa_TK())});
    }
    public int updatePass(TaiKhoan tk) {
        ContentValues values = new ContentValues();
        values.put("sdt", tk.getSdt());
        values.put("hoTen", tk.getHoTen());
        values.put("matKhau", tk.getMatKhau());
        values.put("chucVu", tk.getChucVu());

        return db.update("TaiKhoan", values, "maTK = ?", new String[]{String.valueOf(tk.getMa_TK())});
    }

    public int delete(int ma_tk) {
        return db.delete("TaiKhoan", "maTK = ?", new String[]{String.valueOf(ma_tk)});
    }

    public List<TaiKhoan> getAllNV() {
        String sql = "SELECT * FROM TaiKhoan WHERE chucVu = 'NV'";
        return getData(sql);
    }
    @SuppressLint("Range")
    private List<TaiKhoan> getData(String sql, String... selectionArgs) {
        List<TaiKhoan> list = new ArrayList<TaiKhoan>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            TaiKhoan obj = new TaiKhoan();
            obj.setMa_TK(cursor.getInt(cursor.getColumnIndex("maTK")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            obj.setChucVu(cursor.getString(cursor.getColumnIndex("chucVu")));
            list.add(obj);
        }
        return list;
    }

    //GET ALL
    public List<TaiKhoan> getAll(){
        String sql = "SELECT * FROM TaiKhoan";
        return getData(sql);
    }
    //GET ID
    public TaiKhoan getID(String ma_TK) {
        String sql = "SELECT * FROM TaiKhoan WHERE maTK=?";
        List<TaiKhoan> list = getData(sql, ma_TK);
        return list.get(0);
    }
    public TaiKhoan getSDT(String SDT) {
        String sql = "SELECT * FROM TaiKhoan WHERE sdt=?";
        List<TaiKhoan> list = getData(sql, SDT);
        return list.get(0);
    }

    //Check LOGIN
    public int checkLogin(String strSdt, String strPass) {
        String sqlcheckSDT = "SELECT * FROM TaiKhoan WHERE sdt=?";
        Cursor checkSDT = db.rawQuery(sqlcheckSDT,new String[]{strSdt});

        String sql = "SELECT * FROM TaiKhoan WHERE sdt=? AND matKhau=?";
        Cursor cursor = db.rawQuery(sql, new String[]{strSdt, strPass});

        if (checkSDT.getCount() > 0){
            if (cursor.getCount() > 0) {
                // Đăng nhập thành công, có một bản ghi với tên thủ thư và mật khẩu tương ứng
                cursor.close();
                return 1;
            } else {
                //sai mật khẩu
                cursor.close();
                return -1;
            }
        }else {
            // Đăng nhập không thành công, không có bản ghi nào khớp
            return 0;
        }
    }


}
