package com.dccs.calculadora.dao;


/**
 * Created by androidm on 07/05/2015.
 */
public class OperacionesDAOImpl implements IOperaciones {
    private int oper;
    private int a,b;
//BigDecimal usado para operaciones matemáticas

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

      public void setOper(int oper) {
        this.oper = oper;
    }

    @Override
    public int suma(int a, int b) {
        int result = a + b;
        return result;
    }

    @Override
    public int resta(int a, int b) {
        int result = a - b;
        return result;
    }

    @Override
    public int multiplicacion(int a, int b) {
        int result = a * b;
        return result;
    }

    @Override
    public int division(int a, int b) {
        if (b>0){
            int result = a / b;
            return result;
        }
        else{
            return 0;
        }
    }

    @Override
    public int calcular() {
        int result;

        switch (oper){
            case 1:
                result=suma(a,b);
                return result;
            case 2:
                result=resta(a, b);
                return result;
            case 3:
                result=multiplicacion(a, b);
                return result;
            case 4:
                result=division(a, b);
                return result;
            default:
                return 0;
        }

    }

}
