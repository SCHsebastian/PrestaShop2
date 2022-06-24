package es.sch.prestashop.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class DescargarImagen {

    public static String UBICACION_IMAGENES = "/images/";

    public static void cargaImagen(ImageView ivImagen, String uri) {
        Glide
                .with(ivImagen.getContext())
                //url de la imagen
                .load(uri)
                //centramos la imagen
                .centerCrop()
                //mientras se carga la imagen que imagen queremos mostrar
                .placeholder(android.R.drawable.stat_notify_sync_noanim)
                //Imagen que mostramos si hay error
                .error(android.R.drawable.stat_notify_error)
                //donde colocamos la imagen
                .into(ivImagen);
    }


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

    public static String writeResponseBodyToDisk(ResponseBody body, String name, Context context) {
        try {
            String path = context.getFilesDir().toString();
            Log.d("DescargarImagen", "Guardando imagen en: " + path);
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();


            File imagen = new File(path, name);
            if (imagen.exists()) {
                imagen.delete();
            }


            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                //long fileSize = body.contentLength();
                //long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(imagen);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    //fileSizeDownloaded += read;
                }

                outputStream.flush();

                return imagen.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                return "ERROR";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
