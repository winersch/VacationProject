package com.example.vacationproject;


import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ListView listView1 = (ListView) findViewById(R.id.test_list_1);
        ListView listView2 = (ListView) findViewById(R.id.test_list_2);

        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 100; i ++){
            arrayList.add("시발"+i);
        }


    }

}
