package com.example.fronttossremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class mode_select extends AppCompatActivity {

    public static int modeSelect;

    static {
        modeSelect = 0;
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_select);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        BluetoothSocket btSocket = FrontPage.btSocket; // Getting btSocket from Front Page activity

        try {

            OutputStream outputStream = btSocket.getOutputStream(); // Initializes btSocket outstream

            Button preset_Mode = (Button) findViewById(R.id.Preset);
            preset_Mode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        outputStream.write((byte)49);
                        modeSelect = 1;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    openNumberofBaseballs();
                }
            });

            Button List_Mode = (Button) findViewById(R.id.List);
            List_Mode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        outputStream.write((byte)52);
                        modeSelect = 4;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    OpenListEntry();
                }
            });

            Button random_Mode = (Button) findViewById(R.id.Random);
            random_Mode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        outputStream.write((byte)50);
                        modeSelect = 2;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    openNumberofBaseballs();
                }
            });

            Button onDemand_Mode = (Button) findViewById(R.id.OnDemand);
            onDemand_Mode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        outputStream.write((byte)51);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    OpenOnDemandSelect();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openNumberofBaseballs() {
        Intent intent = new Intent(this, NumberofBaseballs.class);
        startActivity(intent);
    }

    public void OpenOnDemandSelect() {
        Intent intent = new Intent(this, SelectGrid_OnDemand.class);
        startActivity(intent);
    }

    public void OpenListEntry() {
        Intent intent = new Intent(this, ListPage.class);
        startActivity(intent);
    }

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }

}
