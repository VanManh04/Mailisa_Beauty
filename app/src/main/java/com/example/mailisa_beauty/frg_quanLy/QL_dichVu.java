package com.example.mailisa_beauty.frg_quanLy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
    private static final int REQUEST_IMAGE_PICK = 1;
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
        fladdDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogADD();
            }
        });
        return view;
    }

    public void opendialogADD() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dichvu, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        ImageView imgDV_DLDV = view.findViewById(R.id.imgDV_DLDV);
        EditText edtenDV_DLDV = view.findViewById(R.id.edtenDV_DLDV);
        EditText edgiaDV_DLDV = view.findViewById(R.id.edgiaDV_DLDV);
        EditText edloai_DLDV = view.findViewById(R.id.edloai_DLDV);
        EditText edtrangThai_DLDV = view.findViewById(R.id.edtrangThai_DLDV);
        EditText edghiChu_DLDV = view.findViewById(R.id.edghiChu_DLDV);

        Button btnaddimg_DLDV = view.findViewById(R.id.btnaddimg_DLDV);
        Button btnSave_DLNV = view.findViewById(R.id.btnSave_DLNV);
        Button btnCancel_DLNV = view.findViewById(R.id.btnCancel_DLNV);
        btnSave_DLNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDV = edtenDV_DLDV.getText().toString();
                String giaDV = edgiaDV_DLDV.getText().toString();
                String loaiDV = edloai_DLDV.getText().toString();
                String trangThai = edtrangThai_DLDV.getText().toString();
                String ghiChu = edghiChu_DLDV.getText().toString();
                    if (tenDV.trim().isEmpty() || giaDV.trim().isEmpty() || loaiDV.trim().isEmpty()|| trangThai.trim().isEmpty()|| ghiChu.trim().isEmpty()) {
                        Toast.makeText(getActivity(), "Không đuợc bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                    }else {
                        DichVu dichVu = new DichVu();
                        dichVu.setTenDV(tenDV);
                        dichVu.setGiaDV(giaDV);
                        dichVu.setLoaiDV(loaiDV);
                        dichVu.setTrangThai(trangThai);
                        dichVu.setGhiChu(ghiChu);


                        if (dichVuDAO.insert(dichVu) > 0) {
                            list.clear();
                            list.addAll(dichVuDAO.getAll());
                            dichVuADAPTER.notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Thêm thất bại.", Toast.LENGTH_SHORT).show();
                        }

                    }

            }
        });

        btnCancel_DLNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtenDV_DLDV.setText("");
                edgiaDV_DLDV.setText("");
                edghiChu_DLDV.setText("");
            }
        });
    }
}