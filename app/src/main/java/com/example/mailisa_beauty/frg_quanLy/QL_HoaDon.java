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

import com.example.mailisa_beauty.ADAPTER.HoaDon_QL_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.LichKhachHang_KH_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.HoaDonDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.HoaDon;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;

public class QL_HoaDon extends Fragment {

    public QL_HoaDon() {
        // Required empty public constructor
    }
        RecyclerView rcvQLHD;
    TaiKhoanDAO taiKhoanDAO;
    HoaDonDAO hoaDonDAO;
    DichVuDAO dichVuDAO;
    LichKhachHang_DAO lichKhachHangDao;
    HoaDon_QL_ADAPTER hoaDon_ql_adapter;
    private ArrayList<HoaDon> list = new ArrayList<HoaDon>();
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ql_hoadon, container, false);
        rcvQLHD = view.findViewById(R.id.rcvQLHD);
        hoaDonDAO = new HoaDonDAO(getActivity());
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvQLHD.setLayoutManager(layoutManager);
        hoaDon_ql_adapter = new HoaDon_QL_ADAPTER(getActivity(),list);
        rcvQLHD.setAdapter(hoaDon_ql_adapter);

        dichVuDAO = new DichVuDAO(getActivity());
        lichKhachHangDao = new LichKhachHang_DAO(getActivity());


        return view;
    }
    private void handleSearch(String query) {
        List<HoaDon> listSearch = new ArrayList<>();
        for (HoaDon hoaDon : list) {
            LichKhachHang lichKhachHang = lichKhachHangDao.getByMaLKH(hoaDon.getMaLKH());
            DichVu dichVu = dichVuDAO.getID(String.valueOf(lichKhachHang.getMaDV()));
            if (String.valueOf(dichVu.getTenDV()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(hoaDon);
            }
        }
        hoaDon_ql_adapter = new HoaDon_QL_ADAPTER(getActivity(), (ArrayList<HoaDon>) listSearch);
        rcvQLHD.setAdapter(hoaDon_ql_adapter);

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