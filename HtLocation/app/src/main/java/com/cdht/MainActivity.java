package com.cdht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cdht.Service.LocationService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        btn= (Button) findViewById(R.id.btn);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent intent=new Intent(MainActivity.this, LocationService.class);
                startService(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
