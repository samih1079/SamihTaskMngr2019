package com.example.samihtaskmngr2019;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.example.samihtaskmngr2019.ui.main.MyFragmentsPagerAdapter;

public class MainTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tasks);
        MyFragmentsPagerAdapter sectionsPagerAdapter = new MyFragmentsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        SharedPreferences preferences=getSharedPreferences("mypref",MODE_PRIVATE);
        String key = preferences.getString("key", "");
        if(key.length()==0)
        {
            Toast.makeText(this, "No key found", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("key","Hi");
            edit.apply();
        }
        else
        {
            Toast.makeText(this, "key:"+key, Toast.LENGTH_SHORT).show();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Task", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(getApplication(),AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}