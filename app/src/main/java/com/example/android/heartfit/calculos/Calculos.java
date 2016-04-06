package com.example.android.heartfit.calculos;

import android.util.Log;

/**
 * Created by olmanqj on 04/04/16.
 */
public class Calculos {


    //Calculo del metabolismo basal
    //Recive el genero en String ("H" o "M"), peso en double en kg, altura en double en m y edad en int en años
    //Devuelve el metabolismo basal
    //TMB Mujer = 655 + (9,6 * P) + (1,8 * A) – (4,7 * E)
    //TMB Hombre = 66 + (13,7 * P) + (5 * A) – (6,8 * E)

    public double getBasal(String genero, double peso, double altura, int edad){


        if(genero == "hombre"){      //Si es hombre
            return (66 + (13.7 * peso) + (5 * altura) - (6.8 * edad));
        }
        else {
            return (655 + (9.6 * peso) + (1.8 * altura) - (4.7 * edad));
        }
    }




    //Calcular Indice de Masa Corporal IMC
    // peso, expresado en kilos, entre la estatura, en metros, elevada al cuadrado (kg/m2)
    public double getIMC(double peso, double altura){
        return (peso/Math.pow(altura/100,2));
    }



    //Referencia de IMC como ideal, bajo peso, sobrepeso, obesidad, o deficiencia nutricional (infrapeso).
    /*
    Clasificación	            IMC (kg/m2)
    deficiencia nutricional     <16,00
    bajo peso	                <18,50
    Normal	                    18.5 – 24,99
    Sobrepeso	                ≥25,00
    Obeso	                    ≥30,00
    */
    public String getReferenciaIMC(double IMC){

        if(IMC<16.00){
            return "Deficiencia nutricional";
        }
        else if(IMC<18.50 ){
            return "Bajo de peso";
        }
        else if(IMC<25.00){
            return "Normal";
        }
        else if(IMC<30){
            return "Sobrepeso";
        }
        else{
            return "Obeso";
        }
    }


    //Cantidad de calorías diarias a consumir solamente para mantener el peso
    // basal x 1,2
    public double getCalDia(String nivel_actividad,double basal){
        String[] lista_niveles_acividad = {"Sedentario", "Actividad Ligera", "Actividad Moderada", "Actividad Intensa", "Actividad Muy Intensa"};
        if(nivel_actividad.equals(lista_niveles_acividad[0])){
            return basal * 1.2;
        }
        if(nivel_actividad.equals(lista_niveles_acividad[1])){
            return basal * 1.375;
        }
        if(nivel_actividad.equals(lista_niveles_acividad[2])){
            return basal * 1.55;
        }
        if(nivel_actividad.equals(lista_niveles_acividad[3])){
            return basal * 1.725;
        }
        if(nivel_actividad.equals(lista_niveles_acividad[4])){
            return basal * 1.9;
        }
        Log.d("Calculos",lista_niveles_acividad[0]+":"+nivel_actividad);
        return 0;
    }

}
