package com.example.serveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Checkbox extends AppCompatActivity {
    Button button;
    String value="";

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_yes:
                if (checked){
                 value="Yes";

                }

            else{

                }

                break;
            case R.id.checkbox_no:
                if (checked){

                    value="NO";
                }
                else
                {

                }

                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);
        TextView textView=(TextView)findViewById(R.id.question);
        button=findViewById(R.id.next);

        if(MainActivity.indexno+1==5)
        {

        button.setText("DashBoard");

        }
        textView.setText(MainActivity.Questions.get(MainActivity.indexno));
    }

    public void next(View view) {

        MainActivity.form.put("likeproduct",value);
        Log.i("Checkbox",value);

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
