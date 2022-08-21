package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.project.Entity.Product;
import com.example.project.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterProductUser extends BaseAdapter {
    Context context;
    ArrayList<Product> mLst;

    public AdapterProductUser(Context context, ArrayList<Product> mLst) {
        this.context = context;
        this.mLst = mLst;
    }

    @Override
    public int getCount() {
        return mLst.size();
    }

    @Override
    public Object getItem(int position) {
        return mLst.get(position);
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
            convertView = inflater.inflate(R.layout.item_product_user, null, true);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtTenSanPham);
            viewHolder.txtPrice = (TextView) convertView.findViewById(R.id.txtGia);
            viewHolder.txtQuantity = (TextView) convertView.findViewById(R.id.txtSoLuong);
            viewHolder.txtDes = (TextView) convertView.findViewById(R.id.txtMoTa);
            viewHolder.imgImagePro = (ImageView) convertView.findViewById(R.id.imgAnhDaiDien);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product p = (Product) mLst.get(position);
        viewHolder.txtName.setText(p.getNameproduct());
        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        viewHolder.txtPrice.setText(fmt.format(p.getPrice()));
        viewHolder.txtQuantity.setText(p.getQuantity() + "");
        viewHolder.txtDes.setText(p.getOtherparameters());
        Picasso.get().load(p.getImagelist()).into(viewHolder.imgImagePro);

        return convertView;
    }

    public class ViewHolder {
        public TextView txtName, txtPrice, txtQuantity, txtDes, tvUpdate;
        public ImageView imgImagePro;
        public EditText edtName;
    }
}
