package com.os10.tcc.filmesja.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.os10.tcc.filmesja.Model.MDCatalogo;
import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.control.ControlConta;

public class ExibeFilmeActivity extends AppCompatActivity {

    private ImageView imageView;

    private TextView txtNomeMidia;
    private TextView txtAno;
    private TextView txtSinopse;

    public static MDCatalogo mdCatalogo;

    private static ImageButton imgBtnDepre;
    private static ImageButton imgBtnMeio;
    private static ImageButton imgBtnFeliz;

    private static ImageButton imgButtonFavorito;

    public static String nota;

    public static String IDFilme;
    public static String IDConta;

    private static String newValue = "";

    private static boolean haveFavorito = false;

    private static AppCompatActivity appCompatActivity;

    private static ControlConta controlConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_filme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        imageView = (ImageView) findViewById(R.id.imgTelaFilme);
        imageView.setImageBitmap(mdCatalogo.getImage());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), Player2Activity.class);
                startActivity(i);
            }
        });

        controlConta = new ControlConta();

        txtNomeMidia = (TextView) findViewById(R.id.txtNomeMidia);
        txtNomeMidia.setText(mdCatalogo.getDefaultNome());

        txtAno = (TextView) findViewById(R.id.txtAnoExibe);
        txtAno.setText(Integer.toString(mdCatalogo.getAno()));

        txtSinopse = (TextView) findViewById(R.id.txtSinopse);
        txtSinopse.setText(mdCatalogo.getDefaultSinopse());

        imgBtnDepre = (ImageButton) findViewById(R.id.imgButtonDepre);
        imgBtnDepre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newValue = "1";
                SetButton("1");
                controlConta.SalvarNota(IDConta, String.valueOf(mdCatalogo.getIDCat()), "1", appCompatActivity);
            }
        });

        imgBtnMeio = (ImageButton) findViewById(R.id.imgButtonMeio);
        imgBtnMeio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newValue = "2";
                SetButton("2");
                controlConta.SalvarNota(IDConta, String.valueOf(mdCatalogo.getIDCat()), "2", appCompatActivity);
            }
        });

        imgBtnFeliz = (ImageButton) findViewById(R.id.imgButtonFeliz);
        imgBtnFeliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newValue = "3";
                SetButton("3");
                controlConta.SalvarNota(IDConta, String.valueOf(mdCatalogo.getIDCat()), "3", appCompatActivity);
            }
        });

        controlConta.PegarNota(IDConta, String.valueOf(mdCatalogo.getIDCat()), this);

        imgButtonFavorito = (ImageButton) findViewById(R.id.imgButtonFavorito);
        imgButtonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(haveFavorito){
                    controlConta.RemoveFavorito(IDConta, String.valueOf(mdCatalogo.getIDCat()), appCompatActivity);
                }else{
                    controlConta.AddFavorito(IDConta, String.valueOf(mdCatalogo.getIDCat()), appCompatActivity);
                }
            }
        });


        appCompatActivity = this;
        //controlConta.HaveFavorito(IDConta, String.valueOf(mdCatalogo.getIDCat()), appCompatActivity);

    }

    public static void SetButton(String tipo){
        imgBtnDepre.setImageResource(R.drawable.depre);
        imgBtnMeio.setImageResource(R.drawable.naquelas);
        imgBtnFeliz.setImageResource(R.drawable.feliz);

        if(tipo.indexOf("1") != -1){
            imgBtnDepre.setImageResource(R.drawable.deprevermelinho);
        }
        else if(tipo.indexOf("2") != -1){
            imgBtnMeio.setImageResource(R.drawable.naquelasamarelo);
        }
        else {
            imgBtnFeliz.setImageResource(R.drawable.felizverdinho);
        }
    }

    public static void PegarNota(String tipo){
        SetButton(tipo);
        controlConta.HaveFavorito(IDConta, String.valueOf(mdCatalogo.getIDCat()), appCompatActivity);
    }

    public static void Atualizar(){
        Log.d("TCCURGENTE", newValue);
        SetButton(newValue);
    }

    public static void ChangeFavorito(int t){
        if(t == 0){
            imgButtonFavorito.setImageResource(R.drawable.coracaocheio);
            haveFavorito = true;
        }

        if(t == 1){
            imgButtonFavorito.setImageResource(R.drawable.coracaovazio);
            haveFavorito = false;
        }
    }
}
