package com.example.serveyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   static ProgressBar spinner;
   static Button button;
   static int indexno=0;
   static int listitem=0;
   boolean isloading=true;
    static ArrayList<String>content=new ArrayList<>();
    static Map<String, String> form = new HashMap<String, String>();
   static ArrayList<String>Questions=new ArrayList<>();
   static ArrayList<String>Type=new ArrayList<>();
   static ArrayList<String>Options=new ArrayList<>();
   static ArrayList<Boolean>Required=new ArrayList<>();
   public void chooseActivity(View view){
       Intent intent;
       indexno=0;
      content.add("Survey Number:"+(listitem+1));

       if(Type.get(indexno).equals("multiple choice")) {
           intent = new Intent(getApplicationContext(), MultipleChoice.class);
           startActivity(intent);
       }
      else if(Type.get(indexno).equals("number")) {
          intent = new Intent(this, numbers.class);
           startActivity(intent);
       }
       else if(Type.get(indexno).equals("text")) {
            intent = new Intent(this, text.class);
           startActivity(intent);
       }
      else if(Type.get(indexno).equals("Checkbox")) {
            intent = new Intent(this, Checkbox.class);
           startActivity(intent);
       }
       else if(Type.get(indexno).equals("dropdown")) {
            intent = new Intent(this, dropdown.class);
           startActivity(intent);
       }







   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       MenuInflater menuInflater=getMenuInflater();
       menuInflater.inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        Intent intent;
         switch (item.getItemId())
         {
             case R.id.dashboard:
                 intent=new Intent(getApplicationContext(),Dashboard.class);
                 intent.putExtra("b",0);
                 startActivity(intent);
                 return true;



                 default:
                     return false;


         }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.startServey);


        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        if(Questions.isEmpty())
        {
            button.setEnabled(false);
            spinner.setVisibility(View.VISIBLE);
        }

       SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.serveyapp",MODE_PRIVATE);
       Log.i("sharedpref",String.valueOf(sharedPreferences.getInt("listitem",0)));
       if(sharedPreferences.getInt("listitem",0)==0)
       {
           listitem=0;
       }
       else
       {
           listitem=sharedPreferences.getInt("listitem",0);
       }





        if(Questions.isEmpty())
        {
        Questions.clear();
        Options.clear();
        Type.clear();
        Required.clear();
        form.clear();
        if(listitem>0){
            for(int i=1;i<=listitem;i++)
            {
                Log.i("created","item");
                content.add("Survey Number-"+(i));
            }}
        doInBackground task=new doInBackground();
        task.execute("https://example-response.herokuapp.com/getSurvey");



        }




    }


}
