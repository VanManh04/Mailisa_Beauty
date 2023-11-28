package com.example.mailisa_beauty.frg_khachHang;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    CheckBox checkbox_frg_kh_giohang;
    Button btnDatLich_frg_kh_giohang;
    DichVuTrongGio_DAO dichVuTrongGio_dao;
    DichVuKHTrongGio_ADAPTER dichVuKHTrongGioAdapter;
    private ArrayList<DichVuTrongGio> list = new ArrayList<DichVuTrongGio>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_kh_giodichvu, container, false);

        String dataMaTK = "null";
        Bundle bundle = getArguments();
        if (bundle != null) {
            dataMaTK = bundle.getString("key_idTK");
            // Now you have the string data, you can use it as needed
        }
//        Toast.makeText(getActivity(), dataMaTK, Toast.LENGTH_SHORT).show();

        rcvFRGKHGDV = view.findViewById(R.id.rcvFRGKHGDV);
        checkbox_frg_kh_giohang = view.findViewById(R.id.checkbox_frg_kh_giohang);
        btnDatLich_frg_kh_giohang = view.findViewById(R.id.btnDatLich_frg_kh_giohang);

        dichVuTrongGio_dao = new DichVuTrongGio_DAO(getActivity());
        int setLan1 = dichVuTrongGio_dao.setAllTrangThai(false);
        list = (ArrayList<DichVuTrongGio>) dichVuTrongGio_dao.getAllByMaTK(dataMaTK);
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvFRGKHGDV.setLayoutManager(layout);
        dichVuKHTrongGioAdapter = new DichVuKHTrongGio_ADAPTER(getActivity(), list, dataMaTK);
        rcvFRGKHGDV.setAdapter(dichVuKHTrongGioAdapter);

        String finalDataMaTK = dataMaTK;
        checkbox_frg_kh_giohang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dichVuTrongGio_dao = new DichVuTrongGio_DAO(getActivity());
                int setCheck = dichVuTrongGio_dao.setAllTrangThai(isChecked);
                // Kiểm tra xem có bao nhiêu dòng đã được cập nhật
                if (setCheck > 0) {
                    // Cập nhật thành công
                    list.clear();
                    list = (ArrayList<DichVuTrongGio>) dichVuTrongGio_dao.getAllByMaTK(finalDataMaTK);
                    LinearLayoutManager layout = new LinearLayoutManager(getActivity());
                    rcvFRGKHGDV.setLayoutManager(layout);
                    dichVuKHTrongGioAdapter = new DichVuKHTrongGio_ADAPTER(getActivity(), list, finalDataMaTK);
                    rcvFRGKHGDV.setAdapter(dichVuKHTrongGioAdapter);
                    dichVuKHTrongGioAdapter.notifyDataSetChanged();
                } else {
                    // Cập nhật không thành công hoặc không có dữ liệu để cập nhật
                    Toast.makeText(getActivity(), "Lỗi !", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnDatLich_frg_kh_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_kh_datLich.class);
                startActivity(intent);
            }
        });
        return view;
    }
}