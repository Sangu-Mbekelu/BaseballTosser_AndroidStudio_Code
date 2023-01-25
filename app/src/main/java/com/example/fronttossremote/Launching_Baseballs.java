package com.example.fronttossremote;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Objects;


public class Launching_Baseballs extends AppCompatActivity {

    public BluetoothSocket btSocket = FrontPage.btSocket;

    public int modeSelect = mode_select.modeSelect;

    public int[] BaseballsArray = ListPage.BaseballsArray;

    public int[] BaseballsPosition = ListPage.BaseballsPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launching_baseballs);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        if(modeSelect == 1)
        {
            startThread();
        } else if(modeSelect == 2){
            startThread();
        } else if(modeSelect == 4){
            startThread2();
        } else{
            Toast.makeText(Launching_Baseballs.this, "MODE ERROR", Toast.LENGTH_LONG).show();
        }
    }

    public void startThread() {
        CommThread thread = new CommThread();
        thread.start();
    }

    public void startThread2() {
        CommThread2 thread = new CommThread2();
        thread.start();
    }

    //--------------------------------------------------------------
    public class CommThread extends Thread{

        @Override
        public void run() {

            boolean Arduino_Response = false;

            while(!Arduino_Response)
            {
                try {
                    InputStream inputStream = btSocket.getInputStream();

                    byte Response = (byte) inputStream.read(); //Reads one byte from the stream

                    if(Response == 49)
                    {
                        Arduino_Response = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            open_mode_select();
        }
    }

    public class CommThread2 extends Thread{

        @Override
        public void run() {

                for(int j=0; j<9; ++j)
                {
                    try {
                        OutputStream outputStream = btSocket.getOutputStream(); // Initialize output stream
                        outputStream.write((byte)BaseballsPosition[j]); // OUTPUT the value of the position of baseballs
                        System.out.println(BaseballsPosition[j]);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }

                    try {
                        OutputStream outputStream = btSocket.getOutputStream(); // Initialize output stream
                        outputStream.write((byte)BaseballsArray[j]); // OUTPUT the value of the submitted number of baseballs
                        System.out.println(BaseballsArray[j]);
                    } catch (IOException k) {
                        k.printStackTrace();
                    }
                }

            boolean Arduino_Response = false;

            while(!Arduino_Response)
            {
                try {
                    InputStream inputStream = btSocket.getInputStream();

                    byte Response = (byte) inputStream.read(); //Reads one byte from the stream

                    if(Response == 49)
                    {
                        Arduino_Response = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            open_mode_select();
        }
    }

    //--------------------------------------------------------------
    // Opens the FrontPage Activity
    public void open_mode_select() {
        Intent intent = new Intent(this, mode_select.class); //Creates intent to Front Page Activity
        startActivity(intent);
    }

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }
}
