package es.sch.prestashop.ui.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.sch.prestashop.R;
import es.sch.prestashop.databinding.FragmentShopBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCategoria;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.clases.DBUser;
import es.sch.prestashop.ui.actProducto;
import es.sch.prestashop.ui.shop.adapters.AdaptProductos;

public class ShopFragment extends Fragment{

    public static final String TAG = "ShopFragment";

    private FragmentShopBinding binding;
    private DBUser user;
    private ShopViewModel shopViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = PrestaDB.getInstance(getContext()).userDao().getUser();
        iniciarUi();

        return root;
    }

    private void iniciarUi() {

        shopViewModel = new ShopViewModel(getActivity().getApplication());
        RecyclerView rv = binding.rvProductos;
        LiveData<List<DBProducto>> productosLiveData = shopViewModel.getProductos();
        productosLiveData.observe(getViewLifecycleOwner(), products -> {
            if (products.size()!=0){
                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                AdaptProductos adaptProductos = new AdaptProductos(products, getContext());
                adaptProductos.setOnItemClickListener(new AdaptProductos.OnItemClickListener() {
                    @Override
                    public void onItemLongClick(View view, int position) {
                        if (user!=null){
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                            builder.setTitle(getString(R.string.add_to_cart_dialog));
                            builder.setPositiveButton(R.string.add, (dialog, which) -> {
                                DBProducto producto = products.get(position);
                                shopViewModel.insertarProducto(producto);
                            });
                            builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                                dialog.dismiss();
                            });
                            builder.create().show();
                        }
                    }

                    @Override
                    public void onItemClick(View view, int position) {
                        DBProducto producto = products.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", producto.getId());
                        Intent intent = new Intent(getActivity(), actProducto.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                rv.setAdapter(adaptProductos);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void onFragmentInteraction(int id) {
        DBCategoria categoria = PrestaDB.getInstance(getContext()).categoriaDao().getById(id);
        if (categoria!=null){
            shopViewModel.setCondicionBusqueda(categoria.getName());
        }
    }

}