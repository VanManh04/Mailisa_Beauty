//package com.example.mailisa_beauty.ADAPTER;
//
//public class DichVuKHDatLich_ADAPTER {
//}

package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.DichVuTrongGio_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.DichVuTrongGio;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_khachHang.Activity_kh_ChiTietSP;

import java.util.ArrayList;

public class DichVuKHDatLich_ADAPTER extends RecyclerView.Adapter<DichVuKHDatLich_ADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVuTrongGio> list;
    private String dataMaTK="null";
    DichVuTrongGio_DAO dichVuTrongGio_dao;
    DichVuDAO dichVuDAO;

    public DichVuKHDatLich_ADAPTER(Context context, ArrayList<DichVuTrongGio> list) {
        this.context = context;
        this.list = list;
        dichVuTrongGio_dao = new DichVuTrongGio_DAO(context);
        dichVuDAO = new DichVuDAO(context);
    }
    public DichVuKHDatLich_ADAPTER(Context context, ArrayList<DichVuTrongGio> list,String dataMaTK) {
        this.context = context;
        this.list = list;
        this.dataMaTK = dataMaTK;
        dichVuTrongGio_dao = new DichVuTrongGio_DAO(context);
        dichVuDAO = new DichVuDAO(context);
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dichvutronggio, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        tvTenDV_itDVTG,soLuong_itDVTG,tvgiaDV_itDVTG,tvgiaSALE_itDVTG;
        holder.imgdelete_itDVTG.setVisibility(View.GONE);
        holder.checkbox_itDVTG.setVisibility(View.GONE);
        holder.slCong_itDVTG.setVisibility(View.GONE);
        holder.slTru_itDVTG.setVisibility(View.GONE);

        DichVuTrongGio dichVuTrongGio = list.get(position);
        DichVu dichVu = dichVuDAO.getID(String.valueOf(dichVuTrongGio.getMaDV()));

        holder.img_itDVTG.setImageURI(dichVu.getHinhAnh());

        String ten = dichVu.getTenDV();
        if (ten.length() > 17) {
            String ten17 = ten.substring(0, 14);
            holder.tvTenDV_itDVTG.setText(ten17 + "...");
        } else {
            holder.tvTenDV_itDVTG.setText(dichVu.getTenDV());
        }
        holder.soLuong_itDVTG.setText(String.valueOf(dichVuTrongGio.getSoLuong()));
        if (dichVuTrongGio.isCheck()==true){
            holder.checkbox_itDVTG.setChecked(true);
        }else{
            holder.checkbox_itDVTG.setChecked(false);
        }

        holder.checkbox_itDVTG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    dichVuTrongGio.setCheck(true);
                    holder.checkbox_itDVTG.setChecked(true);
                }else{
                    dichVuTrongGio.setCheck(false);
                    holder.checkbox_itDVTG.setChecked(false);
                }
                if (dichVuTrongGio_dao.updateCheck(dichVuTrongGio) > 0) {
                    list.clear();
                    list.addAll(dichVuTrongGio_dao.getAllByMaTK(dataMaTK));
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder.slCong_itDVTG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int slcong = list.get(position).getSoLuong();
//                slcong++;
//                holder.soLuong_itDVTG.setText(String.valueOf(slcong));
//                dichVuTrongGio.setSoLuong(slcong);
//                if (dichVuTrongGio_dao.update(dichVuTrongGio) > 0) {
//                    list.clear();
//                    list.addAll(dichVuTrongGio_dao.getAllByMaTK(dataMaTK));
//                    notifyDataSetChanged();
////                    Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        holder.slTru_itDVTG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int sltru = list.get(position).getSoLuong();
//                sltru--;
//                if (sltru < 1) {
//                    Toast.makeText(context, "Số người phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
//                } else {
//                    holder.soLuong_itDVTG.setText(String.valueOf(sltru));
//                    dichVuTrongGio.setSoLuong(sltru);
//                    if (dichVuTrongGio_dao.update(dichVuTrongGio) > 0) {
//                        list.clear();
//                        list.addAll(dichVuTrongGio_dao.getAllByMaTK(dataMaTK));
//                        notifyDataSetChanged();
////                    Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
        if (dichVu.getTrangThai().equals("NEW")||dichVu.getTrangThai().equals("KHONG")){
            holder.tvgiaSALE_itDVTG.setVisibility(View.GONE);
            holder.tvgiaDV_itDVTG.setPaintFlags(holder.tvgiaDV_itDVTG.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvgiaDV_itDVTG.setText(String.valueOf("Giá: " +dichVu.getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDVTG.setText(String.valueOf("Giá SALE: " +dichVu.getGiaSALE())+" VNĐ");
        }else {
            holder.tvgiaSALE_itDVTG.setVisibility(View.VISIBLE);
            holder.tvgiaDV_itDVTG.setPaintFlags(holder.tvgiaDV_itDVTG.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvgiaDV_itDVTG.setText(String.valueOf("Giá gốc: " +dichVu.getGiaDV())+" VNĐ");
            holder.tvgiaSALE_itDVTG.setText(String.valueOf("Giá SALE: " +dichVu.getGiaSALE())+" VNĐ");
        }
        holder.imgdelete_itDVTG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // tạo đối tượng
                builder.setTitle("Cảnh báo"); // set tiêu đề
                builder.setIcon(R.drawable.icon_warning); // set icon
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dichVuTrongGio_dao.delete(dichVuTrongGio.getMaDVTG()) > 0) {
                            list.clear();
                            list.addAll(dichVuTrongGio_dao.getAllByMaTK(dataMaTK));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                // tạo dialog, show hộp thoại
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String masp = ""+list.get(position).getMaDV();
//                String anh = String.valueOf(list.get(position).getHinhAnh());
//                String ten = list.get(position).getTenDV();
//                String gia =""+ list.get(position).getGiaSALE();
//                String loai = list.get(position).getLoaiDV();
//                String mota = list.get(position).getGhiChu();
//
//
//                Context context = view.getContext();
//                Intent intent = new Intent(context, Activity_kh_ChiTietSP.class);
//                intent.putExtra("masp", masp);
//                intent.putExtra("anh",anh);
//                intent.putExtra("ten", ten);
//                intent.putExtra("gia", gia);
//                intent.putExtra("loai",loai);
//                intent.putExtra("mota", mota);
//
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDV_itDVTG, soLuong_itDVTG, tvgiaDV_itDVTG, tvgiaSALE_itDVTG;
        ImageView img_itDVTG, slCong_itDVTG, slTru_itDVTG, imgdelete_itDVTG;
        CheckBox checkbox_itDVTG;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDV_itDVTG = itemView.findViewById(R.id.tvTenDV_itDVTG);
            slCong_itDVTG = itemView.findViewById(R.id.slCong_itDVTG);
            soLuong_itDVTG = itemView.findViewById(R.id.soLuong_itDVTG);
            slTru_itDVTG = itemView.findViewById(R.id.slTru_itDVTG);
            tvgiaDV_itDVTG = itemView.findViewById(R.id.tvgiaDV_itDVTG);
            tvgiaSALE_itDVTG = itemView.findViewById(R.id.tvgiaSALE_itDVTG);
            imgdelete_itDVTG = itemView.findViewById(R.id.imgdelete_itDVTG);
            img_itDVTG = itemView.findViewById(R.id.img_itDVTG);
            checkbox_itDVTG = itemView.findViewById(R.id.checkbox_itDVTG);
        }
    }
}
