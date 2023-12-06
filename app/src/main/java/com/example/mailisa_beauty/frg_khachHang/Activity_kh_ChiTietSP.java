package com.example.mailisa_beauty.frg_khachHang;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mailisa_beauty.ADAPTER.FeedBack_Xem_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.DAO.FeedBackDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.Model.FeedBack;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;


public class Activity_kh_ChiTietSP extends AppCompatActivity {
    DichVuDAO dichVuDAO;
    DichVuTrongGio_DAO dichVuTrongGio_dao;
    TextView soDanhgia;
    ImageButton btnxemDanhGia;
    RecyclerView rcvdanhgiatheotungsampham;
    private ArrayList<FeedBack> listFeedBack = new ArrayList<FeedBack>();
    private ArrayList<FeedBack> listFeedBackAll = new ArrayList<FeedBack>();
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
                listFeedBack.clear();
                listFeedBackAll.clear();
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
        giaSale.setText(String.valueOf("Giá sale: "+dichVu.getGiaSALE()));

        TextView giaLoaiTextView = findViewById(R.id.txtgiaspOld_ct);


        TextView moTa = findViewById(R.id.ghiChu_ct);
        moTa.setText(dichVu.getGhiChu());

        ImageView imgsanpham_chitiet = findViewById(R.id.imgsanpham_chitiet);
        imgsanpham_chitiet.setImageURI(dichVu.getHinhAnh());//Uri.parse(String.valueOf(dichVu.getHinhAnh()))

        if (dichVu.getGiaDV()<=dichVu.getGiaSALE()){
            giaSale.setVisibility(View.GONE);
            giaLoaiTextView.setText(String.valueOf("Giá: "+dichVu.getGiaDV()));
        }else {
            giaSale.setVisibility(View.VISIBLE);
            giaLoaiTextView.setText(String.valueOf("Giá gốc: "+dichVu.getGiaDV()));
        }
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
//                    Toast.makeText(Activity_kh_ChiTietSP.this, "Thêm vào giỏ dịch vụ thành công !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_kh_ChiTietSP.this, "Lỗi", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent1);
            }
        });





        soDanhgia = findViewById(R.id.soDanhgia);
        btnxemDanhGia = findViewById(R.id.btnxemDanhGia);

        FeedBackDAO feedBackDAO = new FeedBackDAO(this);
        listFeedBackAll = (ArrayList<FeedBack>) feedBackDAO.getAll();
        LichKhachHang_DAO lichKhachHangDao = new LichKhachHang_DAO(this);

        for (FeedBack feedBack: listFeedBackAll){
            int maLKH = feedBack.getMaLKH();
            LichKhachHang lichKhachHang = lichKhachHangDao.getByMaLKH(maLKH);
            DichVu dichVu1 = dichVuDAO.getID(String.valueOf(lichKhachHang.getMaDV()));
            if (dichVu1.getTenDV().equals(dichVu.getTenDV())){
                listFeedBack.add(feedBack);
            }
        }
        soDanhgia.setText(String.valueOf(listFeedBack.size()));
        btnxemDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soDanhgia.getText().equals("0")){
                    Toast.makeText(Activity_kh_ChiTietSP.this, "Không có đánh giá !", Toast.LENGTH_SHORT).show();
                }else {
                    showDanhGiaDialog(listFeedBack);
                }
            }
        });
    }
    private void showDanhGiaDialog(List<FeedBack> danhGiaList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.rcv_danhgia_sp, null); // Thay thế layout_danh_gia_rcv bằng layout của bạn
        builder.setView(view);
        rcvdanhgiatheotungsampham = view.findViewById(R.id.rcvdanhgiatheotungsampham );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvdanhgiatheotungsampham.setLayoutManager(layoutManager);
        FeedBack_Xem_ADAPTER danhGiaAdapter = new FeedBack_Xem_ADAPTER(this, (ArrayList<FeedBack>) danhGiaList);
        rcvdanhgiatheotungsampham.setAdapter(danhGiaAdapter);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}