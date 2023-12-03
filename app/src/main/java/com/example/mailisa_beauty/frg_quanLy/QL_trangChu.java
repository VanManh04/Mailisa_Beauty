package com.example.mailisa_beauty.frg_quanLy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mailisa_beauty.ADAPTER.DichVu_SL_NEW_ADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_gioDichVu;

import java.util.ArrayList;
public class QL_trangChu extends Fragment {

    public QL_trangChu() {
        // Required empty public constructor
    }
    RecyclerView rcvTCSPSALE,rcvTCSPNEW;
    DichVuDAO dichVuDAO;
    DichVu_SL_NEW_ADAPTER dichVuSLAdapter;

    Button btnDatLich,btnDichVu,btnLS,btnFB;
    private ArrayList<DichVu> list = new ArrayList<DichVu>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_trangchu, container, false);

        rcvTCSPSALE = view.findViewById(R.id.rcvTCSPSALE);
        dichVuDAO = new DichVuDAO(getActivity());
        list = (ArrayList<DichVu>) dichVuDAO.getAllSALE();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcvTCSPSALE.setLayoutManager(layout);

        dichVuSLAdapter = new DichVu_SL_NEW_ADAPTER(getActivity(), list);
        rcvTCSPSALE.setAdapter(dichVuSLAdapter);

        rcvTCSPNEW = view.findViewById(R.id.rcvTCSPNEW);
        dichVuDAO = new DichVuDAO(getActivity());
        list = (ArrayList<DichVu>) dichVuDAO.getAllNEW();
        LinearLayoutManager layout_DVNEW = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcvTCSPNEW.setLayoutManager(layout_DVNEW);

        dichVuSLAdapter = new DichVu_SL_NEW_ADAPTER(getActivity(), list);
        rcvTCSPNEW.setAdapter(dichVuSLAdapter);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        ImageSlider imageSlider2 = view.findViewById(R.id.imageSlider2);
        ArrayList<SlideModel> slideModels2= new ArrayList<>();
        slideModels2.add(new SlideModel(R.drawable.sk1, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.sk2, ScaleTypes.FIT));

        imageSlider2.setImageList(slideModels2,ScaleTypes.FIT);

        //
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi hình ảnh được click
                Uri uri = Uri.parse("https://maps.app.goo.gl/EerPr9kWRqJwqDXV8"); // Đường dẫn liên kết của bạn
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        super.onViewCreated(view, savedInstanceState);

    }

}