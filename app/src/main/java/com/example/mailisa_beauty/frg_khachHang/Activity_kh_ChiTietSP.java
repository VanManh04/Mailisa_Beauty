package com.example.mailisa_beauty.frg_khachHang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;


public class Activity_kh_ChiTietSP extends AppCompatActivity {
    DichVuDAO dichVuDAO;
    DichVuTrongGio_DAO dichVuTrongGio_dao;
    private ArrayList<DichVuTrongGio> list = new ArrayList<DichVuTrongGio>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_chitietsp);

        ImageButton imgbtTrove = findViewById(R.id.icon_back);
        imgbtTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        dichVuDAO  = new DichVuDAO(this);
        dichVuTrongGio_dao = new DichVuTrongGio_DAO(this);

        Intent intent = getIntent();
        String maDV = intent.getStringExtra("maDV");
//        Toast.makeText(this, maDV, Toast.LENGTH_SHORT).show();
        DichVu dichVu  = dichVuDAO.getID(maDV);
        // Hiển thị dữ liệu trong Activity
        TextView tenTextView = findViewById(R.id.txttensp_ct);
        tenTextView.setText(dichVu.getTenDV());

//        txtgiaspOld_ct
        TextView giaSale = findViewById(R.id.txtgiasp_ct);
        giaSale.setText(String.valueOf(dichVu.getGiaSALE()));

        TextView giaLoaiTextView = findViewById(R.id.txtgiaspOld_ct);
        giaLoaiTextView.setText(String.valueOf(dichVu.getGiaDV()));

        TextView moTa = findViewById(R.id.ghiChu_ct);
        moTa.setText(dichVu.getGhiChu());

        ImageView imgsanpham_chitiet = findViewById(R.id.imgsanpham_chitiet);
        imgsanpham_chitiet.setImageURI(dichVu.getHinhAnh());//Uri.parse(String.valueOf(dichVu.getHinhAnh()))


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String DATA_MATK = preferences.getString("DATA_MATK", "null");


        //Thêm vào list giỏ hàng
        list = (ArrayList<DichVuTrongGio>) dichVuTrongGio_dao.getAll();
        DichVuTrongGio dichVuTrongGio = new DichVuTrongGio();
        ImageView imgbt_giohang_ct = findViewById(R.id.imgbt_giohang_ct);
        imgbt_giohang_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dichVuTrongGio.setMaTK(Integer.parseInt(DATA_MATK));
                dichVuTrongGio.setMaDV(Integer.parseInt(maDV));
                dichVuTrongGio.setSoLuong(1);
                dichVuTrongGio.setCheck(false);
                if (dichVuTrongGio_dao.insert(dichVuTrongGio)>0){
                    list.clear();
                    list.addAll(dichVuTrongGio_dao.getAll());
                    Toast.makeText(Activity_kh_ChiTietSP.this, "Thêm vào giỏ dịch vụ thành công !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_kh_ChiTietSP.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });


        TextView txtmuahang_ct = findViewById(R.id.txtmuahang_ct);
        txtmuahang_ct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Activity_kh_ChiTietSP.this, Activity_kh_datLich.class);
                intent1.putExtra("maDV_muaNgay", maDV);
                int dich = dichVuTrongGio_dao.setAllTrangThai(false);
                dichVuTrongGio.setMaTK(Integer.parseInt(DATA_MATK));
                dichVuTrongGio.setMaDV(Integer.parseInt(maDV));
                dichVuTrongGio.setSoLuong(1);
                dichVuTrongGio.setCheck(true);
                if (dichVuTrongGio_dao.insert(dichVuTrongGio)>0){
                    list.clear();
                    list.addAll(dichVuTrongGio_dao.getAll());
                    Toast.makeText(Activity_kh_ChiTietSP.this, "Thêm vào giỏ dịch vụ thành công !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_kh_ChiTietSP.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent1);
            }
        });
    }
}