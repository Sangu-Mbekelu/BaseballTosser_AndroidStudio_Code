package com.example.fronttossremote;

import static java.lang.Integer.*;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class NumberofBaseballs extends AppCompatActivity {

    private static int num_baseballs;

    public int modeSelect = mode_select.modeSelect;

    public static int getNumberofBaseballs(){ return num_baseballs; }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numberof_baseballs);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        BluetoothSocket btSocket = FrontPage.btSocket; //Acquire the Bluetooth socket that was opened in the last Activity

        EditText numberofbaseballs = findViewById(R.id.Number_of_Baseballs); // Defining the text editing line
        Button Enterbtn = findViewById(R.id.Enter); // Defining the Enter button

        Enterbtn.setOnClickListener(view -> {
            String num_bbs = numberofbaseballs.getText().toString(); // WHEN ENTER BUTTON PRESSED
            num_baseballs = parseInt(num_bbs);
            if(num_baseballs > 0 && num_baseballs < 26) // Any value below 1 and above 25 is invalid
            {
                try {
                    OutputStream outputStream = btSocket.getOutputStream(); // Initialize output stream
                    outputStream.write((byte)num_baseballs); // OUTPUT the value of the submitted number of baseballs
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(modeSelect == 1) {
                    openSelectGrid(); //If the mode selected is PRESET, Open the Position Selection Activity
                } else if(modeSelect == 2){
                    openLaunching_Baseballs(); //If the mode selected is RANDOM, Open the Wait (Launching) Activity
                } else {
                    Toast.makeText(NumberofBaseballs.this, "MODE ERROR", Toast.LENGTH_SHORT).show(); // Any other value for modeselect is an Error
                }
            }else{
                Toast.makeText(NumberofBaseballs.this, "Invalid Entry", Toast.LENGTH_SHORT).show(); // Any value below 1 and above 25 is invalid
            }
        });
    }

    // FUNCTIONS TO OPEN NEW ACTIVITIES =============================================
    public void openSelectGrid() {
        Intent intent = new Intent(this, selectgrid.class); //Creates intent to Position Selection Activity
        startActivity(intent);
    }

    public void openLaunching_Baseballs() {
        Intent intent = new Intent(this, Launching_Baseballs.class); //Creates intent to Wait (Launching) Activity
        startActivity(intent);
    }
    //================================================================================

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }
}