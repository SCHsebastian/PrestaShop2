package es.sch.prestashop;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import es.sch.prestashop.databinding.ActivityMainBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCategoria;
import es.sch.prestashop.ui.shop.ShopFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Menu menuPrincipal;
    private NavController navController;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_shop, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.addOnDestinationChangedListener((navController, navDestination, bundle) -> {
            if (menuPrincipal!=null){
                menuPrincipal.findItem(R.id.menu_filter).setVisible(navDestination.getId() == R.id.navigation_shop);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter, menu);
        menuPrincipal = menu;
        List<DBCategoria> categoriaList = PrestaDB.INSTANCE.categoriaDao().getAll();
        if (categoriaList!=null){
            if (menuPrincipal!=null){
                menuPrincipal.findItem(R.id.menu_filter).setOnMenuItemClickListener(menuItem -> {
                    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                    builder.setTitle(R.string.filter_category);
                    //Añadimos un list picker para los items de la lista categorias
                    List<String> listaCategorias = new ArrayList<>();
                    for (DBCategoria categoria : categoriaList) {
                        listaCategorias.add(categoria.getName());
                    }

                    //NO HAY MANERA DE CONSEGUIR EL FRAGMENT AL QUE ME QUIERO REFERIR...

                    builder.setSingleChoiceItems(listaCategorias.toArray(new String[0]), -1, (dialog, which) -> {
                        /*shopFragment.onFragmentInteraction(categoriaList.get(which).getId());*/
                        dialog.dismiss();
                    });

                    builder.create().show();

                    return false;
                });
            }
        }
        return super.onCreateOptionsMenu(menu);
    }
}