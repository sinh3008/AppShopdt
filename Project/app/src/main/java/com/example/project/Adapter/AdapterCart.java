package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Entity.BillDetails;
import com.example.project.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterCart extends BaseAdapter {
    Context context;
    ArrayList<BillDetails> arrayCart;

    public AdapterCart(Context context, ArrayList<BillDetails> arrayCart) {
        this.context = context;
        this.arrayCart = arrayCart;
    }

    @Override
    public int getCount() {
        return arrayCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder {
        public TextView txtCartName, txtCartPrice, txtCartNumber, txtCartTang, txtCartGiam, txtCartTotalPrice, txtCartDelete;
        public ImageView imgCartImages;
        public CheckBox cbCartChon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cart, null);
            viewHolder.txtCartName = convertView.findViewById(R.id.itemCartNameProduct);
            viewHolder.txtCartPrice = convertView.findViewById(R.id.itemCartPriceProduct);
            viewHolder.txtCartNumber = convertView.findViewById(R.id.itemCartNumberProduct);
            viewHolder.txtCartTang = convertView.findViewById(R.id.itemCartTang);
            viewHolder.txtCartGiam = convertView.findViewById(R.id.itemCartGiam);
            viewHolder.txtCartTotalPrice = convertView.findViewById(R.id.itemCartTotalPrice);
            viewHolder.txtCartDelete = convertView.findViewById(R.id.itemCartDelete);
            viewHolder.imgCartImages = convertView.findViewById(R.id.itemCartImageProduct);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BillDetails b = (BillDetails) getItem(position);
        viewHolder.txtCartName.setText(b.getNameproduct());

        NumberFormat fmt = NumberFormat.getCurrencyInstance(new Locale("VI", "VN"));
        viewHolder.txtCartPrice.setText(fmt.format(b.getPrice()));

        viewHolder.txtCartNumber.setText(b.getQuantity()+"");
        return convertView;
    }
}
