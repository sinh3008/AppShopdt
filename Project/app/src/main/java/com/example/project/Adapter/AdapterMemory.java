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
import com.example.project.Activity.Admin.ColorsAdminActivity;
import com.example.project.Activity.Admin.MemoryAdminActivity;
import com.example.project.DB.Server;
import com.example.project.Entity.Colors;
import com.example.project.Entity.Memory;
import com.example.project.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterMemory extends BaseAdapter {
    Context context;
    ArrayList<Memory> mList;

    public AdapterMemory(Context context, ArrayList<Memory> mList) {
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
            convertView = inflater.inflate(R.layout.item_memory, null, true);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtDungLuong);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txtGiaDL);
            viewHolder.txtStatus = (TextView) convertView.findViewById(R.id.txtTrangThaiDL);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Memory m = mList.get(position);
        viewHolder.txtName.setText(m.getNamememory());
        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
        viewHolder.txtPrice.setText(fmt.format(m.getExtraprice()));
        if (m.isStatus() == true) {
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

        viewHolder.imgdel = (ImageView) convertView.findViewById(R.id.imgXoaDungLuongAdmin);
        viewHolder.imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMemory(position);
            }
        });

        return convertView;
    }
    public class ViewHolder {
        public TextView txtName,txtPrice,txtStatus, tvUpdate;
        public ImageView imgdel,imgedit;
        public EditText edtName,edtPrice;
        private RadioButton rdHien, rdAn;
    }


    public void removeMemory(int position){
        Memory m = mList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có chắc chắn muốn xóa muốn xóa " + m.getNamememory() + " ?")
                .setCancelable(false)
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        JSONObject jsonObj = new JSONObject();
                        try {
                            jsonObj.put("idmem", m.getIdmem());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Server.getmemory + m.getIdmem(), jsonObj, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(context, MemoryAdminActivity.class);
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

    public void dialogUpdate(int position){
        ViewHolder viewHolder = new ViewHolder();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_dungluong, null, true);

        Memory m = (Memory) mList.get(position);
        viewHolder.edtName = (EditText) view.findViewById(R.id.edtDungLuongSua);
        viewHolder.edtPrice = (EditText) view.findViewById(R.id.edtGiaDLSua);
        viewHolder.rdHien = (RadioButton) view.findViewById(R.id.radAnDLSua);
        viewHolder.rdAn = (RadioButton) view.findViewById(R.id.radAnDLSua);

        viewHolder.edtName.setText(m.getNamememory());
        viewHolder.edtPrice.setText(m.getExtraprice()+"");
        if (m.isStatus() == true) {
            viewHolder.rdHien.setChecked(true);
        } else if (m.isStatus() == false) {
            viewHolder.rdAn.setChecked(true);
        }
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        viewHolder.tvUpdate = (TextView) view.findViewById(R.id.tvSuaDungLuong);
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
                        jsonObj.put("idmem", m.getIdmem());
                        jsonObj.put("namememory", name);
                        jsonObj.put("extraprice", price);
                        jsonObj.put("status", statusStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Server.getmemory, jsonObj, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(context, "Đã sửa thành công", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, MemoryAdminActivity.class);
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
