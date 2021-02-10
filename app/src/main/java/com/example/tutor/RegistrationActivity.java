package com.example.tutor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegistrationActivity extends AppCompatActivity {
    EditText et_Name,et_Email,et_Pass,et_Phone,et_Qualification,et_Address,et_experties,et_Charges;
    String Name, Email, Password, Phone,Qulification, Address, experties, charges;
   // String Subjects ;
    String profle_url;

    private FirebaseAuth fireauth;
    ImageView profile_Picture;

    public Uri imageUri;
    Spinner subjectSpinner;
    private FirebaseStorage storage;
    private StorageReference mReference;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        /*subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegistrationActivity.this
                    , android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.Subjects));
                   myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                     subjectSpinner.setAdapter(myAdapter);
        Subjects = (String) subjectSpinner.getSelectedItem().toString();*/
        init();
    }
    private void init() {
        et_Name = findViewById(R.id.et_Name);
        et_Email = findViewById(R.id.et_Email);
        et_Pass = findViewById(R.id.et_Pass);
        et_Phone = findViewById(R.id.et_Phone);
        et_experties = findViewById(R.id.et_experties);
        et_Qualification = findViewById(R.id.et_Subject);
        et_Address = findViewById(R.id.et_Address);
        et_Charges = findViewById(R.id.et_Charges);

        fireauth = FirebaseAuth.getInstance();
        profile_Picture = findViewById(R.id.Detail_profile_Picture);
        storage = FirebaseStorage.getInstance();
        mReference = storage.getReference().child("RegisterUser");
        //mReference = storage.getReference().child(Subjects);


    }
    public void SignUp(View v) {

        Name = et_Name.getText().toString().trim();
        Email = et_Email.getText().toString().trim();
        Password = et_Pass.getText().toString().trim();
        Phone = et_Phone.getText().toString().trim();
        Qulification = et_Qualification.getText().toString().trim();
        Address = et_Address.getText().toString().trim();
        experties = et_experties.getText().toString().trim();
        charges = et_Charges.getText().toString().trim();


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
        else if (Qulification.isEmpty())
        {
            et_Qualification.setError("Password Can't be empty");
            et_Qualification.requestFocus();
        }
        else if (Address.isEmpty())
        {
            et_Address.setError("Password Can't be empty");
            et_Address.requestFocus();
        }
        else if (experties.isEmpty())
        {
            et_experties.setError("Password Can't be empty");
            et_experties.requestFocus();
        }
        else if (charges.isEmpty())
        {
            et_Charges.setError("Password Can't be empty");
            et_Charges.requestFocus();
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
        else if (imageUri == null)
        {
            Toast.makeText(this, "Please choose an image.....!", Toast.LENGTH_SHORT).show();
        }
       /* else if(Subjects.equals("subjects"))
        {
            Toast.makeText(this, "Please Select any subject.....!", Toast.LENGTH_SHORT).show();
        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("Maths"))
        {
            Subjects = "Maths";
            Toast.makeText(this, "Maths", Toast.LENGTH_SHORT).show();

        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("Physics"))
        {
            Subjects = "Physics";
            Toast.makeText(this, "Physics", Toast.LENGTH_SHORT).show();

        } else if (subjectSpinner.getSelectedItem().toString().trim().equals("Chemistry"))
        {
            Subjects = "Chemistry";
            Toast.makeText(this, "Chemistry", Toast.LENGTH_SHORT).show();
        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("Biology"))
        {
            Subjects = "Biology";
            Toast.makeText(this, "Biology   ", Toast.LENGTH_SHORT).show();
        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("English"))
        {
            Subjects = "English";
            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("Android"))
        {
            Subjects = "Android";
            Toast.makeText(this, "Android", Toast.LENGTH_SHORT).show();
        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("Java"))
        {
            Subjects = "Java";
            Toast.makeText(this, "Java", Toast.LENGTH_SHORT).show();
        }
        else if (subjectSpinner.getSelectedItem().toString().trim().equals("C++"))
        {
            Subjects = "C++";
            Toast.makeText(this, "c++", Toast.LENGTH_SHORT).show();
        }*/
         else
        { //Subjects = (String) subjectSpinner.getSelectedItem().toString();
            uploadPicture();
           InsertData();
            //Toast.makeText(this, "Subject "+Subjects, Toast.LENGTH_LONG).show();


        }
    }
    private void InsertData() {

        fireauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    RegisterUser user = new RegisterUser(Name,Email,Phone,Qulification,Address,experties,charges, profle_url);
                    FirebaseDatabase.getInstance().getReference().child("RegisterUser")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this, "User has been Register", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistrationActivity.this, TeacherLoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, "Sorry User is not Registered! Try again ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });




    }
    public void choosePicture(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            profile_Picture.setImageURI(imageUri);
        }
    }
    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image....");
        pd.show();


            if (imageUri !=null)
            {
                StorageReference storageRef = mReference.child("image");
        storageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_SHORT).show();
                        pd.dismiss();
                      //  profle_url =  mReference.child("image").getDownloadUrl().toString();
                       // profle_url = taskSnapshot.getStorage().getDownloadUrl().toString();
                        taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                profle_url = task.getResult().toString();
                                Toast.makeText(RegistrationActivity.this, "Url"+profle_url, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegistrationActivity.this, "Failed to upload the image", Toast.LENGTH_SHORT).show();

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                pd.setMessage("Percentage: "+(int)progressPercent+ "%");
            }

        });
            }
            else
            {
                Toast.makeText(this, "No file is Uploaded", Toast.LENGTH_SHORT).show();
            }




    }
    public void back(View v) {
        startActivity(new Intent(RegistrationActivity.this, TeacherLoginActivity.class));
        finish();
    }
}