package com.example.project.Activity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Activity.Admin.AdminActivity;
import com.example.project.DB.CheckConnection;
import com.example.project.DB.Server;
import com.example.project.R;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String USERNAMECHUYEN = "USERNAMECHUYEN";
    EditText username, password;
    CheckBox checkboxRemember;

    private static final String FILE_NAME = "myFile";
    private static final String FILE_SAVE = "savePass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AutoFill();
        TextView btnLogin = findViewById(R.id.flgbtnLogin);
        ImageView btnSignup = findViewById(R.id.flgBtnSignup);

        btnLogin.setOnClickListener(listenerLogin);
        btnSignup.setOnClickListener(listenerRegister);
    }

    private View.OnClickListener listenerRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener listenerLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                login();
            } else {
                Toast.makeText(getApplicationContext(), "Vui lòng kiểm tra kết nối internet", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void login() {
        EditText edtUsername = findViewById(R.id.flgTxtUsername);
        EditText edtPassword = findViewById(R.id.flgTxtPassword);
        TextView cbox = findViewById(R.id.CheckboxRemember);
        CheckBox cbRemember = findViewById(R.id.flgCbRememberMe);

        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest requestcus = new StringRequest(Request.Method.GET, Server.getuser + username + "/" + MD5(password), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("true")) {
                        finish();
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), TrangchuActivity.class);
                        Remember(username, password);
                        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parameters = new HashMap<String, String>();
                    parameters.put("username", username);
                    parameters.put("password", MD5(password));
                    return parameters;
                }
            };
            queue.add(requestcus);

            StringRequest requestadmin = new StringRequest(Request.Method.GET, Server.getaccount + username + "/" + MD5(password), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("true")) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        Remember(username, password);
                        SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(intent);
                        finish();
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parameters = new HashMap<String, String>();
                    parameters.put("username", username);
                    parameters.put("password", MD5(password));
                    return parameters;
                }
            };
            queue.add(requestadmin);
        }
    }


    private void AutoFill() {
        username = findViewById(R.id.flgTxtUsername);
        password = findViewById(R.id.flgTxtPassword);

        //Remember me  :)))  tự  động điền tài khoản sau khi logout nếu có check vào remember
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_SAVE, MODE_PRIVATE);
        String edittext_user = sharedPreferences.getString("edittext_user", "");
        String edittext_password = sharedPreferences.getString("edittext_password", "");

        username.setText(edittext_user);
        password.setText(edittext_password);
    }


    private void Remember(String edittext_user, String edittext_password) {
        checkboxRemember = findViewById(R.id.flgCbRememberMe);
        if (checkboxRemember.isChecked()) {
            SharedPreferences.Editor editor = getSharedPreferences(FILE_SAVE, MODE_PRIVATE).edit();
            editor.putString("edittext_user", edittext_user);
            editor.putString("edittext_password", edittext_password);
            editor.apply();
        } else {
            SharedPreferences.Editor editor = getSharedPreferences(FILE_SAVE, MODE_PRIVATE).edit();
            editor.putString("edittext_user", "");
            editor.putString("edittext_password", "");
            editor.apply();
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