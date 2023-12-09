package com.example.mailisa_beauty.frg_nhanVien;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mailisa_beauty.ADAPTER.TaiKhoanKH_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.DAO.FeedBackDAO;
import com.example.mailisa_beauty.DAO.HoaDonDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.HoaDon;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NV_QLKhachHang extends Fragment {


    public NV_QLKhachHang() {
        // Required empty public constructor
    }

    RecyclerView rcvTaikhoanKH;
    Button fltaddKH;
    TaiKhoanDAO taiKhoanDAO;
    TaiKhoanKH_ADAPTER taiKhoanADAPTER;
    private ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
    private ArrayList<HoaDon> listhoadon = new ArrayList<HoaDon>();
    private ArrayList<LichKhachHang> listLKH = new ArrayList<LichKhachHang>();
    private SearchView searchView;

    LichKhachHang_DAO lichKhachHangDao;
    HoaDonDAO hoaDonDAO;
    FeedBackDAO feedBackDAO;
    DichVuTrongGio_DAO dichVuTrongGio_dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nv_qlkhachhang, container, false);
        rcvTaikhoanKH = view.findViewById(R.id.rcvTKKHNV);
        fltaddKH = view.findViewById(R.id.btnAddKhNV);

        taiKhoanDAO = new TaiKhoanDAO(getActivity());
        list = (ArrayList<TaiKhoan>) taiKhoanDAO.getAllKH();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());

        rcvTaikhoanKH.setLayoutManager(layout);

        taiKhoanADAPTER = new TaiKhoanKH_ADAPTER(getActivity(), list);
        rcvTaikhoanKH.setAdapter(taiKhoanADAPTER);
        fltaddKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogthem();
            }
        });
        return view;
    }
    public void opendialogthem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nhanvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        EditText edsdt_DLNV = view.findViewById(R.id.edsdt_DLNV);
        EditText edhoTen_DLNV = view.findViewById(R.id.edhoTen_DLNV);
        EditText edmatKhau_DLNV = view.findViewById(R.id.edmatKhau_DLNV);
        EditText ednhapLaiMatKhau_DLNV = view.findViewById(R.id.ednhapLaiMatKhau_DLNV);
        Button btnSaveSach = view.findViewById(R.id.btnSave_DLNV);
        Button btnCancelSach = view.findViewById(R.id.btnCancel_DLNV);

        btnSaveSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edsdt_DLNV.getText().toString();
                String ten = edhoTen_DLNV.getText().toString();
                String matkhau = edmatKhau_DLNV.getText().toString();
                if (sdt.length() == 10 || sdt.length() == 11) {
                    if (sdt.trim().isEmpty() || ten.trim().isEmpty() || matkhau.trim().isEmpty() || ednhapLaiMatKhau_DLNV.getText().toString().trim().isEmpty()) {
                        Toast.makeText(getActivity(), "Không đuợc bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                    } else if (matkhau.equalsIgnoreCase(ednhapLaiMatKhau_DLNV.getText().toString()) == false) {
                        Toast.makeText(getActivity(), "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    } else {
                        TaiKhoan taiKhoan = new TaiKhoan();
                        taiKhoan.setSdt(sdt);
                        taiKhoan.setHoTen(ten);
                        taiKhoan.setMatKhau(matkhau);
                        taiKhoan.setChucVu("KH");

                        if (taiKhoanDAO.insert(taiKhoan) > 0) {
                            list.clear();
                            list.addAll(taiKhoanDAO.getAllKH());
                            taiKhoanADAPTER.notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        } else if (taiKhoanDAO.insert(taiKhoan) == -1) {
                            Toast.makeText(getActivity(), "Số điện thoại đã tồn tại trong hệ thống!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Thêm thất bại.", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {
                    Toast.makeText(getActivity(), "Số điện thoại phải có 10 hoặc 11 số !", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancelSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edsdt_DLNV.setText("");
                edhoTen_DLNV.setText("");
                edmatKhau_DLNV.setText("");
                ednhapLaiMatKhau_DLNV.setText("");
            }
        });
    }
    //
    private void handleSearch(String query) {
        List<TaiKhoan> listSearch = new ArrayList<>();
        for (TaiKhoan taiKhoan : list) {
            if (String.valueOf(taiKhoan.getHoTen()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(taiKhoan);
            }
        }
        taiKhoanADAPTER = new TaiKhoanKH_ADAPTER(getActivity(), (ArrayList<TaiKhoan>) listSearch);
        rcvTaikhoanKH.setAdapter(taiKhoanADAPTER);

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleSearch(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}