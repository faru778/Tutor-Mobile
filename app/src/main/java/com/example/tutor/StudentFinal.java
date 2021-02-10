package com.example.tutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_final);
    }
    public void Done(View v)
    {
        Intent intent = new Intent(StudentFinal.this, TutorDetails.class);
        startActivity(intent);
        finish();
    }
}