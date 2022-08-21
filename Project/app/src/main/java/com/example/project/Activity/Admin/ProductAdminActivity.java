package com.example.project.Activity.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Activity.User.ChitietsanphamActivity;
import com.example.project.Adapter.AdapterProduct;
import com.example.project.DB.Server;
import com.example.project.Entity.Product;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ProductAdminActivity extends AppCompatActivity {
    private ImageView ivAttribute;
    public static ArrayList<Product> mList = new ArrayList<>();
    private AdapterProduct adapterProduct;
    private static ProgressDialog mProgressDialog;
    private ListView lstDsSanpham;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_admin);

        ImageView backHome = findViewById(R.id.IVBackToHome);
        ivAttribute = (ImageView) findViewById(R.id.IVThuocTinh);
        lstDsSanpham = findViewById(R.id.lstDsSanPhamAdmin);
        ImageView imgThem = findViewById(R.id.imgThemSanPhamAdmin);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductAdminActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        ivAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuAttribute();
            }
        });

        imgThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductAdminActivity.this, ThemSanPhamActivity.class);
                startActivity(intent);
            }
        });

        lstDsSanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietAdminActivity.class);
                int idproduct = mList.get(position).getIdpro();
                intent.putExtra("idproduct", mList.get(position).getIdpro());
                Log.e("a", String.valueOf(idproduct));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        loadSanPham();
        super.onResume();
    }

    public void menuAttribute() {
        PopupMenu popupMenu = new PopupMenu(this, ivAttribute);
        popupMenu.getMenuInflater().inflate(R.menu.attribute_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.colorsMenu:
                        Intent intentColors = new Intent(ProductAdminActivity.this, ColorsAdminActivity.class);
                        startActivity(intentColors);
                        break;
                    case R.id.memoryMenu:
                        Intent intentMemory = new Intent(ProductAdminActivity.this, MemoryAdminActivity.class);
                        startActivity(intentMemory);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void loadSanPham() {
        showSimpleProgressDialog(this, "Thông báo", "Đang tải dữ liệu....", false);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getproduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mList.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idpro = jsonObject.getInt("idpro");
                        String nameproduct = new String(jsonObject.optString("nameproduct").getBytes("ISO-8859-1"), "UTF-8");
                        int idcategory = jsonObject.getInt("idcategory");
                        float pricepro = (float) jsonObject.getDouble("price");
                        int quantitypro = jsonObject.getInt("quantity");
                        String sizecreen = new String(jsonObject.optString("sizecreen").getBytes("ISO-8859-1"), "UTF-8");
                        String screentechnology = new String(jsonObject.optString("screentechnology").getBytes("ISO-8859-1"), "UTF-8");
                        String rearcamera = new String(jsonObject.optString("rearcamera").getBytes("ISO-8859-1"), "UTF-8");
                        String frontcamera = new String(jsonObject.optString("frontcamera").getBytes("ISO-8859-1"), "UTF-8");
                        String chipset = new String(jsonObject.optString("chipset").getBytes("ISO-8859-1"), "UTF-8");
                        String sim = new String(jsonObject.optString("sim").getBytes("ISO-8859-1"), "UTF-8");
                        String os = new String(jsonObject.optString("os").getBytes("ISO-8859-1"), "UTF-8");
                        int idcolor = jsonObject.getInt("idcolor");
                        int idmemory = jsonObject.getInt("idmemory");
                        String imagelist = new String(jsonObject.optString("imagelist").getBytes("ISO-8859-1"), "UTF-8");
                        String otherparameters = new String(jsonObject.optString("otherparameters").getBytes("ISO-8859-1"), "UTF-8");
                        String namecategory = new String(jsonObject.optString("namecategory").getBytes("ISO-8859-1"), "UTF-8");
                        String namecolor = new String(jsonObject.optString("namecolor").getBytes("ISO-8859-1"), "UTF-8");
                        String namememory = new String(jsonObject.optString("namememory").getBytes("ISO-8859-1"), "UTF-8");
                        boolean status = jsonObject.getBoolean("status");
                        Product p = new Product(idpro, idcategory, idcolor, idmemory, quantitypro, nameproduct, sizecreen, screentechnology, rearcamera, frontcamera, chipset, sim, os, imagelist, otherparameters, namecategory, namecolor, namememory, pricepro, status);
                        mList.add(p);

                        adapterProduct = new AdapterProduct(ProductAdminActivity.this, mList);
                        lstDsSanpham.setAdapter(adapterProduct);
                        adapterProduct.notifyDataSetChanged();
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
                Toast.makeText(ProductAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
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