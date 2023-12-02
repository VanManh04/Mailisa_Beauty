package com.example.mailisa_beauty.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class TaiKhoanNV_ADAPTER_SPINER extends ArrayAdapter<TaiKhoan> {
    private Context context;
    private ArrayList<TaiKhoan> list;
    TextView tvmanv_SPN,tvtennv_SPN;
    public TaiKhoanNV_ADAPTER_SPINER(@NonNull Context context, ArrayList<TaiKhoan> list) {
        super(context, 0,list);
        this.context = context;
        this.list =list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_nhanvien_spinner,null);
        }
        final TaiKhoan item = list.get(position);
        if (item != null){
            tvmanv_SPN = view.findViewById(R.id.tvmanv_SPN);
            tvmanv_SPN.setText(item.getMa_TK()+". ");
            tvtennv_SPN = view.findViewById(R.id.tvtennv_SPN);
            tvtennv_SPN.setText(item.getHoTen());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_nhanvien_spinner,null);
        }
        final TaiKhoan item = list.get(position);
        if (item != null){
            tvmanv_SPN = view.findViewById(R.id.tvmanv_SPN);
            tvmanv_SPN.setText(item.getMa_TK()+". ");
            tvtennv_SPN = view.findViewById(R.id.tvtennv_SPN);
            tvtennv_SPN.setText(item.getHoTen());
        }
        return view;
    }
}
