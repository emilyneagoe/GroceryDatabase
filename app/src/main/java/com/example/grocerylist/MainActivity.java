package com.example.grocerylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        scrollView.removeAllViewsInLayout();
        DatabaseManager dbm = new DatabaseManager(this);
        TreeSet<String> list = dbm.getGrocery();
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());
        for(String category : list) {
            TextView text = new TextView(this);
            text.setText(category);
            text.setTextSize(40);
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ViewCategory.class);
                    i.putExtra("CATEGORY", ((TextView) view).getText().toString());
                    startActivity(i);
                }
            });
            grid.addView(text);

        }
        scrollView.addView(grid);


    }


    public void addNewPressed(View v) {
        Intent i = new Intent(this, AddActivity.class);
        i.putExtra("ADD", true);
        startActivity(i);
    }
}
