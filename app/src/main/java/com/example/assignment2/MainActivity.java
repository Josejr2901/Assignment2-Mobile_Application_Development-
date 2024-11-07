package com.example.assignment2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import com.example.assignment2.R;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Here we are declaring the database helper and UI components
    DatabaseHelper dbHelper;
    EditText etAddress, etLatitude, etLongitude;
    TextView tvResult;
    Button btnAdd, btnUpdate, btnDelete, btnQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here we will initialize the database helper and UI elements
        dbHelper = new DatabaseHelper(this);
        etAddress = findViewById(R.id.etAddress);
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        tvResult = findViewById(R.id.tvResult);

        // To initialize buttons and set click listeners
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnQuery = findViewById(R.id.btnQuery);

        // Set button actions for Add, Update, Delete, and Query
        btnAdd.setOnClickListener(v -> addLocation());
        btnUpdate.setOnClickListener(v -> updateLocation());
        btnDelete.setOnClickListener(v -> deleteLocation());
        btnQuery.setOnClickListener(v -> queryLocation());
    }

    // This method is to add a location to the database
    private void addLocation() {
        String address = etAddress.getText().toString();
        String latitudeStr = etLatitude.getText().toString();
        String longitudeStr = etLongitude.getText().toString();

        // This will check if any fields are empty
        if (address.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields to add a location", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse latitude and longitude values
            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            // Attempt to add location to the database
            if (dbHelper.addLocation(address, latitude, longitude)) {
                Toast.makeText(this, "Location Added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Add Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format for Latitude or Longitude", Toast.LENGTH_SHORT).show();
        }
    }

    // This method will update a location's details
    private void updateLocation() {
        String address = etAddress.getText().toString();
        String latitudeStr = etLatitude.getText().toString();
        String longitudeStr = etLongitude.getText().toString();

        // Check if any fields are empty
        if (address.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields to update a Location", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Parse latitude and longitude values
            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            // Attempt to update location in the database
            if (dbHelper.updateLocation(address, latitude, longitude)) {
                Toast.makeText(this, "Location Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format for Latitude or Longitude", Toast.LENGTH_SHORT).show();
        }
    }

    // This method is meant to delete a location from the database
    private void deleteLocation() {
        String address = etAddress.getText().toString();

        // Attempt to delete the location based on the address
        if (dbHelper.deleteLocation(address)) {
            Toast.makeText(this, "Location Deleted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to query the database and display latitude and longitude for a given address
    private void queryLocation() {
        String address = etAddress.getText().toString();
        Cursor cursor = dbHelper.getLocationByAddress(address);

        // Check if the location was found in the database
        if (cursor != null && cursor.moveToFirst()) {
            double latitude = cursor.getDouble(0);
            double longitude = cursor.getDouble(1);
            // Display the latitude and longitude of the found location
            tvResult.setText("The latitude of '" + address + "' is: " + latitude + "\nThe longitude of '" + address + "' is: " + longitude);
            cursor.close();
        } else {
            tvResult.setText("Location not found.");
        }
    }

}
