package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
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

public class DichVuKH_ADAPTER extends RecyclerView.Adapter<DichVuKH_ADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDAO dichVuDAO;

    public DichVuKH_ADAPTER(Context context, ArrayList<DichVu> list) {
        this.context = context;
        this.list = list;
        dichVuDAO = new DichVuDAO(context);


    }

    public interface OnItemClickListener {
        void onItemClick(int position);
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
        holder.imgdelete_itDV.setVisibility(View.GONE);
        holder.tvloaiDV_itDV.setVisibility(View.GONE);
        holder.tvtrangThai_itDV.setVisibility(View.GONE);


        DichVu dichVu = list.get(position);
        holder.tvTenDV_itDV.setText(list.get(position).getTenDV());
        String ten = dichVu.getTenDV();

        if (ten.length()>33){
            String ten33 = ten.substring(0, 29);
            holder.tvTenDV_itDV.setText(ten33+"...");
        }else {
            holder.tvTenDV_itDV.setText(list.get(position).getTenDV());
        }

        holder.tvloaiDV_itDV.setText("Loại: "+list.get(position).getLoaiDV());
        holder.tvtrangThai_itDV.setText("Trạng thái: "+list.get(position).getTrangThai());
        if (dichVu.getTrangThai().equals("NEW")||dichVu.getTrangThai().equals("KHONG")){
            holder.tvgiaSALE_itDV.setVisibility(View.GONE);
            holder.tvgiaDV_itDV.setText(String.valueOf("Giá: " +list.get(position).getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDV.setText(String.valueOf("Giá SALE: " +list.get(position).getGiaSALE())+" VNĐ");
        }else {
            holder.tvgiaDV_itDV.setText(String.valueOf("Giá gốc: " +list.get(position).getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDV.setText(String.valueOf("Giá SALE: " +list.get(position).getGiaSALE())+" VNĐ");
        }
        String ghiChu = dichVu.getGhiChu();
        if (ghiChu.length() > 40) {
            String first40Characters = ghiChu.substring(0, 80);
            holder.tvghiChu_itDV.setText(first40Characters +"...");
        } else {
            holder.tvghiChu_itDV.setText(dichVu.getGhiChu());
        }
//        String r = "R.drawable."+list.get(position).getHinhAnh();
//        Toast.makeText(context, r, Toast.LENGTH_SHORT).show();

        Glide.with(context)
                .load("file:///android_asset/" + list.get(position).getHinhAnh())
                .placeholder(R.drawable.dv1)
                .into(holder.img_itDV);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDV_itDV, tvgiaDV_itDV,tvloaiDV_itDV,tvtrangThai_itDV,tvghiChu_itDV,tvgiaSALE_itDV;
        ImageView img_itDV,imgdelete_itDV;
        Button btndatlich_itDV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDV_itDV = itemView.findViewById(R.id.tvTenDV_itDV);
            tvloaiDV_itDV = itemView.findViewById(R.id.tvloaiDV_itDV);
            tvtrangThai_itDV = itemView.findViewById(R.id.tvtrangThai_itDV);
            tvghiChu_itDV = itemView.findViewById(R.id.tvghiChu_itDV);
            tvgiaDV_itDV = itemView.findViewById(R.id.tvgiaDV_itDV);
            tvgiaSALE_itDV = itemView.findViewById(R.id.tvgiaSALE_itDV);
            img_itDV = itemView.findViewById(R.id.img_itDV);
            imgdelete_itDV = itemView.findViewById(R.id.imgdelete_itDV);
            btndatlich_itDV = itemView.findViewById(R.id.btndatlich_itDV);
        }
    }
}
