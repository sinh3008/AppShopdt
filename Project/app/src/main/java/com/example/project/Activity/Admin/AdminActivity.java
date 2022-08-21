package com.example.project.Activity.Admin;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.project.Activity.User.LoginActivity;
import com.example.project.Activity.User.ThongtincanhanActivity;
import com.example.project.R;

public class AdminActivity extends AppCompatActivity {
    private static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        CardView cvDanhMucAdmin = findViewById(R.id.cvDanhMucAdmin);
        CardView cvSanPhamAdmin = findViewById(R.id.cvSanPhamAdmin);
        CardView cvUserAdmin = findViewById(R.id.cvUserAdmin);
        CardView cvBills = findViewById(R.id.cvBillAdmin);
        TextView logout = findViewById(R.id.admin_logout);

        showSimpleProgressDialog(this, "Thông báo", "Đang tải....", false);
        removeSimpleProgressDialog();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        cvDanhMucAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CategoryAdminActivity.class);
                startActivity(intent);
            }
        });
        cvSanPhamAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ProductAdminActivity.class);
                startActivity(intent);
            }
        });
        cvUserAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, UserAdminActivity.class);
                startActivity(intent);
            }
        });
        cvBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, BillsAdminActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setMessage("Bạn có chắc chắn muốn thoát không  ?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentLogin = new Intent(AdminActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                                finish();
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
        });
    }

    private static void showSimpleProgressDialog(Context context, String title, String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}