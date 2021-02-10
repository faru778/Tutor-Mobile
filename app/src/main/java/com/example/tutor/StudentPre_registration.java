package com.example.tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StudentPre_registration extends AppCompatActivity {
    EditText et_Name, et_Email, et_Pass, et_Phone, et_Subject, et_Address;
    String Name, Email, Password, Phone, Address,Subject;
    private FirebaseAuth fireauth;
    private FirebaseStorage storage;
    private StorageReference mReference;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pre_registration);
        init();
    }

    private void init() {
        et_Name = findViewById(R.id.et_Name);
        et_Email = findViewById(R.id.et_Email);
        et_Pass = findViewById(R.id.et_Pass);
        et_Phone = findViewById(R.id.et_Phone);
        et_Subject = findViewById(R.id.et_Subject);
        et_Address = findViewById(R.id.et_Address);
        fireauth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        mReference = storage.getReference().child("StudentUser");
    }

    public void SignUp(View v) {

        Name = et_Name.getText().toString().trim();
        Email = et_Email.getText().toString().trim();
        Password = et_Pass.getText().toString().trim();
        Phone = et_Phone.getText().toString().trim();
        Address = et_Address.getText().toString().trim();
        Subject = et_Subject.getText().toString().trim();
        if (Name.isEmpty())
        {
            et_Name.setError("Name Can't be empty");
            et_Name.requestFocus();
        }
        else if (Email.isEmpty())
        {
            et_Email.setError("Email Can't be empty");
            et_Email.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            et_Email.setError("Please Provide valid email ");
            et_Email.requestFocus();
        }
        else if (Phone.isEmpty())
        {
            et_Phone.setError("Phone Can't be empty");
            et_Phone.requestFocus();
        }

        else if (Address.isEmpty())
        {
            et_Address.setError("Password Can't be empty");
            et_Address.requestFocus();
        }
         else if (Subject.isEmpty())
        {
            et_Subject.setError("Password Can't be empty");
            et_Subject.requestFocus();
        }

        else if (Password.isEmpty())
        {
            et_Pass.setError("Password Can't be empty");
            et_Pass.requestFocus();
        }
        else if (Password.length()<6)
        {
            et_Pass.setError("Min Password length should be 6 character");
            et_Pass.requestFocus();
        }
        else
        {

            InsertData();


        }
    }

    private void InsertData() {
        fireauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    StudentUser user = new StudentUser(Name,Email,Phone,Address,Subject);
                    FirebaseDatabase.getInstance().getReference("StudentUser")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(StudentPre_registration.this, "User has been Register", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(StudentPre_registration.this, "Sorry User is not Registered! Try again ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        Intent intent = new Intent(StudentPre_registration.this, StudnetLoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void back(View v) {
        startActivity(new Intent(StudentPre_registration.this, TutorDetails.class));
        finish();
    }
}