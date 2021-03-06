package es.sch.prestashop.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.sch.prestashop.R;
import es.sch.prestashop.api.BinshopApi;
import es.sch.prestashop.api.RetrofitClient;
import es.sch.prestashop.api.binshop.BaseResponse;
import es.sch.prestashop.api.binshop.User;
import es.sch.prestashop.api.prestashop.Products;
import es.sch.prestashop.databinding.FragmentDashboardBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.clases.DBCarrito;
import es.sch.prestashop.db.clases.DBProducto;
import es.sch.prestashop.db.clases.DBUser;
import es.sch.prestashop.db.daos.CarritoDao;
import es.sch.prestashop.db.daos.UserDao;
import es.sch.prestashop.utils.dialogs.RegisterDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements View.OnClickListener {

    private FragmentDashboardBinding binding;
    private UserDao userDao;
    private DashboardViewModel viewModel;
    private BinshopApi api;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        api = RetrofitClient.getApiBinshop();
        userDao = PrestaDB.INSTANCE.userDao();
        viewModel = new DashboardViewModel(userDao.getUser());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if ( userDao.getUser() == null){
            binding.tvNombreUsuario.setText(getText(R.string.title_login));
            binding.lytLogReg.setVisibility(View.VISIBLE);

            /*binding.btnModify.setVisibility(View.GONE);*/
            binding.btnLogout.setVisibility(View.GONE);
            binding.btnIniciarSesion.setVisibility(View.VISIBLE);
            binding.btnRegistrar.setVisibility(View.VISIBLE);

            Button btnReg = binding.btnRegistrar;
            btnReg.setOnClickListener(this);
            Button btnLogin = binding.btnIniciarSesion;
            btnLogin.setOnClickListener(this);
        }else {
            DBUser user = userDao.getUser();
            String nombre = user.getFirstName() + " " + user.getLastName();
            binding.tvNombreUsuario.setText(nombre);
            binding.lytLogReg.setVisibility(View.GONE);
            //btns
            binding.btnIniciarSesion.setVisibility(View.GONE);
            binding.btnRegistrar.setVisibility(View.GONE);
            /*binding.btnModify.setVisibility(View.VISIBLE);*/
            binding.btnLogout.setVisibility(View.VISIBLE);
            /*binding.btnModify.setOnClickListener(this);*/
            binding.btnLogout.setOnClickListener(this);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnRegistrar){
            RegisterDialog dialog = RegisterDialog.newInstance(null);
            dialog.setOnRegisterListener((correo, pass, nombre, apellidos) -> {
                api.register(correo, pass, nombre, apellidos).enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()){
                            BaseResponse baseResponse = response.body();
                            if (baseResponse.getCode()==200){
                                Toast.makeText(getContext(), "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });

            });
            dialog.show(getParentFragmentManager(), "RegisterDialog");
        }else if (view.getId()==R.id.btnIniciarSesion){
            String correo = binding.etEmail.getText().toString();
            String pass = binding.etPassword.getText().toString();
            api.login(correo,pass).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()){
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getCode()==200){
                            User user = baseResponse.getPsdata().getUser();
                            user.setCookieValue(response.headers().get("set-cookie"));
                            DBUser dbUser = new DBUser(user.getFirstname(),user.getLastname(),user.getEmail(),user.getCookieValue());
                            userDao.insert(dbUser);
                            viewModel.setUser(dbUser);
                            onViewCreated(binding.getRoot(), null);
                            api.getCart().enqueue(new Callback<BaseResponse>() {
                                @Override
                                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                    if (response.isSuccessful()){
                                        BaseResponse baseResponse = response.body();
                                        if (baseResponse.getCode()==200){
                                            List<DBProducto> products = baseResponse.getPsdata().getProducts();
                                            CarritoDao dap = PrestaDB.getInstance(getContext()).carritoDao();
                                            if (products!=null){
                                                for (DBProducto item: products){
                                                    DBCarrito dbCarrito = new DBCarrito(
                                                            item.getId()
                                                            ,item.getCache_default_attribute(),item.getCart_quantity());
                                                    dap.insert(dbCarrito);
                                                }
                                            }
                                            Toast.makeText(getContext(), R.string.cart_succesfully_obtained, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<BaseResponse> call, Throwable t) {
                                    Toast.makeText(getActivity(), R.string.cart_obtaining_error, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
                }
            });
        }else if (view.getId()==R.id.btnLogout){
            api.logout().enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()){
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getCode()==200){
                            Toast.makeText(getContext(), getText(R.string.logout_text), Toast.LENGTH_SHORT).show();
                        }
                        userDao.delete(viewModel.getUser());
                        PrestaDB.getInstance(getContext()).carritoDao().deleteAll();
                        viewModel.setUser(null);
                        onViewCreated(binding.getRoot(), null);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    userDao.delete(viewModel.getUser());
                    viewModel.setUser(null);
                    onViewCreated(binding.getRoot(), null);
                }
            });

        }/*else if (view.getId()==R.id.btnModify){
            Bundle bundle = new Bundle();
            bundle.putString("titulo", getText(R.string.title_modify_user).toString());
            bundle.putString("btn", getText(R.string.modify).toString());

            RegisterDialog dialog = RegisterDialog.newInstance(bundle);
            dialog.setOnRegisterListener((correo, pass, nombre, apellidos) -> api.updateUser(correo, pass, nombre, apellidos,1).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()){
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getCode()==200){
                            User user = baseResponse.getPsdata().getUser();
                            user.setCookieValue(response.headers().get("set-cookie"));
                            DBUser dbUser = new DBUser(user.getFirstname(),user.getLastname(),user.getEmail(),user.getCookieValue());
                            userDao.insert(dbUser);
                            viewModel.setUser(dbUser);
                            onViewCreated(binding.getRoot(), null);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Error al modificar", Toast.LENGTH_SHORT).show();
                }
            }));
            dialog.show(getParentFragmentManager(), "ModifyDialog");
        }*/
    }
}