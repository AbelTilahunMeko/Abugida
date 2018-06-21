package com.aaitabem.abugida.abugida;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aaitabem.abugida.abugida.View.Login;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    Button changeView;
    Date date = new Date();
    int getSecondAdd = date.getSeconds()+ 20000;
    int getSecondNow = date.getSeconds();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeView = findViewById(R.id.change);

        changeView.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_SMS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 123);
        }

//        String x = String.valueOf(date.getSeconds());
//        Toast.makeText(this, x,Toast.LENGTH_LONG).show();


        while(getSecondNow <= getSecondAdd){
            System.out.println("This is the best ");
            this.getSecondNow = getSecondNow + 1;
        }
        if(getSecondAdd == getSecondNow){
            Toast.makeText(this, "I am here", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onClick(View v){
        if(v == changeView){
            Intent  intent = new Intent(this, Login.class);
            finish();
            startActivity(intent);
        }
    }
}
