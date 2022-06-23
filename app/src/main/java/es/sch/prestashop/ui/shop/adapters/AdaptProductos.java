package es.sch.prestashop.ui.shop.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.sch.prestashop.R;
import es.sch.prestashop.databinding.AdaptProductosBinding;
import es.sch.prestashop.db.clases.DBProducto;

public class AdaptProductos extends RecyclerView.Adapter<AdaptProductos.ViewHolder> {

   private OnItemClickListener mListener;
   private final List<DBProducto> productos;
   private AdaptProductosBinding adaptProductosBinding;

   public AdaptProductos(List<DBProducto> productos) {
      this.productos = productos;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      adaptProductosBinding = AdaptProductosBinding.inflate(LayoutInflater.from(parent.getContext()), parent,false);
      View itemView = adaptProductosBinding.getRoot();
      return new ViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      DBProducto item = productos.get(position);
      if (item!=null){
         holder.nombre.setText(item.getName());
      }
   }

   @Override
   public int getItemCount() {
      return productos.size();
   }

   public interface OnItemClickListener {
      void onItemLongClick(View view, int position);
      void onItemClick(View view, int position);
   }


   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

      ImageView imagen;
      TextView nombre, precio;

      public ViewHolder(@NonNull View v) {
         super(v);
         imagen = v.findViewById(R.id.ivProductoAdapter);
         nombre = v.findViewById(R.id.tvDialogTitulo);
         precio = v.findViewById(R.id.tvPrecioAdapter);
      }

      @Override
      public void onClick(View view) {
         mListener.onItemClick(view, getAdapterPosition());
      }


      @Override
      public boolean onLongClick(View view) {
         mListener.onItemLongClick(view, getAdapterPosition());
         return false;
      }
   }
}
