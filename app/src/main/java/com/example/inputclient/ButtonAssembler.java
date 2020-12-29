package com.example.inputclient;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.HashMap;

public class ButtonAssembler {
    private ViewGroup referenceGroup;
    private Context appContext;
    public final static CommandClient commandClient = MainActivity.commandClient;
    // Have a button assembler for quick lookup of buttons
    private HashMap<String, Button> buttonMap;


    public ButtonAssembler(ViewGroup referenceGroup, Context appContext) {
        this.referenceGroup = referenceGroup;
        this.appContext = appContext;
        this.buttonMap = new HashMap<>();
    }

    public void addButton(String buttonName, String buttonCommand) {
        // Create the button
        Button button = new Button(appContext);
        button.setText(buttonName);
        button.setOnClickListener(v -> {
            commandClient.sendCommand(buttonCommand);
        });
        // Add a reference to it by name
        buttonMap.put(buttonName, button);
        referenceGroup.addView(button);

        // Set the event handler for the button
        button.setOnClickListener(v -> {
            commandClient.sendCommand(buttonCommand);
        });
    }

    public void addButtonTo(ViewGroup viewGroup, String buttonName, String buttonCommand) {
        // Create the button
        Button button = new Button(appContext);
        button.setText(buttonName);
        button.setOnClickListener(v -> {
            commandClient.sendCommand(buttonCommand);
        });
        // Add a reference to it by name
        buttonMap.put(buttonName, button);
        viewGroup.addView(button);
    }

    public void setCommandClient(CommandClient commandClient_) {
        // commandClient = commandClient_;
    }
    // Get button by name
    // Only works if button was created using this class
    public Button getButtonByName(String buttonName) {
        return buttonMap.get(buttonName);
    }
}
