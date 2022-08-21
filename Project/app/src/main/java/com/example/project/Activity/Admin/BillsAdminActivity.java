package com.example.project.Activity.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.project.R;

public class BillsAdminActivity extends AppCompatActivity {
    private ImageView ivMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_admin);

        ImageView backHome = findViewById(R.id.IVBackToHome);
        ivMenu = (ImageView) findViewById(R.id.IVMenuBill);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillsAdminActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBills();
            }
        });
    }

    private void menuBills(){
        PopupMenu popupMenu = new PopupMenu(this, ivMenu);
        popupMenu.getMenuInflater().inflate(R.menu.bills_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.paymentMenu:
                        Intent inAttribute = new Intent(getApplicationContext(), PaymentActivity.class);
                        startActivity(inAttribute);
                        break;
                    case R.id.shippingMenu:
                        Intent inAttributeValues = new Intent(getApplicationContext(), ShippingAdminActivity.class);
                        startActivity(inAttributeValues);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}