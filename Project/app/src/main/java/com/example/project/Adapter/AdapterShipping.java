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
import com.example.project.Activity.Admin.ShippingAdminActivity;
import com.example.project.DB.Server;
import com.example.project.Entity.Shipping;
import com.example.project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterShipping extends BaseAdapter {
    Context context;
    ArrayList<Shipping> mList;

    public AdapterShipping(Context context, ArrayList<Shipping> object) {
        this.context = context;
        this.mList = object;
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
            convertView = inflater.inflate(R.layout.item_shipping, null, true);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtGiaoHang);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txtPhiShip);
            viewHolder.txtStatus = (TextView) convertView.findViewById(R.id.txtTrangThaiSP);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Shipping sp = mList.get(position);
        viewHolder.txtName.setText(sp.getNameshipping());
        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
        viewHolder.txtPrice.setText(fmt.format(sp.getPrice()));
        if (sp.isStatus() == true) {
            viewHolder.txtStatus.setText("Hiện");
        } else {
            viewHolder.txtStatus.setText("Ẩn");
        }

        convertView.setClickable(true);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdate(position);
            }
        });

        viewHolder.imgdel = (ImageView) convertView.findViewById(R.id.imgXoaSP);
        viewHolder.imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeShippping(position);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        public TextView txtName, txtPrice, txtStatus, tvUpdate;
        public ImageView imgdel;
        public EditText edtName, edtPrice;
        private RadioButton rdHien, rdAn;
    }

    public void removeShippping(int position) {
        Shipping sp = mList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có chắc chắn muốn xóa muốn xóa " + sp.getNameshipping() + " ?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("id", sp.getId());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Server.getpshipping + sp.getId(), jsonObj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, ShippingAdminActivity.class);
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

    public void dialogUpdate(int position) {
        ViewHolder viewHolder = new ViewHolder();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_giaohang, null, true);

        viewHolder.edtName = (EditText) view.findViewById(R.id.edtGiaoHangSua);
        viewHolder.edtPrice = (EditText) view.findViewById(R.id.edtPhiShipSua);
        viewHolder.rdHien = (RadioButton) view.findViewById(R.id.radHienSPSua);
        viewHolder.rdAn = (RadioButton) view.findViewById(R.id.radAnSPSua);

        Shipping sp = (Shipping) mList.get(position);
        viewHolder.edtName.setText(sp.getNameshipping());
        viewHolder.edtPrice.setText(""+sp.getPrice());
        if (sp.isStatus() == true) {
            viewHolder.rdHien.setChecked(true);
        } else if (sp.isStatus() == false) {
            viewHolder.rdAn.setChecked(true);
        }

        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        viewHolder.tvUpdate = (TextView) view.findViewById(R.id.tvGiaoHangSua);
        viewHolder.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = viewHolder.edtName.getText().toString().trim();
                String price = viewHolder.edtPrice.getText().toString().trim();
                Boolean check = true;
                if (viewHolder.rdHien.isChecked()) {
                    check = true;
                } else if (viewHolder.rdAn.isChecked()) {
                    check = false;
                }
                String statusStr = String.valueOf(check);
                if (name.equals("") || price.equals("")) {
                    Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    JSONObject jsonObj = new JSONObject();
                    try {
                        jsonObj.put("id", sp.getId());
                        jsonObj.put("nameshipping", name);
                        jsonObj.put("price", price);
                        jsonObj.put("status", statusStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Server.getpshipping, jsonObj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, "Đã sửa thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, ShippingAdminActivity.class);
                            context.startActivity(intent);
                            dialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    requestQueue.add(request);
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
