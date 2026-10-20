package com.example.smartpantrymanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView tvAppName = findViewById(R.id.tvSettingsAppName);
        TextView tvAppDescription = findViewById(R.id.tvSettingsDescription);
        TextView tvAppVersion = findViewById(R.id.tvSettingsVersion);
        Button btnBack = findViewById(R.id.btnBackFromSettings);

        tvAppName.setText("Smart Pantry Manager");
        tvAppDescription.setText(
                "Plan meals, manage pantry ingredients, and discover recipes " +
                        "you can make with what you have."
        );
        tvAppVersion.setText("Version 1.0");

        btnBack.setOnClickListener(v -> finish());
    }
}
