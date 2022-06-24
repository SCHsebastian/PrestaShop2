package es.sch.prestashop.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.sch.prestashop.databinding.ActivityActProductoBinding;
import es.sch.prestashop.databinding.ActivityMainBinding;

public class actProducto extends AppCompatActivity {

    private ActivityActProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}