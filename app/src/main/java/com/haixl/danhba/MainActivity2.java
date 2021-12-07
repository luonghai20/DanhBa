package com.haixl.danhba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView img2=findViewById(R.id.img2);
        TextView tv1=findViewById(R.id.name2);
        TextView tv2=findViewById(R.id.phone2);


        Bundle bundle=getIntent().getExtras();
        tv1.setText(bundle.getString("name","loikey"));
        tv2.setText(bundle.getString("phone","loikey"));
        img2.setImageResource(bundle.getInt("img",0));

    }
}