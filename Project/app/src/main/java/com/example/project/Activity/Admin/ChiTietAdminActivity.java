package com.example.project.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.project.Activity.User.ChitietsanphamActivity;
import com.example.project.DB.Server;
import com.example.project.DB.VolleySingleton;
import com.example.project.Entity.Product;
import com.example.project.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChiTietAdminActivity extends AppCompatActivity {
    private int idproduct;
    public static ArrayList<Product> mList = new ArrayList<>();
    ImageView imgSanPham;
    TextView txtPrdName, txtPrdPrice,txtPrdQuantity, txtPrdSizeScreen, txtPrdScreenTeg, txtPrdCamsau, txtPrdCamtruoc, txtPrdChip, txtPrdSim, txtPrdOs, txtPrdOrtherPara,txtPrdCat,txtPrdCol,txtPrdMem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_admin);

        txtPrdName = findViewById(R.id.txtChitietTenSP);
        txtPrdPrice = (TextView) findViewById(R.id.txtChitietGiaSP);
        txtPrdQuantity = (TextView) findViewById(R.id.txtChitietSoLuong);
        txtPrdSizeScreen = (TextView) findViewById(R.id.txtChitietMH);
        txtPrdScreenTeg = (TextView) findViewById(R.id.txtChitietCMH);
        txtPrdCamsau = (TextView) findViewById(R.id.txtChitietFCAM);
        txtPrdCamtruoc = (TextView) findViewById(R.id.txtChitietRCAM);
        txtPrdChip = (TextView) findViewById(R.id.txtChitietChip);
        txtPrdSim = (TextView) findViewById(R.id.txtChitietSim);
        txtPrdOs = (TextView) findViewById(R.id.txtChitietOs);
        txtPrdCat = (TextView) findViewById(R.id.txtChitietCat);
        txtPrdCol = (TextView) findViewById(R.id.txtChitietCol);
        txtPrdMem = (TextView) findViewById(R.id.txtChitietMem);
        txtPrdOrtherPara = (TextView) findViewById(R.id.txtChitietOtherPara);
        imgSanPham = (ImageView) findViewById(R.id.imgSanPham);
        TextView tvBack = findViewById(R.id.TVBackSanPham);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        loadData();
        super.onResume();
    }

    private void loadData() {
        Intent intent = getIntent();
        idproduct = intent.getIntExtra("idproduct", idproduct);

        StringRequest request = new StringRequest(Request.Method.GET, Server.getproductdetails + idproduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int idpro = jsonObject.getInt("idpro");
                    String nameproduct = new String(jsonObject.optString("nameproduct").getBytes("ISO-8859-1"), "UTF-8");
                    float price = (float) jsonObject.getDouble("price");
                    int quantity = jsonObject.getInt("quantity");
                    String sizecreen = new String(jsonObject.optString("sizecreen").getBytes("ISO-8859-1"), "UTF-8");
                    String screentechnology = new String(jsonObject.optString("screentechnology").getBytes("ISO-8859-1"), "UTF-8");
                    String rearcamera = new String(jsonObject.optString("rearcamera").getBytes("ISO-8859-1"), "UTF-8");
                    String frontcamera = new String(jsonObject.optString("frontcamera").getBytes("ISO-8859-1"), "UTF-8");
                    String chipset = new String(jsonObject.optString("chipset").getBytes("ISO-8859-1"), "UTF-8");
                    String sim = new String(jsonObject.optString("sim").getBytes("ISO-8859-1"), "UTF-8");
                    String os = new String(jsonObject.optString("os").getBytes("ISO-8859-1"), "UTF-8");
                    String namecategory = new String(jsonObject.optString("namecategory").getBytes("ISO-8859-1"), "UTF-8");
                    String namecolor = new String(jsonObject.optString("namecolor").getBytes("ISO-8859-1"), "UTF-8");
                    String namememory = new String(jsonObject.optString("namememory").getBytes("ISO-8859-1"), "UTF-8");
                    String imagelist = new String(jsonObject.optString("imagelist").getBytes("ISO-8859-1"), "UTF-8");
                    String otherparameters = new String(jsonObject.optString("otherparameters").getBytes("ISO-8859-1"), "UTF-8");

                    Product p = new Product(idpro, nameproduct, price,quantity, sizecreen, screentechnology, rearcamera, frontcamera, chipset, sim, os,namecategory,namecolor,namememory,imagelist, otherparameters);
                    mList.add(p);

                    txtPrdName.setText(nameproduct);
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("VI", "VN"));
                    txtPrdPrice.setText(fmt.format(p.getPrice()));
                    txtPrdQuantity.setText(p.getQuantity()+"");
                    txtPrdSizeScreen.setText(sizecreen);
                    txtPrdScreenTeg.setText(screentechnology);
                    txtPrdCamsau.setText(rearcamera);
                    txtPrdCamtruoc.setText(frontcamera);
                    txtPrdChip.setText(chipset);
                    txtPrdSim.setText(sim);
                    txtPrdOs.setText(os);
                    txtPrdCat.setText(namecategory);
                    txtPrdCol.setText(namecolor);
                    txtPrdMem.setText(namememory);
                    txtPrdOrtherPara.setText(otherparameters);
                    Picasso.get().load(imagelist).into(imgSanPham);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChiTietAdminActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idproduct", String.valueOf(idproduct));
                return param;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }
}