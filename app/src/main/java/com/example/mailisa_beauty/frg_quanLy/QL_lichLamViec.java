package com.example.mailisa_beauty.frg_quanLy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.LichLamViecADAPTER;
import com.example.mailisa_beauty.DAO.LichLamViecDAO;
import com.example.mailisa_beauty.Model.LichLamViec;
import com.example.mailisa_beauty.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QL_lichLamViec extends Fragment {
    public QL_lichLamViec() {
        // Required empty public constructor
    }
    RecyclerView rcvQLLLVQL;
    Button fladdQLLLVQL;
    private ArrayList<LichLamViec> list = new ArrayList<LichLamViec>();
    LichLamViecDAO lichLamViecDAO;
    LichLamViecADAPTER lichLamViecADAPTER;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ql_lichlamviec, container, false);
        rcvQLLLVQL = view.findViewById(R.id.rcvQLLLVQL);
        fladdQLLLVQL = view.findViewById(R.id.fladdQLLLVQL);

        lichLamViecDAO = new LichLamViecDAO(getActivity());
        list = (ArrayList<LichLamViec>) lichLamViecDAO.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvQLLLVQL.setLayoutManager(layout);

        lichLamViecADAPTER = new LichLamViecADAPTER(getActivity(), list);
        rcvQLLLVQL.setAdapter(lichLamViecADAPTER);
        fladdQLLLVQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                opendialogthem();
            }
        });
        return  view;

    }
}