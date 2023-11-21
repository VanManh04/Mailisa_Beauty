package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class TaiKhoanADAPTER extends RecyclerView.Adapter<TaiKhoanADAPTER.viewholder> {
    private final Context context;
    private final ArrayList<TaiKhoan> list;
    ArrayList<TaiKhoan> listNV = new ArrayList<>();
    TaiKhoanDAO taiKhoanDAO;
    public TaiKhoanADAPTER(Context context, ArrayList<TaiKhoan> list) {
        this.context = context;
        this.list = list;
        taiKhoanDAO = new TaiKhoanDAO(context);
    }

    public TaiKhoanADAPTER(Context context, ArrayList<TaiKhoan> list,ArrayList<TaiKhoan> listNV) {
        this.context = context;
        this.list = list;
        this.listNV = listNV;
        taiKhoanDAO = new TaiKhoanDAO(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nhanvien,null);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.txtSDT_itNV.setText("SĐT: "+list.get(position).getSdt());
        holder.txthoTen_itNV.setText("Họ tên: " + list.get(position).getHoTen());
        holder.txtmatKhau_itNV.setText(String.valueOf(list.get(position).getMatKhau()));
        holder.txtmatKhau_itNV.setText("Mật khẩu: "+"*********");
        TaiKhoan taiKhoan = list.get(position);

        holder.btndelete_itNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // tạo đối tượng
                builder.setTitle("Cảnh báo"); // set tiêu đề
                builder.setIcon(R.drawable.icon_warning); // set icon
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (taiKhoanDAO.delete(taiKhoan.getMa_TK())>0) {
                            list.clear();
                            list.addAll(taiKhoanDAO.getAllNV());
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

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                opendialogsua(taiKhoan);
                return true;
            }
        });
    }
    public void opendialogsua(TaiKhoan taiKhoan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nhanvien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();


        EditText edsdt_DLNV = view.findViewById(R.id.edsdt_DLNV);
        EditText edhoTen_DLNV = view.findViewById(R.id.edhoTen_DLNV);
        EditText edmatKhau_DLNV = view.findViewById(R.id.edmatKhau_DLNV);
        EditText ednhapLaiMatKhau_DLNV = view.findViewById(R.id.ednhapLaiMatKhau_DLNV);
        Button btnSaveSach = view.findViewById(R.id.btnSaveSach);
        Button btnCancelSach = view.findViewById(R.id.btnCancelSach);


        edsdt_DLNV.setText(taiKhoan.getSdt());
        edhoTen_DLNV.setText(taiKhoan.getHoTen());
        edmatKhau_DLNV.setText(taiKhoan.getMatKhau());
        // Ẩn ednhapLaiMatKhau_DLNV
        ednhapLaiMatKhau_DLNV.setVisibility(View.GONE);
        btnSaveSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edsdt_DLNV.getText().toString();
                String ten = edhoTen_DLNV.getText().toString();
                String matkhau = edmatKhau_DLNV.getText().toString();
                if (sdt.length() == 10 || sdt.length() == 11) {
                    if (sdt.trim().isEmpty() || ten.trim().isEmpty() || matkhau.trim().isEmpty()) {
                        Toast.makeText(context, "Không đuợc bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                    } else {
                        taiKhoan.setSdt(sdt);
                        taiKhoan.setHoTen(ten);
                        taiKhoan.setMatKhau(matkhau);
                        taiKhoan.setChucVu("NV");

                        if (taiKhoanDAO.update(taiKhoan) > 0) {
                            list.clear();
                            list.addAll(taiKhoanDAO.getAllNV());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(context, "Số điện thoại phải có 10 hoặc 11 số !", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnCancelSach.setOnClickListener(new View.OnClickListener() {
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

    public static class viewholder extends RecyclerView.ViewHolder {
        TextView txtSDT_itNV, txthoTen_itNV, txtmatKhau_itNV;
        Button btnupdate_itNV, btndelete_itNV;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            txtSDT_itNV = itemView.findViewById(R.id.txtSDT_itNV);
            txthoTen_itNV = itemView.findViewById(R.id.txthoTen_itNV);
            txtmatKhau_itNV = itemView.findViewById(R.id.txtmatKhau_itNV);
            btndelete_itNV = itemView.findViewById(R.id.btndelete_itNV);
            btnupdate_itNV = itemView.findViewById(R.id.btnupdate_itNV);
        }

    }
}
