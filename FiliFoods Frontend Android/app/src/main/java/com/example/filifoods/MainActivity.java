package com.example.filifoods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    Button buttonLogout;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,SearchActivity.class);
            startActivity(i);

        });

        ImageButton btnFilter = findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,SendActivity.class);
            startActivity(i);

        });

        ImageButton btnRandom = findViewById(R.id.btn_random);
        btnRandom.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,RandomActivity.class);
            startActivity(i);

        });

        ImageButton btnAll = findViewById(R.id.btn_all);
        btnAll.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity.this,AllActivity.class);
            startActivity(i);

        });

        auth = FirebaseAuth.getInstance();
        buttonLogout = findViewById(R.id.btn_profile);
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), UserLogin.class);
            startActivity(intent);
            finish();
        }
        else{

        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
            }
        });




    }
}