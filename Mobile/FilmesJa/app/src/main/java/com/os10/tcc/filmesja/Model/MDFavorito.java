package com.os10.tcc.filmesja.Model;

/**
 * Created by Ricardo on 27/05/2017.
 */

public class MDFavorito {
    private String IDFavorito;
    private MDCatalogo mdCatalogo;
    private int IDConta;
    private int Ativo;

    public String getIDFavorito() {
        return IDFavorito;
    }

    public void setIDFavorito(String IDFavorito) {
        this.IDFavorito = IDFavorito;
    }

    public MDCatalogo getMdCatalogo() {
        return mdCatalogo;
    }

    public void setMdCatalogo(MDCatalogo mdCatalogo) {
        this.mdCatalogo = mdCatalogo;
    }

    public int getIDConta() {
        return IDConta;
    }

    public void setIDConta(int IDConta) {
        this.IDConta = IDConta;
    }

    public int getAtivo() {
        return Ativo;
    }

    public void setAtivo(int ativo) {
        Ativo = ativo;
    }
}
