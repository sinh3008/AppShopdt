package com.example.project.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
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
import com.example.project.Activity.Admin.ThemDanhMucActivity;
import com.example.project.DB.Server;
import com.example.project.Entity.Category;
import com.example.project.Entity.Customer;
import com.example.project.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThaydoithongtinActivity extends AppCompatActivity {

    Context context;
    List<Customer> mLst = new ArrayList<>();
    private static final String FILE_NAME = "myFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaydoithongtin);

        Anhxa();

        getData();
        TextView btnThaydoiSave = findViewById(R.id.formThayDoiBtnSave);
        btnThaydoiSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateCustomer();
            }
        });
        TextView btnThaydoiExit = findViewById(R.id.formThayDoiBtnBack);
        btnThaydoiExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intentexit = new Intent(ThaydoithongtinActivity.this, ThongtincanhanActivity.class);
                startActivity(intentexit);
            }
        });

    }

    public static int idcus;

    private void getData() {
        TextView btnSaveThaydoi = findViewById(R.id.formThayDoiBtnSave);
        TextView btnBackThongtin = findViewById(R.id.formThayDoiBtnBack);
        EditText txtName = findViewById(R.id.formThaydoiNamecustomer);
        EditText txtPhone = findViewById(R.id.formThaydoiPhoneCustomer);
        EditText txtAddress = findViewById(R.id.formThaydoiAddressCustomer);
        EditText txtPassword = findViewById(R.id.formThaydoiPassword);
        RadioButton radioNam = findViewById(R.id.formThaydoiRadNam);
        RadioButton radioNu = findViewById(R.id.formThaydoiRadNu);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        String usernew = TrangchuActivity.email_user.toString();
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

                    idcus = id;
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("idcus", idcus);
                    editor.apply();

                    Customer c = new Customer(id, namecustomer, username, password, phone, address, gender);
                    mLst.add(c);
                    if (c.isGender() == true) {
                        radioNam.setChecked(true);
                    } else {
                        radioNu.setChecked(true);
                    }
                    txtName.setText(namecustomer);
                    txtPhone.setText(phone);
                    txtAddress.setText(address);
                    txtPassword.setText(password);
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
                param.put("id", String.valueOf(idcus));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Anhxa() {
        EditText txtName = findViewById(R.id.formThaydoiNamecustomer);
        EditText txtPhone = findViewById(R.id.formThaydoiPhoneCustomer);
        EditText txtAddress = findViewById(R.id.formThaydoiAddressCustomer);
        EditText txtPassword = findViewById(R.id.formThaydoiPassword);
        RadioButton radioNam = findViewById(R.id.formThaydoiRadNam);
        RadioButton radioNu = findViewById(R.id.formThaydoiRadNu);
    }

    private void UpdateCustomer() {
        EditText txtName = findViewById(R.id.formThaydoiNamecustomer);
        EditText txtPhone = findViewById(R.id.formThaydoiPhoneCustomer);
        EditText txtAddress = findViewById(R.id.formThaydoiAddressCustomer);
        EditText txtPassword = findViewById(R.id.formThaydoiPassword);
        RadioButton radioNam = findViewById(R.id.formThaydoiRadNam);
        RadioButton radioNu = findViewById(R.id.formThaydoiRadNu);

        String txtNameCus = txtName.getText().toString();
        String txtPhoneCus = txtPhone.getText().toString().trim();
        String txtAddressCus = txtAddress.getText().toString();
        String txtPassCus = txtPassword.getText().toString().trim();

        boolean genderchecked ;
        if (radioNu.isChecked()) {
            genderchecked = false;
        } else {
            genderchecked = true;
        }
        String gendernew = String.valueOf(genderchecked);
        if (txtNameCus.equals("") || txtPhoneCus.equals("") || txtAddressCus.equals("") || txtPassCus.equals("")) {
            Toast.makeText(this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObj = new JSONObject();
            Customer c = new Customer();
            try {
                jsonObj.put("id", idcus);
                jsonObj.put("namecustomer", txtNameCus);
                jsonObj.put("password", MD5(txtPassCus));
                jsonObj.put("phone", txtPhoneCus);
                jsonObj.put("address", txtAddressCus);
                jsonObj.put("gender", gendernew);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            Customer result = gson.fromJson(jsonObj.toString(), Customer.class);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Server.getcustomer, jsonObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intenttrove = new Intent(ThaydoithongtinActivity.this, ThongtincanhanActivity.class);
                    startActivity(intenttrove);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ThaydoithongtinActivity.this, "Cập nhật không thành công! Lỗi: " + error.toString(), Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(request);
        }
    }
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),TrangchuActivity.class));
    }
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }


}

