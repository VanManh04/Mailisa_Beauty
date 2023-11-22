package com.example.mailisa_beauty.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mailisa_beauty.DAO.TaiKhoanDAO;
import com.example.mailisa_beauty.Nav_VIEW;
import com.example.mailisa_beauty.R;

public class dang_Nhap extends AppCompatActivity {
    TextView chua_cotaikhoan;
    EditText edSdt;
    EditText edPass;
    Button btnLogin,btnCancel;
    TaiKhoanDAO tkDAO;
    CheckBox chkCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        chua_cotaikhoan = findViewById(R.id.chua_cotaikhoan);
        edSdt = findViewById(R.id.edSdt);
        edPass = findViewById(R.id.edPass);
        chkCheck = findViewById(R.id.chkluu);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancellg);

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

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edSdt.setText("");
                edPass.setText("");
            }
        });

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
}