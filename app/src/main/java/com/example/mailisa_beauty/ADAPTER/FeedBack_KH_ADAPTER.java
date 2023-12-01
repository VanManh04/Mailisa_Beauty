package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.FeedBackDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.FeedBack;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class FeedBack_KH_ADAPTER extends RecyclerView.Adapter<FeedBack_KH_ADAPTER.ViewHolder>{
    private final Context context;
    private final ArrayList<FeedBack> list;
    FeedBackDAO feedBackDAO;
    LichKhachHang_DAO lichKhachHangDao;
    TaiKhoanDAO taiKhoanDAO;

    public FeedBack_KH_ADAPTER(Context context, ArrayList<FeedBack> list) {
        this.context = context;
        this.list = list;
        feedBackDAO = new FeedBackDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_feedback, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ratingBar_ITFB.setIsIndicator(true);
        lichKhachHangDao = new LichKhachHang_DAO(context);
        taiKhoanDAO = new TaiKhoanDAO(context);
        FeedBack feedBack = list.get(position);
        int maLKH = feedBack.getMaLKH();
        float soSao = feedBack.getSoSao();
        String ghiChuKH = feedBack.getGhiChuKH();
        String ghiChuQL = feedBack.getGhiChuQL();

        LichKhachHang lichKhachHang = lichKhachHangDao.getByMaLKH(maLKH);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichKhachHang.getMaTK()));

        holder.texttenkh_ITFB.setText(taiKhoan.getHoTen());
        holder.ratingBar_ITFB.setRating(soSao);
        holder.ghiChuKH_ITFB.setText(ghiChuKH);
        holder.ghiChuQL_ITFB.setText(ghiChuQL);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttenkh_ITFB,ghiChuKH_ITFB,ghiChuQL_ITFB;
        RatingBar ratingBar_ITFB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            texttenkh_ITFB = itemView.findViewById(R.id.texttenkh_ITFB);
            ghiChuKH_ITFB = itemView.findViewById(R.id.ghiChuKH_ITFB);
            ghiChuQL_ITFB = itemView.findViewById(R.id.ghiChuQL_ITFB);
            ratingBar_ITFB = itemView.findViewById(R.id.ratingBar_ITFB);
        }
    }
}
