package com.example.vacationproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class XmlActivity extends AppCompatActivity {


    private ListView mainListView;
    private Adapters listAdapter;
    private ArrayList<NewsData> list;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        getRSS();

        mainListView = (ListView) findViewById(R.id.xml_list);
        listAdapter = new Adapters(this, list);

        mainListView.setAdapter(listAdapter);

    }

    private void getRSS() {

        XMLParsing vp = new XMLParsing();
        list = vp.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoadTask.recycleBitmap();
    }
}
