package com.example.fronttossremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class SelectGrid_OnDemand extends AppCompatActivity implements View.OnClickListener{

    public BluetoothSocket btSocket = FrontPage.btSocket;

    public byte Baseball_Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_on_demand);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        Button Onebtn2 = findViewById(R.id.Number1);
        Button Twobtn2 = findViewById(R.id.Number2);
        Button Threebtn2 = findViewById(R.id.Number3);
        Button Fourbtn2 = findViewById(R.id.Number4);
        Button Fivebtn2 = findViewById(R.id.Number5);
        Button Sixbtn2 = findViewById(R.id.Number6);
        Button Sevenbtn2 = findViewById(R.id.Number7);
        Button Eightbtn2 = findViewById(R.id.Number8);
        Button Ninebtn2 = findViewById(R.id.Number9);
        Button Exit = findViewById(R.id.EXIT);

        Onebtn2.setOnClickListener(this);
        Twobtn2.setOnClickListener(this);
        Threebtn2.setOnClickListener(this);
        Fourbtn2.setOnClickListener(this);
        Fivebtn2.setOnClickListener(this);
        Sixbtn2.setOnClickListener(this);
        Sevenbtn2.setOnClickListener(this);
        Eightbtn2.setOnClickListener(this);
        Ninebtn2.setOnClickListener(this);
        Exit.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Number1:
                Baseball_Position = (byte) 49;
                startThread();
                break;
            case R.id.Number2:
                Baseball_Position = (byte) 50;
                startThread();
                break;
            case R.id.Number3:
                Baseball_Position = (byte) 51;
                startThread();
                break;
            case R.id.Number4:
                Baseball_Position = (byte) 52;
                startThread();
                break;
            case R.id.Number5:
                Baseball_Position = (byte) 53;
                startThread();
                break;
            case R.id.Number6:
                Baseball_Position = (byte) 54;
                startThread();
                break;
            case R.id.Number7:
                Baseball_Position = (byte) 55;
                startThread();
                break;
            case R.id.Number8:
                Baseball_Position = (byte) 56;
                startThread();
                break;
            case R.id.Number9:
                Baseball_Position = (byte) 57;
                startThread();
                break;
            case R.id.EXIT:
                Baseball_Position = (byte) 48;

                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write(Baseball_Position);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                open_mode_select();
                break;
        }
    }

    public void startThread() {
        SelectGrid_OnDemand.LaunchandWaitThread thread = new SelectGrid_OnDemand.LaunchandWaitThread();
        thread.start();
    }

    //--------------------------------------------------------------
    public class LaunchandWaitThread extends Thread{

        @Override
        public void run() {
                    try {
                        OutputStream outputStream = btSocket.getOutputStream();
                        outputStream.write(Baseball_Position);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            boolean Arduino_Response = false;

            while(!Arduino_Response)
            {
                try {
                    InputStream inputStream = btSocket.getInputStream();
                    inputStream.skip(inputStream.available());

                    byte Response = (byte) inputStream.read(); //Reads one byte from the stream

                    if(Response == 49)
                    {
                        Arduino_Response = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //--------------------------------------------------------------

    public void open_mode_select() {
        Intent intent = new Intent(this, mode_select.class);
        startActivity(intent);
    }

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }
}