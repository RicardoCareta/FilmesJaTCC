package com.os10.tcc.filmesja.control;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.os10.tcc.filmesja.Activitys.FavoritoFragment;
import com.os10.tcc.filmesja.Model.MDCatalogo;
import com.os10.tcc.filmesja.Model.MDFavorito;
import com.os10.tcc.filmesja.util.ConnectWeb;
import com.os10.tcc.filmesja.util.ConsLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Ricardo on 08/05/2017.
 */

public class ControlFavoritos extends ControlBase {
    private static final int CARREGAR_FAVORITOS = 1;

    private ConnectWeb connectWeb;
    private HashMap<String, String> ModelFavorito;

    private AppCompatActivity fragment;

    private int OperationType = 0;

    public void CarregarFavoritos(String IDConta, AppCompatActivity fragment){
        ModelFavorito = new HashMap<>();
        ModelFavorito.put("IDFAVORITO", IDConta);
        this.fragment = fragment;

        connectWeb = new ConnectWeb(ConsLink.PegarFavoritos, ModelFavorito, this, fragment);
        OperationType = CARREGAR_FAVORITOS;
        connectWeb.Start();
    }

    @Override
    public void FinishOperation() {
        if (OperationType == CARREGAR_FAVORITOS){
            JSONObject jsonObject = null;
            //System.out.println(getRetorno());
            try {
                jsonObject = new JSONObject(getRetorno());
                JSONArray jsonArray = jsonObject.getJSONArray("favoritos");

                FavoritoFragment.mdFavoritos = new MDFavorito[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    Log.d("TCC", object.getString("nome"));
                    //FavoritoFragment.ImagesFavoritos[i] = FavoritoFragment.imageBase;

                    String imagemCode = object.getString("imagem");
                    byte[] lista = Base64.decode(imagemCode, Base64.DEFAULT);
                   // Log.d("TCCC", lista.toString());
                    Bitmap imageBimmap = BitmapFactory.decodeByteArray(lista, 0, lista.length);

                    MDCatalogo c = new MDCatalogo();
                    c.setDefaultNome(object.getString("nome"));
                    c.setImage(imageBimmap);
                    c.setDefaultSinopse(object.getString("sinopse"));
                    //Quando o programador precisar do ID do favorito, só descomentar, mas ai precisa arrumar no WS kkkk

                    //Ele disse que não era forte, mas que podia ser bem mais
                    //Quando acabou a sua sorte, ele mostrou do que era capaz

                 //   FavoritoFragment.mdFavoritos[i].setIDFavorito();
                    MDFavorito favorito = new MDFavorito();
                    favorito.setMdCatalogo(c);
                    favorito.setIDFavorito(object.getString("idCat"));
                    FavoritoFragment.mdFavoritos[i] = favorito;

                }
                Log.d("TCCCC", "acabou control");
                FavoritoFragment.ConfigurarFavoritos();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
