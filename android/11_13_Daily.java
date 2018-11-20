package com.example.com418.datastorage;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class Daily extends AppCompatActivity {
    String fileName="";
    DatePicker dp;
    EditText et;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily);
        dp=(DatePicker)findViewById(R.id.datePicker);
        et=(EditText)findViewById(R.id.daily_text);
        bt=(Button)findViewById(R.id.daily_btn);
        Calendar cal=Calendar.getInstance();
        int cYear=cal.get(Calendar.YEAR);
        int cMonth=cal.get(Calendar.MONTH);
        int cDay=cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_"
                        + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth) + ".txt";
                String str = readDiary(fileName);

                et.setText(str);
                bt.setEnabled(true);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = null;
                    try {
                        outFs = openFileOutput(fileName,MODE_PRIVATE);
                        String str = et.getText().toString();
                        outFs.write(str.getBytes());
                        outFs.close();
                        Toast.makeText(getApplicationContext(),
                                fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // FileOutputStream outFs = new FileOutputStream(new File(getFilesDir(), fileName));

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),
                            "error:"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            // inFs=new  FileInputStream(new File(getFilesDir(), fileName));
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            bt.setText("수정 하기");
        } catch (IOException e) {
            et.setHint("일기 없음");
            bt.setText("새로 저장");
        }
        return diaryStr;
    }
}
