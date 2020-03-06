package com.os10.tcc.filmesja.control;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.os10.tcc.filmesja.Activitys.ContasActivity;
import com.os10.tcc.filmesja.util.ConnectWeb;
import com.os10.tcc.filmesja.util.ConsLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Ricardo on 09/03/2017.
 */

public class ControlCliente extends ControlBase{
    private static final String LOGIN_VALID = "1";
    private static final String LOGIN_INVALID = "0";

    private static final int LOGIN = 1;
    private static final int CARREGAR_CONTAS = 2;

    private int OperationType = 0;

    private ConnectWeb con;
    private HashMap<String, String> ModelCliente;

    private AppCompatActivity activity;

    public boolean Logar(String Email, String Senha, AppCompatActivity activity){
        ModelCliente = new HashMap<>();
        ModelCliente.put("email", Email);
        ModelCliente.put("senha", Senha);
        this.activity = activity;
        con  = new ConnectWeb(ConsLink.UsuarioLogin, ModelCliente, this, activity);
        OperationType = LOGIN;
        con.Start();
        return false;
    }

    public void CarregarContas(String IDCliente, AppCompatActivity activity){
        ModelCliente = new HashMap<>();
        ModelCliente.put("IDUSUARIO", IDCliente);
        this.activity = activity;
        con = new ConnectWeb(ConsLink.PegarContas, ModelCliente, this, activity);
        OperationType = CARREGAR_CONTAS;
        con.Start();
    }

    @Override
    public void FinishOperation() {
        if (OperationType == LOGIN) {
            //Call perfil control
            if (!getRetorno().equals("0")) {
                ContasActivity.IDUsuario = 0;
                Intent intent = new Intent(activity.getBaseContext(), ContasActivity.class);
                //System.out.println("p" + getRetorno());
                activity.startActivity(intent);
            } else {
                Toast.makeText(activity.getBaseContext(), "Login inválido", Toast.LENGTH_LONG).show();
            }
        }

        if (OperationType == CARREGAR_CONTAS) {
            try {
                //System.out.print(getRetorno());
                JSONObject jsonObject = new JSONObject(getRetorno());
                JSONArray jsonArray = jsonObject.getJSONArray("contas");

                if(jsonArray.length() < 4){
                    ContasActivity.btnAdicionarConta.setVisibility(View.VISIBLE);
                }

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);
                    String imagemCode = object.getString("imagem");
                    byte[] lista = Base64.decode(imagemCode, Base64.DEFAULT);

                    Bitmap imageBitmap = BitmapFactory.decodeByteArray(lista, 0, lista.length);
                    ContasActivity.imageContas[i].setImageBitmap(imageBitmap);
                    ContasActivity.txtContas[i].setText(object.getString("nome"));
                    ContasActivity.ids[i] = Integer.parseInt(object.getString("IDConta"));
                    ContasActivity.imageContas[i].setOnClickListener(ContasActivity.SelecionarConta);
                    ContasActivity.baseCodes[i] = imagemCode;
                    ContasActivity.datasNascimento[i] = object.getString("data");

                   // ContasActivity.image.setImageBitmap(imageBitmap);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
