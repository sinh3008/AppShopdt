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
import com.example.project.Adapter.AdapterColors;
import com.example.project.DB.Server;
import com.example.project.Entity.Colors;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ColorsAdminActivity extends AppCompatActivity {
    public static ArrayList<Colors> mList = new ArrayList<>();
    private AdapterColors adapterColors;
    private static ProgressDialog mProgressDialog;
    private ListView lstMauSac;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors_admin);

        TextView backHome = findViewById(R.id.TVBackMauSac);
        lstMauSac = findViewById(R.id.lstDsMauSac);
        TextView tvadd = findViewById(R.id.btnThemMauSac);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductAdminActivity.class);
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
        loadMauSac();
        super.onResume();
    }
    private void loadMauSac() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getcolors, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idcol = jsonObject.getInt("idcol");
                        String namecol = new String(jsonObject.optString("namecolor").getBytes("ISO-8859-1"), "UTF-8");
                        float pricecol = (float) jsonObject.getDouble("extraprice");
                        boolean statuscol = jsonObject.getBoolean("status");

                        Colors cl = new Colors(idcol, namecol, pricecol,statuscol);
                        mList.add(cl);
                        adapterColors = new AdapterColors(ColorsAdminActivity.this, mList);
                        lstMauSac.setAdapter(adapterColors);
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
                Toast.makeText(ColorsAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void themmoi() {
        EditText edtName = findViewById(R.id.edtMauSac);
        EditText edtPrice = findViewById(R.id.edtGiaMau);
        RadioButton rdHien = findViewById(R.id.radHienMS);
        RadioButton rdAn = findViewById(R.id.radAnMS);

        Boolean check = true;
        if (rdHien.isChecked()) {
            check = true;
        } else if (rdAn.isChecked()) {
            check = false;
        }
        String namecol = edtName.getText().toString().trim();
        String pricecol = edtPrice.getText().toString().trim();
        String statusStr = String.valueOf(check);
        if (namecol.equals("") || pricecol.equals("")) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonObj = new JSONObject();
            Colors cl = new Colors();
            try {
                jsonObj.put("idcol", cl.getIdcol());
                jsonObj.put("namecolor", namecol);
                jsonObj.put("extraprice", pricecol);
                jsonObj.put("status", statusStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.getcolors, jsonObj, new Response.Listener<JSONObject>() {
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
                    Toast.makeText(getApplicationContext(), "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
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