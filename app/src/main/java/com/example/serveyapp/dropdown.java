package com.example.serveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class dropdown extends AppCompatActivity {
 TextView textView;
 Button button;

 Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropdown);
        dropdown=findViewById(R.id.spinner);
        button=findViewById(R.id.next);
        if(MainActivity.indexno+1==5)
        {

            button.setText("DashBoard");

        }
        textView=findViewById(R.id.question);
        textView.setText(MainActivity.Questions.get(MainActivity.indexno));
        String strMain = MainActivity.Options.get(MainActivity.indexno);
        String[] items = strMain.split(", ");

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        dropdown.setAdapter(arrayAdapter);



    }
    public void next(View view) {

        MainActivity.form.put("useproduct",dropdown.getSelectedItem().toString());


        if(MainActivity.Required.get(MainActivity.indexno)){


            if(!dropdown.getSelectedItem().toString().equals("")){
                MainActivity.indexno+=1;

                Intent intent;
                if(MainActivity.indexno==5)
                {

                    intent = new Intent(getApplicationContext(), Dashboard.class);
                    intent.putExtra("b",1);
                    startActivity(intent);
                }

                else if(MainActivity.Type.get(MainActivity.indexno).equals("multiple choice")) {
                    intent = new Intent(getApplicationContext(), MultipleChoice.class);
                    startActivity(intent);
                }
                else if(MainActivity.Type.get(MainActivity.indexno).equals("number")) {
                    intent = new Intent(this, numbers.class);
                    startActivity(intent);
                }
                else if(MainActivity.Type.get(MainActivity.indexno).equals("text")) {
                    intent = new Intent(this, text.class);
                    startActivity(intent);
                }
                else if(MainActivity.Type.get(MainActivity.indexno).equals("Checkbox")) {
                    intent = new Intent(this, Checkbox.class);
                    startActivity(intent);
                }
                else if(MainActivity.Type.get(MainActivity.indexno).equals("dropdown")) {
                    intent = new Intent(this, dropdown.class);
                    startActivity(intent);
                }

            }
            else
            {
                Toast.makeText(this, "This filled is required", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            MainActivity.indexno+=1;
            Intent intent;
            if(MainActivity.indexno==5)
            {

                intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }


            else if(MainActivity.Type.get(MainActivity.indexno).equals("multiple choice")) {
                intent = new Intent(getApplicationContext(), MultipleChoice.class);
                startActivity(intent);
            }
            else if(MainActivity.Type.get(MainActivity.indexno).equals("number")) {
                intent = new Intent(this, numbers.class);
                startActivity(intent);
            }
            else if(MainActivity.Type.get(MainActivity.indexno).equals("text")) {
                intent = new Intent(this, text.class);
                startActivity(intent);
            }
            else if(MainActivity.Type.get(MainActivity.indexno).equals("Checkbox")) {
                intent = new Intent(this, Checkbox.class);
                startActivity(intent);
            }
            else if(MainActivity.Type.get(MainActivity.indexno).equals("dropdown")) {
                intent = new Intent(this, dropdown.class);
                startActivity(intent);
            }

        }
    }
}
