//package com.example.mailisa_beauty.ADAPTER;
//
//public class LichKhachHang_KH_ADAPTER {
//}
package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.FeedBack;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHchapNhan;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHchuaChapNhan;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHhoanThanh;
import com.example.mailisa_beauty.frg_quanLy.QL_LKHhuy;
import com.example.mailisa_beauty.frg_quanLy.QL_lichKhachHangTatCa;

import java.text.SimpleDateFormat;
import java.util.List;

public class LichKhachHang_KH_ADAPTER extends RecyclerView.Adapter<LichKhachHang_KH_ADAPTER.ViewHolder> {

    private final Context context;
    private final List<LichKhachHang> list;
    private final LichKhachHang_DAO lichKhachHangDAO;
    TaiKhoanDAO taiKhoanDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    FeedBackDAO feedBackDAO ;
    DichVuDAO dichVuDAO;

    public LichKhachHang_KH_ADAPTER(Context context, List<LichKhachHang> list) {
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
        feedBackDAO = new FeedBackDAO(context);
        holder.btnxacnhan_itLDKH.setVisibility(View.GONE);
        holder.btnhoanthanh_itLDKH.setVisibility(View.GONE);
        LichKhachHang lichKhachHang = list.get(position);
        int maTK = lichKhachHang.getMaTK();
        taiKhoanDAO = new TaiKhoanDAO(context);
        dichVuDAO= new DichVuDAO(context);
        DichVu dichVu = dichVuDAO.getID(String.valueOf(lichKhachHang.getMaDV()));
        TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(maTK));
        holder.hoTenLichKhachHang_itLDKH.setText("Họ và tên: " + taiKhoan.getHoTen());
        holder.sdtLichKhachHang_itLDKH.setText("SĐT: "+taiKhoan.getSdt());
        holder.dichVuLichKhachHang_itLDKH.setText("Dịch vụ: "+dichVu.getTenDV());
        holder.ngayDatLichKhachHang_itLDKH.setText("Ngày đặt: " + sdf.format(lichKhachHang.getNgayDat()));
        holder.gioDatLichKhachHang_itLDKH.setText("Giờ đặt: " + lichKhachHang.getGioDat());
        holder.ptttLichKhachHang_itLDKH.setText("PTTT: " + lichKhachHang.getPTTT());
        holder.trangThaiLichKhachHang_itLDKH.setText("Trạng thái: " + lichKhachHang.getTrangThai());
        holder.ghiChuLichKhachHang_itLDKH.setText("Ghi chú: " + lichKhachHang.getGhiChu());
        holder.feedbackLichKhachHang_itLDKH.setText("Đánh giá: " + lichKhachHang.getFeedBack());

//        if (lichKhachHang.getFeedBack().length()>1){
//            holder.btndanhgia_itLDKH.setVisibility(View.GONE);
//        }else if (lichKhachHang.getTrangThai().equals("Xác nhận")){
//            holder.btndanhgia_itLDKH.setVisibility(View.VISIBLE);
//        }else {
//            holder.btndanhgia_itLDKH.setVisibility(View.GONE);
//        }

        if (lichKhachHang.getTrangThai().equals("Hoàn thành")){
            holder.btndanhgia_itLDKH.setVisibility(View.VISIBLE);
        }else {
            holder.btndanhgia_itLDKH.setVisibility(View.GONE);
        }

        holder.btnhuy_itLDKH.setVisibility(View.GONE);
        if (lichKhachHang.getTrangThai().equals("Đang chờ")){
            holder.btnhuy_itLDKH.setVisibility(View.VISIBLE);
        }else {
            holder.btnhuy_itLDKH.setVisibility(View.GONE);
        }

        if (lichKhachHang.getFeedBack().length()>1){
            holder.btndanhgia_itLDKH.setVisibility(View.GONE);
        }
        if (dichVu.getTrangThai().equals("SALE")){
            holder.tongTienLichKhachHang_itLDKH.setText("Tổng thanh toán: "+dichVu.getGiaSALE()+" VNĐ");
        }else {
            holder.tongTienLichKhachHang_itLDKH.setText("Tổng thanh toán: "+dichVu.getGiaDV()+" VNĐ");
        }

//        holder.btndelete_itLDKH.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Cảnh báo");
//                builder.setIcon(R.drawable.icon_warning);
//                builder.setMessage("Bạn có muốn xóa không?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (lichKhachHangDAO.delete(lichKhachHang.getMaLKH()) > 0) {
//                            list.remove(position);
//                            notifyDataSetChanged();
//                            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        holder.btnhuy_itLDKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichKhachHang.setTrangThai("Bị hủy");
                if (lichKhachHangDAO.update(lichKhachHang)>0){
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã hủy lịch !", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder.btnxacnhan_itLDKH.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                opendialogGhiChu(lichKhachHang);
//                lichKhachHang.setTrangThai("Xác nhận");
//                if (lichKhachHangDAO.update(lichKhachHang)>0){
//                    notifyDataSetChanged();
//                    Toast.makeText(context, "Đã xác nhận !", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        if (lichKhachHang.getTrangThai().equals("Xác nhận")){
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.cam));
        }else if (lichKhachHang.getTrangThai().equals("Bị hủy")){
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.red));
        }else if (lichKhachHang.getTrangThai().equals("Hoàn thành")){
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.xanh));
        }else {
            holder.trangThaiLichKhachHang_itLDKH.setTextColor(context.getResources().getColor(R.color.vang));
        }

        holder.btndanhgia_itLDKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogDanhGia(lichKhachHang);
//                    holder.btndanhgia_itLDKH.setVisibility(View.GONE);
                notifyDataSetChanged();

            }
        });
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
                tongTienLichKhachHang_itLDKH,sdtLichKhachHang_itLDKH,
                feedbackLichKhachHang_itLDKH,dichVuLichKhachHang_itLDKH;
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
            dichVuLichKhachHang_itLDKH = itemView.findViewById(R.id.dichVuLichKhachHang_itLDKH);
            btnxacnhan_itLDKH = itemView.findViewById(R.id.btnxacnhan_itLDKH);
            btnhuy_itLDKH = itemView.findViewById(R.id.btnhuy_itLDKH);
            btndanhgia_itLDKH = itemView.findViewById(R.id.btndanhgia_itLDKH);
            tongTienLichKhachHang_itLDKH = itemView.findViewById(R.id.tongTienLichKhachHang_itLDKH);
            sdtLichKhachHang_itLDKH = itemView.findViewById(R.id.sdtLichKhachHang_itLDKH);
            btnhoanthanh_itLDKH = itemView.findViewById(R.id.btnhoanthanh_itLDKH);
        }
    }

    // Trong phương thức opendialogsua
    private void opendialogDanhGia(LichKhachHang lichKhachHang) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_danhgia_phanhoi_kh, null);
        builder.setView(view);

        RatingBar ratingBar = view.findViewById(R.id.ratingBar_danhGia);
        EditText edDanhGia = view.findViewById(R.id.edDanhGia_danhGia);
        Button btnSubmit = view.findViewById(R.id.btnSubmit_danhGia);
        Button btnesc_danhGia = view.findViewById(R.id.btnesc_danhGia);


        AlertDialog dialog = builder.create();
        dialog.show();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float danhGia = ratingBar.getRating();
                String ghichuKH = edDanhGia.getText().toString();
                //add list đánh giá
                FeedBack feedBack = new FeedBack();
                feedBack.setMaLKH(lichKhachHang.getMaLKH());
                feedBack.setSoSao(danhGia);
                feedBack.setGhiChuKH(ghichuKH);
                feedBack.setGhiChuQL("");
                if (feedBackDAO.insert(feedBack)>0){
                    Toast.makeText(context, "Gửi đánh giá thành công!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                }
                // Lưu đánh giá và nhận xét vào đối tượng LichKhachHang
                lichKhachHang.setFeedBack(ghichuKH);

                // Cập nhật vào cơ sở dữ liệu
                if (lichKhachHangDAO.update(lichKhachHang) > 0) {
                    notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
        btnesc_danhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
