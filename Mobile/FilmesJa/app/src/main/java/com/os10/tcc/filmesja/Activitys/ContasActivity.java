package com.os10.tcc.filmesja.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.os10.tcc.filmesja.R;
import com.os10.tcc.filmesja.control.ControlCliente;

public class ContasActivity extends AppCompatActivity {

    public static Bitmap image;

    public static ContasActivity activity;

    public static ImageView[] imageContas;
    public static TextView[] txtContas;
    public static int[] ids;
    public static String[] baseCodes;
    public static String[] datasNascimento;

    public static Button btnAdicionarConta;

    public static String baseA;

    public static int IDUsuario = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);

        activity = this;

        btnAdicionarConta = (Button)findViewById(R.id.btnAdicionarConta);
        btnAdicionarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getBaseContext(), CriarContaActivity.class);
                CriarContaActivity.IDUsuario = IDUsuario;
                //System.out.println("p" + getRetorno());
                activity.startActivity(intent);
            }
        });

        btnAdicionarConta.setVisibility(View.INVISIBLE);



        imageContas = new ImageView[4];
        imageContas[0] = (ImageView) findViewById(R.id.imgConta1);
        imageContas[1] = (ImageView) findViewById(R.id.imgConta2);
        imageContas[2] = (ImageView) findViewById(R.id.imgConta3);
        imageContas[3] = (ImageView) findViewById(R.id.imgConta4);

        txtContas = new TextView[4];
        txtContas[0] = (TextView) findViewById(R.id.txtConta1);
        txtContas[1] = (TextView) findViewById(R.id.txtConta2);
        txtContas[2] = (TextView) findViewById(R.id.txtConta3);
        txtContas[3] = (TextView) findViewById(R.id.txtConta4);

        ids = new int[4];
        baseCodes = new String[4];
        datasNascimento = new String[4];

        ControlCliente cliente = new ControlCliente();


        if(IDUsuario == 0){
            IDUsuario = Integer.parseInt(ControlCliente.getRetorno());
        }
        cliente.CarregarContas(String.valueOf(IDUsuario), this);
    }

    public static View.OnClickListener SelecionarConta = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String base = "";
            String nome = "";
            String dataNascimento = "";
            int id = 0;
            for (int i = 0; i < imageContas.length; i++){
                if (imageContas[i].getId() == view.getId()){
                    base = baseCodes[i];
                    nome = txtContas[i].getText().toString();
                    dataNascimento = datasNascimento[i];
                    id = ids[i];
                }
            }
            Intent intent = new Intent(activity.getBaseContext(), FulanoActivity.class);
            intent.putExtra("IMAGEM", base);
            intent.putExtra("NOME", nome);
            intent.putExtra("ID", String.valueOf(id));
            intent.putExtra("DATA", dataNascimento);
            activity.startActivity(intent);
        }
    };

    @Override
    public void onBackPressed(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Deslogando")
                .setMessage("Você tem certeza que deseja deslogar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(activity.getBaseContext(), LoginActivity.class);
                        finish();
                        startActivity(i);
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }
}
