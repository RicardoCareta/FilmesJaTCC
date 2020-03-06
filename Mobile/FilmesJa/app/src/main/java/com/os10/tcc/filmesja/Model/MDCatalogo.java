package com.os10.tcc.filmesja.Model;

import android.graphics.Bitmap;

/**
 * Created by Ricardo on 26/05/2017.
 */

public class MDCatalogo {
    private int IDCat;
    private Bitmap Image;
    private int Classificacao;
    private String Caminho;
    private int Episodio;
    private int Ano;
    private String DefaultNome;
    private String DefaultSinopse;
    private int Temporada;

    public int getIDCat() {
        return IDCat;
    }

    public void setIDCat(int IDCat) {
        this.IDCat = IDCat;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public int getClassificacao() {
        return Classificacao;
    }

    public void setClassificacao(int classificacao) {
        Classificacao = classificacao;
    }

    public String getCaminho() {
        return Caminho;
    }

    public void setCaminho(String caminho) {
        Caminho = caminho;
    }

    public int getEpisodio() {
        return Episodio;
    }

    public void setEpisodio(int episodio) {
        Episodio = episodio;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int ano) {
        Ano = ano;
    }

    public String getDefaultNome() {
        return DefaultNome;
    }

    public void setDefaultNome(String defaultNome) {
        DefaultNome = defaultNome;
    }

    public String getDefaultSinopse() {
        return DefaultSinopse;
    }

    public void setDefaultSinopse(String defaultSinopse) {
        DefaultSinopse = defaultSinopse;
    }

    public int getTemporada() {
        return Temporada;
    }

    public void setTemporada(int temporada) {
        Temporada = temporada;
    }
}
