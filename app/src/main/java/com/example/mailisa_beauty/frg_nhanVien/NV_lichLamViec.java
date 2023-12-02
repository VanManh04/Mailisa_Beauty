package com.example.mailisa_beauty.frg_nhanVien;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mailisa_beauty.ADAPTER.LichLamViecADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanNV_ADAPTER_SPINER;
import com.example.mailisa_beauty.DAO.LichLamViecDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichLamViec;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NV_lichLamViec extends Fragment {
    public NV_lichLamViec() {
        // Required empty public constructor
    }
    RecyclerView rcvQLLLVQL;
    Button fladdQLLLVQL;
    private ArrayList<LichLamViec> list = new ArrayList<LichLamViec>();
    LichLamViecDAO lichLamViecDAO;
    LichLamViecADAPTER lichLamViecADAPTER;
    TaiKhoanNV_ADAPTER_SPINER taiKhoanNVAdapterSpiner;
    private ArrayList<TaiKhoan> listTaiKhoan = new ArrayList<TaiKhoan>();
    TaiKhoanDAO taiKhoanDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_nv_lichlamviec, container, false);
        rcvQLLLVQL = view.findViewById(R.id.rcvllvnvnv);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String DATA_MATK = preferences.getString("DATA_MATK", "null");

        lichLamViecDAO = new LichLamViecDAO(getActivity());
        list = (ArrayList<LichLamViec>) lichLamViecDAO.getByMaTK(Integer.parseInt(DATA_MATK));
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvQLLLVQL.setLayoutManager(layout);

        lichLamViecADAPTER = new LichLamViecADAPTER(getActivity(), list);
        rcvQLLLVQL.setAdapter(lichLamViecADAPTER);

        taiKhoanDAO = new TaiKhoanDAO(getActivity());
        listTaiKhoan = (ArrayList<TaiKhoan>) taiKhoanDAO.getAllNV();
        taiKhoanNVAdapterSpiner = new TaiKhoanNV_ADAPTER_SPINER(getActivity(), listTaiKhoan);

        return view;
    }
}