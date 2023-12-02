package com.example.mailisa_beauty.frg_quanLy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mailisa_beauty.ADAPTER.HoaDon_QL_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.DAO.HoaDonDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.HoaDon;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class QL_HoaDon extends Fragment {

    public QL_HoaDon() {
        // Required empty public constructor
    }
        RecyclerView rcvQLHD;
    TaiKhoanDAO taiKhoanDAO;
    HoaDonDAO hoaDonDAO;
    HoaDon_QL_ADAPTER hoaDon_ql_adapter;
    private ArrayList<HoaDon> list = new ArrayList<HoaDon>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ql_hoadon, container, false);
        rcvQLHD = view.findViewById(R.id.rcvQLHD);
        hoaDonDAO = new HoaDonDAO(getActivity());
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvQLHD.setLayoutManager(layoutManager);
        hoaDon_ql_adapter = new HoaDon_QL_ADAPTER(getActivity(),list);
        rcvQLHD.setAdapter(hoaDon_ql_adapter);


        return view;
    }
}