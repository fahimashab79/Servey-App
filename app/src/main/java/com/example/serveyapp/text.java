package com.example.serveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class text extends AppCompatActivity {
    EditText editText;
    Button button;
    String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        button=findViewById(R.id.next);
        if(MainActivity.indexno+1==5)
        {

            button.setText("DashBoard");

        }
        TextView textView=(TextView)findViewById(R.id.question);
        editText=(EditText)findViewById(R.id.mobileno);
        String s=MainActivity.Questions.get(MainActivity.indexno);
        textView.setText(s);



    }
    public void next(View view) {

        value=editText.getText().toString();
        MainActivity.form.put("address",value);
        Log.i("Text",value);
        if(MainActivity.Required.get(MainActivity.indexno)){


            if(!value.equals("")){
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
