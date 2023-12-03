package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.LichLamViecDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichLamViec;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LichLamViecADAPTER extends RecyclerView.Adapter<LichLamViecADAPTER.viewholder>{
    private final Context context;
    private final ArrayList<LichLamViec> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    TaiKhoanDAO taiKhoanDAO;
    LichLamViecDAO lichLamViecDAO;
    int mYear,mMonth,mDay;

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

        holder.btndelete_itLLV.setVisibility(View.GONE);
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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String DATA_MATK = preferences.getString("DATA_MATK", "null");
        TaiKhoan taiKhoan1 = taiKhoanDAO.getID(DATA_MATK);
        if (taiKhoan1.getChucVu().equals("QL")){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opendialogsua(lichLamViec);
                }
            });
        }else {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

    }

    public void opendialogsua(LichLamViec lichLamViec) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_lichlamviec, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        Spinner spnNV = view.findViewById(R.id.spnNV);

        TextView tenNhanVien_diaLongLLV = view.findViewById(R.id.tenNhanVien_diaLongLLV);
        spnNV.setVisibility(View.GONE);
        tenNhanVien_diaLongLLV.setVisibility(View.VISIBLE);
        EditText edngayBatDau_DLLLV = view.findViewById(R.id.edngayBatDau_DLLLV);
        EditText edca_DLLLV = view.findViewById(R.id.edca_DLLLV);
        EditText edghiChu_DLLLV = view.findViewById(R.id.edghiChu_DLLLV);
        Button btnSave_DLLLV = view.findViewById(R.id.btnSave_DLLLV);
        Button btnCancel_DLLLV = view.findViewById(R.id.btnCancel_DLLLV);

        TaiKhoan taiKhoan= taiKhoanDAO.getID(String.valueOf(lichLamViec.getMaTK()));
        tenNhanVien_diaLongLLV.setText(taiKhoan.getHoTen());
        edngayBatDau_DLLLV.setText(sdf.format(lichLamViec.getNgayBatDau()));
        edca_DLLLV.setText(lichLamViec.getCa());
        edghiChu_DLLLV.setText(lichLamViec.getGhiChu());
        DatePickerDialog.OnDateSetListener ngayDat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar gcalender = new GregorianCalendar(mYear,mMonth,mDay);
                edngayBatDau_DLLLV.setText(sdf.format(gcalender.getTime()));
            }
        };
        edngayBatDau_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(context,0,ngayDat,mYear,mMonth,mDay);
                dialog.show();
            }
        });
        edca_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
                builder1.setTitle("Ca làm việc:");
                String[] ca1 = {"Sáng", "Chiều"};
                builder1.setItems(ca1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edca_DLLLV.setText(ca1[which]);
                    }
                });
                android.app.AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });
        btnSave_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String tennv = edten_DLLLV.getText().toString();
                String ngaybatdau = edngayBatDau_DLLLV.getText().toString();
                String ca = edca_DLLLV.getText().toString();
                String ghichu = edghiChu_DLLLV.getText().toString();
                try {
                    lichLamViec.setNgayBatDau(sdf.parse(ngaybatdau));
                } catch (ParseException e) {
//                    Toast.makeText(getActivity(), "Lỗi !", Toast.LENGTH_SHORT).show();
                }
                lichLamViec.setCa(ca);
                lichLamViec.setGhiChu(ghichu);
                if (ngaybatdau.trim().isEmpty() || ca.trim().isEmpty() || ghichu.trim().isEmpty()){
                    Toast.makeText(context, "Không được bỏ trống thông tin !", Toast.LENGTH_SHORT).show();
                }else {
                    if (lichLamViecDAO.update(lichLamViec)>0){
                        list.clear();
                        list.addAll(lichLamViecDAO.getAll());
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel_DLLLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngaybatdau = edngayBatDau_DLLLV.getText().toString();
                String ca = edca_DLLLV.getText().toString();
                String ghichu = edghiChu_DLLLV.getText().toString();
                if (ngaybatdau.trim().isEmpty() && ca.trim().isEmpty() && ghichu.trim().isEmpty()){
                    dialog.dismiss();
                }else {
                    edngayBatDau_DLLLV.setText("");
                    edca_DLLLV.setText("");
                    edghiChu_DLLLV.setText("");
                }
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
