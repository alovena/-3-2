package com.example.com418.datastorage;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button saveBtn;
    Button loadBtn;
    Button clearBtn;
    Button externalSave;
    Button externalLoad;
    static final int MY_PERMISSION_REQUEST_STORAGE=500;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText = (EditText) findViewById(R.id.TextEdit);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        loadBtn = (Button) findViewById(R.id.Load);
        clearBtn = (Button) findViewById(R.id.Clear);
        externalSave=(Button)findViewById(R.id.externalSave);
        externalLoad=(Button)findViewById(R.id.externalLoad);
        checkPermission();
        externalSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                externalSave(view);
            }
        });
        externalLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                externalLoad(view);
            }
        });
        final String strSDPath= Environment.getExternalStorageDirectory().getAbsolutePath();
        final String StrSDPath2=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
        InputStream infs=getResources().openRawResource(R.raw.abc);
        Log.d("path",strSDPath);
        Log.d("path",StrSDPath2);
        try {
            FileOutputStream outfs=new FileOutputStream(new File(strSDPath,"hanbat.png"));
            FileOutputStream outfs2=new FileOutputStream(new File(StrSDPath2,"hanbat.png"));
            int c;
            while((c=infs.read()) != -1){
                outfs.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void internalLoad(View v){
            try {
                FileInputStream inFs=openFileInput("internal.txt");
                byte []txt=new byte[500];
                inFs.read(txt);
                inFs.close();
                Toast.makeText(getApplicationContext(),txt+"",Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void internalSave(View v){
        FileOutputStream outFs= null;
        try {
            outFs = openFileOutput("internal.txt",MODE_PRIVATE);
            String str=editText.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void put(View v) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("silentMode", "값넣었지");
        editor.commit();
    }
    public void load(View v) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String Str=settings.getString("silentMode","");
        editText.setText(Str+"");
    }
    // We need an Editor object to make preference changes.
    // All objects are from android.context.Context
    public void externalSave(View v){
        File myDir=new File(getExternalFilesDir(null).getAbsolutePath()+"/mydir");
        myDir.mkdir();

        Log.d("mkdir",myDir.toString());


        try {
            FileOutputStream fos=new FileOutputStream(new File(myDir,"external.txt"));
            String str="Hello world!!!";
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//external.txt 파일 가져오기
    public void externalLoad(View v){

        try {
                File myDir=new File(getExternalFilesDir(null).getAbsolutePath()+"/mydir");
                FileInputStream fis=new FileInputStream(new File(myDir,"external.txt"));
                byte b[]=new byte[500];
                fis.read(b);
                fis.close();
                String str=new String(b);
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
        }


    }
    private void checkPermission() {

        //Log.d("hwang","check=" + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to write the permission.
                Toast.makeText(this, "Read/Write external storage", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST_STORAGE);

            // MY_PERMISSION_REQUEST_STORAGE is an
            // app-defined int constant

        } else {
            //Log.e(TAG, "permission deny");
            //writeFile();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {


                    // permission was granted,

                } else {

                    Log.d("hwang", "Permission always deny");

                    // permission denied,

                }
                break;
        }
    }
    public void deleteClick(View v){
        //String strSDpaht=Environment.getE
    }
}
