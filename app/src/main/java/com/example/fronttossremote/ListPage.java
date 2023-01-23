package com.example.fronttossremote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class ListPage extends AppCompatActivity {

    public static int Tot;

    public int modeSelect = mode_select.modeSelect;

    public static int[] BaseballsArray;

    public static int[] BaseballsPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Front Toss Remote");

        BluetoothSocket btSocket = FrontPage.btSocket; //Acquire the Bluetooth socket that was opened in the last Activity

       EditText TopRightNum = findViewById(R.id.TopRightEntry); // Defining the TopRight textedit
       EditText TopCenterNum = findViewById(R.id.TopCenterEntry); // Defining the TopCenter textedit
       EditText TopLeftNum = findViewById(R.id.TopLeftEntry); // Defining the TopRight textedit
       EditText CenterRightNum = findViewById(R.id.CenterRightEntry); // Defining the CenterRight textedit
       EditText CenterCenterNum = findViewById(R.id.CenterCenterEntry); // Defining the CenterCenter textedit
       EditText CenterLeftNum = findViewById(R.id.CenterLeftEntry); // Defining the CenterLeft textedit
       EditText BottomRightNum = findViewById(R.id.BottomRightEntry); // Defining the BottomRight textedit
       EditText BottomCenterNum = findViewById(R.id.BottomCenterEntry); // Defining the BottomCenter textedit
       EditText BottomLeftNum = findViewById(R.id.BottomLeftEntry); // Defining the BottomLeft textedit
       Button Enterbtn2 = findViewById(R.id.Enter2); // Defining the Enter button

        Enterbtn2.setOnClickListener(view -> {
            String a = TopRightNum.getText().toString();
            String b = TopCenterNum.getText().toString();
            String c = TopLeftNum.getText().toString();
            String d = CenterRightNum.getText().toString();
            String e = CenterCenterNum.getText().toString();
            String f = CenterLeftNum.getText().toString();
            String g = BottomRightNum.getText().toString();
            String h = BottomCenterNum.getText().toString();
            String i = BottomLeftNum.getText().toString();

            BaseballsArray = new int[] {Integer.parseInt(a),Integer.parseInt(b),Integer.parseInt(c),Integer.parseInt(d),Integer.parseInt(e),Integer.parseInt(f),Integer.parseInt(g),Integer.parseInt(h),Integer.parseInt(i)};
            BaseballsPosition = new int[] {1,2,3,4,5,6,7,8,9};

            Tot = Integer.parseInt(a)+Integer.parseInt(b)+Integer.parseInt(c)+Integer.parseInt(d)+Integer.parseInt(e)+Integer.parseInt(f)+Integer.parseInt(g)+Integer.parseInt(h)+Integer.parseInt(i);

            System.out.println(Tot);

            if(Tot > 0 && Tot < 26) // Any value below 1 and above 25 is invalid
            {
                if(modeSelect == 4) {
                    openLaunching_Baseballs(); //If the mode selected is List, Open the Launching (Wait) Activity
                } else {
                    Toast.makeText(ListPage.this, "MODE ERROR", Toast.LENGTH_SHORT).show(); // Any other value for modeselect is an Error
                }
            }else{
                Toast.makeText(ListPage.this, "Invalid Entry", Toast.LENGTH_SHORT).show(); // Any value below 1 and above 25 is invalid
            }
        });

    }

    public void openLaunching_Baseballs() {
        Intent intent = new Intent(this, Launching_Baseballs.class); //Creates intent to Wait (Launching) Activity
        startActivity(intent);
    }

    // Disables the use of the back button on the Android Device
    @Override
    public void onBackPressed() { }
}