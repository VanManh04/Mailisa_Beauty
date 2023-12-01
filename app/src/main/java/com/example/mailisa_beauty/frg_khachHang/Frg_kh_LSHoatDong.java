package com.example.mailisa_beauty.frg_khachHang;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mailisa_beauty.ADAPTER.LichKhachHang_KH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.LichKhachHang_QL_ADAPTER;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class Frg_kh_LSHoatDong extends Fragment {
    public Frg_kh_LSHoatDong() {
        // Required empty public constructor
    }
    RecyclerView rcvLKH_FRGKH;
    LichKhachHang_DAO lichKhachHangDao;
    LichKhachHang_KH_ADAPTER lichKhachHang_kh_adapter;
    private ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.frg_kh_lshoatdong, container, false);
        rcvLKH_FRGKH = view.findViewById(R.id.rcvLKH_FRGKH);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String DATA_MATK = preferences.getString("DATA_MATK", "null");

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getByMaTK(Integer.parseInt(DATA_MATK));
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvLKH_FRGKH.setLayoutManager(layout);
        lichKhachHang_kh_adapter = new LichKhachHang_KH_ADAPTER(getActivity(), list);
        rcvLKH_FRGKH.setAdapter(lichKhachHang_kh_adapter);

        return view;
    }
}