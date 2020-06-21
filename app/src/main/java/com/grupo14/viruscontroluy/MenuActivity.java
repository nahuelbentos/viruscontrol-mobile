package com.grupo14.viruscontroluy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.grupo14.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontroluy.providers.AuthProvider;
import com.grupo14.viruscontroluy.providers.TokenProvider;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TokenProvider mTokenProvider;
    private AuthProvider mAuthProvider;
    private TextView tvUsername;
    private TextView tvCorreoElectronico;
    ProfileTracker mProfileTracker;
    ProfilePictureView profilePictureView;
    ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mAuthProvider = new AuthProvider();
        mTokenProvider = new TokenProvider();
        generarToken();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.solicitar_medico, R.id.nav_reportes, R.id.nav_Cerrar_Sesion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        tvUsername = headerView.findViewById(R.id.username_menu_lateral);
        tvCorreoElectronico = headerView.findViewById(R.id.textView_correo_electronico);

        profilePictureView = headerView.findViewById((R.id.imageView));

        if(Profile.getCurrentProfile() == null) {
            mProfileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                    Log.v("MenuActivity - fb - nm", currentProfile.getFirstName());
                    Log.v("MenuActivity - fb - id", currentProfile.getId());
                    mProfileTracker.stopTracking();
                    tvUsername.setText(Profile.getCurrentProfile().getName());
                    profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                }
            };

            // no need to call startTracking() on mProfileTracker
            // because it is called by its constructor, internally.
        }
        else {
            Profile profile = Profile.getCurrentProfile();
            Log.v("MenuActivity - facebook", profile.getFirstName());
            Log.v("MenuActivity - facebook", profile.getLastName());
            Log.v("MenuActivity - facebook", profile.getId());
            //mProfileTracker.stopTracking();
            tvUsername.setText(Profile.getCurrentProfile().getName());
            profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void generarToken(){

        mTokenProvider.create(mAuthProvider.getId());
    }

}