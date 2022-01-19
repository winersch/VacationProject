package com.example.vacationproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = MainActivity.this;

        Log.d("Main", String.valueOf(MainActivity.this));

        Button testBtn = (Button) findViewById(R.id.test_btn);

        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XmlActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("종료하시겠습니까?");
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "종료합니다.", Toast.LENGTH_SHORT).show();

                finish();   //종료
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "종료하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    public synchronized void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "나갔습니다.", Toast.LENGTH_SHORT).show();
    }

    public synchronized void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "들어왔습니다.", Toast.LENGTH_SHORT).show();
    }


}

