package com.example.tutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
public class TutorList extends AppCompatActivity {
    RecyclerView recyclerView;
    TutorAdapter tutorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_lists);
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<RegisterUser> options =
                new FirebaseRecyclerOptions.Builder<RegisterUser>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RegisterUser"), RegisterUser.class)
                        .build();
        tutorAdapter = new TutorAdapter(options, this);
        recyclerView.setAdapter(tutorAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        tutorAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        tutorAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ProcessSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                ProcessSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void ProcessSearch(String query) {
        FirebaseRecyclerOptions<RegisterUser> options =
                new FirebaseRecyclerOptions.Builder<RegisterUser>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RegisterUser").orderByChild("name").startAt(query).endAt(query+"\uf8ff"), RegisterUser.class)
                        .build();
        tutorAdapter = new TutorAdapter(options, this);
        tutorAdapter.startListening();
        recyclerView.setAdapter(tutorAdapter);
    }
    public void back(View v) {
        startActivity(new Intent(TutorList.this, MainActivity.class));
        finish();
    }
}