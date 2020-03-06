package com.os10.tcc.filmesja.control;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.os10.tcc.filmesja.Activitys.FavoritoFragment;
import com.os10.tcc.filmesja.Activitys.InicioFragment;
import com.os10.tcc.filmesja.Model.MDCatalogo;
import com.os10.tcc.filmesja.util.ConnectWeb;
import com.os10.tcc.filmesja.util.ConsLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Ricardo on 19/05/2017.
 */

public class ControlCatalogo extends ControlBase {

    private static final int CARREGAR_TOP_MIDIA = 1;
    private static final int PESQUISAR_MIDIA = 2;

    private int TipoOperacao = 0;

    private ConnectWeb connectWeb;
    private HashMap<String, String> modelCatalogo;

    private AppCompatActivity appCompatActivity;

    public void CarregarTopMidia(AppCompatActivity activity) {
        this.appCompatActivity = activity;
        modelCatalogo = new HashMap<>();

        connectWeb = new ConnectWeb(ConsLink.PegarTopMidia, modelCatalogo, this, appCompatActivity);
        TipoOperacao = CARREGAR_TOP_MIDIA;
        connectWeb.Start();
    }

    public void PesquisarMidia(AppCompatActivity activity, String nome){

        if(nome.equals("")){
            CarregarTopMidia(activity);
            return;
        }

        this.appCompatActivity = activity;
        modelCatalogo = new HashMap<>();


        String sql = "select * from tblCatalogo where upper(nome) like '%"+nome.toUpperCase()+"%'";

        connectWeb = new ConnectWeb(ConsLink.GeralFunction + "?sql=" + sql + "&tp=1", modelCatalogo, this, appCompatActivity);
        TipoOperacao = PESQUISAR_MIDIA;
        connectWeb.Start();
    }

    @Override
    public void FinishOperation() {
        if(TipoOperacao == CARREGAR_TOP_MIDIA){
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getRetorno());
                JSONArray jsonArray = jsonObject.getJSONArray("catalogo");

                InicioFragment.IDCategoria = new String[jsonArray.length()];
                InicioFragment.IDMidia = new String[jsonArray.length()];
                InicioFragment.ImagemMidia = new Bitmap[jsonArray.length()];

                InicioFragment.mdCatalogos = new MDCatalogo[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String imageCode = object.getString("imagem");
                    byte[] lista = Base64.decode(imageCode, Base64.DEFAULT);
                    Bitmap imageBimmap = BitmapFactory.decodeByteArray(lista, 0, lista.length);

                    InicioFragment.IDMidia[i] = object.getString("IDCat");
                    InicioFragment.IDCategoria[i] = object.getString("IDCategoria");
                    InicioFragment.ImagemMidia[i] = imageBimmap;

                    InicioFragment.mdCatalogos[i] = new MDCatalogo();
                    InicioFragment.mdCatalogos[i].setDefaultSinopse(object.getString("sinopse"));
                    InicioFragment.mdCatalogos[i].setDefaultNome(object.getString("nome"));
                    InicioFragment.mdCatalogos[i].setAno(Integer.parseInt(object.getString("ano")));
                    InicioFragment.mdCatalogos[i].setImage(imageBimmap);
            }
            InicioFragment.ConfigurarTopMidia();

            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }

        else if(TipoOperacao == PESQUISAR_MIDIA){
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getRetorno());
                JSONArray jsonArray = jsonObject.getJSONArray("RESULTADO");

                Log.d(String.valueOf(jsonArray.length()), "FilmesJa");


                InicioFragment.IDCategoria = new String[jsonArray.length()];
                InicioFragment.IDMidia = new String[jsonArray.length()];
                InicioFragment.ImagemMidia = new Bitmap[jsonArray.length()];

                InicioFragment.mdCatalogos = new MDCatalogo[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String imageCode = object.getString("IMAGEM");
                    byte[] lista = Base64.decode(imageCode, Base64.DEFAULT);
                    Bitmap imageBimmap = BitmapFactory.decodeByteArray(lista, 0, lista.length);

                    InicioFragment.IDMidia[i] = object.getString("IDCAT");
                    InicioFragment.IDCategoria[i] = object.getString("IDCATEGORIA");
                    InicioFragment.ImagemMidia[i] = imageBimmap;

                    Log.d(InicioFragment.IDMidia[i].toString(), "FilmesJa");

                    InicioFragment.mdCatalogos[i] = new MDCatalogo();
                    InicioFragment.mdCatalogos[i].setDefaultSinopse(object.getString("SINOPSE"));
                    InicioFragment.mdCatalogos[i].setDefaultNome(object.getString("NOME"));
                    InicioFragment.mdCatalogos[i].setAno(Integer.parseInt(object.getString("ANO")));
                    InicioFragment.mdCatalogos[i].setImage(imageBimmap);
                }
                InicioFragment.ConfigurarTopMidia();

            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
    }
}


