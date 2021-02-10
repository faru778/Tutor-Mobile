package com.example.tutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void TutorsView(View v)
    {
        Intent intent = new Intent(HomeActivity.this,TutorDetails.class);
        startActivity(intent);

    }

    public void Maths(View v)
    {
        Intent intent = new Intent(HomeActivity.this, TutorList.class);
        startActivity(intent);
        finish();
    }
    public void English(View v)
    {

    }public void physics(View v)
    {

    }public void chemistry(View v)
    {

    }public void biology(View v)
    {

    }public void Java(View v)
    {

    }public void Android(View v)
    {

    }public void Cplus_plus(View v)
    {

    }
    public void back(View v) {
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
        finish();
    }

}