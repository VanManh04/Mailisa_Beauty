package com.example.mailisa_beauty.frg_khachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mailisa_beauty.ADAPTER.DichVuKHDatLich_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.DichVuKHTrongGio_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.DichVuKH_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Activity_kh_datLich extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcv_ATVT_KHDL;
    DichVuTrongGio_DAO dichvutronggioDao;
    DichVuKHDatLich_ADAPTER dichVuKHDatLichAdapter;
    private ArrayList<DichVuTrongGio> list = new ArrayList<DichVuTrongGio>();
    TaiKhoanDAO taiKhoanDAO;
    DichVuDAO dichVuDAO;
    TextView hoTen_ATVT_KHDL,sdt_ATVT_KHDL,tvtienGoc_ATVT_KHDL,tvtienSale_ATVT_KHDL;
    EditText edNgayDat_ATVT_KHDL,edGioDat_ATVT_KHDL;
    RadioGroup gropRadio_ATVT_KHDL;
    Button btnThanhToan_ATVT_KHDL;
    int mYear,mMonth,mDay,mHour,mMinute;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String radioPTTT = "null";
    int giaGoc,giaSale;
    LichKhachHang_DAO lichKhachHang_dao;
    int checkRdo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_datlich);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String DATA_MATK = preferences.getString("DATA_MATK", "null");

        toolbar = findViewById(R.id.toolbar); // Thay R.id.toolbar bằng ID của Toolbar trong XML
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back); // Đặt icon quay lại
            getSupportActionBar().setTitle("Đặt lịch");
            toolbar.setTitleTextColor(Color.WHITE);
        }
//        Toast.makeText(this, String.valueOf(DATA_MATK), Toast.LENGTH_SHORT).show();
        rcv_ATVT_KHDL = findViewById(R.id.rcv_ATVT_KHDL);
        hoTen_ATVT_KHDL = findViewById(R.id.hoTen_ATVT_KHDL);
        sdt_ATVT_KHDL = findViewById(R.id.sdt_ATVT_KHDL);
        tvtienGoc_ATVT_KHDL = findViewById(R.id.tvtienGoc_ATVT_KHDL);
        tvtienSale_ATVT_KHDL = findViewById(R.id.tvtienSale_ATVT_KHDL);
        edNgayDat_ATVT_KHDL = findViewById(R.id.edNgayDat_ATVT_KHDL);
        edGioDat_ATVT_KHDL = findViewById(R.id.edGioDat_ATVT_KHDL);
        gropRadio_ATVT_KHDL = findViewById(R.id.gropRadio_ATVT_KHDL);
        btnThanhToan_ATVT_KHDL = findViewById(R.id.btnThanhToan_ATVT_KHDL);

        taiKhoanDAO = new TaiKhoanDAO(this);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(DATA_MATK);
        hoTen_ATVT_KHDL.setText("Họ tên: "+ taiKhoan.getHoTen());
        sdt_ATVT_KHDL.setText("SĐT: "+ taiKhoan.getSdt());


        DatePickerDialog.OnDateSetListener ngayDat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar gcalender = new GregorianCalendar(mYear,mMonth,mDay);
                edNgayDat_ATVT_KHDL.setText(sdf.format(gcalender.getTime()));
                if (isStartDateValid() == false) {
                    return;
                }
            }
        };
        edNgayDat_ATVT_KHDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(Activity_kh_datLich.this,0,ngayDat,mYear,mMonth,mDay);
                dialog.show();
            }
        });
        TimePickerDialog.OnTimeSetListener gioDat = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Xử lý khi chọn giờ xong
                mHour = hourOfDay;
                mMinute = minute;
                edGioDat_ATVT_KHDL.setText(String.format(Locale.getDefault(), "%02d:%02d", mHour, mMinute));
            }
        };
        edGioDat_ATVT_KHDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thời gian hiện tại
                Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Hiển thị TimePickerDialog
                TimePickerDialog dialog = new TimePickerDialog(Activity_kh_datLich.this, gioDat, mHour, mMinute, true);
                dialog.show();
//                edGioDat_ATVT_KHDL.setText(mHour+":"+mMinute);
            }
        });

        gropRadio_ATVT_KHDL.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton  = findViewById(checkedId);
                radioPTTT = radioButton.getText().toString();
                checkRdo=1;
            }
        });


        Intent intent = getIntent();
        String maDV_muaNgay = intent.getStringExtra("maDV_muaNgay");
//            Toast.makeText(this, String.valueOf(maDV_muaNgay), Toast.LENGTH_SHORT).show();
        dichvutronggioDao = new DichVuTrongGio_DAO(this);
//            int setAllTrangThai = dichvutronggioDao.setAllTrangThai(false);
        int setTT = dichvutronggioDao.setTrangThaiByMaTKAndMaDV(true,DATA_MATK,maDV_muaNgay);
        list = (ArrayList<DichVuTrongGio>) dichvutronggioDao.getAllByTrangThaiAndMaTK(true,DATA_MATK);
        DichVuTrongGio selectedDichVuTrongGio = list.get(0);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rcv_ATVT_KHDL.setLayoutManager(layout);
        dichVuKHDatLichAdapter = new DichVuKHDatLich_ADAPTER(this, list, DATA_MATK);
        rcv_ATVT_KHDL.setAdapter(dichVuKHDatLichAdapter);

        dichVuDAO = new DichVuDAO(this);
        for (DichVuTrongGio dvtg :list) {
            DichVu dichVu = dichVuDAO.getID(String.valueOf(dvtg.getMaDV()));
            giaGoc += dichVu.getGiaDV()*dvtg.getSoLuong();
            giaSale+= dichVu.getGiaSALE()*dvtg.getSoLuong();
        }


        tvtienGoc_ATVT_KHDL.setText(String.valueOf(giaGoc));
        tvtienSale_ATVT_KHDL.setText(String.valueOf(giaSale));
        if (giaSale<=giaGoc){

        }else {
            tvtienSale_ATVT_KHDL.setVisibility(View.GONE);
            TextView b = findViewById(R.id.b);
            b.setVisibility(View.GONE);
        }
        //addlich
        lichKhachHang_dao = new LichKhachHang_DAO(this);
        btnThanhToan_ATVT_KHDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edNgayDat_ATVT_KHDL.getText().toString().trim().isEmpty()){
                    Toast.makeText(Activity_kh_datLich.this, "Không bỏ trống ngày !", Toast.LENGTH_SHORT).show();
                }else if (edGioDat_ATVT_KHDL.getText().toString().trim().isEmpty()){
                    Toast.makeText(Activity_kh_datLich.this, "Không bỏ trống giờ !", Toast.LENGTH_SHORT).show();
                }else if (checkRdo==0){
                    Toast.makeText(Activity_kh_datLich.this, "Phải chọn phương thức thanh toán !", Toast.LENGTH_SHORT).show();
                }else {
                    //add lich
                    LichKhachHang lichKhachHang = new LichKhachHang();

                    lichKhachHang.setMaTK(Integer.parseInt(DATA_MATK));
                    lichKhachHang.setMaDV(Integer.parseInt(String.valueOf(selectedDichVuTrongGio.getMaDV())));
                    String gio = edGioDat_ATVT_KHDL.getText().toString();
                    String ngay =edNgayDat_ATVT_KHDL.getText().toString();

                    try {
                        lichKhachHang.setNgayDat(sdf.parse(ngay));
                    } catch (ParseException e) {
                        Toast.makeText(Activity_kh_datLich.this, "Lỗi ngày", Toast.LENGTH_SHORT).show();
                    }
                    lichKhachHang.setGioDat(gio);
                    lichKhachHang.setPTTT(radioPTTT);
                    lichKhachHang.setTrangThai("Đang chờ");
                    lichKhachHang.setFeedBack("");
                    lichKhachHang.setGhiChu("");
                    if (lichKhachHang_dao.insert(lichKhachHang)>0){
                        Intent resultIntent = new Intent();
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                        Toast.makeText(Activity_kh_datLich.this, "Đặt lịch thành công!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(Activity_kh_datLich.this, "Lỗi !", Toast.LENGTH_SHORT).show();
                    }
                    for (DichVuTrongGio dichvuTG : list) {
                        if (dichvutronggioDao.delete(dichvuTG.getMaDVTG())>0){
//                            Toast.makeText(Activity_kh_datLich.this, "ok Xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    // Xử lý sự kiện khi nút quay lại được nhấn
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Xử lý hành động khi nút quay lại được nhấn
                finish(); // Đóng Activity hiện tại
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private boolean isStartDateValid() {
        Calendar selectedCalendar = new GregorianCalendar(mYear, mMonth, mDay);
        Calendar currentCalendar = Calendar.getInstance();

        if (!selectedCalendar.before(currentCalendar) || selectedCalendar.equals(currentCalendar)) {
            // Ngày đặt phải là ngày hiện tại hoặc sau ngày hiện tại
            return true;
        } else {
            edNgayDat_ATVT_KHDL.setText("");
            Toast.makeText(this, "Bạn phải đặt dịch vụ trước 1 ngày !", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}