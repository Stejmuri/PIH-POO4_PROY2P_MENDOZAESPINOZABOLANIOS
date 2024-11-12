/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
//Guillermo
import static Modelo.Cliente.clientes;
import static Modelo.GestorDeBases.bases;
import static Modelo.Sabor.sabores;
import static Modelo.Topping.toppings;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tomas
 */
public class ManejoArchivos {
    public static final String rutaArchivos = "archivos/";
    public static String rutaImagenes;

    public static String capitalizar(String nombreBase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private LectorDeArchivos lector;
    private EscritorDeArchivos escritor;

    // Constructor que permite inyectar diferentes estrategias de lectura y escritura
    public ManejoArchivos(LectorDeArchivos lector, EscritorDeArchivos escritor) {
        this.lector = lector;
        this.escritor = escritor;
    }

    public ArrayList<String> leerArchivo(String nombreArchivo) {
        return lector.leer(nombreArchivo);
    }

    public void escribirArchivo(String nombreArchivo, boolean agregar, String contenido) {
        escritor.escribir(nombreArchivo, agregar, contenido);
    }
      
}
