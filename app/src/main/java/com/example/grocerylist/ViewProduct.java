package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewProduct extends AppCompatActivity {

    private TextView productView;
    private TextView categoryView;
    private TextView quantityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        productView = findViewById(R.id.productView);
        categoryView = findViewById(R.id.categoryView);
        quantityView = findViewById(R.id.quantityView);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String product = i.getStringExtra("PRODUCT");
        String[] entry = dbm.view(product);
        productView.setText(entry[0]);
        categoryView.setText(entry[1]);
        quantityView.setText(entry[2]);
    }


    public void deletePressed(View view) {
        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String product = i.getStringExtra("PRODUCT");


        Intent o = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(o);

        dbm.deleteProduct(product);
    }



}

