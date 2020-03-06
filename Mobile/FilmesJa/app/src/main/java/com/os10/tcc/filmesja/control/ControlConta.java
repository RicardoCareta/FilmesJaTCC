package com.os10.tcc.filmesja.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.os10.tcc.filmesja.Activitys.ContasActivity;
import com.os10.tcc.filmesja.Activitys.ExibeFilmeActivity;
import com.os10.tcc.filmesja.util.ConnectWeb;
import com.os10.tcc.filmesja.util.ConsLink;

import java.util.HashMap;

/**
 * Created by Ricardo on 28/06/2017.
 */

public class ControlConta extends ControlBase {

    private static final int SALVAR_CONTA = 1;
    private static final int INSERT_CONTA = 2;
    private static final int EXCLUIR_CONTA = 3;
    private static final int PEGAR_NOTA = 4;
    private static final int SALVAR_NOTA = 5;
    private static final int ATUALIZAR_FAVORITO = 6;
    private static final int APAGAR_FAVORITO = 7;
    private static final int HAVE_FAVORITO = 8;

    private int OperationType = 0;

    private ConnectWeb con;
    private HashMap<String, String> ModelConta;

    private AppCompatActivity activity;
    private Intent intentContas;

    public void SalvarConta(String Nome, String Idioma, String DataNascimento, AppCompatActivity activity){
        ModelConta = new HashMap<>();
        ModelConta.put("NOME", Nome);
        ModelConta.put("IDIOMA", Idioma);
        ModelConta.put("DTNASC", DataNascimento);

        this.activity = activity;

        con = new ConnectWeb(ConsLink.SalvarContas, ModelConta, this, activity);
        OperationType = SALVAR_CONTA;
        con.Start();
    }

    public void ExcluirConta(String IDConta, AppCompatActivity activity){
        ModelConta = new HashMap<>();
        ModelConta.put("IDCONTA", IDConta);

        this.activity = activity;
        con = new ConnectWeb(ConsLink.ExcluirConta, ModelConta, this, activity);
        OperationType = EXCLUIR_CONTA;
        con.Start();
    }

    public void InserirConta(String Nome, String foto, String DataNascimento, String Idioma, int Usuario, AppCompatActivity activity){
        ModelConta = new HashMap<>();
        ModelConta.put("NOME", Nome);
        ModelConta.put("FOTO", foto);
        ModelConta.put("DTNASCIMENTO", DataNascimento);
        ModelConta.put("IDIDIOMA", Idioma);
        ModelConta.put("IDUSUARIO", String.valueOf(Usuario));

        this.activity = activity;
        //this.intentContas = intentContas;

        con = new ConnectWeb(ConsLink.InsertConta, ModelConta, this, activity);
        OperationType = INSERT_CONTA;
        con.Start();
    }

    public void PegarNota(String IDConta, String IDCatalogo, AppCompatActivity activity){
        ModelConta = new HashMap<>();

        this.activity = activity;
        String sql = "?sql=select avaliacao from tblHistorico where avaliacao != 0 and idcat = " + IDCatalogo + " and idconta = " + IDConta;
        con =  new ConnectWeb(ConsLink.GeralFunction + sql + "&tp=1", ModelConta, this, activity);
        OperationType = PEGAR_NOTA;
        con.Start();
    }

    public void SalvarNota(String IDConta, String IDCatalogo, String Nota, AppCompatActivity activity){
        ModelConta = new HashMap<>();
        this.activity = activity;
        String sql = "?sql=update tblHistorico set avaliacao = " + Nota  + " where idcat = " + IDCatalogo + " and idconta = " + IDConta;
        con = new ConnectWeb(ConsLink.GeralFunction + sql + "&tp=2", ModelConta, this, activity);
        OperationType = SALVAR_NOTA;
        con.Start();
    }

    public void RemoveFavorito(String IDConta, String IDCatalogo, AppCompatActivity activity){
        ModelConta = new HashMap<>();

        this.activity = activity;
        String sql = "?sql=update tblFavorito set ativo = 0 where idcat = " + IDCatalogo + " and idconta = " + IDConta;
        con =  new ConnectWeb(ConsLink.GeralFunction + sql + "&tp=2", ModelConta, this, activity);
        OperationType = APAGAR_FAVORITO;
        con.Start();
    }


    public void AddFavorito(String IDConta, String IDCatalogo, AppCompatActivity activity){
        ModelConta = new HashMap<>();

        this.activity = activity;
        String sql = "?sql=insert into tblFavorito(IDCAT, IDCONTA, ATIVO) values(" + IDCatalogo + "," + IDConta + ", 1)";
        con =  new ConnectWeb(ConsLink.GeralFunction + sql + "&tp=2", ModelConta, this, activity);
        OperationType = ATUALIZAR_FAVORITO;
        con.Start();
    }

    public void HaveFavorito(String IDConta, String IDCat, AppCompatActivity activity){
        ModelConta = new HashMap<>();

        this.activity = activity;
        String sql = "?sql=select case when (select count(*) from tblFavorito where ativo = 1 and idCat = " + IDCat +" and idconta = " + IDConta + ") != 0 then '1' else '0' end as HaveFavorito from dual";
        con =  new ConnectWeb(ConsLink.GeralFunction + sql + "&tp=1", ModelConta, this, activity);
        OperationType = HAVE_FAVORITO;
        con.Start();
    }


    @Override
    public void FinishOperation() {
        if(OperationType == SALVAR_CONTA){

        }

        if (OperationType == INSERT_CONTA){
            if (getRetorno().equals("1")) {
                activity.finish();
                Intent intent = new Intent(activity.getBaseContext(), ContasActivity.class);
                activity.startActivity(intent);
                Toast.makeText(activity.getBaseContext(), "Conta criada com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(activity.getBaseContext(), "Falha ao criar conta", Toast.LENGTH_LONG).show();
                Log.d("TCC_ERRO", getRetorno());
            }
        }

        if(OperationType == EXCLUIR_CONTA){
            if(getRetorno().equals("1")){
                Toast.makeText(activity.getBaseContext(), "Conta excluída com sucesso!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity.getBaseContext(), ContasActivity.class);
                activity.startActivity(intent);

            }else{
                Toast.makeText(activity.getBaseContext(), "Falha ao tentar excluir uma conta", Toast.LENGTH_LONG).show();
                Log.d("TCC_ERRO", getRetorno());
            }
        }

        if(OperationType == PEGAR_NOTA){

            ExibeFilmeActivity.PegarNota(getRetorno());

        }

        if(OperationType == SALVAR_NOTA){
            if(getRetorno().indexOf("1") != -1){
                ExibeFilmeActivity.Atualizar();
            }
        }

        if(OperationType == APAGAR_FAVORITO){
            ExibeFilmeActivity.ChangeFavorito(1);
        }

        if(OperationType == ATUALIZAR_FAVORITO){
            ExibeFilmeActivity.ChangeFavorito(0);
        }

        if(OperationType == HAVE_FAVORITO){
            if(getRetorno().indexOf("1") != -1){
                ExibeFilmeActivity.ChangeFavorito(0);
            }
        }
    }
}
