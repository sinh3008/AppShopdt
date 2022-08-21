package com.example.project.Activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.example.project.Activity.Admin.ProductAdminActivity;
import com.example.project.Activity.Admin.ThemDanhMucActivity;
import com.example.project.DB.Server;
import com.example.project.DB.VolleySingleton;
import com.example.project.Entity.BillDetails;
import com.example.project.Entity.Bills;
import com.example.project.Entity.Category;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChitietsanphamActivity extends AppCompatActivity {
    int idproduct, idBills;
    private ArrayList<Product> mList = new ArrayList<>();
    private ListView listviewChitiet;
    private Context context;
    Spinner sp_chitietsp;
    Button txtThemvaogiohang;
    ImageView img_sanpham;
    TextView txtAddtoCard, txtPrdName, txtPrdPrice, txtPrdSizeScreen, txtPrdScreenTeg, txtPrdCamsau, txtPrdCamtruoc, txtPrdChip, txtPrdSim, txtPrdOs, txtPrdOrtherPara, txtPrdCol, txtPrdMem;

    private static final String FILE_NAME = "myFile";
    SharedPreferences sharedPreferences;
    private static int iduser_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);

        Anhxa();
        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        iduser_share = sharedPreferences.getInt("idcustomer", 0);
        idBills = sharedPreferences.getInt("idbill", 0);
//        CreateBills(iduser_share);

        txtAddtoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateBills();
                getBills();
                addtocart();
            }
        });


    }


    public void Anhxa() {
        txtAddtoCard = findViewById(R.id.chitietTxtProductAddtoCart);
        txtPrdName = findViewById(R.id.chitietTxtProductName);
        txtPrdPrice = (TextView) findViewById(R.id.chitietTxtProductPrice);
        txtPrdSizeScreen = (TextView) findViewById(R.id.chitietTxtProductSizeScreen);
        txtPrdScreenTeg = (TextView) findViewById(R.id.chitietTxtProductScreenTeg);
        txtPrdCamsau = (TextView) findViewById(R.id.chitietTxtProductCamsau);
        txtPrdCamtruoc = (TextView) findViewById(R.id.chitietTxtProductCamtruoc);
        txtPrdChip = (TextView) findViewById(R.id.chitietTxtProductChip);
        txtPrdSim = (TextView) findViewById(R.id.chitietTxtProductSim);
        txtPrdOs = (TextView) findViewById(R.id.chitietTxtProductOs);
        txtPrdCol = (TextView) findViewById(R.id.chitietTxtProductCol);
        txtPrdMem = (TextView) findViewById(R.id.chitietTxtProductMem);
        txtPrdOrtherPara = (TextView) findViewById(R.id.chitietTxtProductOtherPara);
        img_sanpham = (ImageView) findViewById(R.id.img_sanpham);
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
                    String sizecreen = new String(jsonObject.optString("sizecreen").getBytes("ISO-8859-1"), "UTF-8");
                    String screentechnology = new String(jsonObject.optString("screentechnology").getBytes("ISO-8859-1"), "UTF-8");
                    String rearcamera = new String(jsonObject.optString("rearcamera").getBytes("ISO-8859-1"), "UTF-8");
                    String frontcamera = new String(jsonObject.optString("frontcamera").getBytes("ISO-8859-1"), "UTF-8");
                    String chipset = new String(jsonObject.optString("chipset").getBytes("ISO-8859-1"), "UTF-8");
                    String sim = new String(jsonObject.optString("sim").getBytes("ISO-8859-1"), "UTF-8");
                    String os = new String(jsonObject.optString("os").getBytes("ISO-8859-1"), "UTF-8");
                    String namecolor = new String(jsonObject.optString("namecolor").getBytes("ISO-8859-1"), "UTF-8");
                    String namememory = new String(jsonObject.optString("namememory").getBytes("ISO-8859-1"), "UTF-8");
                    String imagelist = new String(jsonObject.optString("imagelist").getBytes("ISO-8859-1"), "UTF-8");
                    String otherparameters = new String(jsonObject.optString("otherparameters").getBytes("ISO-8859-1"), "UTF-8");

                    Product p = new Product(idpro, nameproduct, price, sizecreen, screentechnology, rearcamera, frontcamera, chipset, sim, os, namecolor, namememory, imagelist, otherparameters);
                    mList.add(p);
                    txtPrdName.setText(nameproduct);
                    NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("VI", "VN"));
                    txtPrdPrice.setText(fmt.format(p.getPrice()));
                    txtPrdSizeScreen.setText(sizecreen);
                    txtPrdScreenTeg.setText(screentechnology);
                    txtPrdCamsau.setText(rearcamera);
                    txtPrdCamtruoc.setText(frontcamera);
                    txtPrdChip.setText(chipset);
                    txtPrdSim.setText(sim);
                    txtPrdOs.setText(os);
                    txtPrdOrtherPara.setText(otherparameters);
                    Picasso.get().load(imagelist).into(img_sanpham);


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChitietsanphamActivity.this, "Lỗi" + error.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void CreateBills() {

        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject jsonObj = new JSONObject();
        Bills p = new Bills();
        try {
            jsonObj.put("idcustomer", TrangchuActivity.iduser);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.postBills, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
//                    int idbill = jsonObject.getInt("id");
//                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
//                    editor.putInt("idbill", idbill);
//                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Thêm bill thành công", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Bill đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }

    private static int idBillss;

    private void getBills() {
        List<Bills> mLst = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Server.getBillsByIdcus + "/" + TrangchuActivity.iduser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    int idBills = jsonObject.getInt("id");
                    int idcustom = jsonObject.getInt("idcustomer");
                    Bills b = new Bills(idBills, idcustom);
                    mLst.add(b);
                    idBillss = idBills;
                    SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit();
                    editor.putInt("idbill",idBills);
                    editor.apply();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChitietsanphamActivity.this, "Lỗi ở đây", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idbill", String.valueOf(idBillss));
                return param;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(stringRequest);
    }


    private void addtocart() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObj = new JSONObject();
        BillDetails b = new BillDetails();
        try {
            jsonObj.put("idbill", idBills);
            jsonObj.put("idproduct", idproduct);
            jsonObj.put("quantity", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Server.gebilldetails, jsonObj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(ChitietsanphamActivity.this, GiohangActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChitietsanphamActivity.this, "Lỗi này: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }


    public void trovetrangchu(View view) {
        finish();
        Intent intenttrove = new Intent(ChitietsanphamActivity.this, DanhsachsanphamActivity.class);
        startActivity(intenttrove);
    }
}