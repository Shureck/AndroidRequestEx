package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SwitchClass extends AppCompatActivity {

    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        aSwitch = findViewById(R.id.switch1);

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(aSwitch.getId());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(SwitchClass.this, "pause", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(SwitchClass.this, "destroy", Toast.LENGTH_SHORT);
    }
}
