package com.example.com418.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    Button thirdBtn,thirdBack;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        thirdBtn=(Button)findViewById(R.id.thirdBtn);
        thirdBack=(Button)findViewById(R.id.thirdBack);
        text=(TextView)findViewById(R.id.resultText);
        Intent i=getIntent();
        if(i!=null){
            String getString=i.getStringExtra("first");
            int num1=i.getIntExtra("second",0);
            int num2=i.getIntExtra("third",0);
            int sum=num1+num2;
            Log.d("합계",sum+"");
            text.setText(getString+":"+sum);

        }

        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //1,2,3,4,1 이 쌓인상태에서 1에서 뒤로가기누르면 4 3 2 1 로가는게 아니라 다 비워버림
                startActivity(intent);
            }
        });
        thirdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                intent.putExtra("result",30);
                setResult(RESULT_OK,intent);
                startActivity(intent);
                finish();
            }
        });

    }
}
