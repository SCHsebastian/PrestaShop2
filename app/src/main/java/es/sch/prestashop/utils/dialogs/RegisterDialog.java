package es.sch.prestashop.utils.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import es.sch.prestashop.R;

public class RegisterDialog extends DialogFragment {

    private OnRegisterListener mListener;

    public static RegisterDialog newInstance(Bundle bundle) {

        Bundle args = new Bundle();

        RegisterDialog fragment = new RegisterDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_register, null);
        builder.setView(view);

        EditText etCorreo = view.findViewById(R.id.etDialogEmail);
        EditText etPass = view.findViewById(R.id.etDialogPass);
        EditText etNombre = view.findViewById(R.id.etDialogNombre);
        EditText etApellidos = view.findViewById(R.id.etDialogApellidos);

        Button btnRegistrar = view.findViewById(R.id.btnDialogRegistrar);
        Button btnCancelar = view.findViewById(R.id.btnDialogCancelar);


        TextView tvTitulo = view.findViewById(R.id.tvDialogTitulo);
        //get bundle
        if (getArguments().get("titulo") != null) {
            tvTitulo.setText(getArguments().get("titulo").toString());
        }else{
            tvTitulo.setText(getContext().getString(R.string.title_register));
        }
        if (getArguments().get("btn")!=null){
            btnRegistrar.setText(getArguments().get("btn").toString());
        }

        btnRegistrar.setOnClickListener(view1 -> {

            String correo = etCorreo.getText().toString();
            String pass = etPass.getText().toString();
            String nombre = etNombre.getText().toString();
            String apellidos = etApellidos.getText().toString();
            if (mListener != null) {
                mListener.onRegister(correo, pass, nombre, apellidos);
            }
            dismiss();

        });
        btnCancelar.setOnClickListener(view12 -> dismiss());

        return builder.create();

    }

    public void setOnRegisterListener(OnRegisterListener listener) {
        mListener = listener;
    }

    public interface OnRegisterListener {
        void onRegister(String correo, String pass, String nombre, String apellidos);
    }
}
