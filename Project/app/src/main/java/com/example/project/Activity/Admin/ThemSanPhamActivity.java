package com.example.project.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.DB.Server;
import com.example.project.Entity.Category;
import com.example.project.Entity.Colors;
import com.example.project.Entity.Memory;
import com.example.project.Entity.Product;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ThemSanPhamActivity extends AppCompatActivity {
    private List<Category> listC = new ArrayList<>();
    private List<Colors> listCl = new ArrayList<>();
    private List<Memory> listM = new ArrayList<>();
    Spinner spnDanhmuc;
    Spinner spnMausac;
    Spinner spnDungluong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        TextView tvBack = findViewById(R.id.TVBackSanPham);
        TextView tvThem = findViewById(R.id.tvThemSanPham);

        spnDanhmuc = findViewById(R.id.spnDanhMuc);
        spnMausac = findViewById(R.id.spnMauSac);
        spnDungluong = findViewById(R.id.spnDungLuong);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themmoi();
            }
        });
    }

    @Override
    protected void onResume() {
        loadSpinnerDanhmuc();
        loadSpinnerMausac();
        loadSpinnerDungluong();
        super.onResume();
    }

    private void loadSpinnerDanhmuc() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getcategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listC.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idcate = jsonObject.getInt("id");
                        String namecate = jsonObject.getString("namecategory");

                        Category c = new Category(idcate, namecate);
                        listC.add(c);
                    }

                    ArrayAdapter<Category> spinnerArrayAdapter = new ArrayAdapter<Category>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, listC);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnDanhmuc.setAdapter(spinnerArrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSanPhamActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void loadSpinnerMausac() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getcolors, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listCl.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idcol = jsonObject.getInt("idcol");
                        String namecol = new String(jsonObject.optString("namecolor").getBytes("ISO-8859-1"), "UTF-8");

                        Colors cl = new Colors(idcol, namecol);
                        listCl.add(cl);
                    }

                    ArrayAdapter<Colors> spinnerArrayAdapter = new ArrayAdapter<Colors>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, listCl);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnMausac.setAdapter(spinnerArrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSanPhamActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void loadSpinnerDungluong() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getmemory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listM.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idmem = jsonObject.getInt("idmem");
                        String namemem = jsonObject.getString("namememory");

                        Memory m = new Memory(idmem, namemem);
                        listM.add(m);
                    }

                    ArrayAdapter<Memory> spinnerArrayAdapter = new ArrayAdapter<Memory>(ThemSanPhamActivity.this, android.R.layout.simple_spinner_item, listM);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnDungluong.setAdapter(spinnerArrayAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemSanPhamActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void themmoi() {
        EditText edtName = findViewById(R.id.edtTenDT);
        EditText edtPrice = findViewById(R.id.edtGiaDT);
        EditText edtQuantity = findViewById(R.id.edtSoLuongDT);
        EditText edtSizeScreen = findViewById(R.id.edtManHinh);
        EditText edtScreenTech = findViewById(R.id.edtCNManHinh);
        EditText edtRCAM = findViewById(R.id.edtCAMTruoc);
        EditText edtFCAM = findViewById(R.id.edtCAMSau);
        EditText edtChip = findViewById(R.id.edtChip);
        EditText edtSIM = findViewById(R.id.edtSIM);
        EditText edtOS = findViewById(R.id.edtOS);
        EditText edtlink = findViewById(R.id.edtlinkAnh);
        EditText edtDes = findViewById(R.id.edtDespro);
        RadioButton rdHien = findViewById(R.id.radHienDT);
        RadioButton rdAn = findViewById(R.id.radAnDT);

        String namepro = edtName.getText().toString().trim();
        String pricepro = edtPrice.getText().toString().trim();
        String quantitypro = edtQuantity.getText().toString().trim();
        String sizescreenpro = edtSizeScreen.getText().toString().trim();
        String screentechpro = edtScreenTech.getText().toString().trim();
        String rcampro = edtRCAM.getText().toString().trim();
        String fcampro = edtFCAM.getText().toString().trim();
        String chippro = edtChip.getText().toString().trim();
        String simpro = edtSIM.getText().toString().trim();
        String ospro = edtOS.getText().toString().trim();
        String linkpro = edtlink.getText().toString().trim();
        String despro = edtDes.getText().toString().trim();
        Category c = (Category) spnDanhmuc.getSelectedItem();
        Colors cl = (Colors) spnMausac.getSelectedItem();
        Memory m = (Memory) spnDungluong.getSelectedItem();
        Boolean check = true;
        if (rdHien.isChecked()) {
            check = true;
        } else if (rdAn.isChecked()) {
            check = false;
        }
        String statusStr = String.valueOf(check);

        if (namepro.equals("") || pricepro.equals("") || quantitypro.equals("") || sizescreenpro.equals("") || screentechpro.equals("") || rcampro.equals("") ||
                fcampro.equals("") || chippro.equals("") || simpro.equals("") || ospro.equals("") || linkpro.equals("") || despro.equals("")) {
            Toast.makeText(ThemSanPhamActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            RequestQueue queue = Volley.newRequestQueue(this);
            JSONObject jsonObj = new JSONObject();
            Product p = new Product();
            try {
                jsonObj.put("idpro", p.getIdpro());
                jsonObj.put("nameproduct", namepro);
                jsonObj.put("idcategory", c.getId());
                jsonObj.put("price", pricepro);
                jsonObj.put("quantity", quantitypro);
                jsonObj.put("sizecreen", sizescreenpro);
                jsonObj.put("screentechnology", screentechpro);
                jsonObj.put("rearcamera", rcampro);
                jsonObj.put("frontcamera", fcampro);
                jsonObj.put("chipset", chippro);
                jsonObj.put("sim", simpro);
                jsonObj.put("os", ospro);
                jsonObj.put("idcolor", cl.getIdcol());
                jsonObj.put("idmemory", m.getIdmem());
                jsonObj.put("imagelist", linkpro);
                jsonObj.put("otherparameters", despro);
                jsonObj.put("status", statusStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.getproduct, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), ProductAdminActivity.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            queue.add(request);
        }
    }
}