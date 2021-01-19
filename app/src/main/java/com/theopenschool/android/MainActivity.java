package com.theopenschool.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.theopenschool.android.ui.main.CoursesFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CoursesFragment.newInstance())
                    .commitNow();
        }*/
    }
}