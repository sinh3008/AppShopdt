package com.example.project.Activity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.project.DB.Server;
import com.example.project.Entity.Customer;
import com.example.project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView btnBack = findViewById(R.id.frgtBtnBacklogin);
        TextView btnRegister = findViewById(R.id.frgtBtnRegister);
        btnBack.setOnClickListener(listenerBack);
        btnRegister.setOnClickListener(listenerRegister);
    }

    private View.OnClickListener listenerBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener listenerRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Register();
        }
    };

    private void Register() {
        EditText edtUsername = findViewById(R.id.frgtEdtUsername);
        EditText edtPassword = findViewById(R.id.frgtEdtPassword);
        EditText edtRePassword = findViewById(R.id.frgtEdtRePassword);
        EditText edtNamecustomer = findViewById(R.id.frgtEdtNameCustomer);
        EditText edtPhonecustomer = findViewById(R.id.frgtEdtRegisterPhone);
        EditText edtAddresscustomer = findViewById(R.id.frgtEdtRegisterAddress);
        RadioGroup radGender = findViewById(R.id.radRegisterGender);
        RadioButton radioButtonNam = findViewById(R.id.radRegisterNam);
        RadioButton radioButtonNu = findViewById(R.id.radRegisterNu);

        Customer c = new Customer();

        List<Customer> mLst = new ArrayList<>();

        String UsernameRe = edtUsername.getText().toString();
        String NamecusRegister = edtNamecustomer.getText().toString();
        String PhoneCusregister = edtPhonecustomer.getText().toString();
        String AddressCusRegister = edtAddresscustomer.getText().toString();
        String RegPass = edtPassword.getText().toString();
        String Rerepass = edtRePassword.getText().toString();
        boolean GenderCusRegister = true;
        if (radioButtonNu.isChecked()) {
            GenderCusRegister = false;
        } else {
            GenderCusRegister = true;
        }
        int Repa = RegPass.length();

        if (UsernameRe.equals("") || NamecusRegister.equals("") || PhoneCusregister.equals("") || AddressCusRegister.equals("") || RegPass.equals("") || Rerepass.equals("")) {
            Toast.makeText(this, "Vui l??ng ??i???n ????? th??ng tin", Toast.LENGTH_SHORT).show();
        } else if (RegPass.equals(Rerepass) != true) {
            Toast.makeText(this, " M???t kh???u kh??ng tr??ng kh???p", Toast.LENGTH_SHORT).show();
        } else if (Repa < 6) {
            Toast.makeText(this, "M???t kh???u kh??ng th??? nh??? h??n 6 k?? t???", Toast.LENGTH_SHORT).show();
        } else {
            //  check t??i kho???n ???? t???n t???i hay ch??a
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            boolean finalGenderCusRegister = GenderCusRegister;
            StringRequest request = new StringRequest(Request.Method.GET, Server.getuser + UsernameRe, new Response.Listener<String>() {
                @Override
                public void onResponse(String responses) {
                    if (responses.equals("true")) {
                        Toast.makeText(RegisterActivity.this, "T??i kho???n ???? t???n t???i, vui l??ng nh???p t??i kho???n kh??c!", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("username", UsernameRe);
                            jsonObj.put("namecustomer", NamecusRegister);
                            jsonObj.put("phone", PhoneCusregister);
                            jsonObj.put("address", AddressCusRegister);
                            jsonObj.put("password", MD5(RegPass));
                            jsonObj.put("gender", finalGenderCusRegister);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest Jsrequest = new JsonObjectRequest(Request.Method.POST, Server.getcustomer, jsonObj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(RegisterActivity.this, "????ng k?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this, "????ng k?? th???t b???i", Toast.LENGTH_SHORT).show();
                            }
                        });
                        requestQueue.add(Jsrequest);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterActivity.this, "L???i r???i!", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue.add(request);
        }
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