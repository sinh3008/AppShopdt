package com.example.project.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Adapter.AdapterProductUser;
import com.example.project.DB.Server;
import com.example.project.Entity.Product;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimkiemActivity extends AppCompatActivity {
    ListView listviewTimkiem;
    public ArrayList<Product> mList = new ArrayList<>();
    AdapterProductUser adapterProduct;
    EditText SearchTxtProduct;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timkiem);

        adapterProduct = new AdapterProductUser(TimkiemActivity.this, mList);
        listviewTimkiem = findViewById(R.id.listviewTimkiem);
        listviewTimkiem.setAdapter(adapterProduct);
        loadSanPham();
    }
    private void loadSanPham() {
        Intent intent = getIntent();
        id = intent.getStringExtra("edtTimkiem");
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Server.getproductsearch + id, new Response.Listener<String>() {
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
                Toast.makeText(TimkiemActivity.this, "Bạn chưa tìm gì cả", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                Intent intent = getIntent();
                id = intent.getStringExtra("edtTimkiem");
                param.put("nameproduct",id );
                return param;
            }
        };
        queue.add(request);
    }

    public void Trovetrangchu(View view) {
        finish();
        Intent intent = new Intent(TimkiemActivity.this, TrangchuActivity.class);
        startActivity(intent);
    }

}