package com.unsera.sipe3.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.unsera.sipe3.R;

public class AdminActivity extends AppCompatActivity {


    private String noKTP;

    private static final String EXTRA_NO_KTP = "com.unsera.sipe3.extra.noktp";

    public static Intent newIntent(Context context, String noKTP) {
        Intent intent = new Intent(context, AdminActivity.class);
        intent.putExtra(EXTRA_NO_KTP, noKTP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        noKTP = getIntent().getStringExtra(EXTRA_NO_KTP);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_kandidat, R.id.navigation_pengaduan, R.id.navigation_pelanggaran, R.id.navigation_pasal_pelanggaran)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public String getNoKTP() {
        return noKTP;
    }

}
