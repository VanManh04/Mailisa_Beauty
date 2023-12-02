//package com.example.mailisa_beauty.ADAPTER;
//
//public class HoaDon_QL_ADAPTER {
//}
package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.FeedBackDAO;
import com.example.mailisa_beauty.DAO.HoaDonDAO;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.FeedBack;
import com.example.mailisa_beauty.Model.HoaDon;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoaDon_QL_ADAPTER extends RecyclerView.Adapter<HoaDon_QL_ADAPTER.ViewHolder>{
    private final Context context;
    private final ArrayList<HoaDon> list;
    HoaDonDAO hoaDonDAO;
    LichKhachHang_DAO lichKhachHangDao;
    TaiKhoanDAO taiKhoanDAO;
    DichVuDAO dichVuDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public HoaDon_QL_ADAPTER(Context context, ArrayList<HoaDon> list) {
        this.context = context;
        this.list = list;
        hoaDonDAO = new HoaDonDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoadon, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoaDon hoaDon = list.get(position);
        lichKhachHangDao = new LichKhachHang_DAO(context);
        taiKhoanDAO = new TaiKhoanDAO(context);
        dichVuDAO = new DichVuDAO(context);

        int maLKH = hoaDon.getMaLKH();
        LichKhachHang lichKhachHang = lichKhachHangDao.getByMaLKH(maLKH);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichKhachHang.getMaTK()));
        DichVu dichVu = dichVuDAO.getID(String.valueOf(lichKhachHang.getMaDV()));
//        textViewTenKhachHang,textViewTenDichVu,textViewngayThanhToan,textViewTongTien
        holder.textViewTenKhachHang.setText("Tên khách hàng: "+taiKhoan.getHoTen());

        holder.textViewTenDichVu.setText("Tên dịch vụ: "+dichVu.getTenDV());
        holder.textViewngayThanhToan.setText("Ngày thanh toán: "+sdf.format(hoaDon.getNgayTT()));

        if (dichVu.getGiaSALE()<dichVu.getGiaDV()){
//            hoaDon.setTongTien(dichVu.getGiaSALE());
            holder.textViewTongTien.setText("Tổng thanh toán: "+dichVu.getGiaSALE()+"VNĐ");
        }else {
//            hoaDon.setTongTien(dichVu.getGiaDV());
            holder.textViewTongTien.setText("Tổng thanh toán: "+dichVu.getGiaDV()+"VNĐ");
        }
        holder.textViewPTTT.setText("PTTT: "+lichKhachHang.getPTTT());
        holder.textViewGhiChu.setText("Ghi chú: "+hoaDon.getGhiChu());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                opendialogsua(hoaDon);
                return true;
            }
        });
    }
    public void opendialogsua(HoaDon hoaDon) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_hoadon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText editTexttenkh = view.findViewById(R.id.editTexttenkh);
        EditText editTextdichvu = view.findViewById(R.id.editTextdichvu);
        EditText editTextngaythanhtoan = view.findViewById(R.id.editTextngaythanhtoan);
        EditText editTexttongtien = view.findViewById(R.id.editTexttongtien);
        EditText editTextngayTT =view.findViewById(R.id. editTextngayTT);
        EditText editTextGhiChu = view.findViewById(R.id.editTextGhiChu);
        EditText editTextPTTT = view.findViewById(R.id.editTextPTTT);
        Button buttonSave111 = view.findViewById(R.id.buttonSave111);
        Button buttoncance111 = view.findViewById(R.id.buttoncance111);

        int maLKH1 = hoaDon.getMaLKH();
        LichKhachHang lichKhachHang = lichKhachHangDao.getByMaLKH(maLKH1);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichKhachHang.getMaTK()));
        DichVu dichVu = dichVuDAO.getID(String.valueOf(lichKhachHang.getMaDV()));

        editTexttenkh.setText(taiKhoan.getHoTen());
        editTextdichvu.setText(dichVu.getTenDV());
        editTextngaythanhtoan.setText(sdf.format(lichKhachHang.getNgayDat()));
//        editTexttongtien.setText(String.valueOf(dichVu.getGiaDV()));
        if (dichVu.getGiaSALE()<dichVu.getGiaDV()){
            editTexttongtien.setText(String.valueOf(dichVu.getGiaSALE()));
        }else {
            editTexttongtien.setText(String.valueOf(dichVu.getGiaDV()));
        }
        editTextngayTT.setText(sdf.format(hoaDon.getNgayTT()));
        editTextPTTT.setText(lichKhachHang.getPTTT());
        editTextGhiChu.setText(hoaDon.getGhiChu());
        buttonSave111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ghichu = editTextGhiChu.getText().toString();
                hoaDon.setGhiChu(ghichu);
                if (hoaDonDAO.update(hoaDon) > 0) {
                    list.clear();
                    list.addAll(hoaDonDAO.getAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttoncance111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTenKhachHang,textViewTenDichVu,textViewngayThanhToan,textViewTongTien,textViewGhiChu,textViewPTTT,textViewngaytt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTenKhachHang = itemView.findViewById(R.id.textViewTenKhachHang);
            textViewTenDichVu = itemView.findViewById(R.id.textViewTenDichVu);
            textViewngayThanhToan = itemView.findViewById(R.id.textViewngayThanhToan);
            textViewTongTien = itemView.findViewById(R.id.textViewTongTien);
            textViewGhiChu = itemView.findViewById(R.id.textViewGhiChu);
            textViewPTTT = itemView.findViewById(R.id.textViewPTTT);
        }
    }
}
