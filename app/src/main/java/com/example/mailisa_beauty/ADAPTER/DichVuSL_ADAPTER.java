
package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;
//DIch Vu SALE
public class DichVuSL_ADAPTER extends RecyclerView.Adapter<DichVuSL_ADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDAO dichVuDAO;

    public DichVuSL_ADAPTER(Context context, ArrayList<DichVu> list) {
        this.context = context;
        this.list = list;
        dichVuDAO = new DichVuDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dichvusale,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DichVu dichVu = list.get(position);
        holder.tvTenDV_itDVSL.setText(dichVu.getTenDV());
        holder.tvloaiDV_itDVSL.setText("Loại: "+dichVu.getLoaiDV());
        holder.tvtrangThai_itDVSL.setText("Trạng thái: "+dichVu.getTrangThai());
        holder.tvgiaDV_itDVSL.setText(String.valueOf("Giá: " +list.get(position).getGiaSALE()));
        holder.tvgiaSALE_itDVSL.setText(String.valueOf("Giá SALE: " +list.get(position).getGiaSALE()));
        holder.tvghiChu_itDVSL.setText(dichVu.getGhiChu());

        if (dichVu.getGhiChu().length() > 40){
            holder.tvghiChu_itDVSL.setVisibility(View.GONE);
        }

//        String r = "R.drawable."+list.get(position).getHinhAnh();
//        Toast.makeText(context, r, Toast.LENGTH_SHORT).show();

        Glide.with(context)
                .load("file:///android_asset/" + dichVu.getHinhAnh())
                .placeholder(R.drawable.dv1)
                .into(holder.img_itDVSL);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDV_itDVSL, tvgiaDV_itDVSL,tvloaiDV_itDVSL,tvtrangThai_itDVSL,tvghiChu_itDVSL,tvgiaSALE_itDVSL;
        ImageView img_itDVSL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDV_itDVSL = itemView.findViewById(R.id.tvTenDV_itDVSL);
            tvloaiDV_itDVSL = itemView.findViewById(R.id.tvloaiDV_itDVSL);
            tvtrangThai_itDVSL = itemView.findViewById(R.id.tvtrangThai_itDVSL);
            tvghiChu_itDVSL = itemView.findViewById(R.id.tvghiChu_itDVSL);
            tvgiaDV_itDVSL = itemView.findViewById(R.id.tvgiaDV_itDVSL);
            tvgiaSALE_itDVSL = itemView.findViewById(R.id.tvgiaSALE_itDVSL);
            img_itDVSL = itemView.findViewById(R.id.img_itDVSL);
        }
    }
}

