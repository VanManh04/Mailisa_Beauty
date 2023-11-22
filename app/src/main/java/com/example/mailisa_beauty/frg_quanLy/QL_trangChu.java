package com.example.mailisa_beauty.frg_quanLy;

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
 * Use the {@link QL_trangChu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QL_trangChu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QL_trangChu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QL_trangChu.
     */
    // TODO: Rename and change types and number of parameters
    public static QL_trangChu newInstance(String param1, String param2) {
        QL_trangChu fragment = new QL_trangChu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_l_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels= new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.tintuc1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.tintuc2, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        ImageSlider imageSlider2 = view.findViewById(R.id.imageSlider2);
        ArrayList<SlideModel> slideModels2= new ArrayList<>();
        slideModels2.add(new SlideModel(R.drawable.sukien1, ScaleTypes.FIT));
        slideModels2.add(new SlideModel(R.drawable.sukien2, ScaleTypes.FIT));

        imageSlider2.setImageList(slideModels2,ScaleTypes.FIT);

        //

        super.onViewCreated(view, savedInstanceState);
    }
}