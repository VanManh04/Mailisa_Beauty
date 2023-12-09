package com.example.mailisa_beauty.frg_quanLy;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.LichKhachHang_QL_ADAPTER;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
import java.util.List;

public class QL_lichKhachHangTatCa extends Fragment {
    RecyclerView rcvLKH_QLLKH;
    static LichKhachHang_DAO lichKhachHangDao;
    static LichKhachHang_QL_ADAPTER lichKhachHang_ql_adapter;
    private static ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();
//    private SearchView searchView;
    TaiKhoanDAO taiKhoanDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_lichkhachhangtatca, container, false);
        rcvLKH_QLLKH = view.findViewById(R.id.rcvLKH_QLLKH);

        taiKhoanDAO = new TaiKhoanDAO(getActivity());

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvLKH_QLLKH.setLayoutManager(layout);
        lichKhachHang_ql_adapter = new LichKhachHang_QL_ADAPTER(getActivity(), list);
        rcvLKH_QLLKH.setAdapter(lichKhachHang_ql_adapter);

//        rcvLKH_QLLKH.setListener(new SwipeLeftRightCallback.Listener() {
//            @Override
//            public void onSwipedRight(int position) {
//                lichKhachHang_ql_adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onSwipedLeft(int position) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Cảnh báo");
//                builder.setIcon(R.drawable.icon_warning);
//                builder.setMessage("Bạn có muốn xóa không?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Xử lý logic khi người dùng chọn Yes
//                        LichKhachHang lichKhachHang = list.get(position); // Lấy đối tượng DichVu tại vị trí vuốt
//                        if (lichKhachHangDao.delete(lichKhachHang.getMaLKH()) > 0) {
//                            list.remove(position);
//                            lichKhachHang_ql_adapter.notifyDataSetChanged();
//                            Toast.makeText(getActivity(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Xử lý logic khi người dùng chọn No
//                        dialog.dismiss();
//                        lichKhachHang_ql_adapter.notifyDataSetChanged();
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
        return view;
    }
//    @Override
//    public void onDestroyView() {
//        if (searchView != null && !searchView.isIconified()) {
//            searchView.setIconified(true);
//        }
//        super.onDestroyView();
//    }


    public static void reloadData() {
        list.clear();
        list.addAll(lichKhachHangDao.getAll());
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
        rcvLKH_QLLKH.setAdapter(lichKhachHang_ql_adapter);

    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.toolbar_search, menu);
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        MenuItem searchItem = menu.findItem(R.id.search);
//        searchView = (SearchView) searchItem.getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                handleSearch(newText);
//                return true;
//            }
//        });
//        super.onCreateOptionsMenu(menu, inflater);
//    }
}