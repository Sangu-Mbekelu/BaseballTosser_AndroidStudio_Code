package com.example.fronttossremote;

import static java.lang.Thread.sleep;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;


public class FrontPage extends AppCompatActivity {

    // Universal number used for all bluetooth serial boards. Used to initialize the bluetooth socket
    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public static BluetoothSocket btSocket; // The BluetoothSocket object

    public byte[] OK = {79, 75};

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        Button bluetooth_Connect = findViewById(R.id.Bluetooth_Connection);

        // MAYBE MAKE THE CONNECTION OF THE BLUETOOTH SOCKET A SEPERATE THREAD
        // AND USE THE BUTTON TO CHECK THE THREAD. IF NOT NULL, MOVE ON?

        if(btSocket == null || !(btSocket.isConnected())) { // When the btSocket does not exisit, or is not connected, attempt to connect again
            startThread(); // Starting Connection Thread
        }

        bluetooth_Connect.setOnClickListener(view -> { // When the Connect Button is clicked...

            try {
                sleep(200); // 200ms built in delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (btSocket != null) {
                try {
                    OutputStream outputStream = btSocket.getOutputStream(); // Output serial bytes of OK to signal to arduino a connection
                    for (int i = 0; i < 2; i++) {
                        outputStream.write(OK[i]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//=========NEW SHIT====================================================================
            boolean Arduino_Response = false;

                try {
                    InputStream inputStream = btSocket.getInputStream();

                    while(inputStream.available()<1) {} // A loop to prevent code from running as Arduino sends byte confirming connection

                    byte Response = (byte) inputStream.read(); //Reads one byte from the stream

                    if(Response == 49)
                    {
                        Arduino_Response = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//=============================================================================
            if (btSocket != null && btSocket.isConnected() && Arduino_Response) {
                Toast.makeText(FrontPage.this, "Bluetooth Connected", Toast.LENGTH_SHORT).show(); // If successful connect
                openMode_Select();
            } else {
                Toast.makeText(FrontPage.this, "Bluetooth not Connected", Toast.LENGTH_LONG).show(); // If unsuccessful connect
            }
        });
    }

    public void startThread() { // Function to start background thread
        FrontPage.BTConnect thread = new BTConnect();
        thread.start();
    }

    public static class BTConnect extends Thread{

        @SuppressLint("MissingPermission")
        @Override
        public void run() {
            BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter(); //Allows phone bluetooth adaptor to be used
            // USES ACCESS TO BLUETOOTH - Will maybe ask for permission later
            System.out.println(btAdapter.getBondedDevices()); // Prints out MAC addresses connected to device bluetooth adaptor

            BluetoothDevice HC05 = btAdapter.getRemoteDevice("98:D3:B1:FE:1A:50"); //Using address of HC05 to grab the right device from the multiple potential devices in a list


            try {
                btSocket = HC05.createRfcommSocketToServiceRecord(mUUID); // Creates a bluetooth socket with the module using the serial bluetooth ID
            } catch (IOException e) {
                e.printStackTrace();
            }

            int count = 0;
            do {
                try {
                    sleep(50); // Built in 50 ms delay for attempting socket connection
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if (btSocket != null) btSocket.connect(); // Attempting socket connection

                } catch (IOException e) {
                    e.printStackTrace();
                }
                count++;
            } while (btSocket != null && !btSocket.isConnected() && count < 3); // Allows for 3 connection attempts. Checks for connection count and connection status
        }
    }

    public void openMode_Select() { // Funciton to open the Mode Select Activity
        Intent intent = new Intent(this, mode_select.class);
        startActivity(intent);
    }

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }

}

// USED TO CLOSE BLUETOOTH SOCKET. DON'T KNOW IF THIS NEEDS TO BE USED

// ACTUALLY MAYBE I USE THIS WHEN DESTROY IS CALLED
/*
        try {
            Thread.sleep(5000);
            btSocket.close();
            System.out.println(btSocket.isConnected());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
*/