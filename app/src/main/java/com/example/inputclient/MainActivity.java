package com.example.inputclient;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    public static boolean running = true;
    public static CommandClient commandClient;
    public static final String FIRST_NAME_COMMAND = "fname";
    public static final String LAST_NAME_COMMAND = "lname";
    public static final String ADDRESS_COMMAND = "address";
    public static final String NEWLINE_COMMAND = "nl";
    public static final String CLEAR_COMMAND = "clear";
    public static final String CITY_COMMAND = "city";
    public static final String EMAIL_COMMAND = "email";
    public static final String PASSWORD_COMMAND = "password";
    public static final String SELECT_COMMAND = "sel_all";
    public static final String BACKSPACE_COMMAND = "bs";
    public static final String SPACE_COMMAND = "sp";
    public static final String OPEN_GMAIL_COMMAND = "open_gmail";
    public static final String ZIP_COMMAND = "zip";
    public static final String CLOSE_TAB = "close_tab";
    public static final String CLOSE_WINDOW = "closeapp";
    public static final String RIT_EMAIL = "rit_email";
    public static final String STATE = "state";
    public static final String TAB = "tab";
    public static final String BACKTAB = "backtab";
    public static final String PHONE = "phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger logger = Logger.getLogger(this.getClass().getName());

        // Initialize the input client and spin while waiting for commands
        Thread networkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Get the data from the intent
                Bundle intent_data = getIntent().getExtras();
                String ip = intent_data.getString(IPSearchActivity.IP_MESSAGE);
                commandClient = new CommandClient(ip, CommandClient.INPUT_PORT);
                // Spin here while connection is established
                while (running) {

                }
            }
        });
        networkThread.start();



        // Get the application context for the button assembler
        Context appContext = getApplicationContext();
        ViewGroup tableViewGroup = (ViewGroup) this.findViewById(R.id.buttonTable);
        ButtonAssembler buttonAssembler = new ButtonAssembler(tableViewGroup, appContext);
        buttonAssembler.setCommandClient(commandClient);
        buttonAssembler.addButton("Allah", "STATE");
        buttonAssembler.getButtonByName("Allah").setOnClickListener(v -> {
            commandClient.sendCommand((String) ((Button) v).getText());
        });
    }

    public void onBackPressed() {
        running = false;
        commandClient.close();
        finish();
    }

    public void sendFirstName(View view) {
        commandClient.sendCommand(FIRST_NAME_COMMAND);
    }

    public void sendLastName(View view) {
        commandClient.sendCommand(LAST_NAME_COMMAND);
    }

    public void sendAddress(View view) {
        commandClient.sendCommand(ADDRESS_COMMAND);
    }

    public void sendNewline(View view) {commandClient.sendCommand(NEWLINE_COMMAND);}

    public void sendClear(View view) {commandClient.sendCommand(CLEAR_COMMAND);}

    public void sendCity(View view) {commandClient.sendCommand(CITY_COMMAND);}

    public void sendEmail(View view) {commandClient.sendCommand(EMAIL_COMMAND);}

    public void sendPassword(View view) {commandClient.sendCommand(PASSWORD_COMMAND);}

    public void sendSelectAll(View view) {commandClient.sendCommand(SELECT_COMMAND);}

    public void sendBackSpace(View view) {commandClient.sendCommand(BACKSPACE_COMMAND);}

    public void sendSpace(View view) {commandClient.sendCommand(SPACE_COMMAND);}

    public void sendOpenGmail(View view) {commandClient.sendCommand(OPEN_GMAIL_COMMAND);}

    public void sendZip(View view) {commandClient.sendCommand(ZIP_COMMAND);}

    public void sendCloseTab(View view) {commandClient.sendCommand(CLOSE_TAB);}

    public void sendCloseWindow(View view) {commandClient.sendCommand(CLOSE_WINDOW);}

    public void sendRITEmail(View view) {commandClient.sendCommand(RIT_EMAIL);}

    public void sendState(View view) {commandClient.sendCommand(STATE);}

    public void sendTab(View view) {commandClient.sendCommand(TAB);}

    public void sendBacktab(View view) {commandClient.sendCommand(BACKTAB);}

    public void sendPhoneNumber(View view) {commandClient.sendCommand(PHONE);}
}

