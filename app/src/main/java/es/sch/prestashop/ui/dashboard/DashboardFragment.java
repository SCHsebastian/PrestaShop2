package es.sch.prestashop.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import es.sch.prestashop.databinding.FragmentDashboardBinding;
import es.sch.prestashop.db.PrestaDB;
import es.sch.prestashop.db.daos.UserDao;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private UserDao userDao;
    private DashboardViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        userDao = PrestaDB.INSTANCE.userDao();
        viewModel = new DashboardViewModel(userDao.getUser());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if ( userDao.getUser() == null){
            binding.lytLogReg.setVisibility(View.VISIBLE);
        }else {
            binding.lytLogReg.setVisibility(View.GONE);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}