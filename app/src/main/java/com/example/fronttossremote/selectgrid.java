package com.example.fronttossremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class selectgrid extends AppCompatActivity implements View.OnClickListener {

    public int i; // initializes i

    int Number_of_Baseballs = NumberofBaseballs.getNumberofBaseballs(); // Set number of balls from NumberofBaseballs class

    //public static byte [] Baseball_Positions = new byte [Number_of_Baseballs]; // Initialize an array of bytes

    public static byte [] Baseball_Positions = new byte [NumberofBaseballs.getNumberofBaseballs()]; // Initialize an array of bytes

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
                Baseball_Positions[i] = (byte) 49; // Set ith array element to char 1
                ++i; // Increment i
                break;
            case R.id.Number_2:
                Baseball_Positions[i] = (byte) 50; // Set ith array element to char 2
                ++i;
                break;
            case R.id.Number_3:
                Baseball_Positions[i] = (byte) 51; // Set ith array element to char 3
                ++i;
                break;
            case R.id.Number_4:
                Baseball_Positions[i] = (byte) 52; // Set ith array element to char 4
                ++i;
                break;
            case R.id.Number_5:
                Baseball_Positions[i] = (byte) 53; // Set ith array element to char 5
                ++i;
                break;
            case R.id.Number_6:
                Baseball_Positions[i] = (byte) 54; // Set ith array element to char 6
                ++i;
                break;
            case R.id.Number_7:
                Baseball_Positions[i] = (byte) 55; // Set ith array element to char 7
                ++i;
                break;
            case R.id.Number_8:
                Baseball_Positions[i] = (byte) 56; // Set ith array element to char 8
                ++i;
                break;
            case R.id.Number_9:
                Baseball_Positions[i] = (byte) 57; // Set ith array element to char 9
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