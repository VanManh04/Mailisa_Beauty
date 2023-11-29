package com.example.mailisa_beauty.ADAPTER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.net.Uri;
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
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.R;

import java.util.ArrayList;

public class DichVuQL_ADAPTER extends RecyclerView.Adapter<DichVuQL_ADAPTER.ViewHolder> {
    private final Context context;
    private final ArrayList<DichVu> list;
    DichVuDAO dichVuDAO;

    public DichVuQL_ADAPTER(Context context, ArrayList<DichVu> list) {
        this.context = context;
        this.list = list;
        dichVuDAO = new DichVuDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dichvu, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btndatlich_itDV.setVisibility(View.GONE);
        holder.tvloaiDV_itDV.setVisibility(View.GONE);
        holder.tvtrangThai_itDV.setVisibility(View.GONE);

        DichVu dichVu = list.get(position);
        holder.img_itDV.setImageURI(dichVu.getHinhAnh());
        holder.tvTenDV_itDV.setText(list.get(position).getTenDV());
        holder.tvloaiDV_itDV.setText("Loại: " + list.get(position).getLoaiDV());
        holder.tvtrangThai_itDV.setText("Trạng thái: " + list.get(position).getTrangThai());

        if (dichVu.getTrangThai().equals("NEW") || dichVu.getTrangThai().equals("KHONG")) {
            holder.tvgiaSALE_itDV.setVisibility(View.GONE);
            holder.tvgiaDV_itDV.setPaintFlags(holder.tvgiaDV_itDV.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.tvgiaDV_itDV.setText(String.valueOf("Giá: " + list.get(position).getGiaDV()) + " VNĐ");
            holder.tvgiaSALE_itDV.setText(String.valueOf("Giá SALE: " + list.get(position).getGiaSALE()) + " VNĐ");
        } else {
            holder.tvgiaSALE_itDV.setVisibility(View.VISIBLE);
            holder.tvgiaDV_itDV.setPaintFlags(holder.tvgiaDV_itDV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvgiaDV_itDV.setText(String.valueOf("Giá gốc: " + list.get(position).getGiaDV()) + " VNĐ");
            holder.tvgiaSALE_itDV.setText(String.valueOf("Giá SALE: " + list.get(position).getGiaSALE()) + " VNĐ");
        }
        String ghiChu = dichVu.getGhiChu();
        if (ghiChu.length() > 40) {
            String first40Characters = ghiChu.substring(0, 80);
            holder.tvghiChu_itDV.setText(first40Characters + "...");
        } else {
            holder.tvghiChu_itDV.setText(dichVu.getGhiChu());
        }

        holder.imgdelete_itDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context); // tạo đối tượng
                builder.setTitle("Cảnh báo"); // set tiêu đề
                builder.setIcon(R.drawable.icon_warning); // set icon
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dichVuDAO.delete(dichVu.getMaDV()) > 0) {
                            list.clear();
                            list.addAll(dichVuDAO.getAll());
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
                opendialogUPDATE(dichVu);
                notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenDV_itDV, tvgiaDV_itDV, tvloaiDV_itDV, tvtrangThai_itDV, tvghiChu_itDV, tvgiaSALE_itDV, tvxemChiTiet_itDV;
        ImageView img_itDV, imgdelete_itDV;
        Button btndatlich_itDV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDV_itDV = itemView.findViewById(R.id.tvTenDV_itDV);
            tvloaiDV_itDV = itemView.findViewById(R.id.tvloaiDV_itDV);
            tvtrangThai_itDV = itemView.findViewById(R.id.tvtrangThai_itDV);
            tvgiaDV_itDV = itemView.findViewById(R.id.tvgiaDV_itDV);
            tvgiaSALE_itDV = itemView.findViewById(R.id.tvgiaSALE_itDV);
            tvxemChiTiet_itDV = itemView.findViewById(R.id.tvxemChiTiet_itDV);
            tvghiChu_itDV = itemView.findViewById(R.id.tvghiChu_itDV);
            img_itDV = itemView.findViewById(R.id.img_itDV);
            imgdelete_itDV = itemView.findViewById(R.id.imgdelete_itDV);
            btndatlich_itDV = itemView.findViewById(R.id.btndatlich_itDV);
        }
    }

    public void opendialogUPDATE(DichVu dichVu1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dichvu, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        ImageView imgDV_DLDV = view.findViewById(R.id.imgDV_DLDV);
        EditText edtenDV_DLDV = view.findViewById(R.id.edtenDV_DLDV);
        EditText edloai_DLDV = view.findViewById(R.id.edloai_DLDV);
        EditText edtrangThai_DLDV = view.findViewById(R.id.edtrangThai_DLDV);
        EditText edgiaDV_DLDV = view.findViewById(R.id.edgiaDV_DLDV);
        EditText edgiaSALE_DLDV = view.findViewById(R.id.edgiaSALE_DLDV);
        EditText edghiChu_DLDV = view.findViewById(R.id.edghiChu_DLDV);
        Button btnSave_DLNV = view.findViewById(R.id.btnSave_DLNV);
        Button btnCancel_DLNV = view.findViewById(R.id.btnCancel_DLNV);
        TextView hindgia = view.findViewById(R.id.hindgia);

        imgDV_DLDV.setImageURI(Uri.parse(String.valueOf(dichVu1.getHinhAnh())));

        edtenDV_DLDV.setText(dichVu1.getTenDV());

        String loaisettext = dichVu1.getLoaiDV();
        if (loaisettext.equals("PT")) {
            edloai_DLDV.setText("Phẫu thuật");
        } else if (loaisettext.equals("PS")) {
            edloai_DLDV.setText("Phun săm");
        } else if (loaisettext.equals("KHAC")) {
            edloai_DLDV.setText("Khác");
        } else {
            Toast.makeText(context, "Loại vô lý !", Toast.LENGTH_SHORT).show();
        }

        String trangThaiSetText = dichVu1.getTrangThai();
        if (trangThaiSetText.equals("SALE")) {
            edtrangThai_DLDV.setText("SALE");
        } else if (trangThaiSetText.equals("NEW")) {
            edtrangThai_DLDV.setText("NEW");
        } else if (trangThaiSetText.equals("KHONG")) {
            edtrangThai_DLDV.setText("Khác");
        } else {
            Toast.makeText(context, "Trạng thái vô lý !", Toast.LENGTH_SHORT).show();
        }

        edgiaDV_DLDV.setText(String.valueOf(dichVu1.getGiaDV()));
        edgiaSALE_DLDV.setText(String.valueOf(dichVu1.getGiaSALE()));
        edghiChu_DLDV.setText(dichVu1.getGhiChu());
        if (dichVu1.getTrangThai().equals("SALE")){
            edgiaDV_DLDV.setHint("Giá gốc");
            hindgia.setText("Giá gốc");
            edgiaSALE_DLDV.setVisibility(View.VISIBLE);
        }else {
            hindgia.setText("Giá");
            edgiaDV_DLDV.setHint("Giá");
            edgiaSALE_DLDV.setVisibility(View.GONE);
        }

        edloai_DLDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
                builder1.setTitle("Loại dịch vụ");
                String[] loai = {"Phẫu thuật", "Phun săm", "Khác"};
                builder1.setItems(loai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edloai_DLDV.setText(loai[which]);
                    }
                });
                android.app.AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });

        edtrangThai_DLDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
                builder1.setTitle("Trạng thái dịch vụ:");
                String[] trangThai = {"SALE", "NEW", "Không"};
                builder1.setItems(trangThai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtrangThai_DLDV.setText(trangThai[which]);

                        if (trangThai[which].equals("SALE")) {
                            hindgia.setText("Giá gốc");
                            edgiaDV_DLDV.setHint("Giá gốc");
                            edgiaSALE_DLDV.setVisibility(View.VISIBLE);
                        } else {
                            edgiaSALE_DLDV.setVisibility(View.GONE);
                            edgiaDV_DLDV.setHint("Giá");
                            hindgia.setText("Giá");
                        }
                    }
                });
                android.app.AlertDialog alertDialog = builder1.create();
                alertDialog.show();
            }
        });
        btnSave_DLNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDV = edtenDV_DLDV.getText().toString();
                String giaDV = edgiaDV_DLDV.getText().toString();
                String giaSALE = edgiaSALE_DLDV.getText().toString();
                String loaiDV = edloai_DLDV.getText().toString();
                String trangThai = edtrangThai_DLDV.getText().toString();
                String ghiChu = edghiChu_DLDV.getText().toString();

                if (tenDV.trim().isEmpty() || giaDV.trim().isEmpty() || loaiDV.trim().isEmpty() || trangThai.trim().isEmpty() || ghiChu.trim().isEmpty()) {
                    Toast.makeText(context, "Không đuợc bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    dichVu1.setTenDV(tenDV);
                    dichVu1.setGiaDV(Integer.parseInt(giaDV));
                    if (loaiDV.equals("Phẫu thuật")) {
                        dichVu1.setLoaiDV("PT");
                    } else if (loaiDV.equals("Phun săm")) {
                        dichVu1.setLoaiDV("PS");
                    } else if (loaiDV.equals("Khác")) {
                        dichVu1.setLoaiDV("KHAC");
                    } else {
                        Toast.makeText(context, "Loại vô lý !", Toast.LENGTH_SHORT).show();
                    }
                    if (trangThai.equals("SALE")) {
                        dichVu1.setTrangThai("SALE");
                        dichVu1.setGiaSALE(Integer.parseInt(giaSALE));
                    } else if (trangThai.equals("NEW")) {
                        dichVu1.setTrangThai("NEW");
                        dichVu1.setGiaSALE(dichVu1.getGiaDV());
                    } else if (trangThai.equals("Không")) {
                        dichVu1.setTrangThai("KHONG");
                        dichVu1.setGiaSALE(dichVu1.getGiaDV());
                    } else {
                        Toast.makeText(context, "Trạng thái vô lý !", Toast.LENGTH_SHORT).show();
                    }
                    dichVu1.setGhiChu(ghiChu);
                    if (dichVu1.getTrangThai().equals("SALE")){
                        if (Integer.parseInt(giaSALE)==0||Integer.parseInt(giaSALE)>Integer.parseInt(giaDV)){
                            Toast.makeText(context, "Giá SALE phải lớn hơn 0 và nhỏ hơn giá gốc !", Toast.LENGTH_SHORT).show();
                        }else {
                            if (dichVuDAO.update(dichVu1) > 0) {
                                list.clear();
                                list.addAll(dichVuDAO.getAll());
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else {
                        if (dichVuDAO.update(dichVu1) > 0) {
                            list.clear();
                            list.addAll(dichVuDAO.getAll());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại.", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            }
        });
        btnCancel_DLNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDV = edtenDV_DLDV.getText().toString();
                String giaDV = edgiaDV_DLDV.getText().toString();
                String loaiDV = edloai_DLDV.getText().toString();
                String trangThai = edtrangThai_DLDV.getText().toString();
                String ghiChu = edghiChu_DLDV.getText().toString();
                String giaSALE = edgiaSALE_DLDV.getText().toString();
                if (tenDV.trim().isEmpty() && giaDV.trim().isEmpty() && loaiDV.trim().isEmpty() && trangThai.trim().isEmpty() && ghiChu.trim().isEmpty()&& giaSALE.trim().isEmpty()) {
                    dialog.dismiss();
                } else {
                    edtenDV_DLDV.setText("");
                    edgiaDV_DLDV.setText("");
                    edghiChu_DLDV.setText("");
                    edloai_DLDV.setText("");
                    edtrangThai_DLDV.setText("");
                    edgiaSALE_DLDV.setText("");
                }
            }
        });
    }

}
