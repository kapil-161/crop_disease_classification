package com.example.mlwithtensorflowlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0);
    }

    @Override
    public void onRefresh() {
        // Perform the refresh operation here
        // For example, you can reload the page or fetch new data

        // Stop the refreshing animation
        swipeRefreshLayout.setRefreshing(false);
        spinner.setSelection(0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        spinner.setSelection(0);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), selectedItem, Toast.LENGTH_SHORT).show();

        if (selectedItem.equals("Tomato")) {
            Intent intent = new Intent(MainActivity.this, TomatoClassificationActivity.class);
            startActivity(intent);
        }
        if (selectedItem.equals("Potato")) {
            Intent intent = new Intent(MainActivity.this, PotatoClassificationActivity.class);
            startActivity(intent);
        }
        if (selectedItem.equals("Pepper")) {
            Intent intent = new Intent(MainActivity.this, PepperClassificationActivity.class);
            startActivity(intent);
        }
        if (selectedItem.equals("Apple")) {
            Intent intent = new Intent(MainActivity.this, AppleClassificationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Exit the app
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}