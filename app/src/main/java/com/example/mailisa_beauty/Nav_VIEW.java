package com.example.mailisa_beauty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Login.dang_Nhap;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_LSHoatDong;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_dichVu;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_feedBack;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_gioDichVu;
import com.example.mailisa_beauty.frg_nhanVien.NV_QLKhachHang;
import com.example.mailisa_beauty.frg_nhanVien.NV_lichKhachHang;
import com.example.mailisa_beauty.frg_nhanVien.NV_lichLamViec;
import com.example.mailisa_beauty.frg_quanLy.QL_DoanhSo;
import com.example.mailisa_beauty.frg_quanLy.QL_FeedBack;
import com.example.mailisa_beauty.frg_quanLy.QL_HoaDon;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHMAIN;
import com.example.mailisa_beauty.frg_quanLy.QL_dichVu;
import com.example.mailisa_beauty.frg_quanLy.QL_khachHang;
import com.example.mailisa_beauty.frg_quanLy.QL_lichKhachHangTatCa;
import com.example.mailisa_beauty.frg_quanLy.QL_lichLamViec;
import com.example.mailisa_beauty.frg_quanLy.QL_nhanVien;
import com.example.mailisa_beauty.frg_quanLy.QL_trangChu;
import com.google.android.material.navigation.NavigationView;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Nav_VIEW extends AppCompatActivity {
    NavigationView nav;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TaiKhoanDAO taiKhoanDAO;
    TextView headertennguoidung;

    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);

        bottomNavigation= findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.icon_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.icon_service2));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.icon_giodv));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.icon_feedback_242));


        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES


                switch (model.getId()){

                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frmNav, new QL_trangChu())
                                .commit();
                                 setTitle("Trang chủ");
                        break;

                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frmNav, new Frg_kh_dichVu())
                                .commit();;
                        setTitle("Dịch vụ");
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frmNav, new Frg_kh_gioDichVu())
                                .commit();;
                        setTitle("Giỏ dịch vụ");
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frmNav, new Frg_kh_feedBack())
                                .commit();;
                        setTitle("Đánh giá");
                        break;
                }

                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES



                return null;
            }
        });




        //set toolbar thay thế action bar
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //dung frg pm lam home
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setItemIconTintList(null);

        //set id header
        View mHeaderView = nav.getHeaderView(0);
        headertennguoidung = mHeaderView.findViewById(R.id.headertennguoidung);

        Intent i = getIntent();
        String sdt = i.getStringExtra("sdt");
        taiKhoanDAO = new TaiKhoanDAO(this);
        TaiKhoan taiKhoan = taiKhoanDAO.getSDT(sdt);
        String chucVu = taiKhoan.getChucVu();
        String hoTen = taiKhoan.getHoTen();
        headertennguoidung.setText(hoTen);


        // WARNING--------------------------------------------------------------------------------------------------------
        String DATA_MATK = String.valueOf(taiKhoan.getMa_TK());
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("DATA_MATK", DATA_MATK);
        editor.apply();
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String DATA_MATK = preferences.getString("DATA_MATK", "null");
        // WARNING--------------------------------------------------------------------------------------------------------


        QL_trangChu qlTrangChu = new QL_trangChu();
        setTitle("Trang chủ");
        replaceFrg(qlTrangChu);

        //set add tai khoan
        if (chucVu.equals("QL")){
            nav.getMenu().findItem(R.id.nav_lichKhachHangQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_nhanVienQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_khachHangQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_dichVuQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_danhgiaQL).setVisible(true);

            nav.getMenu().findItem(R.id.nav_doiMatKhau).setVisible(true);
            nav.getMenu().findItem(R.id.nav_dangXuat).setVisible(true);

            nav.getMenu().findItem(R.id.nav_lichLamViecNV).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichKhachHangNV).setVisible(false);
            nav.getMenu().findItem(R.id.nav_khachHangNV).setVisible(false);
            nav.getMenu().findItem(R.id.nav_trangChuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_datLichKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichSuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_feedBackKH).setVisible(false);

        }else if (chucVu.equals("NV")){
            nav.getMenu().findItem(R.id.nav_lichLamViecNV).setVisible(true);
            nav.getMenu().findItem(R.id.nav_lichKhachHangNV).setVisible(true);
            nav.getMenu().findItem(R.id.nav_khachHangNV).setVisible(true);

            nav.getMenu().findItem(R.id.nav_doiMatKhau).setVisible(true);
            nav.getMenu().findItem(R.id.nav_dangXuat).setVisible(true);

            nav.getMenu().findItem(R.id.nav_lichKhachHangQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_nhanVienQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_khachHangQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_trangChuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_datLichKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichSuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_feedBackKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_danhgiaQL).setVisible(false);

        }else {
            nav.getMenu().findItem(R.id.nav_trangChuKH).setVisible(true);
            nav.getMenu().findItem(R.id.nav_dichVuKH).setVisible(true);
            nav.getMenu().findItem(R.id.nav_datLichKH).setVisible(true);
            nav.getMenu().findItem(R.id.nav_lichSuKH).setVisible(true);
            nav.getMenu().findItem(R.id.nav_feedBackKH).setVisible(true);

            nav.getMenu().findItem(R.id.nav_doiMatKhau).setVisible(true);
            nav.getMenu().findItem(R.id.nav_dangXuat).setVisible(true);


            nav.getMenu().findItem(R.id.nav_lichLamViecNV).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichKhachHangNV).setVisible(false);
            nav.getMenu().findItem(R.id.nav_khachHangNV).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichKhachHangQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_nhanVienQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_khachHangQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_danhgiaQL).setVisible(false);
        }
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.nav_lichKhachHangNV){
                    setTitle("Lịch khách hàng");
                    NV_lichKhachHang lkhnv = new NV_lichKhachHang();
                    replaceFrg(lkhnv);
                }else if (item.getItemId()==R.id.nav_khachHangNV){
                    setTitle("Quản lý khách hàng");
                    NV_QLKhachHang qlkhhh = new NV_QLKhachHang();
                    replaceFrg(qlkhhh);
                }else if (item.getItemId()==R.id.nav_lichLamViecNV){
                    setTitle("Lịch làm việc");
                    NV_lichLamViec LLV = new NV_lichLamViec();
                    replaceFrg(LLV);
                }else if (item.getItemId()==R.id.nav_doanhSoQL){
                    setTitle("Quản lý doanh số");
                    QL_DoanhSo QLDS = new QL_DoanhSo();
                    replaceFrg(QLDS);
                }else if (item.getItemId()==R.id.nav_hoaDonQL){
                    setTitle("Quản lý hóa đơn");
                    QL_HoaDon QL_HD = new QL_HoaDon();
                    replaceFrg(QL_HD);
                }else if (item.getItemId()==R.id.nav_danhgiaQL){
                    setTitle("Đánh giá");
                    QL_FeedBack QL_FB = new QL_FeedBack();
                    replaceFrg(QL_FB);
                }else if (item.getItemId()==R.id.nav_feedBackKH){
                    setTitle("Đánh giá");
                    Frg_kh_feedBack frg_FB = new Frg_kh_feedBack();
                    replaceFrg(frg_FB);
                }else if (item.getItemId()==R.id.nav_lichSuKH){
                    setTitle("Lịch sử đặt lịch");
                    Frg_kh_LSHoatDong ls_HD = new Frg_kh_LSHoatDong();
                    replaceFrg(ls_HD);
                }else if (item.getItemId()==R.id.nav_lichKhachHangQL){
                    setTitle("Lịch đặt của khách hàng");
                    QL_LKHMAIN ql_LKH = new QL_LKHMAIN();
                    replaceFrg(ql_LKH);
                }else if (item.getItemId()==R.id.nav_datLichKH){
                    setTitle("Giỏ dịch vụ");
                    Frg_kh_gioDichVu frggioDichVu = new Frg_kh_gioDichVu();
                    //Sent String to Frg_kh_gioDichVu
                    Bundle bundle = new Bundle();
                    String idTK = String.valueOf(taiKhoan.getMa_TK());
                    bundle.putString("key_idTK", idTK);
                    // Set the arguments to the fragment
                    frggioDichVu.setArguments(bundle);
                    replaceFrg(frggioDichVu);
                }else if (item.getItemId()==R.id.nav_dichVuKH){
                    setTitle("Dịch vụ");
                    Frg_kh_dichVu frgDichVu = new Frg_kh_dichVu();
                    replaceFrg(frgDichVu);
                }else if (item.getItemId()==R.id.nav_lichLamViecQL){
                    setTitle("Quản lý lịch làm việc");
                    QL_lichLamViec qllichlamviec = new QL_lichLamViec();
                    replaceFrg(qllichlamviec);
                }else if (item.getItemId()==R.id.nav_khachHangQL){
                    setTitle("Quản lý khách hàng");
                    QL_khachHang qlkhachhang = new QL_khachHang();
                    replaceFrg(qlkhachhang);
                }else if (item.getItemId()==R.id.nav_dichVuQL){
                    setTitle("Quản lý dịch vụ");
                    QL_dichVu qlDichVu = new QL_dichVu();
                    replaceFrg(qlDichVu);
                }else if (item.getItemId()==R.id.nav_trangChuKH){
                    setTitle("Trang chủ");
                    QL_trangChu frtrangChu = new QL_trangChu();
                    replaceFrg(frtrangChu);
                }else if (item.getItemId()==R.id.nav_nhanVienQL){
                    setTitle("Quản lý nhân viên");
                    QL_nhanVien qlNhanVien = new QL_nhanVien();
                    replaceFrg(qlNhanVien);
                }else if (item.getItemId()==R.id.nav_doiMatKhau){
                    setTitle("Thay đổi mật khẩu");
                    doiMatKhau frchangepass = new doiMatKhau();
                    replaceFrg(frchangepass);
                }else if (item.getItemId()==R.id.nav_dangXuat){
                    Intent i = new Intent(Nav_VIEW.this, dang_Nhap.class);
                    startActivity(i);
                    finish();
                }
                drawerLayout.close();
                return true;
            }
        });

    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmNav,frg).commit();
    }


}