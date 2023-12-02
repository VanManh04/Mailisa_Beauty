package com.example.mailisa_beauty.frg_quanLy;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.LichLamViecADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanNV_ADAPTER_SPINER;
import com.example.mailisa_beauty.DAO.LichLamViecDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichLamViec;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class QL_lichLamViec extends Fragment {
    public QL_lichLamViec() {
        // Required empty public constructor
    }
    SwipeableRecyclerView rcvQLLLVQL;
    Button fladdQLLLVQL;
    private ArrayList<LichLamViec> list = new ArrayList<LichLamViec>();
    LichLamViecDAO lichLamViecDAO;
    LichLamViecADAPTER lichLamViecADAPTER;
    TaiKhoanNV_ADAPTER_SPINER taiKhoanNVAdapterSpiner;
    private ArrayList<TaiKhoan> listTaiKhoan = new ArrayList<TaiKhoan>();
    TaiKhoanDAO taiKhoanDAO;
    int maTaiKhoan;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;
    EditText edngayBatDau_DLLLV,edca_DLLLV,edghiChu_DLLLV;
    private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ql_lichlamviec, container, false);
        rcvQLLLVQL = view.findViewById(R.id.rcvQLLLVQL);
        fladdQLLLVQL = view.findViewById(R.id.fladdQLLLVQL);

        lichLamViecDAO = new LichLamViecDAO(getActivity());
        list = (ArrayList<LichLamViec>) lichLamViecDAO.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvQLLLVQL.setLayoutManager(layout);

        lichLamViecADAPTER = new LichLamViecADAPTER(getActivity(), list);
        rcvQLLLVQL.setAdapter(lichLamViecADAPTER);
        fladdQLLLVQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogthem();
            }
        });

        taiKhoanDAO = new TaiKhoanDAO(getActivity());
        listTaiKhoan = (ArrayList<TaiKhoan>) taiKhoanDAO.getAllNV();
        taiKhoanNVAdapterSpiner = new TaiKhoanNV_ADAPTER_SPINER(getActivity(), listTaiKhoan);

        rcvQLLLVQL.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedRight(int position) {
                lichLamViecADAPTER.notifyDataSetChanged();
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
                        LichLamViec lichLamViec = list.get(position); // Lấy đối tượng DichVu tại vị trí vuốt
                        if (lichLamViecDAO.delete(lichLamViec.getMaLLV()) > 0) {
                            list.remove(position);
                            lichLamViecADAPTER.notifyDataSetChanged();
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
                        lichLamViecADAPTER.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return  view;

    }
    private void opendialogthem() {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_lichlamviec, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
//        EditText edten_DLLLV = view.findViewById(R.id.edten_DLLLV);
        Spinner spnNV = view.findViewById(R.id.spnNV);

        TextView tenNhanVien_diaLongLLV = view.findViewById(R.id.tenNhanVien_diaLongLLV);
        tenNhanVien_diaLongLLV.setVisibility(View.GONE);
        edngayBatDau_DLLLV = view.findViewById(R.id.edngayBatDau_DLLLV);
        edca_DLLLV = view.findViewById(R.id.edca_DLLLV);
        edghiChu_DLLLV = view.findViewById(R.id.edghiChu_DLLLV);
        Button btnSave_DLLLV = view.findViewById(R.id.btnSave_DLLLV);
        Button btnCancel_DLLLV = view.findViewById(R.id.btnCancel_DLLLV);

//                taiKhoanDAO = new TaiKhoanDAO(getActivity());
//                listTaiKhoan = (ArrayList<TaiKhoan>) taiKhoanDAO.getAllNV();
//                taiKhoanNVAdapterSpiner = new TaiKhoanNV_ADAPTER_SPINER(getActivity(),listTaiKhoan);
        spnNV.setAdapter(taiKhoanNVAdapterSpiner);
        DatePickerDialog.OnDateSetListener ngayDat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar gcalender = new GregorianCalendar(mYear,mMonth,mDay);
                edngayBatDau_DLLLV.setText(sdf.format(gcalender.getTime()));
            }
        };
        edngayBatDau_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,ngayDat,mYear,mMonth,mDay);
                dialog.show();
            }
        });
        spnNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTaiKhoan = listTaiKhoan.get(position).getMa_TK();
//                Toast.makeText(getActivity(), String.valueOf(maTaiKhoan), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edca_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(getActivity());
                builder1.setTitle("Ca làm việc:");
                String[] ca1 = {"Sáng", "Chiều"};
                builder1.setItems(ca1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edca_DLLLV.setText(ca1[which]);
                    }
                });
                android.app.AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });
        btnSave_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String tennv = edten_DLLLV.getText().toString();
                String ngaybatdau = edngayBatDau_DLLLV.getText().toString();
                String ca = edca_DLLLV.getText().toString();
                String ghichu = edghiChu_DLLLV.getText().toString();

                TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(maTaiKhoan));

                LichLamViec lichLamViec = new LichLamViec();
                lichLamViec.setMaTK(maTaiKhoan);
                try {
                    lichLamViec.setNgayBatDau(sdf.parse(ngaybatdau));
                } catch (ParseException e) {
//                    Toast.makeText(getActivity(), "Lỗi !", Toast.LENGTH_SHORT).show();
                }
                lichLamViec.setCa(ca);
                lichLamViec.setGhiChu(ghichu);
                if (valudate()==1){
                    if (lichLamViecDAO.insert(lichLamViec)>0){
                        list.clear();
                        list.addAll(lichLamViecDAO.getAll());
                        lichLamViecADAPTER.notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Nhân viên đã có lịch làm việc !", Toast.LENGTH_SHORT).show();
                    }
                }else {

                }
            }
        });

        btnCancel_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaybatdau = edngayBatDau_DLLLV.getText().toString();
                String ca = edca_DLLLV.getText().toString();
                String ghichu = edghiChu_DLLLV.getText().toString();
                if (ngaybatdau.trim().isEmpty() && ca.trim().isEmpty() && ghichu.trim().isEmpty()){
                    dialog.dismiss();
                }else {
                    edngayBatDau_DLLLV.setText("");
                    edca_DLLLV.setText("");
                    edghiChu_DLLLV.setText("");
                }
            }
        });
    }

    public int valudate(){
        int check = 1;
        String ngaybatdau = edngayBatDau_DLLLV.getText().toString();
        String ca = edca_DLLLV.getText().toString();
        String ghichu = edghiChu_DLLLV.getText().toString();
        if (ngaybatdau.trim().isEmpty() || ca.trim().isEmpty() || ghichu.trim().isEmpty()){
            Toast.makeText(getActivity(), "Không được bỏ trống thông tin !", Toast.LENGTH_SHORT).show();
            check = 0;
        }
        return check;
    }
    private void handleSearch(String query) {
        List<LichLamViec> listSearch = new ArrayList<>();
        for (LichLamViec lichLamViec : list) {
            TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichLamViec.getMaTK()));
            if (String.valueOf(taiKhoan.getHoTen()).toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(lichLamViec);
            }
        }
        lichLamViecADAPTER = new LichLamViecADAPTER(getActivity(), (ArrayList<LichLamViec>) listSearch);
        rcvQLLLVQL.setAdapter(lichLamViecADAPTER);

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