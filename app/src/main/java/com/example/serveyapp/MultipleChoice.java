package com.example.serveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MultipleChoice extends AppCompatActivity {
    TextView textView;
    Button button;
    String[] arrSplit;
    String value="";
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.option1:
                if (checked)
                    value=arrSplit[0];
                    break;
            case R.id.option2:
                if (checked)
                    value=arrSplit[1];
                    break;
            case R.id.option3:
                if (checked)
                    value=arrSplit[2];
                    break;
            case R.id.option4:
                if (checked)
                    value=arrSplit[3];
                    break;
            case R.id.option5:
                if (checked)
                    value=arrSplit[4];
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
        textView=(TextView)findViewById(R.id.question);
        textView.setText(MainActivity.Questions.get(MainActivity.indexno));
        String strMain = MainActivity.Options.get(MainActivity.indexno);
        arrSplit = strMain.split(", ");
        RadioGroup radioGroup=findViewById(R.id.radiogrp);
        int c=radioGroup.getChildCount();
        for(int i=0;i<c;i++)
        {
            if(radioGroup.getChildAt(i) instanceof RadioButton)
                ((RadioButton)radioGroup.getChildAt(i)).setText(arrSplit[i]);
        }

        button=findViewById(R.id.next);

        if(MainActivity.indexno+1==5)
        {

            button.setText("DashBoard");

        }



    }

    public void next(View view) {

        MainActivity.form.put("hry",value);
        Log.i("MultipleChoice ",value);

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
    }
}
