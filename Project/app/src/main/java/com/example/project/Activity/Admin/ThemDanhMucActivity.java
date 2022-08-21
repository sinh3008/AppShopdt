package com.example.project.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.DB.Server;
import com.example.project.Entity.Category;
import com.example.project.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ThemDanhMucActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_danh_muc);

        TextView tvBack = findViewById(R.id.TVBackDanhMuc);
        TextView btnThemmoi = findViewById(R.id.btnThemDanhMuc);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnThemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themmoi();
            }
        });
    }

    private void themmoi() {
        EditText edtName = findViewById(R.id.edtDanhMuc);
        String namecategory = edtName.getText().toString().trim();
        RadioButton rdHien = findViewById(R.id.radHien);
        RadioButton rdAn = findViewById(R.id.radAn);
        Boolean check = true;
        if (rdHien.isChecked()) {
            check = true;
        } else if (rdAn.isChecked()) {
            check = false;
        }
        String statusStr = String.valueOf(check);
        if (namecategory.equals("")) {
            Toast.makeText(ThemDanhMucActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObj = new JSONObject();
            Category c = new Category();
            try {
                jsonObj.put("id", c.getId());
                jsonObj.put("namecategory", namecategory);
                jsonObj.put("status", statusStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.getcategory, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(ThemDanhMucActivity.this, CategoryAdminActivity.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ThemDanhMucActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(request);
        }
    }
}