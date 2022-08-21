package com.example.project.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project.Activity.Admin.ProductAdminActivity;
import com.example.project.DB.Server;
import com.example.project.Entity.Category;
import com.example.project.Entity.Colors;
import com.example.project.Entity.Memory;
import com.example.project.Entity.Product;
import com.example.project.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterProduct extends BaseAdapter {
    Context context;
    ArrayList<Product> mList;
    private List<Category> listC = new ArrayList<>();
    private List<Colors> listCl = new ArrayList<>();
    private List<Memory> listM = new ArrayList<>();

    public AdapterProduct(Context context, ArrayList<Product> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_product, null, true);
            viewHolder.txtName = convertView.findViewById(R.id.txtTenSanPhamAdmin);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtGiaAdmin);
            viewHolder.txtQuantity = convertView.findViewById(R.id.txtSoLuongAdmin);
            viewHolder.txtDes = convertView.findViewById(R.id.txtMoTaAdmin);
            viewHolder.imgImagePro = convertView.findViewById(R.id.imgAnhDaiDienAdmin);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product p = (Product) mList.get(position);
        viewHolder.txtName.setText(p.getNameproduct()+"");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        viewHolder.txtPrice.setText(fmt.format(p.getPrice()));
        viewHolder.txtQuantity.setText(p.getQuantity() + "");
        viewHolder.txtDes.setText(p.getOtherparameters());
        Picasso.get().load(p.getImagelist()).into(viewHolder.imgImagePro);

        viewHolder.imgdel = (ImageView) convertView.findViewById(R.id.imgXoaSanPhamAdmin);
        viewHolder.imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProduct(position);
            }
        });


        viewHolder.imgedit = (ImageView) convertView.findViewById(R.id.imgSuaSanPhamAdmin);
        viewHolder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(position);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public TextView txtName, txtPrice, txtQuantity, txtDes, tvUpdate;
        public ImageView imgdel, imgedit, imgImagePro;
        public EditText edtName, edtPrice, edtQuantity, edtSizeScreen, edtScreenTech, edtRCAM, edtFCAM, edtChip, edtSIM, edtOS, edtlink, edtDes;
        public RadioButton rdHien, rdAn;
    }

    public void removeProduct(int position) {
        Product p = mList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có chắc chắn muốn xóa muốn xóa " + p.getNameproduct() + " ?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("idpro", p.getIdpro());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Server.getproduct + p.getIdpro(), jsonObj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, ProductAdminActivity.class);
                                context.startActivity(intent);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                        requestQueue.add(request);
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Thông báo !");
        alert.show();
    }

    private void dialogUpdate(int position) {
        Product p = (Product) mList.get(position);
        ViewHolder viewHolder = new ViewHolder();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_sanpham, null, true);
        viewHolder.edtName = view.findViewById(R.id.edtTenDTSua);
        viewHolder.edtPrice = view.findViewById(R.id.edtGiaDTSua);
        viewHolder.edtQuantity = view.findViewById(R.id.edtSoLuongDTSua);
        viewHolder.edtSizeScreen = view.findViewById(R.id.edtManHinhSua);
        viewHolder.edtScreenTech = view.findViewById(R.id.edtCNManHinhSua);
        viewHolder.edtRCAM = view.findViewById(R.id.edtCAMTruocSua);
        viewHolder.edtFCAM = view.findViewById(R.id.edtCAMSauSua);
        viewHolder.edtChip = view.findViewById(R.id.edtChipSua);
        viewHolder.edtSIM = view.findViewById(R.id.edtSIMSua);
        viewHolder.edtOS = view.findViewById(R.id.edtOSSua);
        viewHolder.edtlink = view.findViewById(R.id.edtlinkAnhSua);
        viewHolder.edtDes = view.findViewById(R.id.edtDesproSua);
        viewHolder.rdHien = view.findViewById(R.id.radHienDTSua);
        viewHolder.rdAn = view.findViewById(R.id.radAnDTSua);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        viewHolder.edtName.setText(p.getNameproduct());
        viewHolder.edtPrice.setText(p.getPrice() + "");
        viewHolder.edtQuantity.setText(p.getQuantity() + "");
        viewHolder.edtSizeScreen.setText(p.getSizecreen());
        viewHolder.edtScreenTech.setText(p.getScreentechnology());
        viewHolder.edtRCAM.setText(p.getRearcamera());
        viewHolder.edtFCAM.setText(p.getFrontcamera());
        viewHolder.edtChip.setText(p.getChipset());
        viewHolder.edtSIM.setText(p.getSim());
        viewHolder.edtOS.setText(p.getOs());
        viewHolder.edtlink.setText(p.getImagelist());
        viewHolder.edtDes.setText(p.getOtherparameters());

        viewHolder.tvUpdate = view.findViewById(R.id.tvSuaSanPham);
        viewHolder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namepro = viewHolder.edtName.getText().toString().trim();
                String pricepro = viewHolder.edtPrice.getText().toString().trim();
                String quantitypro = viewHolder.edtQuantity.getText().toString().trim();
                String sizescreenpro = viewHolder.edtSizeScreen.getText().toString().trim();
                String screentechpro = viewHolder.edtScreenTech.getText().toString().trim();
                String rcampro = viewHolder.edtRCAM.getText().toString().trim();
                String fcampro = viewHolder.edtFCAM.getText().toString().trim();
                String chippro = viewHolder.edtChip.getText().toString().trim();
                String simpro = viewHolder.edtSIM.getText().toString().trim();
                String ospro = viewHolder.edtOS.getText().toString().trim();
                String linkpro = viewHolder.edtlink.getText().toString().trim();
                String despro = viewHolder.edtDes.getText().toString().trim();
                Boolean check = true;
                if (viewHolder.rdHien.isChecked()) {
                    check = true;
                } else if (viewHolder.rdAn.isChecked()) {
                    check = false;
                }
                String statusStr = String.valueOf(check);
                if (namepro.equals("") || pricepro.equals("") || quantitypro.equals("") || sizescreenpro.equals("") || screentechpro.equals("") || rcampro.equals("") ||
                        fcampro.equals("") || chippro.equals("") || simpro.equals("") || ospro.equals("") || linkpro.equals("") || despro.equals("")) {
                    Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(context);
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put("idpro", p.getIdpro());
                        jsonObj.put("nameproduct", namepro);
                        jsonObj.put("price", pricepro);
                        jsonObj.put("quantity", quantitypro);
                        jsonObj.put("sizecreen", sizescreenpro);
                        jsonObj.put("screentechnology", screentechpro);
                        jsonObj.put("rearcamera", rcampro);
                        jsonObj.put("frontcamera", fcampro);
                        jsonObj.put("chipset", chippro);
                        jsonObj.put("sim", simpro);
                        jsonObj.put("os", ospro);
                        jsonObj.put("imagelist", linkpro);
                        jsonObj.put("otherparameters", despro);
                        jsonObj.put("status", statusStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Server.getproduct, jsonObj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, "Đã sửa thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, ProductAdminActivity.class);
                            dialog.dismiss();
                            context.startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    queue.add(request);
                }
            }
        });

        ((TextView) dialog.findViewById(R.id.TVHuy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
