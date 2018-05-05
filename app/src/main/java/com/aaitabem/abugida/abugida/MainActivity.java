package com.aaitabem.abugida.abugida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aaitabem.abugida.abugida.View.Login;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button changeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeView = findViewById(R.id.change);

        changeView.setOnClickListener(this);
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
