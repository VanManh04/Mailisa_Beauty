package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.LichLamViecDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichLamViec;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LichLamViecADAPTER extends RecyclerView.Adapter<LichLamViecADAPTER.viewholder>{
    private final Context context;
    private final ArrayList<LichLamViec> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    TaiKhoanDAO taiKhoanDAO;
    LichLamViecDAO lichLamViecDAO;

    public LichLamViecADAPTER(Context context, ArrayList<LichLamViec> list) {
        this.context = context;
        this.list = list;
        lichLamViecDAO = new LichLamViecDAO(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichlamviec,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        LichLamViec lichLamViec = list.get(position);
//        NHAAN VIEEN SPINER
        taiKhoanDAO = new TaiKhoanDAO(context);
        TaiKhoan taiKhoan = taiKhoanDAO.getID(String.valueOf(lichLamViec.getMaTK()));
        holder.txttenNV_itLLV.setText("Tên nhân viên: "+taiKhoan.getHoTen());
        holder.txtngaybatdau_itLLV.setText("Ngày bắt đầu: " + sdf.format(lichLamViec.getNgayBatDau()));
        holder.txtca_itLLV.setText("Ca: "+ lichLamViec.getCa());
        holder.txtghichu_itLLV.setText("Ghi chú: "+lichLamViec.getGhiChu());

        holder.btndelete_itLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // tạo đối tượng
                builder.setTitle("Cảnh báo"); // set tiêu đề
                builder.setIcon(R.drawable.icon_warning); // set icon
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (lichLamViecDAO.delete(lichLamViec.getMaLLV())>0) {
                            list.clear();
                            list.addAll(lichLamViecDAO.getAll());
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView txttenNV_itLLV, txtngaybatdau_itLLV, txtca_itLLV,txtghichu_itLLV;
        ImageView btndelete_itLLV;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            txttenNV_itLLV = itemView.findViewById(R.id.txttenNV_itLLV);
            txtngaybatdau_itLLV = itemView.findViewById(R.id.txtngaybatdau_itLLV);
            txtca_itLLV = itemView.findViewById(R.id.txtca_itLLV);
            txtghichu_itLLV = itemView.findViewById(R.id.txtghichu_itLLV);
            btndelete_itLLV = itemView.findViewById(R.id.btndelete_itLLV);
        }

    }
}
