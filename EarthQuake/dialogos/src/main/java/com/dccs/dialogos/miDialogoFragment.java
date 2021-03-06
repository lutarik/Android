package com.dccs.dialogos;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class miDialogoFragment extends DialogFragment {

    private DialogInterface.OnClickListener btn_positivo;
    private DialogInterface.OnClickListener btn_negativo;
    private String msj="Desea Continuar?";

    public miDialogoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder factoria = new AlertDialog.Builder(getActivity());

        factoria.setMessage(msj);
        factoria.setPositiveButton("Si", btn_positivo);
        factoria.setNegativeButton("No", btn_negativo);

        return factoria.create();
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
