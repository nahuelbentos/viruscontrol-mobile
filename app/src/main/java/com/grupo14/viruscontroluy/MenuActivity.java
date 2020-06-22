package com.grupo14.viruscontroluy;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.grupo14.viruscontroluy.modelos.Sintoma;
import com.grupo14.viruscontroluy.providers.AuthProvider;
import com.grupo14.viruscontroluy.providers.LoginBackendProvider;
import com.grupo14.viruscontroluy.providers.TokenProvider;
import com.grupo14.viruscontroluy.ui.cerrarsesion.CerrarSesionActivity;
import com.grupo14.viruscontroluy.utility.Utility;
import com.squareup.picasso.Picasso;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MenuActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TokenProvider mTokenProvider;
    private AuthProvider mAuthProvider;

    private TextView mTextViewNombreUsuario;
    private TextView mTextViewEmailUsuario;

    private ImageView mImageViewFotoUsuario;
    private LoginBackendProvider mLoginBackendProvider;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mAuthProvider = new AuthProvider();
        mTokenProvider = new TokenProvider(MenuActivity.this);
        mLoginBackendProvider = new LoginBackendProvider(MenuActivity.this);

        generarToken();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.solicitar_medico, R.id.nav_reportes, R.id.nav_Cerrar_Sesion)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        Log.d("menuActivity", "getCurrentDestination: " + navController.getCurrentDestination().toString());
        Log.d("menuActivity", "getNavigatorName: " + navController.getCurrentDestination().getNavigatorName().toString());
        Log.d("menuActivity", "getId: " + navController.getCurrentDestination().getId());



        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);



        mTextViewEmailUsuario = headerView.findViewById(R.id.textViewEmail);
        mTextViewNombreUsuario = headerView.findViewById(R.id.textViewNombreUsuario);
        mImageViewFotoUsuario = headerView.findViewById(R.id.imagenUsuario);

        mTextViewNombreUsuario.setText(Utility.getInstance().getFirebaseUser().getDisplayName());
        mTextViewEmailUsuario.setText(Utility.getInstance().getFirebaseUser().getEmail());

        String image = Utility.getInstance().getFirebaseUser().getPhotoUrl().toString();

        mImageViewFotoUsuario.setImageURI(Utility.getInstance().getFirebaseUser().getPhotoUrl());

        Picasso.with(MenuActivity.this ).load(image).into(mImageViewFotoUsuario);

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