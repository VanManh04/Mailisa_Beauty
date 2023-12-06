package com.example.mailisa_beauty.frg_quanLy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mailisa_beauty.ADAPTER.ViewLichKhachHang_ADAPTER;
import com.example.mailisa_beauty.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QL_LKHMAIN extends Fragment {

    public QL_LKHMAIN() {
        // Required empty public constructor
    }

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewLichKhachHang_ADAPTER viewpagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_lkhmain, container, false);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager2 = view.findViewById(R.id.viewpager2);
        viewpagerAdapter = new ViewLichKhachHang_ADAPTER(getActivity());
        viewPager2.setAdapter(viewpagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Tất cả");
                        break;
                    case 1:
                        tab.setText("Đang chờ");
                        break;
                    case 2:
                        tab.setText("Xác nhận");
                        break;
                    case 3:
                        tab.setText("Bị hủy");
                        break;
                }
            }
        }).attach();
        return view;
    }

    // Phương thức để tải lại dữ liệu dựa trên vị trí tab

}