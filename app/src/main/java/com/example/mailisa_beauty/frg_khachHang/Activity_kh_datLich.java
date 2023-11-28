package com.example.mailisa_beauty.frg_khachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.mailisa_beauty.R;

public class Activity_kh_datLich extends AppCompatActivity {
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kh_datlich);

        Intent intent = getIntent();
        String dataMaTK = intent.getStringExtra("key_idTK2");

        toolbar = findViewById(R.id.toolbar); // Thay R.id.toolbar bằng ID của Toolbar trong XML
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back); // Đặt icon quay lại
            getSupportActionBar().setTitle("Đặt lịch");
            toolbar.setTitleTextColor(Color.WHITE);
        }
    }
    // Xử lý sự kiện khi nút quay lại được nhấn
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Xử lý hành động khi nút quay lại được nhấn
                finish(); // Đóng Activity hiện tại
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}