package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_khachHang.Activity_kh_ChiTietSP;

import java.util.ArrayList;

public class DichVuKH_ADAPTER extends RecyclerView.Adapter<DichVuKH_ADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDAO dichVuDAO;
    DichVuTrongGio_DAO dichVuTrongGio_dao;

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
        holder.img_itDV.setImageURI(dichVu.getHinhAnh());
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
            holder.tvgiaDV_itDV.setPaintFlags(holder.tvgiaDV_itDV.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvgiaDV_itDV.setText(String.valueOf( list.get(position).getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDV.setText(String.valueOf("SALE: " +list.get(position).getGiaSALE())+" VNĐ");
        }else {
            holder.tvgiaSALE_itDV.setVisibility(View.VISIBLE);
            holder.tvgiaDV_itDV.setPaintFlags(holder.tvgiaDV_itDV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvgiaDV_itDV.setText(String.valueOf(list.get(position).getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDV.setText(String.valueOf(list.get(position).getGiaSALE())+" VNĐ");
        }
        String ghiChu = dichVu.getGhiChu();
        if (ghiChu.length() > 40) {
            String first40Characters = ghiChu.substring(0, 80);
            holder.tvghiChu_itDV.setText(first40Characters +"...");
        } else {
            holder.tvghiChu_itDV.setText(dichVu.getGhiChu());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maDV = ""+list.get(position).getMaDV();


                Context context = view.getContext();
                Intent intent = new Intent(context, Activity_kh_ChiTietSP.class);
                intent.putExtra("maDV", maDV);
                context.startActivity(intent);
            }
        });
        
        holder.btnthemdatlich_itDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                String DATA_MATK = preferences.getString("DATA_MATK", "null");
                dichVuTrongGio_dao = new DichVuTrongGio_DAO(context);
                DichVuTrongGio dichVuTrongGio = new DichVuTrongGio();
                dichVuTrongGio.setMaTK(Integer.parseInt(DATA_MATK));
                dichVuTrongGio.setMaDV(list.get(position).getMaDV());
                dichVuTrongGio.setSoLuong(1);
                dichVuTrongGio.setCheck(false);
                if (dichVuTrongGio_dao.insert(dichVuTrongGio)>0){
                    Toast.makeText(context, "Thêm vào giỏ dịch vụ thành công !", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDV_itDV, tvgiaDV_itDV,tvloaiDV_itDV,tvtrangThai_itDV,tvghiChu_itDV,tvgiaSALE_itDV;
        ImageView img_itDV,imgdelete_itDV;
        Button btnthemdatlich_itDV;

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
            btnthemdatlich_itDV = itemView.findViewById(R.id.btndatlich_itDV);
        }
    }
}
