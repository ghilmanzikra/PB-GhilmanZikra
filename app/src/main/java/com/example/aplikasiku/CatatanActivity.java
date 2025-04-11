package com.example.aplikasiku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;

public class CatatanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MemoAdapter memoAdapter;

    private ArrayList<String> memoList = new ArrayList<>();
    private FirebaseAuth mAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_catatan);

        recyclerView = findViewById(R.id.recyclerMemo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ambil data memo dari Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("memoList")) {
            memoList = intent.getStringArrayListExtra("memoList");
        } else {
            memoList = new ArrayList<>();
        }


        memoAdapter = new MemoAdapter(memoList);
        recyclerView.setAdapter(memoAdapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(CatatanActivity.this, HomeActivity.class));
                return true;
            } else if (id == R.id.nav_catatan) {
                // Sudah di halaman catatan
                return true;
            } else if (id == R.id.nav_gallery) {
                startActivity(new Intent(CatatanActivity.this, GalleryActivity.class));
                return true;
            }
            return false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.catatan_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}