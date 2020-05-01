package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText productBox;
    private EditText categoryBox;
    private EditText quantityBox;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        productBox = findViewById(R.id.productBox);
        categoryBox = findViewById(R.id.categoryBox);
        quantityBox = findViewById(R.id.quantityBox);
        addButton = findViewById(R.id.addButton);
    }


    public void addPressed(View v) {
        String product = productBox.getText().toString();
        String category = categoryBox.getText().toString();
        String quantity = quantityBox.getText().toString();

        DatabaseManager dbm = new DatabaseManager(this);

        dbm.insert(product, category, quantity);

        finish();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
