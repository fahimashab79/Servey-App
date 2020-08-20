package com.example.serveyapp;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class doInBackground extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground (String... urls) {

        String results="";
        HttpURLConnection httpURLConnection;
        try {
            URL url=new URL(urls[0]);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            InputStreamReader reader=new InputStreamReader(inputStream);
            int data=reader.read();
            while (data!=-1)
            {
                char character=(char)data;
                results+=character;
                data=reader.read();
            }
            Log.i("results",results);

            JSONArray dataArray=new JSONArray(results);
            for(int i=0;i<dataArray.length();i++)
            {
                JSONObject dataObject=dataArray.getJSONObject(i);
                String question=dataObject.getString("question");
                String type =dataObject.getString("type");
                String options=dataObject.getString("options");
                String required=dataObject.getString("required");
                MainActivity.Questions.add(question);
                MainActivity.Type.add(type);
                MainActivity.Options.add(options);
                MainActivity.Required.add(Boolean.parseBoolean(required));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity.spinner.setVisibility(View.GONE);
        MainActivity.button.setEnabled(true);
        super.onPostExecute(s);
    }
}
