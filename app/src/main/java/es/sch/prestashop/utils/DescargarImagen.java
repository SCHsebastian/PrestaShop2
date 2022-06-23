package es.sch.prestashop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;

public class DescargarImagen {

    public static String UBICACION_IMAGENES = "/images/";

    public static Bitmap descargar(String url) {
        Log.d("DescargarImagen", "Descargando imagen: " + url);
        Bitmap bitmap = null;
        try {
            // Descargamos la imagen
            InputStream input = new java.net.URL(url).openStream();
            // Decodificamos
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static String descargarImagen(String imageURL, String itemName, Context context) {
        context.getExternalFilesDir(UBICACION_IMAGENES);
        String fileName = "imagen"+itemName+".jpg";
        // Usamos el método descargar para obtener la imagen y guardarla en la carpeta de imágenes
        Bitmap bitmap = descargar(imageURL);
        if (bitmap != null) {
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(context.getExternalFilesDir(UBICACION_IMAGENES) + "/" + fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
