package com.example.mailisa_beauty.frg_quanLy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.DichVuADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QL_dichVu extends Fragment {
    public QL_dichVu() {
        // Required empty public constructor
    }
    RecyclerView rcvDichVu;
    FloatingActionButton fladdDV;
    DichVuDAO dichVuDAO;
    DichVuADAPTER dichVuADAPTER;
    private ArrayList<DichVu> list = new ArrayList<DichVu>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_dich_vu, container, false);
        rcvDichVu = view.findViewById(R.id.rcvDVQL);
        fladdDV = view.findViewById(R.id.fladdQLDV);

        dichVuDAO = new DichVuDAO(getActivity());
        list = (ArrayList<DichVu>) dichVuDAO.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvDichVu.setLayoutManager(layout);

        dichVuADAPTER = new DichVuADAPTER(getActivity(), list);
        rcvDichVu.setAdapter(dichVuADAPTER);
        return view;
    }
}