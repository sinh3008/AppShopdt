package com.example.project.Activity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.AdapterProduct;
import com.example.project.DB.Server;
import com.example.project.DB.VolleySingleton;
import com.example.project.Entity.Customer;
import com.example.project.Entity.Product;
import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrangchuActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView image_search;
    EditText edtsearch;
    TextView txtXinchao;

    private static final String FILE_NAME = "myFile";
    public static ArrayList<Product> dienthoaiArrayList = new ArrayList<>();
    AdapterProduct adapter;


    public static String email_user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        email_user = sharedPreferences.getString("username", "");
        getUserIdByEmail(email_user);

        TextView txtiduser = findViewById(R.id.txtTrangchuXinchaoUser);
        txtiduser.setText("Xin chào user: " + email_user);

        adapter = new AdapterProduct(this, dienthoaiArrayList);
        getdatauser();

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.menuTrangchu:
                    finish();
                    Intent intenTrangchu = new Intent(TrangchuActivity.this, TrangchuActivity.class);
                    startActivity(intenTrangchu);
                    break;
                case R.id.menuGiohang:
                    finish();
                    Intent intenGiohang = new Intent(TrangchuActivity.this, GiohangActivity.class);
                    startActivity(intenGiohang);
                    break;
                case R.id.menuDanhsach:
                    finish();
                    Intent intentDanhsach = new Intent(TrangchuActivity.this, DanhsachsanphamActivity.class);
                    startActivity(intentDanhsach);
                    break;
                case R.id.menuCaidat:
                    finish();
                    Intent intentCaidat = new Intent(TrangchuActivity.this, ThongtincanhanActivity.class);
                    startActivity(intentCaidat);
                    break;
            }
            return false;
        });
    }

    public void icon_dienthoai(View view) {
    }

    public void btngiohang(View view) {
    }

    public void xemtatca(View view) {
        Intent intentDanhsach = new Intent(TrangchuActivity.this, DanhsachsanphamActivity.class);
        startActivity(intentDanhsach);
    }

    public static int iduser;
    public void getdatauser(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.getcustomerbyname + "/" + email_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int id = jsonObject.getInt("id");
                    iduser = id;
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("iduser",iduser);
                    editor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi Ở đây" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @android.support.annotation.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("username", TrangchuActivity.email_user);
                param.put("iduser", String.valueOf(iduser));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void timkiem(View view) {
        finish();
        Intent intent = new Intent(TrangchuActivity.this, TimkiemActivity.class);
        edtsearch = findViewById(R.id.search);
        String edtTimkiem = edtsearch.getText().toString();
        intent.putExtra("edtTimkiem", edtTimkiem);
        startActivity(intent);
    }

    private static int userid;

    public int getUserIdByEmail(String putusername) {
        String url = Server.getcustomerbyname + "/" + email_user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int id = jsonObject.getInt("id");
                    userid = id;
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("iduser", id);
                    editor.commit();

                    String namecustomer = jsonObject.getString("namecustomer");
                    String username = jsonObject.getString("username");
                    String password = jsonObject.getString("password");
                    String phone = jsonObject.getString("phone");
                    String address = jsonObject.getString("address");
                    Boolean gender = Boolean.valueOf(jsonObject.getString("gender"));
                    Customer user = new Customer(id, namecustomer, username, password, phone, address, gender);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi thu 2" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("username", putusername);
                return param;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
        return userid;

    }


}