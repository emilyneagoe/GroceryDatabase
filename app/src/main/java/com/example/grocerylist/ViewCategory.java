package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewCategory extends AppCompatActivity {

    private ScrollView scrollViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);

        scrollViewCategory = findViewById(R.id.scrollViewCategory);
        scrollViewCategory.removeAllViewsInLayout();
        DatabaseManager dbm = new DatabaseManager(this);

        Intent i = getIntent();
        String category = i.getStringExtra("CATEGORY");


        ArrayList<String> list = dbm.getCategory(category);
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());
        for(String product : list) {
            TextView text = new TextView(this);
            text.setText(product);
            text.setTextSize(40);
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ViewProduct.class);
                    i.putExtra("PRODUCT", ((TextView) view).getText().toString());
                    startActivity(i);
                }
            });
            grid.addView(text);

        }
        scrollViewCategory.addView(grid);
    }



    public void deletePressed(View view) {
        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String category = i.getStringExtra("CATEGORY");


        Intent o = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(o);

        dbm.deleteCategory(category);
    }
}
