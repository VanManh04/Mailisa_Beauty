package com.example.mailisa_beauty.Login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.R;

public class dang_Ky extends AppCompatActivity {
    TextView da_cotaikhoan;
    EditText edsdtDK, edhotenDK, edmatkhauDK, ednhaplaimatkhauDK;
    Button btnhuyDK, btndangki;
    TaiKhoan item;
    TaiKhoanDAO tkDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        da_cotaikhoan = findViewById(R.id.da_cotaikhoan);
        edsdtDK = findViewById(R.id.edsdtDK);
        edhotenDK = findViewById(R.id.edhotenDK);
        edmatkhauDK = findViewById(R.id.edmatkhauDK);
        ednhaplaimatkhauDK=findViewById(R.id.ednhaplaimatkhauDK);
        btndangki=findViewById(R.id.btndangki);
        btnhuyDK=findViewById(R.id.btnhuyDK);

        tkDao = new TaiKhoanDAO(this);

        da_cotaikhoan.setPaintFlags(da_cotaikhoan.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        da_cotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dang_Ky.this, dang_Nhap.class);
                startActivity(intent);
            }
        });

        btnhuyDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edsdtDK.setText("");
                edhotenDK.setText("");
                edmatkhauDK.setText("");
                ednhaplaimatkhauDK.setText("");
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edmatkhauDK.getText().toString().equalsIgnoreCase(ednhaplaimatkhauDK.getText().toString())){
                    item = new TaiKhoan();
                    item.setSdt(edsdtDK.getText().toString());
                    item.setHoTen(edhotenDK.getText().toString());
                    item.setMatKhau(edmatkhauDK.getText().toString());
                    item.setChucVu("KH");
                    if (validate()>0){
                        if (tkDao.insert(item)>0){
                            Toast.makeText(dang_Ky.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        }else if (tkDao.insert(item)==-1){
                            Toast.makeText(dang_Ky.this, "Số điện thoại đã tồn tại trong hệ thống!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(dang_Ky.this, "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(dang_Ky.this, "Nhập lại mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public int validate(){
        int check =1 ;
        if (edsdtDK.getText().length()==0||edhotenDK.getText().length()==0||edmatkhauDK.getText().length()==0||ednhaplaimatkhauDK.getText().length()==0){
            check = -2;
        }else if (edsdtDK.getText().toString().trim().isEmpty()||edhotenDK.getText().toString().trim().isEmpty()||edmatkhauDK.getText().toString().trim().isEmpty()||ednhaplaimatkhauDK.getText().toString().trim().isEmpty()){
            check = -2;
        }

        if(check == -2){
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
        }else if (edsdtDK.getText().toString().length()==10||edsdtDK.getText().toString().length()==11){
            check=1;
        }else {
            check=-1;
            Toast.makeText(this, "Số điện thoại phải có 10 hoặc 11 số", Toast.LENGTH_SHORT).show();
        }
        return check;
    }
}