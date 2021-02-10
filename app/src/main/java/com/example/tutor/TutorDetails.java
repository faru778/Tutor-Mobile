package com.example.tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutorDetails extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    CircleImageView Detail_profile_Picture;

    TextView details_name,details_Experties,details_Bio,details_charges;
    Button details_Hire;
    ImageView details_Location,details_Call,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_details);
        init();
        Intent intent = getIntent();

        reference.child(intent.getStringExtra("id")).addListenerForSingleValueEvent(new ValueEventListener() {
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

                    details_name.setText(name);
                    details_Experties.setText(degree + ","+experties);
                    details_charges.setText(charges);


                }
                details_Hire.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(TutorDetails.this, StudentPre_registration.class);
                        startActivity(intent);
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(TutorDetails.this, TutorList.class));
                        finish();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TutorDetails.this, "Something went wrong ....!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void init() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("RegisterUser");
        userId = user.getUid();
        Detail_profile_Picture = findViewById(R.id.Detail_profile_Picture);
        details_name = findViewById(R.id.details_name);
        details_Experties = findViewById(R.id.details_Experties);
        details_Location = findViewById(R.id.details_Location);
        details_Call = findViewById(R.id.details_Call);
        details_Bio = findViewById(R.id.details_Bio);
        details_Hire =findViewById(R.id.details_Message);
        details_charges =findViewById(R.id.details_charges);
        back = findViewById(R.id.back);

    }
}