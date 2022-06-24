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

import es.sch.prestashop.R;
import es.sch.prestashop.databinding.FragmentShopBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.clases.DBUser;
import es.sch.prestashop.ui.actProducto;
import es.sch.prestashop.ui.shop.adapters.AdaptProductos;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;
    private DBUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        user = PrestaDB.getInstance(getContext()).userDao().getUser();
        iniciarUi();

        return root;
    }

    private void iniciarUi() {

        ShopViewModel homeViewModel = new ShopViewModel(getActivity().getApplication());
        RecyclerView rv = binding.rvProductos;
        LiveData<List<DBProducto>> productosLiveData = homeViewModel.getProductos();
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
                                homeViewModel.insertarProducto(producto);
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
}