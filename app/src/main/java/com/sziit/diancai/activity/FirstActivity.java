package com.sziit.diancai.activity;

import com.sziit.diancai.R;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        requestPermissions();
        Button login = (Button) findViewById(R.id.first_loginbtn);
        Button register = (Button) findViewById(R.id.first_registerbtn);
        Button set = (Button) findViewById(R.id.first_setbtn);
        //登陆按钮跳转
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //注册按钮跳转
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //设置按钮跳转
        set.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    //	检查和申请权限
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(FirstActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {//如果没有获得权限


            ActivityCompat.requestPermissions(FirstActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "授权成功!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "授权失败!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}
