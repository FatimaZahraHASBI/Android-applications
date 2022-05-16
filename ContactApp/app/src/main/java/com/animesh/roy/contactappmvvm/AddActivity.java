package com.animesh.roy.contactappmvvm;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddActivity extends AppCompatActivity {
    FloatingActionButton fb_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        Toolbar toolbar = findViewById(R.id.second_toolbar);
        setSupportActionBar(toolbar);


        fb_home = findViewById(R.id.fab);
        fb_home.setOnClickListener(view -> {
            this.finish();
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu2, menu);
//        return true;
//    }
}
