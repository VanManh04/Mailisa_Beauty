package com.example.mailisa_beauty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.frg_quanLy.QL_trangChu;

import java.util.ArrayList;


public class giaoDienChiTietSP extends AppCompatActivity {
    ArrayList<DichVu> list;




DichVuDAO dao;

ArrayList<DichVu> listgiohang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_chi_tiet_sp);
//        adapter= new SanPhamGH_Adapter(this,listgiohang,dao1);
        dao = new DichVuDAO(this);

        ImageButton imgbtTrove = findViewById(R.id.icon_back);
        imgbtTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent = getIntent();
        String masp = intent.getStringExtra("masp");
        String ten = intent.getStringExtra("ten");
        String gia = intent.getStringExtra("gia");
        String giaSL = intent.getStringExtra("giaSL");
        String loai = intent.getStringExtra("loai");
        String mota = intent.getStringExtra("mota");

        // Hiển thị dữ liệu trong Activity
        TextView tenTextView = findViewById(R.id.txttensp_ct);
        tenTextView.setText(ten);

//        txtgiaspOld_ct
        TextView giaSale = findViewById(R.id.txtgiasp_ct);
        giaSale.setText(giaSL);

        TextView giaLoaiTextView = findViewById(R.id.txtgiaspOld_ct);
        giaLoaiTextView.setText(gia);

        TextView moTa = findViewById(R.id.ghiChu_ct);
        moTa.setText(mota);

        ImageView anh = findViewById(R.id.imgsanpham_chitiet);


//        imgbt_giohang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listgiohang = dao1.getds();
////
//        boolean check =dao1.themVaoGioHang(ten,Integer.parseInt(gia), loai);
//
//        if(check){
//            Toast.makeText(GiaoDienChiTietSP.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//
//            adapter.notifyDataSetChanged();
//        }else {
//            Toast.makeText(GiaoDienChiTietSP.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//
//        }
//            }
//        });






    }
}