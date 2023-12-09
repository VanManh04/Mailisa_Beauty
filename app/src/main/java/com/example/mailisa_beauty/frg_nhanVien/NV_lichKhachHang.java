package com.example.mailisa_beauty.frg_nhanVien;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mailisa_beauty.ADAPTER.LichKhachHang_KH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.LichKhachHang_NV_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;


public class NV_lichKhachHang extends Fragment {

    RecyclerView rcvLKH_QLLKH;
    LichKhachHang_DAO lichKhachHangDao;
    LichKhachHang_NV_ADAPTER lichKhachHang_kh_adapter;
    private ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();
    private SearchView searchView;
    TaiKhoanDAO taiKhoanDAO;
    Button btntatca,btndangcho,btnxacnhan,btnbihuy,btnhoanthanh;
    public NV_lichKhachHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_nv_lichkhachhang, container, false);
        rcvLKH_QLLKH = view.findViewById(R.id.lkhnvnvnv);
        btntatca = view.findViewById(R.id.btntatca1);
        btndangcho = view.findViewById(R.id.btndangcho1);
        btnxacnhan = view.findViewById(R.id.btnxacnhan1);
        btnbihuy = view.findViewById(R.id.btnbihuy1);
        btnhoanthanh = view.findViewById(R.id.btnhoanthanh1);

        taiKhoanDAO = new TaiKhoanDAO(getActivity());
        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvLKH_QLLKH.setLayoutManager(layout);
        lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
        rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);

        btntatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(lichKhachHangDao.getAll());
                lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
                rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);
                lichKhachHang_kh_adapter.notifyDataSetChanged();
            }
        });

        btndangcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(lichKhachHangDao.getAllByTrangThai("Đang chờ"));
                lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
                rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);
                lichKhachHang_kh_adapter.notifyDataSetChanged();
            }
        });
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(lichKhachHangDao.getAllByTrangThai("Xác nhận"));
                lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
                rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);
                lichKhachHang_kh_adapter.notifyDataSetChanged();
            }
        });
        btnhoanthanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(lichKhachHangDao.getAllByTrangThai("Hoàn thành"));
                lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
                rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);
                lichKhachHang_kh_adapter.notifyDataSetChanged();
            }
        });
        btnbihuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                list.addAll(lichKhachHangDao.getAllByTrangThai("Bị hủy"));
                lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), list);
                rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);
                lichKhachHang_kh_adapter.notifyDataSetChanged();
            }
        });
        return view;
    }
    private void handleSearch(String query) {
        List<LichKhachHang> listSearch = new ArrayList<>();
        for (LichKhachHang lichkhachhang : list) {
            TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichkhachhang.getMaTK()));
            if (String.valueOf(taiKhoan.getHoTen()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(lichkhachhang);
            }
        }
        lichKhachHang_kh_adapter = new LichKhachHang_NV_ADAPTER(getActivity(), (ArrayList<LichKhachHang>) listSearch);
        rcvLKH_QLLKH.setAdapter(lichKhachHang_kh_adapter);

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