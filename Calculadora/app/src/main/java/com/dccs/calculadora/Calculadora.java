package com.dccs.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dccs.calculadora.dao.OperacionesDAOImpl;


public class Calculadora extends Activity implements View.OnClickListener {

    /*Declaramos las variables a del Activity*/
    private TextView t_result, t_display;
    private Button b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8, b_9, b_0;
    private Button b_mult, b_div, b_suma, b_resta, b_igual;
    private OperacionesDAOImpl calcula;

    /*Declaramos las variables donde guardaremos los operandos y el resultado*/
    //private int op1, op2, operacion;
    private int op1, operacion;


    //Variable de control para controlar si hemos pulsado un operador. Valores = 0 no pulsado / 1 pulsado
    private int op_pulsado;
    private int igual;//Controlamos si se ha pulsado o no. Valores = 0 no pulsado / 1 pulsado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        //Inicializamos componentes
        inicializaComponentes();

        //Asignamos el setOnClickListener
        b_0.setOnClickListener(this);
        b_1.setOnClickListener(this);
        b_2.setOnClickListener(this);
        b_3.setOnClickListener(this);
        b_4.setOnClickListener(this);
        b_5.setOnClickListener(this);
        b_6.setOnClickListener(this);
        b_7.setOnClickListener(this);
        b_8.setOnClickListener(this);
        b_9.setOnClickListener(this);

        b_mult.setOnClickListener(this);
        b_resta.setOnClickListener(this);
        b_suma.setOnClickListener(this);
        b_div.setOnClickListener(this);
        b_igual.setOnClickListener(this);


    }

    public void inicializaComponentes() {

        calcula = new OperacionesDAOImpl();
        t_result = (TextView) findViewById(R.id.txt_result);
        t_display = (TextView) findViewById(R.id.txt_display);

        b_0 = (Button) findViewById(R.id.btn_0);
        b_1 = (Button) findViewById(R.id.btn_1);
        b_2 = (Button) findViewById(R.id.btn_2);
        b_3 = (Button) findViewById(R.id.btn_3);
        b_4 = (Button) findViewById(R.id.btn_4);
        b_5 = (Button) findViewById(R.id.btn_5);
        b_6 = (Button) findViewById(R.id.btn_6);
        b_7 = (Button) findViewById(R.id.btn_7);
        b_8 = (Button) findViewById(R.id.btn_8);
        b_9 = (Button) findViewById(R.id.btn_9);

        b_mult = (Button) findViewById(R.id.btn_mult);
        b_div = (Button) findViewById(R.id.btn_divide);
        b_suma = (Button) findViewById(R.id.btn_resta);
        b_resta = (Button) findViewById(R.id.btn_sum);
        b_igual = (Button) findViewById(R.id.btn_igual);
    }


    @Override
    public void onClick(View boton) {
        String oper = ((Button) boton).getText().toString();
        //Si es una suma
        if (oper.equals("+")) {
            calcula.setA(op1);
            calcula.setOper(1);
            op_pulsado = 1;
            t_result.setText("");
        }
        //Si es una resta
        else if (oper.equals("-")) {
            calcula.setA(op1);
            calcula.setOper(2);
            op_pulsado = 1;
            t_result.setText("");
        }
        //Si es una multiplicacion
        else if (oper.equals("*")) {
            calcula.setA(op1);
            calcula.setOper(3);
            op_pulsado = 1;
            t_result.setText("");
        }
        //Si es una división
        else if (oper.equals("/")) {
            calcula.setA(op1);
            calcula.setOper(4);
            op_pulsado = 1;
            t_result.setText("");
        }
        //Si es el igual
        else if (oper.equals("=")) {
            calcula.setB(op1);
            //calcula.setB(op2);
            operacion=calcula.calcular();
            t_display.setText(String.valueOf(operacion));
            op_pulsado = 0;
            t_result.setText("");
        }
        //Si es un número
        else {
            if (op_pulsado == 0) {
                t_display.setText("");
                t_result.setText(t_result.getText() + ((Button) boton).getText().toString());
                op1 = Integer.parseInt(t_result.getText().toString());
            } else {
                t_result.setText(t_result.getText() + ((Button) boton).getText().toString());
                op1 = Integer.parseInt(t_result.getText().toString());
            }
        }

    }
}
