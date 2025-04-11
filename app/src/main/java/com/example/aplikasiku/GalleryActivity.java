package com.example.aplikasiku;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotoAdapter adapter;
    private List<PhotoItem> photoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerGallery);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 kolom

        photoList = new ArrayList<>();

        // Tambahkan gambar dari drawable
        photoList.add(new PhotoItem(R.drawable.p1));
        photoList.add(new PhotoItem(R.drawable.p3));
        photoList.add(new PhotoItem(R.drawable.p4));
        photoList.add(new PhotoItem(R.drawable.p7));
        photoList.add(new PhotoItem(R.drawable.p8));
        photoList.add(new PhotoItem(R.drawable.p11));
        photoList.add(new PhotoItem(R.drawable.k2));
        photoList.add(new PhotoItem(R.drawable.k2));
        // Tambahkan lebih banyak kalau mau

        adapter = new PhotoAdapter(photoList);
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Sudah di Home, tidak perlu pindah
                return true;
            } else if (id == R.id.nav_catatan) {
                startActivity(new Intent(GalleryActivity.this, CatatanActivity.class));
                return true;
            } else if (id == R.id.nav_gallery) {
                startActivity(new Intent(GalleryActivity.this, GalleryActivity.class)); // pastikan SettingsActivity sudah dibuat
                return true;
            }
            return false;
        });
    }
}
