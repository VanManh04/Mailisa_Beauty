package com.example.mailisa_beauty.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mailisa_beauty.DAO.DichVuDAO;
import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Model.TaiKhoan;
import com.example.mailisa_beauty.Nav_VIEW;
import com.example.mailisa_beauty.R;
import com.example.mailisa_beauty.frg_khachHang.Frg_kh_gioDichVu;

public class dang_Nhap extends AppCompatActivity {
    TextView chua_cotaikhoan;
    EditText edSdt;
    EditText edPass;
    Button btnLogin;
    TaiKhoanDAO tkDAO;
    CheckBox chkCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        chua_cotaikhoan = findViewById(R.id.chua_cotaikhoan);
        edSdt = findViewById(R.id.edSdt);
        edPass = findViewById(R.id.edPass);
        chkCheck = findViewById(R.id.chkluu);
        btnLogin = findViewById(R.id.btnLogin);


        // LẤY URI
        int imageResourceId = R.drawable.dv1_img;
        // Kiểm tra xem tài nguyên hình ảnh có tồn tại hay không
        if (imageResourceId != 0) {
            // Lấy URI của tài nguyên Drawable
            Uri imageUri = Uri.parse("android.resource://" + this.getPackageName() + "/" + imageResourceId);

            // Bạn có thể sử dụng imageUri theo nhu cầu của mình ở đây
            // Ví dụ: đặt hình ảnh vào ImageView bằng Glide hoặc Picasso
            String Stringuri = String.valueOf(imageUri);
            Toast.makeText(this, Stringuri, Toast.LENGTH_SHORT).show();
            TextView n1 = findViewById(R.id.n1);
            n1.setText(Stringuri);
        } else {
            // Xử lý trường hợp tài nguyên không tồn tại
            // Ví dụ: hiển thị ảnh mặc định hoặc thông báo lỗi
        }
        chua_cotaikhoan.setPaintFlags(chua_cotaikhoan.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        chua_cotaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dang_Nhap.this, dang_Ky.class);
                startActivity(intent);
            }
        });

        tkDAO = new TaiKhoanDAO(this);

        SharedPreferences pref =getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edSdt.setText(pref.getString("PHONE",""));
        edPass.setText(pref.getString("PASSWORD",""));
        chkCheck.setChecked(pref.getBoolean("REMEMBER",false));


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLOGIN();
            }
        });
    }
    private void checkLOGIN(){
        String strSdt = edSdt.getText().toString();
        String strPass = edPass.getText().toString();
        if (strSdt.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Số điện thoại và mật khẩu không được bỏ trống!", Toast.LENGTH_SHORT).show();
        }else {
            if (tkDAO.checkLogin(strSdt,strPass)==1){
                Toast.makeText(getApplicationContext(), "Login Thành công!", Toast.LENGTH_SHORT).show();
                rememberUser(strSdt,strPass,chkCheck.isChecked());
                Intent intent = new Intent(dang_Nhap.this, Nav_VIEW.class);
//                put tên sdt qua nav
                intent.putExtra("sdt",strSdt);
                startActivity(intent);
                finish();
            }else if (tkDAO.checkLogin(strSdt,strPass)==0){
                Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
            }else if (tkDAO.checkLogin(strSdt,strPass)==-1){
                Toast.makeText(getApplicationContext(), "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void rememberUser(String s, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            //delete
            editor.clear();
        }else {
            //save
            editor.putString("PHONE",s);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        //save
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        // Kiểm tra xem người dùng có muốn thoát khỏi ứng dụng khi ở màn hình đăng nhập không
        new AlertDialog.Builder(this)
                .setMessage("Bạn có muốn thoát khỏi ứng dụng không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nếu người dùng chọn "Có", thoát khỏi ứng dụng
                        finishAffinity();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Đóng hộp thoại nếu người dùng chọn "Không"
                        dialog.dismiss();
                    }
                })
                .show();
    }

}