package com.example.project.Activity.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.AdapterCategory;
import com.example.project.DB.Server;
import com.example.project.Entity.Category;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryAdminActivity extends AppCompatActivity {
    public static ArrayList<Category> mList = new ArrayList<>();
    private AdapterCategory adapterCategory;
    private static ProgressDialog mProgressDialog;
    private ListView lstDanhMuc;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_admin);

        ImageView backHome = findViewById(R.id.IVBackToHome);
        lstDanhMuc = findViewById(R.id.lstDsDanhMuc);
        ImageView addDanhMuc = findViewById(R.id.imgThemDanhMuc);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryAdminActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        addDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryAdminActivity.this, ThemDanhMucActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        loadDanhmuc();
        super.onResume();
    }

    private void loadDanhmuc() {
        showSimpleProgressDialog(this, "Thông báo", "Đang tải dữ liệu....", false);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getcategory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idcate = jsonObject.getInt("id");
                        String namecate = jsonObject.getString("namecategory");
                        boolean statuscate = jsonObject.getBoolean("status");

                        Category c = new Category(idcate, namecate, statuscate);
                        mList.add(c);
                        adapterCategory = new AdapterCategory(CategoryAdminActivity.this, mList);
                        lstDanhMuc.setAdapter(adapterCategory);
                        removeSimpleProgressDialog();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CategoryAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
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
}