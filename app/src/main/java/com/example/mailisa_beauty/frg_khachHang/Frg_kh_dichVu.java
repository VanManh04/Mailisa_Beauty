package com.example.mailisa_beauty.frg_khachHang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mailisa_beauty.ADAPTER.DichVuKH_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class Frg_kh_dichVu extends Fragment {

    public Frg_kh_dichVu() {
        // Required empty public constructor
    }
    RecyclerView rcvDichVu;
    DichVuDAO dichVuDAO;
    DichVuKH_ADAPTER dichvuKH_ADAPTER;
    private ArrayList<DichVu> list = new ArrayList<DichVu>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_kh_dichvu, container, false);
        rcvDichVu = view.findViewById(R.id.rcvDVKH);
        dichVuDAO = new DichVuDAO(getActivity());
        list = (ArrayList<DichVu>) dichVuDAO.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvDichVu.setLayoutManager(layout);

        dichvuKH_ADAPTER = new DichVuKH_ADAPTER(getActivity(), list);
        rcvDichVu.setAdapter(dichvuKH_ADAPTER);
        return view;
    }
}