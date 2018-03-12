package com.example.davidjusten.empower_nutrition_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void viewOrders(View view) {
    }

    public void addFoodItem(View view) {
        Intent addFoodIntent = new Intent(MainActivity.this,AddFoodItemActivity.class);
        startActivity(addFoodIntent);
    }
}
