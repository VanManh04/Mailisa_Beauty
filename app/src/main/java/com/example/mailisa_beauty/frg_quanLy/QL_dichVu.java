package com.example.mailisa_beauty.frg_quanLy;

import static android.app.Activity.RESULT_OK;

import static com.gun0912.tedpermission.provider.TedPermissionProvider.context;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.DichVuQL_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.Model.DichVu;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.squareup.picasso.Picasso;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QL_dichVu extends Fragment {
    public QL_dichVu() {
        // Required empty public constructor
    }

    SwipeableRecyclerView rcvDichVu;
    Button fladdDV;
    DichVuDAO dichVuDAO;
    DichVuQL_ADAPTER dichVuADAPTER;
    private ArrayList<DichVu> list = new ArrayList<DichVu>();
    private static final int REQUEST_IMAGE_PICK = 1;
    DichVu dichVu = new DichVu();

    ImageView imgDV_DLDV;
    private boolean isImageSelected = false;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_dichvu, container, false);
        rcvDichVu = view.findViewById(R.id.rcvDVQL);
        fladdDV = view.findViewById(R.id.fladdQLDV);
        dichVuDAO = new DichVuDAO(getActivity());
        list = (ArrayList<DichVu>) dichVuDAO.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvDichVu.setLayoutManager(layout);

        dichVuADAPTER = new DichVuQL_ADAPTER(getActivity(), list);
        rcvDichVu.setAdapter(dichVuADAPTER);
        fladdDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialogADD();
            }
        });
        rcvDichVu.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedRight(int position) {
                dichVuADAPTER.notifyDataSetChanged();
            }

            @Override
            public void onSwipedLeft(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Cảnh báo");
                builder.setIcon(R.drawable.icon_warning);
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý logic khi người dùng chọn Yes
                        DichVu dichVu = list.get(position); // Lấy đối tượng DichVu tại vị trí vuốt
                        if (dichVuDAO.delete(dichVu.getMaDV()) > 0) {
                            list.remove(position);
                            dichVuADAPTER.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý logic khi người dùng chọn No
                        dialog.dismiss();
                        dichVuADAPTER.notifyDataSetChanged();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }

    public void opendialogADD() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dichvu, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        imgDV_DLDV = view.findViewById(R.id.imgDV_DLDV);
        EditText edtenDV_DLDV = view.findViewById(R.id.edtenDV_DLDV);
        EditText edloai_DLDV = view.findViewById(R.id.edloai_DLDV);
        EditText edtrangThai_DLDV = view.findViewById(R.id.edtrangThai_DLDV);
        EditText edgiaDV_DLDV = view.findViewById(R.id.edgiaDV_DLDV);
        EditText edgiaSALE_DLDV = view.findViewById(R.id.edgiaSALE_DLDV);
        EditText edghiChu_DLDV = view.findViewById(R.id.edghiChu_DLDV);
        Button btnSave_DLNV = view.findViewById(R.id.btnSave_DLNV);
        Button btnCancel_DLNV = view.findViewById(R.id.btnCancel_DLNV);
        TextView hindgia = view.findViewById(R.id.hindgia);

        imgDV_DLDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();

            }
        });

        edloai_DLDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(getActivity());
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

        edgiaDV_DLDV.setHint("Giá");
        edgiaSALE_DLDV.setVisibility(View.GONE);
        edtrangThai_DLDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(getActivity());
                builder1.setTitle("Trạng thái dịch vụ:");
                String[] trangThai = {"SALE", "NEW", "Không"};
                builder1.setItems(trangThai, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtrangThai_DLDV.setText(trangThai[which]);

                        if (trangThai[which].equals("SALE")) {
                            edgiaDV_DLDV.setHint("Giá gốc");
                            hindgia.setText("Giá gốc");
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
                String loaiDV = edloai_DLDV.getText().toString();
                String giaSALE = edgiaSALE_DLDV.getText().toString();
                String trangThai = edtrangThai_DLDV.getText().toString();
                String ghiChu = edghiChu_DLDV.getText().toString();
                if (isImageSelected == false) {
                    Toast.makeText(getActivity(), "Bạn phải chọn ảnh cho dịch vụ!", Toast.LENGTH_SHORT).show();
                } else {
                    if (tenDV.trim().isEmpty() || giaDV.trim().isEmpty() || loaiDV.trim().isEmpty() || trangThai.trim().isEmpty() || ghiChu.trim().isEmpty()) {
                        Toast.makeText(getActivity(), "Không đuợc bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                    } else {
                        dichVu.setTenDV(tenDV);
                        dichVu.setGiaDV(Integer.parseInt(giaDV));
                        if (loaiDV.equals("Phẫu thuật")) {
                            dichVu.setLoaiDV("PT");
                        } else if (loaiDV.equals("Phun săm")) {
                            dichVu.setLoaiDV("PS");
                        } else if (loaiDV.equals("Khác")) {
                            dichVu.setLoaiDV("KHAC");
                        } else {
                            Toast.makeText(getActivity(), "Loại vô lý !", Toast.LENGTH_SHORT).show();
                        }
                        if (trangThai.equals("SALE")) {
                            dichVu.setTrangThai("SALE");
                            dichVu.setGiaSALE(Integer.parseInt(giaSALE));
                        } else if (trangThai.equals("NEW")) {
                            dichVu.setTrangThai("NEW");
                            dichVu.setGiaSALE(Integer.parseInt(giaDV));
                        } else if (trangThai.equals("Không")) {
                            dichVu.setTrangThai("KHONG");
                            dichVu.setGiaSALE(Integer.parseInt(giaDV));
                        } else {
                            Toast.makeText(getActivity(), "Trạng thái vô lý !", Toast.LENGTH_SHORT).show();
                        }
                        dichVu.setGhiChu(ghiChu);

                        if (dichVu.getTrangThai().equals("SALE")){
                            if (Integer.parseInt(giaSALE)==0||Integer.parseInt(giaSALE)>Integer.parseInt(giaDV)){
                                Toast.makeText(getActivity(), "Giá SALE phải lớn hơn 0 và nhỏ hơn giá gốc !", Toast.LENGTH_SHORT).show();
                            }else {
                                if (dichVuDAO.insert(dichVu) > 0) {
                                    list.clear();
                                    list.addAll(dichVuDAO.getAll());
                                    dichVuADAPTER.notifyDataSetChanged();
                                    dialog.dismiss();
                                    Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Thêm thất bại.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {
                            if (dichVuDAO.insert(dichVu) > 0) {
                                list.clear();
                                list.addAll(dichVuDAO.getAll());
                                dichVuADAPTER.notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Thêm thất bại.", Toast.LENGTH_SHORT).show();
                            }
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

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);

                            String imageUrl = String.valueOf(uri);

                            // Load and display the image using Picasso
                            Picasso.get()
                                    .load(imageUrl)
                                    .resize(0, 250) // Resize the image to have a fixed height of 250dp, width will be adjusted to maintain the aspect ratio
                                    .centerCrop()    // Crop the image if necessary
                                    .into(imgDV_DLDV);

                            dichVu.setHinhAnh(uri);
                            isImageSelected = true;
//                            Toast.makeText(getActivity(), String.valueOf(uri), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );


//    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == RESULT_OK) {
//                        Intent data = result.getData();
//                        if (data == null) {
//                            return;
//                        }
//                        Uri uri = data.getData();
//                        try {
//                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
//                            imgDV_DLDV.setImageBitmap(bitmap);
//                            // Lưu uri hoặc bitmap tùy thuộc vào yêu cầu của bạn
//
//                            dichVu.setHinhAnh(uri);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//    );
private void handleSearch(String query) {
    List<DichVu> listSearch = new ArrayList<>();
    for (DichVu dichVu1 : list) {
        if (String.valueOf(dichVu1.getTenDV()).toLowerCase().contains(query.toLowerCase())) {
            listSearch.add(dichVu1);
        }
    }
    dichVuADAPTER = new DichVuQL_ADAPTER(getActivity(), (ArrayList<DichVu>) listSearch);
    rcvDichVu.setAdapter(dichVuADAPTER);

}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_search, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleSearch(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}