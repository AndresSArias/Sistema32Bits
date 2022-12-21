/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import Vista.panelSap;

/**
 *
 * @author jhonm
 */
public class ALU {
    
    public String nuevoResgistro;
    
    public static String Multiplicacion(String binario1, String binario2) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal2 = Integer.parseInt(binario2, 2);
        int multiplicacion = decimal1 * decimal2;
        String binario_multiplicacion = Integer.toBinaryString(multiplicacion);
        return binario_multiplicacion;
    }
    
    public static String Potencia(String binario1) {
        int decimal1 = Integer.parseInt(binario1);
        int potencia = decimal1 * 2;
        String binario_potencia = Integer.toBinaryString(potencia);
        return binario_potencia;
    }

    public static String Absoluto(String binario1) {
        int decimal1 = Integer.parseInt(binario1);
        int absoluto = decimal1 > 0 ? decimal1 : -decimal1;
        String binario_absoluto = Integer.toBinaryString(absoluto);
        return binario_absoluto;
    }
    
    public static String Suma(String binario1, String binario2) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal2 = Integer.parseInt(binario2, 2);
        int suma = decimal1 + decimal2;
        String binario_suma = Integer.toBinaryString(suma);
        return binario_suma;
    }
    
    public static String Resta(String binario1, String binario2) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal2 = Integer.parseInt(binario2, 2);
        int resta = decimal1 - decimal2;
        String binario_resta = Integer.toBinaryString(resta);
        return binario_resta;
    }

    public static String Par(String binario1) {
        int decimal1 = Integer.parseInt(binario1);
        if (decimal1 % 2 == 0) {
        return "10";
        } else {
            return "0";
        }
    }

    public String getNuevoResgistro() {
        return nuevoResgistro;
    }
    
    public static String Divide(String binario1, String binario2) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal2 = Integer.parseInt(binario2, 2);
        int divide = decimal1 / decimal2;
        String binario_divide = Integer.toBinaryString(divide);
        return binario_divide;
    }
    
    public static String AND(String binario1, String binario2) {
        if (binario2.length()==binario1.length()){
            int decimal1 = Integer.parseInt(binario1, 2);
            int decimal2 = Integer.parseInt(binario2, 2);
            int and = decimal1 & decimal2;
            String binario_and = Integer.toBinaryString(and);
            return binario_and;
            }else{
                return "0";
            }
        }
    
    public static String XOR(String binario1, String binario2) {
        if (binario2.length()==binario1.length()){
            int decimal1 = Integer.parseInt(binario1, 2);
            int decimal2 = Integer.parseInt(binario2, 2);
            int and = decimal1 ^ decimal2;
            String binario_and = Integer.toBinaryString(and);
            return binario_and;
            }else{
                return "0";
            }
        }
    
    public static String OR(String binario1, String binario2) {
        if (binario2.length()==binario1.length()){
            int decimal1 = Integer.parseInt(binario1, 2);
            int decimal2 = Integer.parseInt(binario2, 2);
            int or = decimal1 | decimal2;
            String binario_or = Integer.toBinaryString(or);
            return binario_or;
            }else{
                return "0";
            }
        }
    
    public static String Impar(String binario1) {
        int decimal1 = Integer.parseInt(binario1);
        if (decimal1 % 2 == 0) {
        return "0";
        } else {
            return "1";
        }
    }
    
    public static String Mayor(String binario1, String binario2) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal2 = Integer.parseInt(binario2, 2);
        if(decimal1 > decimal2) {
            int mayor = 1;
            String binario_suma = Integer.toBinaryString(mayor);
            return binario_suma;
            }
            else{
            return "0";
        }
    }
    
    public static String Menor(String binario1, String binario2) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal2 = Integer.parseInt(binario2, 2);
        if(decimal2 > decimal1) {
            int mayor = 1;
            String binario_suma = Integer.toBinaryString(mayor);
            return binario_suma;
            }
            else{
            return "0";
        }
    }
    
    public static String MODULO(String binario1) {
        int decimal1 = Integer.parseInt(binario1, 2);
        int decimal = decimal1 % 10;
        String binario_modulo = Integer.toBinaryString(decimal);
        return binario_modulo;
    }
    
}
    

