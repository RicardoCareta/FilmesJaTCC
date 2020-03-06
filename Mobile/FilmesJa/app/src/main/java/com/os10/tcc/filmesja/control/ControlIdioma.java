package com.os10.tcc.filmesja.control;

import android.support.v7.app.AppCompatActivity;

import com.os10.tcc.filmesja.Activitys.ContaFragment;
import com.os10.tcc.filmesja.Model.MDIdioma;
import com.os10.tcc.filmesja.StaticValues.StaticIdiomas;
import com.os10.tcc.filmesja.util.ConnectWeb;
import com.os10.tcc.filmesja.util.ConsLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Ricardo on 12/08/2017.
 */

public class ControlIdioma extends ControlBase {
    private static final int CARREGAR_IDIOMAS = 1;

    public static final int CONTA_FRAGMENT = 1;

    private int tipoOperacao = 0;
    private int CarinhaQueChamou = 0;

    private ConnectWeb cn;

    private HashMap<String, String> modelIdioma;

    private AppCompatActivity appCompatActivity;

    public void CarregarIdioma(AppCompatActivity activity, int QmChamou){

        this.appCompatActivity = activity;
        this.CarinhaQueChamou = QmChamou;

        modelIdioma = new HashMap<>();
        cn = new ConnectWeb(ConsLink.PegarIdiomas, modelIdioma, this, appCompatActivity);

        tipoOperacao = CARREGAR_IDIOMAS;
        cn.Start();
    }

    @Override
    public void FinishOperation(){
        if (tipoOperacao == CARREGAR_IDIOMAS){
            JSONObject jsonObject = null;
            try{
                jsonObject = new JSONObject(getRetorno());
                JSONArray jsonArray = jsonObject.getJSONArray("idiomas");

                StaticIdiomas.ListaIdiomas = new MDIdioma[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String nome = object.getString("nome");

                    MDIdioma mdIdioma = new MDIdioma();
                    mdIdioma.ID = Integer.parseInt(id);
                    mdIdioma.Nome = nome;

                    StaticIdiomas.ListaIdiomas[i] = mdIdioma;
                }

                switch (CarinhaQueChamou){
                    case CONTA_FRAGMENT:
                        ContaFragment.CarregarComboIdiomas();
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
