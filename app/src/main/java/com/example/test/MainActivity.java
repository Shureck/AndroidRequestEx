package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    EditText editText;
    ArrayList<String> arrayList = new ArrayList<>();

    private final OkHttpClient client = new OkHttpClient();

    class IOAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return sendData("");
        }

        @Override
        protected void onPostExecute(String response) {
            Log.d("TAG",response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.testList);
        button = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.editbox);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new IOAsyncTask().execute();
//                if(editText.getText().length() > 0) {
//                    arrayList.add(String.valueOf(editText.getText()));
//
//                    if (arrayList.get(arrayList.size()-1).equals("next")){
//                        Intent intent = new Intent(MainActivity.this, SwitchClass.class);
//                        startActivity(intent);
//                    }
//
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
//                            android.R.layout.simple_list_item_1, arrayList);
//
//                    listView.setAdapter(adapter);
//                }
//                editText.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
            }
        });


    }

    public String sendData(String str){
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("LatLong", str)
                    .build();

            Request request = new Request.Builder()
                    .url("https://wttr.in/moscow")
                    .get()
                    //.post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}