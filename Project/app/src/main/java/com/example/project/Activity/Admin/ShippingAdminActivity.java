package com.example.project.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.project.Adapter.AdapterPayment;
import com.example.project.Adapter.AdapterShipping;
import com.example.project.DB.Server;
import com.example.project.Entity.Payment;
import com.example.project.Entity.Shipping;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ShippingAdminActivity extends AppCompatActivity {
    public static ArrayList<Shipping> mList = new ArrayList<>();
    private AdapterShipping adapterShipping;
    private static ProgressDialog mProgressDialog;
    private ListView lstGiaohang;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_admin);

        TextView tvBack = findViewById(R.id.TVBackBill);
        lstGiaohang = findViewById(R.id.lstDsGiaoHang);
        TextView tvThemmoi = findViewById(R.id.tvThemGiaoHang);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShippingAdminActivity.this, BillsAdminActivity.class);
                startActivity(intent);
            }
        });

        tvThemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themmoi();
            }
        });
    }

    @Override
    protected void onResume() {
        loadGiaoHang();
        super.onResume();
    }

    private void loadGiaoHang() {
        showSimpleProgressDialog(this, "Thông báo", "Đang tải dữ liệu....", false);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getpshipping, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idsp = jsonObject.getInt("id");
                        String namesp = new String(jsonObject.optString("nameshipping").getBytes("ISO-8859-1"), "UTF-8");
                        float pricesp = (float) jsonObject.getDouble("price");
                        boolean statussp = jsonObject.getBoolean("status");
                        Shipping sp = new Shipping(idsp,namesp,pricesp,statussp);
                        mList.add(sp);

                        adapterShipping = new AdapterShipping(ShippingAdminActivity.this, mList);
                        lstGiaohang.setAdapter(adapterShipping);
                        adapterShipping.notifyDataSetChanged();
                        removeSimpleProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShippingAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }


    private void themmoi(){
        EditText edtName = findViewById(R.id.edtGiaoHang);
        EditText edtPrice = findViewById(R.id.edtPhiShip);
        RadioButton rdHien = findViewById(R.id.radHienSP);
        RadioButton rdAn = findViewById(R.id.radAnSP);

        Boolean check = true;
        if (rdHien.isChecked()) {
            check = true;
        } else if (rdAn.isChecked()) {
            check = false;
        }

        String name = edtName.getText().toString().trim();
        String price = edtPrice.getText().toString().trim();
        String statusStr = String.valueOf(check);

        if (name.equals("") || price.equals("")) {
            Toast.makeText(ShippingAdminActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObj = new JSONObject();
            Shipping sp = new Shipping();
            try {
                jsonObj.put("id", sp.getId());
                jsonObj.put("nameshipping", name);
                jsonObj.put("price", price);
                jsonObj.put("status", statusStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.getpshipping, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    edtName.setText("");
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    onResume();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ShippingAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
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