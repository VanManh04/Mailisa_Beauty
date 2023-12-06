package com.example.mailisa_beauty.frg_quanLy;

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

import com.example.mailisa_beauty.ADAPTER.LichKhachHang_QL_ADAPTER;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;


public class QL_LKHchapNhan extends Fragment {

    private String mParam1;
    private String mParam2;

    public QL_LKHchapNhan() {
        // Required empty public constructor
    }


    RecyclerView rcvlkhchapnhan;
    static LichKhachHang_DAO lichKhachHangDao;
    static LichKhachHang_QL_ADAPTER lichKhachHang_ql_adapter;
    private static ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();
    private SearchView searchView;
    TaiKhoanDAO taiKhoanDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ql_lkhchapnhan, container, false);
        rcvlkhchapnhan = view.findViewById(R.id.rcvlkhchapnhan);

        taiKhoanDAO = new TaiKhoanDAO(getActivity());

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getAllByTrangThai("Xác nhận");
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvlkhchapnhan.setLayoutManager(layout);
        lichKhachHang_ql_adapter = new LichKhachHang_QL_ADAPTER(getActivity(), list);
        rcvlkhchapnhan.setAdapter(lichKhachHang_ql_adapter);
        return view;
    }
    public static void reloadData() {
        list.clear();
        list.addAll(lichKhachHangDao.getAllByTrangThai("Xác nhận"));
        lichKhachHang_ql_adapter.notifyDataSetChanged();
    }
    private void handleSearch(String query) {
        List<LichKhachHang> listSearch = new ArrayList<>();
//        List<Integer> listmatk_KH = new ArrayList<>();
        for (LichKhachHang lichKhachHang : list) {
//            listmatk_KH.add(lichKhachHang.getMaTK());
            TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichKhachHang.getMaTK()));
            if (String.valueOf(taiKhoan.getHoTen()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(lichKhachHang);
            }
        }
        lichKhachHang_ql_adapter = new LichKhachHang_QL_ADAPTER(getActivity(), listSearch);
        rcvlkhchapnhan.setAdapter(lichKhachHang_ql_adapter);

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