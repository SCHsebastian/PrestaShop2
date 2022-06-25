package es.sch.prestashop.ui.notifications.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.sch.prestashop.R;
import es.sch.prestashop.databinding.AdaptCarritoBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCarrito;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.daos.CarritoDao;
import es.sch.prestashop.utils.DescargarImagen;

public class AdaptCarrito extends RecyclerView.Adapter<AdaptCarrito.AdapViewHolder>{

    private OnItemClickListener mListener;
    private final List<DBProducto> productos;
    private final Context context;

    public AdaptCarrito(List<DBProducto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdaptCarritoBinding adaptProductosBinding = AdaptCarritoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        View itemView = adaptProductosBinding.getRoot();
        return new AdapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapViewHolder holder, int position) {
        DBProducto item = productos.get(position);
        if (item!=null){
            CarritoDao carritoDao = PrestaDB.getInstance(context).carritoDao();
            DBCarrito carrito = carritoDao.getByIdProducto(item.getId());
            float precio = Float.parseFloat(item.getPrice());
            holder.nombre.setText(item.getName());
            String cantidad = carrito.getQty()+" x "+String.format("%.2f", precio)+"€";
            holder.cantidad.setText(cantidad);
            String precioStr = String.format("%.2f", precio*carrito.getQty())+"€";
            holder.precio.setText(precioStr);
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
            }
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class AdapViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView nombre, precio, cantidad, descripcion;

        public AdapViewHolder(@NonNull View v) {
            super(v);
            imagen = v.findViewById(R.id.ivProductoAdapter);
            nombre = v.findViewById(R.id.tvNombreProductoAdapter);
            precio = v.findViewById(R.id.tvPrecioAdapter);
            cantidad = v.findViewById(R.id.tvCantidadAdapter);
            descripcion = v.findViewById(R.id.tvDescripcionAdapter);
            v.setOnClickListener(view -> {
                if (mListener!=null){
                    mListener.onItemClick(v,getAdapterPosition());
                }
            });
            v.setOnLongClickListener(view -> {
                if (mListener!=null){
                    mListener.onItemLongClick(v, getAdapterPosition());
                }
                return true;
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemLongClick(View view, int position);
        void onItemClick(View view, int position);
    }
}
