package com.example.mailisa_beauty.frg_nhanVien;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mailisa_beauty.ADAPTER.LichKhachHang_NV_ADAPTER;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;


public class NV_lichKhachHang extends Fragment {

    RecyclerView rcvLKH_QLLKH;
    LichKhachHang_DAO lichKhachHangDao;
    LichKhachHang_NV_ADAPTER lichKhachHang_ql_adapter;
    private ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();
    public NV_lichKhachHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_nv_lichkhachhang, container, false);
        rcvLKH_QLLKH = view.findViewById(R.id.lkhnvnvnv);

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvLKH_QLLKH.setLayoutManager(layout);
        lichKhachHang_ql_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
        rcvLKH_QLLKH.setAdapter(lichKhachHang_ql_adapter);
        return view;
    }
}