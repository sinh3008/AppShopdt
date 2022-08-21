package com.example.project.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.project.R;

public class GiohangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        ImageView backtoHomepage = findViewById(R.id.veTrangChu);
        ImageView veDanhSach = findViewById(R.id.veDanhSach);
        backtoHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(GiohangActivity.this , TrangchuActivity.class);
                startActivity(i);
            }
        });
        veDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(GiohangActivity.this , TrangchuActivity.class);
                startActivity(i);
            }
        });
    }
}