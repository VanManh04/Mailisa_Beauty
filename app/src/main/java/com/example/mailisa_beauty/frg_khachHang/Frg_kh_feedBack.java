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

import com.example.mailisa_beauty.ADAPTER.FeedBack_KH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.LichKhachHang_KH_ADAPTER;
import com.example.mailisa_beauty.DAO.FeedBackDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.Model.FeedBack;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class Frg_kh_feedBack extends Fragment {

    public Frg_kh_feedBack() {
        // Required empty public constructor
    }

    RecyclerView rcv_FRG_FBKH;
    FeedBackDAO feedBackDAO;
    FeedBack_KH_ADAPTER feedBack_kh_adapter;
    private ArrayList<FeedBack> listFeedBack = new ArrayList<FeedBack>();
    LichKhachHang_DAO lichKhachHangDao;
    LichKhachHang_KH_ADAPTER lichKhachHang_kh_adapter;
    private ArrayList<LichKhachHang> listLichKhachHang = new ArrayList<LichKhachHang>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_kh_feedback, container, false);
        rcv_FRG_FBKH = view.findViewById(R.id.rcv_FRG_FBKH);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String DATA_MATK = preferences.getString("DATA_MATK", "null");

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        lichKhachHang_kh_adapter = new LichKhachHang_KH_ADAPTER(getActivity(),listLichKhachHang);
        listLichKhachHang = (ArrayList<LichKhachHang>) lichKhachHangDao.getByMaTK(Integer.parseInt(DATA_MATK));

        // Lấy mã lịch khách hàng từ listLichKhachHang
        ArrayList<Integer> maLKHList = new ArrayList<>();
        for (LichKhachHang lichKhachHang : listLichKhachHang) {
            maLKHList.add(lichKhachHang.getMaLKH());
        }

        // Lấy danh sách FeedBack theo mã lịch khách hàng
        feedBackDAO = new FeedBackDAO(getActivity());
        listFeedBack = (ArrayList<FeedBack>) feedBackDAO.getAllByMaLKHList(maLKHList);

        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcv_FRG_FBKH.setLayoutManager(layout);
        feedBack_kh_adapter = new FeedBack_KH_ADAPTER(getActivity(), listFeedBack);
        rcv_FRG_FBKH.setAdapter(feedBack_kh_adapter);

        return view;
    }
}