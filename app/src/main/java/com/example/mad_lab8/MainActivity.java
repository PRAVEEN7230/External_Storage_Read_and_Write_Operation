package com.example.mad_lab8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {@SuppressLint("SdCardPath")
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
    final EditText e1=(EditText)findViewById(R.id.editText1); final EditText e2=(EditText)findViewById(R.id.editText2);
    Button b2=(Button)findViewById(R.id.button1);

    Button b1=(Button)findViewById(R.id.button2);
    String path=getPreferences(MODE_PRIVATE).getString("fpath", "/sdcard/file.txt"); e1.setText(path);

    b1.setOnClickListener(
            new OnClickListener()
            {

                @Override
                public void onClick(View arg0) {
// TODO Auto-generated method stub

                    File f=new File(e1.getText().toString());

                    String s="";
                    StringBuilder sb=new StringBuilder();
                    FileReader fr = null;
                    try {
                        fr = new FileReader(f);

                    } catch (FileNotFoundException e) {

//	TODO Auto-generated catch block

                        e.printStackTrace();
                    }
                    BufferedReader br=new BufferedReader(fr);
                    try {
                        while((s=br.readLine())!=null)
                        {
                            sb.append(s+"\n");
                        }
                    } catch (IOException e) {

// TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), "File ReadSuccessfully", Toast.LENGTH_LONG).show();
                    e2.setText(sb);
                }

            });
    b2.setOnClickListener(
            new OnClickListener()
            {
                @Override

                public void onClick(View arg0) {
// TODO Auto-generated method stub

                    File f=new File(e1.getText().toString());

                    FileWriter fw = null;

                    try {
                        fw = new FileWriter(f);
                    } catch (IOException e3) {

//	TODO Auto-generated catch block

                        e3.printStackTrace();
                    }
                    try {

                        fw.write(e2.getText().toString());
                    } catch (IOException e2) {
// TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                    try {

                        fw.close();
                    } catch (IOException e2) {

//	TODO Auto-generated catch block

                        e2.printStackTrace();
                    }
                    SharedPreferences.Editor

                            e=getPreferences(MODE_PRIVATE).edit();
                    e.putString("fpath", f.getPath());
                    e.commit();
                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_LONG).show();

                }
            });
}
}
