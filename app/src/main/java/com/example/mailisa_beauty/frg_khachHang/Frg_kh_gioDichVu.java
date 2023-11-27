package com.example.mailisa_beauty.frg_khachHang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mailisa_beauty.ADAPTER.DichVuKHTrongGio_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.DichVuKH_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class Frg_kh_gioDichVu extends Fragment {

    public Frg_kh_gioDichVu() {
        // Required empty public constructor
    }
    RecyclerView rcvFRGKHGDV;
    DichVuTrongGio_DAO dichVuTrongGio_dao;
    DichVuKHTrongGio_ADAPTER dichVuKHTrongGioAdapter;
    private ArrayList<DichVuTrongGio> list = new ArrayList<DichVuTrongGio>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_kh_giodichvu, container, false);


        rcvFRGKHGDV = view.findViewById(R.id.rcvFRGKHGDV);
        dichVuTrongGio_dao = new DichVuTrongGio_DAO(getActivity());
        list = (ArrayList<DichVuTrongGio>) dichVuTrongGio_dao.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvFRGKHGDV.setLayoutManager(layout);

        dichVuKHTrongGioAdapter = new DichVuKHTrongGio_ADAPTER(getActivity(), list);
        rcvFRGKHGDV.setAdapter(dichVuKHTrongGioAdapter);
        return view;
    }
}