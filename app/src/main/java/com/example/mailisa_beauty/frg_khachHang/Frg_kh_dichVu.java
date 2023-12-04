package com.example.mailisa_beauty.frg_khachHang;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.example.mailisa_beauty.ADAPTER.DichVuKH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;

public class Frg_kh_dichVu extends Fragment {

    public Frg_kh_dichVu() {
        // Required empty public constructor
    }
    RecyclerView rcvDichVu;
    DichVuDAO dichVuDAO;
    DichVuKH_ADAPTER dichvuKH_ADAPTER;
    private ArrayList<DichVu> list = new ArrayList<DichVu>();
    private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frg_kh_dichvu, container, false);
        rcvDichVu = view.findViewById(R.id.rcvDVKH);
        dichVuDAO = new DichVuDAO(getActivity());
        list = (ArrayList<DichVu>) dichVuDAO.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvDichVu.setLayoutManager(layout);

        dichvuKH_ADAPTER = new DichVuKH_ADAPTER(getActivity(), list);
        rcvDichVu.setAdapter(dichvuKH_ADAPTER);
        return view;
    }

    private void handleSearch(String query) {
        List<DichVu> listSearch = new ArrayList<>();
        for (DichVu dichVu : list) {
            if (String.valueOf(dichVu.getTenDV()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(dichVu);
            }
        }
        dichvuKH_ADAPTER = new DichVuKH_ADAPTER(getActivity(), (ArrayList<DichVu>) listSearch);
        rcvDichVu.setAdapter(dichvuKH_ADAPTER);

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