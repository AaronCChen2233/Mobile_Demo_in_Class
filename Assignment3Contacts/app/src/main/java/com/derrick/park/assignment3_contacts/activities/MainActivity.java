package com.derrick.park.assignment3_contacts.activities;

import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.derrick.park.assignment3_contacts.R;

/**
 Requirements
 Your app needs to compile and run.
 V Your app has to use a RecyclerView to display contacts.
 Your app has to use sections to organize contacts.
 V You need to read data from the given web API.
 Check out this library: retrofit
 Your app should be able to add a new contact. (You do not need to have a database for this assignment)
 You need to validate user input.
 Name : Two words (ex. John Doe)
 V Phone : Ten digits (ex. 1234567890)
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
