package com.example.mailisa_beauty.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DbName = "QLDT";

    public DbHelper(@Nullable Context context) {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //BẢNG TÀI KHOẢN
        String CreateTableTaiKhoan = "CREATE TABLE TaiKhoan(" +
                "maTK INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sdt TEXT NOT NULL," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL," +
                "chucVu TEXT NOT NULL)";
        db.execSQL(CreateTableTaiKhoan);
        String data_TK = "INSERT INTO TaiKhoan VALUES" +
                "(1,'01234567890','Vũ Đức Anh','123','KH')," +
                "(2,'01122334455','Phạm Thị Hà','1234','NV')," +
                "(3,'09988776655','Đỗ Thị Kim Anh','1235','QL')";
        db.execSQL(data_TK);
        //BẢNG DỊCH VỤ
        String CreateTableDichVu = "CREATE TABLE DichVu(" +
                "maDV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hinhAnh TEXT NOT NULL," +
                "tenDV TEXT NOT NULL," +
                "giaDV INTEGER NOT NULL," +
                "loaiDV TEXT NOT NULL," +
                "trangThai TEXT NOT NULL," +
                "ghiChu TEXT)";
        db.execSQL(CreateTableDichVu);
        String data_DV = "INSERT INTO DichVu VALUES" +
                "(1,'dv1.png','Triệt lông','200000','PT','SALE','11')," +
                "(2,'dv2.png','Tiêm cằm','300000','PT','NEW','22')," +
                "(3,'dv3.png','Săm môi','400000','PS','KHONG','33')";
        db.execSQL(data_DV);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
            db.execSQL("DROP TABLE IF EXISTS DichVu");
            onCreate(db);
        }
    }
}
