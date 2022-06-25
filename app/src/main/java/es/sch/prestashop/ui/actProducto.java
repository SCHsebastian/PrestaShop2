package es.sch.prestashop.ui;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.sch.prestashop.R;
import es.sch.prestashop.api.RetrofitClient;
import es.sch.prestashop.api.binshop.BaseResponse;
import es.sch.prestashop.api.prestashop.ApiUtils;
import es.sch.prestashop.databinding.ActivityActProductoBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCarrito;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.clases.DBUser;
import es.sch.prestashop.utils.DescargarImagen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class actProducto extends AppCompatActivity {

    public static final String TAG = "actProducto";

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

            DBUser user = PrestaDB.INSTANCE.userDao().getUser();
            if (user!=null){
                binding.lytIfLogged.setVisibility(View.VISIBLE);
                binding.btnAnyadirCarritoProducto.setOnClickListener(view -> anyadirAlCarrito());
            }else {
                binding.lytIfLogged.setVisibility(View.GONE);
            }

        }
    }

    private void anyadirAlCarrito() {
        DBCarrito carrito = new DBCarrito(producto.getId(), producto.getCache_default_attribute(), Integer.parseInt(binding.etCantidad.getText().toString()));
        PrestaDB.getInstance(getApplicationContext()).carritoDao().insert(carrito);
        carrito = PrestaDB.getInstance(getApplicationContext()).carritoDao().getLast();
        RetrofitClient.getApiBinshop()
                .cartItem(carrito.getId(),carrito.getId_producto(), carrito.getAtribute(), "up", ApiUtils.UPDATE_CART_ITEM, carrito.getQty()).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body().getSuccess()){
                    Toast.makeText(getApplicationContext(), R.string.cart_add_succesfully, Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),R.string.cart_adding_error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),R.string.cart_adding_error, Toast.LENGTH_LONG).show();
                Log.e(TAG,"Error al añadir el producto al carrito"+ t.getMessage());
            }
        });
    }
}