package com.example.mailisa_beauty.frg_quanLy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailisa_beauty.ADAPTER.LichKhachHang_QL_ADAPTER;
import com.example.mailisa_beauty.ADAPTER.TaiKhoanADAPTER;
import com.example.mailisa_beauty.DAO.LichKhachHang_DAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.LichKhachHang;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import java.util.ArrayList;

public class QL_lichKhachHang extends Fragment {
    RecyclerView rcvLKH_QLLKH;
    LichKhachHang_DAO lichKhachHangDao;
    LichKhachHang_QL_ADAPTER lichKhachHang_ql_adapter;
    private ArrayList<LichKhachHang> list = new ArrayList<LichKhachHang>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ql_lichkhachhang, container, false);
        rcvLKH_QLLKH = view.findViewById(R.id.rcvLKH_QLLKH);

        lichKhachHangDao = new LichKhachHang_DAO(getActivity());
        list = (ArrayList<LichKhachHang>) lichKhachHangDao.getAll();
        LinearLayoutManager layout = new LinearLayoutManager(getActivity());
        rcvLKH_QLLKH.setLayoutManager(layout);
        lichKhachHang_ql_adapter = new LichKhachHang_QL_ADAPTER(getActivity(), list);
        rcvLKH_QLLKH.setAdapter(lichKhachHang_ql_adapter);

//        rcvLKH_QLLKH.setListener(new SwipeLeftRightCallback.Listener() {
//            @Override
//            public void onSwipedRight(int position) {
//                lichKhachHang_ql_adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onSwipedLeft(int position) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Cảnh báo");
//                builder.setIcon(R.drawable.icon_warning);
//                builder.setMessage("Bạn có muốn xóa không?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Xử lý logic khi người dùng chọn Yes
//                        LichKhachHang lichKhachHang = list.get(position); // Lấy đối tượng DichVu tại vị trí vuốt
//                        if (lichKhachHangDao.delete(lichKhachHang.getMaLKH()) > 0) {
//                            list.remove(position);
//                            lichKhachHang_ql_adapter.notifyDataSetChanged();
//                            Toast.makeText(getActivity(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // Xử lý logic khi người dùng chọn No
//                        dialog.dismiss();
//                        lichKhachHang_ql_adapter.notifyDataSetChanged();
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
        return view;
    }
}