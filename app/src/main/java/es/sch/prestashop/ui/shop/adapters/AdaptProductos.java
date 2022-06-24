package es.sch.prestashop.ui.shop.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.sch.prestashop.R;
import es.sch.prestashop.api.RetrofitClient;
import es.sch.prestashop.api.prestashop.ApiUtils;
import es.sch.prestashop.databinding.AdaptProductosBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.utils.DescargarImagen;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdaptProductos extends RecyclerView.Adapter<AdaptProductos.ViewHolder> {

   private OnItemClickListener mListener;
   private final List<DBProducto> productos;
   private final Context context;

   public AdaptProductos(List<DBProducto> productos, Context context) {
      this.productos = productos;
      this.context = context;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      es.sch.prestashop.databinding.AdaptProductosBinding adaptProductosBinding = AdaptProductosBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
      View itemView = adaptProductosBinding.getRoot();
      return new ViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      DBProducto item = productos.get(position);
      if (item!=null){
         float precio = Float.parseFloat(item.getPrice());
         holder.nombre.setText(item.getName());
         holder.precio.setText(precio+"€");
         if (item.getDescription_short()!=null) {
            holder.descripcion.setText(Html.fromHtml(item.getDescription_short(), Html.FROM_HTML_MODE_COMPACT));
         }else if (item.getDescription()!=null) {
            holder.descripcion.setText(Html.fromHtml(item.getDescription(), Html.FROM_HTML_MODE_COMPACT));
         }else {
            holder.descripcion.setText("");
         }
         if(item.getImagen()!=null){
            DescargarImagen.cargaImagen(holder.imagen,item.getImagen());
         }else {
            holder.imagen.setImageResource(R.drawable.ic_downloading_foreground);
            RetrofitClient.getApiPrestashop().getImage(item.getId(),Integer.parseInt(item.getId_default_image()),ApiUtils.API_KEY).enqueue(new Callback<ResponseBody>() {
               @Override
               public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                       String ubi = DescargarImagen.writeResponseBodyToDisk(response.body(),item.getName()+item.getId()+".jpg",context);
                       if (!ubi.equals("ERROR")){
                          item.setImagen(ubi);
                          Picasso.get().load(item.getImagen()).into(holder.imagen);
                          PrestaDB.INSTANCE.productoDao().updateImagen(item.getId(),ubi);
                       }
                    }
               }

               @Override
               public void onFailure(Call<ResponseBody> call, Throwable t) {

               }
            });
         }

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
      TextView nombre, precio, descripcion;

      public ViewHolder(@NonNull View v) {
         super(v);
         imagen = v.findViewById(R.id.ivProductoAdapter);
         nombre = v.findViewById(R.id.tvNombreProductoAdapter);
         precio = v.findViewById(R.id.tvPrecioAdapter);
         descripcion = v.findViewById(R.id.tvDescripcionAdapter);
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
