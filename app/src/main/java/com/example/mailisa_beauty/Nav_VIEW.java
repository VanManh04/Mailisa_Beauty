package com.example.mailisa_beauty;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_dichVu;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_gioDichVu;
import com.example.mailisa_beauty.frg_quanLy.QL_dichVu;
import com.example.mailisa_beauty.frg_quanLy.QL_khachHang;
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
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.icon_dichvu));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.icon_giodv));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.icon_ldckh));


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

                        break;
                    case 4:

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
        headertennguoidung.setText(hoTen+" !");

        QL_trangChu qlTrangChu = new QL_trangChu();
        setTitle("Trang chủ");
        replaceFrg(qlTrangChu);

        //set add tai khoan
        if (chucVu.equals("QL")){
            nav.getMenu().findItem(R.id.nav_lichKhachHangQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_nhanVienQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_khachHangQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_trangChuQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_dichVuQL).setVisible(true);

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
            nav.getMenu().findItem(R.id.nav_trangChuQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_trangChuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_datLichKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichSuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_feedBackKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuQL).setVisible(false);

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
            nav.getMenu().findItem(R.id.nav_trangChuQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuQL).setVisible(false);
        }
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.nav_datLichKH){
                    setTitle("Đặt lịch");
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
                    setTitle("");
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