package com.dccs.calculadora;


import android.app.Application;

import com.dccs.calculadora.dao.OperacionesImp;

/**
 * Created by androidm on 11/05/2015.
 */
public class CalculadoraApp extends Application {

    private OperacionesImp calculadora;

    @Override
    public void onCreate() {
        super.onCreate();
        calculadora= new OperacionesImp();
    }

    public OperacionesImp getCalculadora() {
        return calculadora;
    }
}
