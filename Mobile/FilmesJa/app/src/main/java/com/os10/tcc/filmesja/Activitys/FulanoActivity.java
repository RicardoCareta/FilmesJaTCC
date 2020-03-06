package com.os10.tcc.filmesja.Activitys;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.control.ControlCatalogo;

public class FulanoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String ID;

    private String Nome;
    private String baseCodeImage;
    private String dataNascimento;

    private EditText txtNome;
    private FloatingActionButton fab;

    private static Activity activity;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulano);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ControlCatalogo controlCatalogo = new ControlCatalogo();
                controlCatalogo.PesquisarMidia((AppCompatActivity) activity, InicioFragment.txtPesquisar.getText().toString());


               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(findViewById(R.id.content_frame).getWindowToken(), 0);
               // Log.d("TCCCCCCC", "entrou");
            }
        });
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        intent = getIntent();
        Nome = intent.getStringExtra("NOME");
        ID = intent.getStringExtra("ID");
        baseCodeImage = intent.getStringExtra("IMAGEM");
        dataNascimento = intent.getStringExtra("DATA");

       // System.out.println(baseCodeImage);

        getSupportActionBar().setTitle("Olá " + Nome);

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fulano, menu);


        if(baseCodeImage != null){

            byte[] lista = Base64.decode(baseCodeImage, Base64.DEFAULT);

            Bitmap imageBitmap = BitmapFactory.decodeByteArray(lista, 0, lista.length);
            RoundedBitmapDrawable imageRedonda = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
            //imageRedonda.setCornerRadius(200);
            imageRedonda.setCircular(true);
            ImageView img = (ImageView) findViewById(R.id.imgPerfil);

            img.setImageDrawable(imageRedonda);
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, InicioFragment.newInstance(ID, "as")).commit();


        activity = this;


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar willm
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        item.getActionView().setBackgroundColor(Color.RED);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        fab.setVisibility(View.INVISIBLE);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.content_frame).getWindowToken(), 0);
        //Log.d("TCCCCCCC", "entrou");

        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_favorito) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, FavoritoFragment.newInstance(ID, "as")).commit();
        } else if (id == R.id.nav_inicio) {
            fab.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().replace(R.id.content_frame, InicioFragment.newInstance(ID, "as")).commit();
        } else if (id == R.id.nav_conta) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, ContaFragment.newInstance(Nome, baseCodeImage, ID, dataNascimento)).commit();
        } else if (id == R.id.nav_config) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, ConfigFragment.newInstance("as", "as")).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
