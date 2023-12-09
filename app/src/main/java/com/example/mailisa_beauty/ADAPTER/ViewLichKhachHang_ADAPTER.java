package com.example.mailisa_beauty.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mailisa_beauty.frg_quanLy.QL_HoaDon;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHchapNhan;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHchuaChapNhan;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHhoanThanh;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHhuy;
import com.example.mailisa_beauty.frg_quanLy.QL_lichKhachHangTatCa;

public class ViewLichKhachHang_ADAPTER extends FragmentStateAdapter {
    public ViewLichKhachHang_ADAPTER(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new QL_lichKhachHangTatCa();
            case 1: return new QL_LKHchuaChapNhan();
            case 2: return new QL_LKHchapNhan();
            case 3: return new QL_LKHhoanThanh();
            case 4: return new QL_LKHhuy();
        }
        return new QL_lichKhachHangTatCa();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
