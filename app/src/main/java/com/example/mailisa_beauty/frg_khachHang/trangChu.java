package com.example.mailisa_beauty.frg_khachHang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link trangChu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class trangChu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public trangChu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment trangChu.
     */
    // TODO: Rename and change types and number of parameters
    public static trangChu newInstance(String param1, String param2) {
        trangChu fragment = new trangChu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_l_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        super.onViewCreated(view, savedInstanceState);




    }
    private void loadData() {
    }
}