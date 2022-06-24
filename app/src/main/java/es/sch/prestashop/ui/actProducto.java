package es.sch.prestashop.ui;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import es.sch.prestashop.R;
import es.sch.prestashop.databinding.ActivityActProductoBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.utils.DescargarImagen;

public class actProducto extends AppCompatActivity {

    private ActivityActProductoBinding binding;
    private DBProducto producto;
    private String nombreCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent()!=null){
            producto = PrestaDB.INSTANCE.productoDao().getProductoById(getIntent().getIntExtra("id",0));
            if (producto!=null){
                nombreCat = PrestaDB.INSTANCE.categoriaDao().getById(Integer.parseInt(producto.getId_category_default())).getName();
            }
        }

        getSupportActionBar().setTitle(producto.getName());

        iniciarUi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void iniciarUi() {
        if (producto!=null){
            binding.tvNombreProducto.setText(producto.getName());
            DescargarImagen.cargaImagen(binding.ivProducto, producto.getImagen());
            float precio = Float.parseFloat(producto.getPrice());
            binding.tvPrecioProducto.setText(precio+"€");

            binding.chipCategory.setText(nombreCat);
            binding.tvDescripcionProducto.setText(Html.fromHtml(producto.getDescription(), Html.FROM_HTML_MODE_COMPACT));
            binding.tvMarcaProducto.setText(producto.getManufacturer_name());
            float precioEnvio = Float.parseFloat(producto.getAdditional_shipping_cost());
            binding.tvPrecioEnvioProducto.setText(precioEnvio+"€");
        }
    }
}