
package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_khachHang.Activity_kh_ChiTietSP;

import java.util.ArrayList;
//DIch Vu SALE
public class DichVu_SL_NEW_ADAPTER extends RecyclerView.Adapter<DichVu_SL_NEW_ADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDAO dichVuDAO;

    public DichVu_SL_NEW_ADAPTER(Context context, ArrayList<DichVu> list) {
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
        holder.tvloaiDV_itDVSL.setVisibility(View.GONE);
        holder.tvTenDV_itDVSL.setText(dichVu.getTenDV());
        String ten =dichVu.getTenDV();
            if (ten.length()>20){
                String ten33 = ten.substring(0, 17);
                holder.tvTenDV_itDVSL.setText(ten33+"...");
            }else {
                holder.tvTenDV_itDVSL.setText(list.get(position).getTenDV());
            }
        holder.img_itDVSL.setImageURI(dichVu.getHinhAnh());
        holder.tvloaiDV_itDVSL.setText("Loại: "+dichVu.getLoaiDV());
        holder.tvtrangThai_itDVSL.setText("Dịch vụ "+dichVu.getTrangThai());
        if (dichVu.getTrangThai().equals("NEW")||dichVu.getTrangThai().equals("KHONG")){
            holder.tvgiaSALE_itDVSL.setVisibility(View.GONE);
            holder.tvgiaDV_itDVSL.setPaintFlags(holder.tvgiaDV_itDVSL.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvgiaDV_itDVSL.setText(String.valueOf("Giá: " +dichVu.getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDVSL.setText(String.valueOf("Giá SALE: " +dichVu.getGiaSALE())+" VNĐ");
        }else {
            holder.tvgiaSALE_itDVSL.setVisibility(View.VISIBLE);
            holder.tvgiaDV_itDVSL.setPaintFlags(holder.tvgiaDV_itDVSL.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvgiaDV_itDVSL.setText(String.valueOf("Giá gốc: " +dichVu.getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDVSL.setText(String.valueOf("Giá SALE: " +dichVu.getGiaSALE())+" VNĐ");
        }
        String ghiChu = dichVu.getGhiChu();
        if (ghiChu.length() > 40) {
            String first40Characters = ghiChu.substring(0, 40);
            holder.tvghiChu_itDVSL.setText(first40Characters +"...");
        } else {
            holder.tvghiChu_itDVSL.setText(dichVu.getGhiChu());
        }

        if (dichVu.getGhiChu().length() > 40){
            holder.tvghiChu_itDVSL.setVisibility(View.GONE);
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

