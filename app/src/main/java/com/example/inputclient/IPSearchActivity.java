package com.example.inputclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IPSearchActivity extends AppCompatActivity {
    public static final String IP_MESSAGE = "com.example.inputclient.IP_MESSAGE";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipsearch);
    }

    public void submitIp(View view) {
        // Create the intent that corresponds to being connected
        EditText editText = (EditText) findViewById(R.id.connectIp);
        String ipConnect = editText.getText().toString();

        // Check for server being up
        InputServerChecker inputServerChecker = new InputServerChecker();
        if (inputServerChecker.isServerUp(ipConnect)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(IP_MESSAGE, ipConnect);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Could not connect to input server: " + ipConnect, Toast.LENGTH_SHORT).show();

        }


    }
}
