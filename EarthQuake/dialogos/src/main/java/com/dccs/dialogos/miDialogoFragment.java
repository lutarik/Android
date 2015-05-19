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

    AlertDialog.Builder factoria;

    public miDialogoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        factoria = new AlertDialog.Builder(getActivity());

        factoria.setMessage("Desea Continuar?");
/*        factoria.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        factoria.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Has pulsado en No", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return factoria.create();
    }

    public boolean setOnClickListenerSi(DialogInterface.OnClickListener listener) {
        factoria.setPositiveButton("Si", listener);
        return false;
    }

    public boolean setOnClickListenerNo(DialogInterface.OnClickListener listener) {
        factoria.setNegativeButton("No", listener);
        return false;
    }
}
