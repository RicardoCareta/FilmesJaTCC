package com.os10.tcc.filmesja.control;

/**
 * Created by Ricardo on 09/03/2017.
 */

public class ControlBase {

    private static String retorno;
    private boolean finish;

    public static String getRetorno(){return retorno;}

    public void setRetorno(String retorno){
        this.retorno = retorno;
    }

    public void FinishOperation(){

    }
}
