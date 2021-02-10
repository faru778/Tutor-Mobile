package com.example.tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherLoginActivity extends AppCompatActivity {
    EditText etLogin_Email, etLogin_Password;
    String Email, Password;
    TextView tvCounter;
    Button btnSignIn;
    int counter;
    private FirebaseAuth fireauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        etLogin_Email = findViewById(R.id.etLogin_Email);
        etLogin_Password = findViewById(R.id.etLogin_Password);
        tvCounter = findViewById(R.id.forgetPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        fireauth = FirebaseAuth.getInstance();
    }

    public void SignIn(View v) {
        Email = etLogin_Email.getText().toString().trim();
        Password = etLogin_Password.getText().toString().trim();

        if (Email.isEmpty()) {
            etLogin_Email.setError("Error..... Name can't be empty");
            etLogin_Email.requestFocus();
        }


        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            etLogin_Email.setError("Please Provide valid email ");
            etLogin_Email.requestFocus();
        }
        else if (Password.isEmpty()) {
            etLogin_Password.setError("Error..... Password can't be empty");
            etLogin_Password.requestFocus();
        }
        else if (Password.length()<6)
        {
            etLogin_Password.setError("Min Password length should be 6 character");
            etLogin_Password.requestFocus();
        }
        else {
                fireauth.signInWithEmailAndPassword(Email,Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()){
                                   Toast.makeText(TeacherLoginActivity.this, "Welcome youre loged in ", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(TeacherLoginActivity.this, TutorFinal.class);
                                   startActivity(intent);
                                   finish();
                                   etLogin_Email.setText("");
                                   etLogin_Password.setText("");
                               }
                               else{
                                   Toast.makeText(TeacherLoginActivity.this, "Sorry please Try again ", Toast.LENGTH_SHORT).show();
                                   etLogin_Password.setError("Email/Password is wrong");
                               }


                            }
                        });
        }
    }

    public void ForgetPassword(View v)
    {
            EditText resetMail = new EditText(v.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
        passwordResetDialog.setTitle("Reset Password");
        passwordResetDialog.setMessage("Please Enter Your Email to recive the password link");
        passwordResetDialog.setView(resetMail);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // extracting email and sending reset link
                String mail = resetMail.getText().toString();

                fireauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TeacherLoginActivity.this, "Reset link sent", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TeacherLoginActivity.this, "Link is not sent"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
           // Close Dialog
            }
        });
        passwordResetDialog.create().show();
    }

    public void Signup(View v) {
            Intent intent = new Intent(TeacherLoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        finish();


        }
    public void back(View v) {
        startActivity(new Intent(TeacherLoginActivity.this, MainActivity.class));
        finish();
    }
    }
