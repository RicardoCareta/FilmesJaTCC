package com.os10.tcc.filmesja.util;

import android.app.Activity;
import android.app.admin.SystemUpdatePolicy;
import android.support.v7.app.AppCompatActivity;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import com.os10.tcc.filmesja.control.ControlBase;

import java.util.HashMap;

public class ConnectWeb implements AsyncResponse {

    private String URLConnect = "";
    private HashMap<String, String> hashMap;
    private PostResponseAsyncTask data;
    private ControlBase controlBase;
    private AppCompatActivity activity;

    public ConnectWeb(String URLConnect, HashMap<String, String> hashMap, ControlBase controlBase, AppCompatActivity activity){
        this.URLConnect = URLConnect;
        this.hashMap = hashMap;
        this.controlBase = controlBase;
        //this.controlBase.setFinish(false);
        this.activity = activity;
    }

    public void Start(){
        data = new PostResponseAsyncTask(activity, hashMap, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                controlBase.setRetorno(s);
                controlBase.FinishOperation();
            }
        });
        data.execute(URLConnect);
    }

    @Override
    public void processFinish(String s) {
    }
}
