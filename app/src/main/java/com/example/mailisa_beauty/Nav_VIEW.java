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

import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.frg_quanLy.QL_trangChu;
import com.google.android.material.navigation.NavigationView;

public class Nav_VIEW extends AppCompatActivity {
    NavigationView nav;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TaiKhoanDAO taiKhoanDAO;
    TextView headertennguoidung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);

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
        replaceFrg(qlTrangChu);

        //set add tai khoan
        if (chucVu.equals("QL")){
            nav.getMenu().findItem(R.id.nav_lichKhachHangQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_nhanVienQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_khachHangQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_trangChuQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_sanPhamQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(true);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(true);

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
            nav.getMenu().findItem(R.id.nav_sanPhamQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_trangChuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_dichVuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_datLichKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichSuKH).setVisible(false);
            nav.getMenu().findItem(R.id.nav_feedBackKH).setVisible(false);


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
            nav.getMenu().findItem(R.id.nav_sanPhamQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_lichLamViecQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_hoaDonQL).setVisible(false);
            nav.getMenu().findItem(R.id.nav_doanhSoQL).setVisible(false);
        }



        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 if (item.getItemId()==R.id.nav_dangXuat){
                    Intent i = new Intent(Nav_VIEW.this,MainActivity.class);
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