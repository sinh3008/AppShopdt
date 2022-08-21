package com.example.project.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Activity.Admin.CategoryAdminActivity;
import com.example.project.DB.Server;
import com.example.project.Entity.Customer;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongtincanhanActivity extends AppCompatActivity {
    List<Customer> mLst = new ArrayList<>();

    private static final String FILE_NAME = "myFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);

        setContentView(com.example.project.R.layout.activity_thongtincanhan);
        TextView btnLogout = findViewById(R.id.BtnLogout);
        TextView btnBackHome = findViewById(R.id.btn_profile_backtohome);
        TextView btnThaydoithongtin = findViewById(R.id.thaydoithongtin);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Bạn có chắc chắn muốn thoát không  ?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent intentLogin = new Intent(ThongtincanhanActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                                finish();
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Thông báo !");
                alert.show();
            }
        });
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(ThongtincanhanActivity.this, TrangchuActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });

        btnThaydoithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThayDoi = new Intent(ThongtincanhanActivity.this, ThaydoithongtinActivity.class);
                startActivity(intentThayDoi);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    public static int iduser;

    private void getData() {

        TextView btnLogout = findViewById(R.id.BtnLogout);
        TextView btnBackHome = findViewById(R.id.btn_profile_backtohome);
        TextView btnThaydoithongtin = findViewById(R.id.thaydoithongtin);
        TextView txtName = findViewById(R.id.txtThongtinName);
        TextView txtuserid = findViewById(R.id.txtThongtinIdUser);
        TextView txtName2 = findViewById(R.id.txtThongtinName2);
        TextView txtPhone = findViewById(R.id.txtThongtinPhone);
        TextView txtGender = findViewById(R.id.txtThongtinGender);
        TextView txtAddress = findViewById(R.id.txtThongtinAddress);

        String usernew = TrangchuActivity.email_user.toString();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.getcustomerbyname + "/" + usernew, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int id = jsonObject.getInt("id");
                    String username = jsonObject.getString("username");
                    String namecustomer = new String(jsonObject.optString("namecustomer").getBytes("ISO-8859-1"), "UTF-8");
                    String address = new String(jsonObject.optString("address").getBytes("ISO-8859-1"), "UTF-8");
                    String phone = jsonObject.getString("phone");
                    String password = jsonObject.getString("password");
                    boolean gender = jsonObject.getBoolean("gender");
                    if (gender == true) {
                        txtGender.setText("Nam");
                    } else {
                        txtGender.setText("Nữ");
                    }
                    iduser = id;
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("iduser",iduser);
                    editor.apply();

                    Customer c = new Customer(id, namecustomer, username, password, phone, address, gender);
                    mLst.add(c);
                    txtuserid.setText("User id :" + id);
                    txtName.setText(namecustomer);
                    txtName2.setText(username);
                    txtPhone.setText(phone);
                    txtAddress.setText(address);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi Ở đây" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
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

    public void backtoHome(View view) {
        finish();
        Intent intentbacktoHome = new Intent(getApplicationContext(), TrangchuActivity.class);
        startActivity(intentbacktoHome);
    }
}