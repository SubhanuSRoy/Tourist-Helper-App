package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);


    }

    public void OpenNumbersActivity(View view) {
        Intent numberIntent = new Intent(this, NumbersActivity.class);
        startActivity(numberIntent);
    }

    public void OpenFamilyActivity(View v) {
        Intent familyIntent = new Intent(this, FamilyActivity.class);
        startActivity(familyIntent);
    }

    public void OpenColorsActivity(View v) {
        Intent colorIntent = new Intent(this, ColorsActivity.class);
        startActivity(colorIntent);
    }

    public void OpenPhrasesActivity(View v) {
        Intent phrasesIntent = new Intent(this, PhrasesActivity.class);
        startActivity(phrasesIntent);
    }
}