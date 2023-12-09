//package com.example.mailisa_beauty.ADAPTER;
//
//public class LichKhachHang_NV_ADAPTER {

package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LichKhachHang_NV_ADAPTER extends RecyclerView.Adapter<LichKhachHang_NV_ADAPTER.ViewHolder> {

    private final Context context;
    private final List<LichKhachHang> list;
    private final LichKhachHang_DAO lichKhachHangDAO;
    TaiKhoanDAO taiKhoanDAO;
    DichVuDAO dichVuDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public LichKhachHang_NV_ADAPTER(Context context, List<LichKhachHang> list) {
        this.context = context;
        this.list = list;
        this.lichKhachHangDAO = new LichKhachHang_DAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichkhachhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btndanhgia_itLDKH.setVisibility(View.GONE);
        dichVuDAO = new DichVuDAO(context);

        LichKhachHang lichKhachHang = list.get(position);
        DichVu dichVu = dichVuDAO.getID(String.valueOf(lichKhachHang.getMaDV()));
        int maTK = lichKhachHang.getMaTK();
        taiKhoanDAO = new TaiKhoanDAO(context);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(maTK));
        holder.hoTenLichKhachHang_itLDKH.setText("Họ và tên: " + taiKhoan.getHoTen());
        holder.sdtLichKhachHang_itLDKH.setText("SĐT: "+ taiKhoan.getSdt());
        holder.dichVuLichKhachHang_itLDKH.setText("Dịch vụ: "+ dichVu.getTenDV());
        holder.ngayDatLichKhachHang_itLDKH.setText("Ngày đặt: " + sdf.format(lichKhachHang.getNgayDat()));
        holder.gioDatLichKhachHang_itLDKH.setText("Giờ đặt: " + lichKhachHang.getGioDat());
        holder.ptttLichKhachHang_itLDKH.setText("PTTT: " + lichKhachHang.getPTTT());
        holder.trangThaiLichKhachHang_itLDKH.setText("Trạng thái: " + lichKhachHang.getTrangThai());
        holder.ghiChuLichKhachHang_itLDKH.setText("Ghi chú: " + lichKhachHang.getGhiChu());
        holder.feedbackLichKhachHang_itLDKH.setText("Đánh giá: " + lichKhachHang.getFeedBack());
        if (dichVu.getTrangThai().equals("SALE")){
            holder.tongTienLichKhachHang_itLDKH.setText("Tổng thanh toán: "+dichVu.getGiaSALE()+" VNĐ");
        }else {
            holder.tongTienLichKhachHang_itLDKH.setText("Tổng thanh toán: "+dichVu.getGiaDV()+" VNĐ");
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        String DATA_MATK = preferences.getString("DATA_MATK", "null");
//        TaiKhoan taiKhoan1 = taiKhoanDAO.getID(DATA_MATK);
//        if (taiKhoan1.getChucVu().equals("QL")){
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    return false;
//                }
//            });
//        }else {
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    holder.btnhuy_itLDKH.setVisibility(View.GONE);
//                    holder.btnxacnhan_itLDKH.setVisibility(View.GONE);
//                    holder.btnxacnhan_itLDKH.setVisibility(View.GONE);
//                    return false;
//                }
//            });
//        }
        if (lichKhachHang.getTrangThai().equals("Xác nhận")){
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.cam));
        }else if (lichKhachHang.getTrangThai().equals("Bị hủy")){
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.red));
        }else if (lichKhachHang.getTrangThai().equals("Hoàn thành")){
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.xanh));
        }else {
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.vang));
        }
        holder.btnhuy_itLDKH.setVisibility(View.GONE);
        holder.btnxacnhan_itLDKH.setVisibility(View.GONE);
        holder.btnhoanthanh_itLDKH.setVisibility(View.GONE);


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView hoTenLichKhachHang_itLDKH, ngayDatLichKhachHang_itLDKH, gioDatLichKhachHang_itLDKH,
                ptttLichKhachHang_itLDKH, trangThaiLichKhachHang_itLDKH, ghiChuLichKhachHang_itLDKH,
                feedbackLichKhachHang_itLDKH,dichVuLichKhachHang_itLDKH,tongTienLichKhachHang_itLDKH,
                sdtLichKhachHang_itLDKH;
        Button  btnxacnhan_itLDKH,btnhuy_itLDKH,btndanhgia_itLDKH,btnhoanthanh_itLDKH;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hoTenLichKhachHang_itLDKH = itemView.findViewById(R.id.hoTenLichKhachHang_itLDKH);
            ngayDatLichKhachHang_itLDKH = itemView.findViewById(R.id.ngayDatLichKhachHang_itLDKH);
            gioDatLichKhachHang_itLDKH = itemView.findViewById(R.id.gioDatLichKhachHang_itLDKH);
            ptttLichKhachHang_itLDKH = itemView.findViewById(R.id.ptttLichKhachHang_itLDKH);
            trangThaiLichKhachHang_itLDKH = itemView.findViewById(R.id.trangThaiLichKhachHang_itLDKH);
            ghiChuLichKhachHang_itLDKH = itemView.findViewById(R.id.ghiChuLichKhachHang_itLDKH);
            feedbackLichKhachHang_itLDKH = itemView.findViewById(R.id.feedbackLichKhachHang_itLDKH);
            btnxacnhan_itLDKH = itemView.findViewById(R.id.btnxacnhan_itLDKH);
            btnhuy_itLDKH = itemView.findViewById(R.id.btnhuy_itLDKH);
            btndanhgia_itLDKH = itemView.findViewById(R.id.btndanhgia_itLDKH);
            dichVuLichKhachHang_itLDKH = itemView.findViewById(R.id.dichVuLichKhachHang_itLDKH);
            tongTienLichKhachHang_itLDKH = itemView.findViewById(R.id.tongTienLichKhachHang_itLDKH);
            sdtLichKhachHang_itLDKH = itemView.findViewById(R.id.sdtLichKhachHang_itLDKH);
            btnhoanthanh_itLDKH = itemView.findViewById(R.id.btnhoanthanh_itLDKH);
        }
    }

    // Trong phương thức opendialogsua
    private void opendialogGhiChu(LichKhachHang lichKhachHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layghichu, null);
        builder.setView(view);

        EditText edGhiChu = view.findViewById(R.id.edGhiChu);
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        AlertDialog dialog = builder.create();
        dialog.show();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ghiChuMoi = edGhiChu.getText().toString();
                // Lưu ghi chú mới vào đối tượng LichKhachHang
                lichKhachHang.setGhiChu(ghiChuMoi);
                // Cập nhật vào cơ sở dữ liệu
                if (lichKhachHangDAO.update(lichKhachHang) > 0) {
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã gửi", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
