package com.example.serveyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity {





   static ArrayAdapter<String>arrayAdapter;
   static Map<String,String>a=new HashMap<String, String>();

   static SQLiteDatabase db;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.dashmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId())
         {
             case R.id.startagain:
                 Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(intent);
                 return  true;
                 default:return false;

         }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.i("content",  String.valueOf(     MainActivity.content.size()));

        db = this.openOrCreateDatabase("FormEntry", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS entry(id INTEGER PRIMARY KEY,hry VARCHAR,phnno VARCHAR,address VARCHAR,likeproduct VARCHAR,useproduct VARCHAR)");
        Intent intent=getIntent();

        int b=intent.getIntExtra("b",0);
        a.clear();

        ListView listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,MainActivity.content);
        listView.setAdapter(arrayAdapter);

      if(b!=0) {


          String sql = ("INSERT INTO entry(hry,phnno,address,likeproduct,useproduct)VALUES(?,?,?,?,?)");
          //db.execSQL("DELETE FROM entry");
          SQLiteStatement statement = db.compileStatement(sql);
          statement.bindString(1, MainActivity.form.get("hry"));
          statement.bindString(2, MainActivity.form.get("phnno"));
          statement.bindString(3, MainActivity.form.get("address"));
          statement.bindString(4, MainActivity.form.get("likeproduct"));
          statement.bindString(5, MainActivity.form.get("useproduct"));
          statement.execute();
          MainActivity.listitem += 1;
          SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.serveyapp", MODE_PRIVATE);
          sharedPreferences.edit().putInt("listitem", MainActivity.listitem).apply();
      }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a.clear();
                //Log.i("id",String.valueOf(i+1));
                Cursor c=db.rawQuery("SELECT * FROM entry where id="+(i+1),null);
                if(c.moveToFirst()){
                   a.put("hry",c.getString(c.getColumnIndex("hry")));
                    a.put("phnno",c.getString(c.getColumnIndex("phnno")));
                    a.put("address",c.getString(c.getColumnIndex("address")));
                    a.put("likeproduct",c.getString(c.getColumnIndex("likeproduct")));
                    a.put("useproduct",c.getString(c.getColumnIndex("useproduct")));
                  // Log.i("helo:",c.getString(c.getColumnIndex("phnno"))+c.getString(c.getColumnIndex("address"))+c.getString(c.getColumnIndex("likeproduct"))+c.getString(c.getColumnIndex("useproduct")));
                    Intent intent=new Intent(getApplicationContext(),selectedItem.class);
                    intent.putExtra("index",i+1);
                    startActivity(intent);
                }

            }
        });







    }
}
