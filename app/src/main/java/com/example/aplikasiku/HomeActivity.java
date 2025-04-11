package com.example.aplikasiku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    private TextView tvCardTitle1, tvCardDescription1;
    private Button btnLogout;
    private TextInputEditText editMemo;
    private Button btnSimpan;
    private FirebaseAuth mAuth;
    private static final String TAG = "HomeActivity";

    public static ArrayList<String> memoList = new ArrayList<>();

    CardView cardView1, cardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //inisialisasi firebase auth
        mAuth = FirebaseAuth.getInstance();
        //ambil data pengguna yang sedang login
        FirebaseUser user = mAuth.getCurrentUser();

        //inisialisasi elemen UI
        tvCardTitle1 = findViewById(R.id.tvCardTitle1);
        tvCardDescription1 = findViewById(R.id.tvCardDescription1);

        editMemo = findViewById(R.id.editMemo);
        btnSimpan = findViewById(R.id.btnsimpan);

        if (user != null) {
            tvCardTitle1.setText("Selamat Datang");
            tvCardDescription1.setText("Ini adalah halaman utama aplikasi");
        }else {
            tvCardTitle1.setText("Halo");
            tvCardDescription1.setText("Silahkan login untuk melihat lebih lanjut");

        }

        btnSimpan.setOnClickListener(v -> {
            String memo = editMemo.getText().toString().trim();
            if (!memo.isEmpty()) {
                memoList.add(memo); // tambahkan memo ke daftar memo
                Toast.makeText(HomeActivity.this, "Memo berhasil disimpan", Toast.LENGTH_SHORT).show();

                // Pindah ke halaman catatan
                Intent intent = new Intent(HomeActivity.this, CatatanActivity.class);
                intent.putStringArrayListExtra("memoList", memoList); // kirim daftar memo ke halaman catatan
                startActivity(intent);
            } else {
                Toast.makeText(HomeActivity.this, "Memo masih kosong", Toast.LENGTH_SHORT).show();
            }
        });

        //navigasi ke halaman profile saat klik
        tvCardTitle1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CatatanActivity.class);
            intent.putStringArrayListExtra("memoList", memoList);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                // Sudah di Home, tidak perlu pindah
                return true;
            } else if (id == R.id.nav_catatan) {
                startActivity(new Intent(HomeActivity.this, CatatanActivity.class));
                return true;
            } else if (id == R.id.nav_gallery) {
                startActivity(new Intent(HomeActivity.this, GalleryActivity.class)); // pastikan SettingsActivity sudah dibuat
                return true;
            }
            return false;
        });

    }
}