package com.example.mailisa_beauty.frg_khachHang;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mailisa_beauty.R;
public class Frg_kh_chiTietDichVu extends Fragment {


    public Frg_kh_chiTietDichVu() {
        // Required empty public constructor
    }
    TextView tvTenDV_itCTDV, tvgiaCTDV_itCTDV,tvloaiDV_itCTDV,tvtrangThai_itCTDV,tvghiChu_itCTDV;
    ImageView img_itCTDV;
    Button btndatlich_itCTDV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_kh_chitietdichvu, container, false);

            tvTenDV_itCTDV = view.findViewById(R.id.tvTenDV_itCTDV);
            tvgiaCTDV_itCTDV = view.findViewById(R.id.tvgiaDV_itCTDV);
            tvloaiDV_itCTDV = view.findViewById(R.id.tvloaiDV_itCTDV);
            tvtrangThai_itCTDV = view.findViewById(R.id.tvtrangThai_itCTDV);
            tvghiChu_itCTDV = view.findViewById(R.id.tvghiChu_itCTDV);
            btndatlich_itCTDV = view.findViewById(R.id.btndatlich_itCTDV);
            img_itCTDV =view.findViewById(R.id.img_itCTDV);


        return view;
    }
}