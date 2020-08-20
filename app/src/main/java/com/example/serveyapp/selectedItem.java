package com.example.serveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class selectedItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);
        TextView top=findViewById(R.id.toptitle);
        TextView item1=findViewById(R.id.item1);
        TextView item2=findViewById(R.id.item2);
        TextView item3=findViewById(R.id.item3);
        TextView item4=findViewById(R.id.item4);
        TextView item5=findViewById(R.id.item5);

        Intent intent=getIntent();
        int index=intent.getIntExtra("index",0);

        top.setText("Showing result for servey No: "+index);
        Map<String,String>a=Dashboard.a;

        item1.setText("How Are You:"+a.get("hry"));
        item2.setText("Mobile No:"+a.get("phnno"));
        item3.setText("Living Address:"+a.get("address"));
        item4.setText("Do You Like Our Product:"+a.get("likeproduct"));
        item5.setText("How often Use Our Product:"+a.get("useproduct"));




    }
}
