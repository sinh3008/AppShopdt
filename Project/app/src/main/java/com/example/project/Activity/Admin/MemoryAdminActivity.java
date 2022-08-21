package com.example.project.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.AdapterColors;
import com.example.project.Adapter.AdapterMemory;
import com.example.project.DB.Server;
import com.example.project.Entity.Colors;
import com.example.project.Entity.Memory;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MemoryAdminActivity extends AppCompatActivity {
    public static ArrayList<Memory> mList = new ArrayList<>();
    private AdapterMemory adapterMemory;
    private static ProgressDialog mProgressDialog;
    private ListView lstDungluong;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_admin);

        TextView backHome = findViewById(R.id.TVBackDungLuong);
        lstDungluong = findViewById(R.id.lstDsDungLuong);
        TextView tvadd = findViewById(R.id.btnThemDungLuong);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MemoryAdminActivity.this, ProductAdminActivity.class);
                startActivity(intent);
            }
        });

        tvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themmoi();
            }
        });
    }

    @Override
    protected void onResume() {
        loadDungluong();
        super.onResume();
    }
    private void loadDungluong() {
        showSimpleProgressDialog(this, "Thông báo", "Đang tải dữ liệu....", false);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getmemory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idmem = jsonObject.getInt("idmem");
                        String namemem = jsonObject.getString("namememory");
                        float pricemem = (float) jsonObject.getDouble("extraprice");
                        boolean statusmem = jsonObject.getBoolean("status");

                        Memory m = new Memory(idmem, namemem, pricemem,statusmem);
                        mList.add(m);
                        adapterMemory = new AdapterMemory(MemoryAdminActivity.this, mList);
                        lstDungluong.setAdapter(adapterMemory);
                        removeSimpleProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MemoryAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void themmoi(){
        EditText edtName = findViewById(R.id.edtDungLuong);
        EditText edtPrice = findViewById(R.id.edtGiaDL);
        RadioButton rdHien = findViewById(R.id.radHienDL);
        RadioButton rdAn = findViewById(R.id.radAnDL);

        Boolean check = true;
        if (rdHien.isChecked()) {
            check = true;
        } else if (rdAn.isChecked()) {
            check = false;
        }
        String namemem = edtName.getText().toString().trim();
        String pricemem = edtPrice.getText().toString().trim();
        String statusStr = String.valueOf(check);
        if (namemem.equals("") || pricemem.equals("")) {
            Toast.makeText(MemoryAdminActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObj = new JSONObject();
            Memory m = new Memory();
            try {
                jsonObj.put("idmem", m.getIdmem());
                jsonObj.put("namememory", namemem);
                jsonObj.put("extraprice", pricemem);
                jsonObj.put("status", statusStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.getmemory, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    edtName.setText("");
                    edtPrice.setText("");
                    rdHien.isChecked();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    onResume();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MemoryAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        }
    }

    private static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showSimpleProgressDialog(Context context, String title, String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}