package com.example.project.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.AdapterProductUser;
import com.example.project.DB.Server;
import com.example.project.Entity.Bills;
import com.example.project.Entity.Product;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DanhsachsanphamActivity extends AppCompatActivity {
    private ArrayList<Product> mList = new ArrayList<>();
    private AdapterProductUser adapterProduct;
    private static ProgressDialog mProgressDialog;
    private ListView lstDsSanpham;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView backHome = findViewById(R.id.IVBackToHome);
        lstDsSanpham = findViewById(R.id.lstDsSanPham);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhsachsanphamActivity.this, TrangchuActivity.class);
                startActivity(intent);
            }
        });

        lstDsSanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                Intent intent = new Intent(getApplicationContext(),ChitietsanphamActivity.class);
                int idproduct = mList.get(position).getIdpro();
                intent.putExtra("idproduct", mList.get(position).getIdpro());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        loadSanPham();
        super.onResume();
    }

//    private void CreateBills() {
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//        JSONObject jsonObj = new JSONObject();
//        Bills p = new Bills();
//        try {
//            jsonObj.put("idcustomer", TrangchuActivity.iduser);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.postBills, jsonObj, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(getApplicationContext(), "Thêm bill thành công", Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//        queue.add(request);
//    }

    private void loadSanPham() {
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
                        String nameproduct = jsonObject.getString("nameproduct");
                        float price = (float) jsonObject.getDouble("price");
                        int quantitypro = jsonObject.getInt("quantity");
                        String imagelist = jsonObject.getString("imagelist");
                        String otherparameters = new String(jsonObject.optString("otherparameters").getBytes("ISO-8859-1"), "UTF-8");
                        Product p = new Product(idpro, nameproduct, price, quantitypro, imagelist, otherparameters);
                        mList.add(p);
                        adapterProduct = new AdapterProductUser(DanhsachsanphamActivity.this, mList);
                        lstDsSanpham.setAdapter(adapterProduct);
                        adapterProduct.notifyDataSetChanged();
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
                Toast.makeText(DanhsachsanphamActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
}