package com.example.com418.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button secondBtn;
    Button secondBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        secondBtn = (Button) findViewById(R.id.secondBtn);
        secondBack = (Button) findViewById(R.id.secondBack);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("first","hello First");
                intent.putExtra("second",10);
                intent.putExtra("third",20);
                startActivityForResult(intent,3);
            }
        });

        secondBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int result=data.getIntExtra("result",0);
        Toast.makeText(getApplicationContext(),result+"결과입니다."+requestCode+"요청코드"+resultCode+"응답코드",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("hs","onstart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("hs","onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("hs","onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("hs","onStop");
    }
}
