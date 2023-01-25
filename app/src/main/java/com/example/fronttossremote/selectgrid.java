package com.example.fronttossremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Objects;
import java.io.OutputStream;
import java.io.InputStream;

public class selectgrid extends AppCompatActivity implements View.OnClickListener {

    public int i; // initializes i

    public BluetoothSocket btSocket = FrontPage.btSocket;

    int Number_of_Baseballs = NumberofBaseballs.getNumberofBaseballs(); // Set number of balls from NumberofBaseballs class


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectgrid);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        i = 0; // Sets i to 0 for initialization
        // Initializes and sets a OnClickListener to all of the Views in the UI ========
        Button Onebtn = findViewById(R.id.Number_1);
        Button Twobtn = findViewById(R.id.Number_2);
        Button Threebtn = findViewById(R.id.Number_3);
        Button Fourbtn = findViewById(R.id.Number_4);
        Button Fivebtn = findViewById(R.id.Number_5);
        Button Sixbtn = findViewById(R.id.Number_6);
        Button Sevenbtn = findViewById(R.id.Number_7);
        Button Eightbtn = findViewById(R.id.Number_8);
        Button Ninebtn = findViewById(R.id.Number_9);

        Onebtn.setOnClickListener(this);
        Twobtn.setOnClickListener(this);
        Threebtn.setOnClickListener(this);
        Fourbtn.setOnClickListener(this);
        Fivebtn.setOnClickListener(this);
        Sixbtn.setOnClickListener(this);
        Sevenbtn.setOnClickListener(this);
        Eightbtn.setOnClickListener(this);
        Ninebtn.setOnClickListener(this);
        // =============================================================================
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Number_1:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 49); // Send char 1 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i; // Increment i
                break;
            case R.id.Number_2:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 50); // Send char 2 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_3:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 51); // Send char 3 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_4:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 52); // Send char 4 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_5:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 53); // Send char 5 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_6:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 54); // Send char 6 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_7:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 55); // Send char 7 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_8:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 56); // Send char 8 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
            case R.id.Number_9:
                try {
                    OutputStream outputStream = btSocket.getOutputStream();
                    outputStream.write((byte) 57); // Send char 9 position to Arduino
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ++i;
                break;
        }
        System.out.println(i);
        System.out.println(Number_of_Baseballs);
        if(i==Number_of_Baseballs) // Called when i equals the Number_of_Baseballs to be tossed
        {
            Intent intent = new Intent(this, Launching_Baseballs.class);
            startActivity(intent); //Opens Activity for intent to Wait (Launching) Page
        }
    }

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }
}