package com.example.mailisa_beauty.frg_khachHang;

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

import com.example.mailisa_beauty.ADAPTER.DichVuKH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.LichKhachHang_KH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.LichKhachHang_QL_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;

public class Frg_kh_LSHoatDong extends Fragment {
    public Frg_kh_LSHoatDong() {
        // Required empty public constructor
    }
    RecyclerView rcvLKH_FRGKH;
    LichKhachHang_DAO lichKhachHangDao;
    LichKhachHang_KH_ADAPTER lichKhachHang_kh_adapter;
    private ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();
    private SearchView searchView;
    DichVuDAO dichVuDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.frg_kh_lshoatdong, container, false);
        rcvLKH_FRGKH = view.findViewById(R.id.rcvLKH_FRGKH);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String DATA_MATK = preferences.getString("DATA_MATK", "null");

        dichVuDAO = new DichVuDAO(getActivity());
        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getByMaTK(Integer.parseInt(DATA_MATK));
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvLKH_FRGKH.setLayoutManager(layout);
        lichKhachHang_kh_adapter = new LichKhachHang_KH_ADAPTER(getActivity(), list);
        rcvLKH_FRGKH.setAdapter(lichKhachHang_kh_adapter);

        return view;
    }
    private void handleSearch(String query) {
        List<LichKhachHang> listSearch = new ArrayList<>();
        for (LichKhachHang lichkhachhang : list) {
            DichVu dichVu = dichVuDAO.getID(String.valueOf(lichkhachhang.getMaDV()));
            if (String.valueOf(dichVu.getTenDV()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(lichkhachhang);
            }
        }
        lichKhachHang_kh_adapter = new LichKhachHang_KH_ADAPTER(getActivity(), (ArrayList<LichKhachHang>) listSearch);
        rcvLKH_FRGKH.setAdapter(lichKhachHang_kh_adapter);

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