package com.dccs.earthquake.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dccs.earthquake.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertDialogFragment extends DialogFragment {

    private DialogInterface.OnClickListener btn_positivo;
    private DialogInterface.OnClickListener btn_negativo;
    private String msj="Desea Continuar?";

    public AlertDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder aviso = new AlertDialog.Builder(getActivity());

        aviso.setMessage(msj);
        aviso.setPositiveButton("Si", btn_positivo);
        aviso.setNegativeButton("No", btn_negativo);

        return aviso.create();
    }

    public boolean setOnClickListenerSi(DialogInterface.OnClickListener listener) {
        this.btn_positivo = listener;
        return false;
    }

    public boolean setOnClickListenerNo(DialogInterface.OnClickListener listener) {
        this.btn_negativo = listener;
        return false;
    }

    public boolean setPersonalMessage(String msj){
        this.msj=msj;
        return false;
    }
}
