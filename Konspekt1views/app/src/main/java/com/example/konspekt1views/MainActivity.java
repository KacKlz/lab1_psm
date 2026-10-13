package com.example.konspekt1views;

import android.os.Bundle;
import android.util.Log; // Zmiana 2: Import do logów
import android.view.View;
import android.widget.Toast; // Zmiana 3: Import dla Toast

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.konspekt1views.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // Zmiana 2: Tag do logów
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.toggleButton.setOnClickListener(v -> {
            boolean isDataVisible = binding.fullNameTextView.getVisibility() == View.VISIBLE;

            // Zmiana 1: View.GONE zamiast View.INVISIBLE (zwolnienie miejsca w układzie)
            binding.fullNameTextView.setVisibility(isDataVisible ? View.GONE : View.VISIBLE);

            binding.toggleButton.setText(isDataVisible ? R.string.show_data : R.string.hide_data);

            // Zmiana 2: Logowanie zdarzenia w konsoli
            Log.d(TAG, "Zmieniono widoczność danych: " + !isDataVisible);

            // Zmiana 3: Wyświetlenie krótkiego komunikatu Toast na ekranie
            Toast.makeText(this, isDataVisible ? "Ukryto dane" : "Pokazano dane", Toast.LENGTH_SHORT).show();
        });
    }
}