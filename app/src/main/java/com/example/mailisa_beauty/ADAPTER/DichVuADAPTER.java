package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class DichVuADAPTER extends RecyclerView.Adapter<DichVuADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDAO dichVuDAO;

    public DichVuADAPTER(Context context, ArrayList<DichVu> list) {
        this.context = context;
        this.list = list;
        dichVuDAO = new DichVuDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dichvu,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btndatlich_itDV.setVisibility(View.GONE);

        holder.tvTenDV_itDV.setText("Dịch vụ: "+list.get(position).getTenDV());
        holder.tvgiaDV_itDV.setText("Giá: " + list.get(position).getGiaDV());
        holder.tvloaiDV_itDV.setText("Loại: "+list.get(position).getLoaiDV());
        holder.tvtrangThai_itDV.setText("Trạng thái: "+list.get(position).getTrangThai());
        holder.tvghiChu_itDV.setText(list.get(position).getGhiChu());

        String r = "R.drawable."+list.get(position).getHinhAnh();
        Toast.makeText(context, r, Toast.LENGTH_SHORT).show();

        Glide.with(context)
                .load("file:///android_asset/" + list.get(position).getHinhAnh())
                .placeholder(R.drawable.dv1)
                .into(holder.img_itDV);
        DichVu dichVu = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDV_itDV, tvgiaDV_itDV,tvloaiDV_itDV,tvtrangThai_itDV,tvghiChu_itDV;
        ImageView img_itDV,imgdelete_itDV;
        Button btndatlich_itDV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDV_itDV = itemView.findViewById(R.id.tvTenDV_itDV);
            tvgiaDV_itDV = itemView.findViewById(R.id.tvgiaDV_itDV);
            tvloaiDV_itDV = itemView.findViewById(R.id.tvloaiDV_itDV);
            tvtrangThai_itDV = itemView.findViewById(R.id.tvtrangThai_itDV);
            tvghiChu_itDV = itemView.findViewById(R.id.tvghiChu_itDV);
            img_itDV = itemView.findViewById(R.id.img_itDV);
            imgdelete_itDV = itemView.findViewById(R.id.imgdelete_itDV);
            btndatlich_itDV = itemView.findViewById(R.id.btndatlich_itDV);
        }
    }
}
