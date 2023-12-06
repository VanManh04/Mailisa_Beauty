package com.example.mailisa_beauty.frg_quanLy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mailisa_beauty.ADAPTER.HoaDon_QL_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.HoaDonDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.Model.HoaDon;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_khachHang.Activity_kh_datLich;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class QL_DoanhSo extends Fragment {
    public QL_DoanhSo() {
        // Required empty public constructor
    }

    private EditText editTextStartDate, editTextEndDate;
    private TextView textViewTongDoanhThu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear, mMonth, mDay;
    int mYear1, mMonth1, mDay1;
    HoaDonDAO hoaDonDAO;
    private ArrayList<HoaDon> listHoaDon = new ArrayList<HoaDon>();
    LichKhachHang_DAO lichKhachHangDao;
    private ArrayList<LichKhachHang> listLichKhachHang = new ArrayList<LichKhachHang>();
    private ArrayList<LichKhachHang> listLichKhachHang2 = new ArrayList<LichKhachHang>();
    private ArrayList<HoaDon> ListHoaDon = new ArrayList<HoaDon>();
    DichVuDAO dichVuDAO;
    private ArrayList<DichVu> listDichVu = new ArrayList<DichVu>();
    int tongGoc, tongSale, tong;
    Button thongkebtn;
    TextView tongtungaytoingay;
    RecyclerView rcvhoadonthongke;
    HoaDon_QL_ADAPTER hoaDon_ql_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_doanhso, container, false);
        editTextStartDate = view.findViewById(R.id.editTextStartDate);
        editTextEndDate = view.findViewById(R.id.editTextEndDate);
        textViewTongDoanhThu = view.findViewById(R.id.textViewTongDoanhThu);
        tongtungaytoingay = view.findViewById(R.id.tongtungaytoingay);
        thongkebtn = view.findViewById(R.id.thongkebtn);
        rcvhoadonthongke = view.findViewById(R.id.rcvhoadonthongke);
        //lấy tất cả hóa đơn
        //lấy lịch khách hàng theo mã lkh từ hóa đơn
        //lấy dịch mụ theo mã dịch vụ từ lkh
        //lấy giá hoặc giá sale
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvhoadonthongke.setLayoutManager(layoutManager);
        hoaDonDAO = new HoaDonDAO(getActivity());
//        listHoaDon = (ArrayList<HoaDon>) hoaDonDAO.getAll();
//        hoaDon_ql_adapter = new HoaDon_QL_ADAPTER(getActivity(),listHoaDon);

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        dichVuDAO = new DichVuDAO(getActivity());
        listHoaDon = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        ArrayList<Integer> maDichVuList = new ArrayList<>();
        for (HoaDon hoaDon : listHoaDon){
            listLichKhachHang2 = (ArrayList<LichKhachHang>) lichKhachHangDao.getALLByMaLKH(hoaDon.getMaLKH());
            for (LichKhachHang lichKhachHang : listLichKhachHang2){
                maDichVuList.add(lichKhachHang.getMaDV());
            }
        }
//        for (HoaDon hoadon : listHoaDon) {
//            listLichKhachHang = (ArrayList<LichKhachHang>) lichKhachHangDao.getALLByMaLKH(hoadon.getMaLKH());
//            for (LichKhachHang lichkhachhang : listLichKhachHang) {
//                maLichKhachHangList.add(lichkhachhang.getMaLKH());
//            }
//        }
//        ArrayList<Integer> maDichVuList = new ArrayList<>();
//        for (Integer maLKH : maLichKhachHangList){
//            listDichVu = (ArrayList<DichVu>) dichVuDAO.getAllById(maLKH);
//            for (DichVu dichVu : listDichVu){
//                maDichVuList.add(dichVu.getMaDV());
//            }
//        }
        int tong = 0;
        int gia = 0;
        for (Integer maDV : maDichVuList) {
            DichVu dichVu = dichVuDAO.getID(String.valueOf(maDV));
            if (dichVu.getGiaSALE() < dichVu.getGiaDV()) {
                gia = dichVu.getGiaSALE();
            } else {
                gia = dichVu.getGiaDV();
            }
            tong += gia;
//            Toast.makeText(getActivity(), String.valueOf(dichVu.getMaDV()), Toast.LENGTH_SHORT).show();
        }

        textViewTongDoanhThu.setText(String.valueOf(tong));


        DatePickerDialog.OnDateSetListener dayStar = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar gcalender = new GregorianCalendar(mYear, mMonth, mDay);
                editTextStartDate.setText(sdf.format(gcalender.getTime()));
                if (isStartDateValid() == false) {
                    return;
                }
            }
        };
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), 0, dayStar, mYear, mMonth, mDay);
                dialog.show();
            }
        });
        DatePickerDialog.OnDateSetListener dayEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear1 = year;
                mMonth1 = month;
                mDay1 = dayOfMonth;
                GregorianCalendar gcalender = new GregorianCalendar(mYear1, mMonth1, mDay1);
                editTextEndDate.setText(sdf.format(gcalender.getTime()));
                if (isEndDateValid() == false) {
                    // Nếu kiểm tra không thành công, có thể xử lý tùy ý, ví dụ như hiển thị thông báo cảnh báo
                    return;
                }
            }
        };
        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear1 = c.get(Calendar.YEAR);
                mMonth1 = c.get(Calendar.MONTH);
                mDay1 = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), 0, dayEnd, mYear1, mMonth1, mDay1);
                dialog.show();
            }
        });

        thongkebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = editTextStartDate.getText().toString();
                String denNgay = editTextEndDate.getText().toString();
                ArrayList<Integer> maDVList2 = new ArrayList<>();
                List<HoaDon> hoaDonList = hoaDonDAO.getHoaDonByDateRange(tuNgay, denNgay);
                for (HoaDon hoaDon : hoaDonList){
                    listLichKhachHang2 = (ArrayList<LichKhachHang>) lichKhachHangDao.getALLByMaLKH(hoaDon.getMaLKH());
                    for (LichKhachHang lichKhachHang : listLichKhachHang2){
                        maDVList2.add(lichKhachHang.getMaDV());
                    }
                }
                hoaDon_ql_adapter = new HoaDon_QL_ADAPTER(getActivity(), (ArrayList<HoaDon>) hoaDonList);
                rcvhoadonthongke.setAdapter(hoaDon_ql_adapter);


                int tong1 = 0;
                int gia1 = 0;
                for (Integer maDV : maDVList2) {
                    DichVu dichVu = dichVuDAO.getID(String.valueOf(maDV));
                    if (dichVu.getGiaSALE() < dichVu.getGiaDV()) {
                        gia1 = dichVu.getGiaSALE();
                    } else {
                        gia1 = dichVu.getGiaDV();
                    }
                    tong1 += gia1;
//                    Toast.makeText(getActivity(), ""+dichVu.getGiaDV(), Toast.LENGTH_SHORT).show();
                }
                tongtungaytoingay.setText(String.valueOf(tong1));
                if (editTextStartDate.getText().toString().trim().isEmpty()||editTextEndDate.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Không được bỏ trống ngày bắt đầu hoặc kết thúc!", Toast.LENGTH_SHORT).show();
                }else if (tong1==0){
                    Toast.makeText(getActivity(), "Không có hóa đơn !", Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
        return view;
    }

    private boolean isStartDateValid() {
        Calendar selectedCalendar = new GregorianCalendar(mYear, mMonth, mDay);
        Calendar currentCalendar = Calendar.getInstance();

        if (selectedCalendar.after(currentCalendar)) {
            editTextStartDate.setText("");
            Toast.makeText(getActivity(), "Ngày bắt đầu phải là ngày hiện tại hoặc trước ngày hiện tại.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean isEndDateValid() {
        Calendar startCalendar = new GregorianCalendar(mYear, mMonth, mDay);
        Calendar endCalendar = new GregorianCalendar(mYear1, mMonth1, mDay1);

        if (endCalendar.before(startCalendar)) {
            editTextEndDate.setText("");
            Toast.makeText(getActivity(), "Ngày kết thúc phải là ngày bắt đầu hoặc sau ngày bắt đầu.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}