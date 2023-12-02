package com.example.mailisa_beauty.frg_quanLy;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanKH_ADAPTER;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QL_khachHang extends Fragment {

    public QL_khachHang() {
        // Required empty public constructor
    }
    SwipeableRecyclerView rcvTaikhoanKH;
    Button fltaddKH;
    TaiKhoanDAO taiKhoanDAO;
    TaiKhoanKH_ADAPTER taiKhoanADAPTER;
    private ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_khachhang, container, false);

        rcvTaikhoanKH = view.findViewById(R.id.rcvTKKHQL);
        fltaddKH = view.findViewById(R.id.btnAddKh);

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
        rcvTaikhoanKH.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedRight(int position) {
                taiKhoanADAPTER.notifyDataSetChanged();
            }

            @Override
            public void onSwipedLeft(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.icon_warning);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý logic khi người dùng chọn Yes
                        TaiKhoan taikhoan = list.get(position); // Lấy đối tượng DichVu tại vị trí vuốt
                        if (taiKhoanDAO.delete(taikhoan.getMa_TK()) > 0) {
                            list.remove(position);
                            taiKhoanADAPTER.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý logic khi người dùng chọn No
                        dialog.dismiss();
                        taiKhoanADAPTER.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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