package com.example.tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TutorFinal extends AppCompatActivity {
        private FirebaseUser user;
        private DatabaseReference reference;
        private String userId;
        TextView tvName, tvEmail, tvPhone, tvAddress,tvQualification,tvExperties,tvCharges;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutors_final);
        init();
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RegisterUser user = snapshot.getValue(RegisterUser.class);
                if (user !=null)
                {
                    String name = user.Name;
                    String email = user.Email;
                    String Phone = user.Phone;
                    String degree = user.Qulification;
                    String address = user.Address;
                    String experties = user.experties;
                    String charges = user.charges;

                        tvName.setText(name);
                        tvEmail.setText(email);
                        tvAddress.setText(address);
                        tvCharges.setText(charges);
                        tvExperties.setText(experties);
                        tvPhone.setText(Phone);
                        tvQualification.setText(degree);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TutorFinal.this, "Something went wrong ....!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Logout(View v) {
        mAuth.signOut();
        startActivity(new Intent(TutorFinal.this, TeacherLoginActivity.class));
        finish();
    }

    private void init() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("RegisterUser");
        userId = user.getUid();
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvQualification = findViewById(R.id.tvQualification);
        tvExperties = findViewById(R.id.tvExperties);
        tvCharges = findViewById(R.id.tvCharges);
        mAuth = FirebaseAuth.getInstance();
    }

        public void back(View v) {
            startActivity(new Intent(TutorFinal.this, TutorList.class));
            finish();
        }

}