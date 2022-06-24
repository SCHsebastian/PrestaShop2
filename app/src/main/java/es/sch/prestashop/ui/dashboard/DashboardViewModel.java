package es.sch.prestashop.ui.dashboard;

import androidx.lifecycle.ViewModel;

import es.sch.prestashop.db.clases.DBUser;

public class DashboardViewModel extends ViewModel {

    public static volatile DBUser user;

    public DashboardViewModel(DBUser user) {
        DashboardViewModel.user = user;
    }

    public static DBUser getUser() {
        return user;
    }

    public static void setUser(DBUser user) {
        DashboardViewModel.user = user;
    }
}