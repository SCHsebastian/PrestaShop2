package es.sch.prestashop.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import es.sch.prestashop.R;
import es.sch.prestashop.databinding.FragmentNotificationsBinding;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.ui.actProducto;
import es.sch.prestashop.ui.notifications.adapter.AdaptCarrito;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        iniciarUi(root);

        return root;
    }

    private void iniciarUi(View v) {
        NotificationsViewModel notificationsViewModel = new NotificationsViewModel(getActivity().getApplication());
        RecyclerView rv = binding.rvCarrito;
        LiveData<List<DBProducto>> productos = notificationsViewModel.getProductos();
        productos.observe(getViewLifecycleOwner(), productos2 -> {
            if (productos2 != null) {
               rv.setLayoutManager(new LinearLayoutManager(getActivity()));
               AdaptCarrito adaptCarrito = new AdaptCarrito(productos2, getContext());
               adaptCarrito.setOnItemClickListener(new AdaptCarrito.OnItemClickListener() {
                   @Override
                   public void onItemLongClick(View view, int position) {
                       MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
                       builder.setTitle(getString(R.string.cart_remove_text));
                       builder.setPositiveButton(R.string.cart_remove, (dialog, which) -> {
                           DBProducto producto = productos2.get(position);
                           notificationsViewModel.eliminarProducto(producto);
                       });
                       builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                           dialog.dismiss();
                       });
                       builder.create().show();
                   }

                   @Override
                   public void onItemClick(View view, int position) {
                       DBProducto producto = productos2.get(position);
                       Bundle bundle = new Bundle();
                       bundle.putInt("id", producto.getId());
                       Intent intent = new Intent(getActivity(), actProducto.class);
                       intent.putExtras(bundle);
                       startActivity(intent);
                   }
               });
               rv.setAdapter(adaptCarrito);
               String total = String.format("%.2f", notificationsViewModel.getPrecioTotal())+"€";
               binding.tvTotalCarrito.setText(total);
            }
        });

        binding.btnRemoveAll.setOnClickListener(view -> {

            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            builder.setTitle(getString(R.string.cart_remove_all_text));
            builder.setPositiveButton(R.string.cart_remove_all, (dialog, which) -> {
                notificationsViewModel.eliminarTodo();
                dialog.dismiss();
            });
            builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                dialog.dismiss();
            });
            builder.create().show();

        });

        binding.btnCheckOut.setOnClickListener(view -> {
            //NO YET IMPLEMENTED
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
            builder.setTitle(getString(R.string.cart_checkout_text));
            builder.setPositiveButton(R.string.cart_checkout, (dialog, which) -> {
                Toast.makeText(getActivity(), "NOT YET IMPLEMENTED", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });
            builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                dialog.dismiss();
            });
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}