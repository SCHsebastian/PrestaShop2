package es.sch.prestashop.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.sch.prestashop.databinding.FragmentShopBinding;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.ui.shop.adapters.AdaptProductos;

public class ShopFragment extends Fragment {

    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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