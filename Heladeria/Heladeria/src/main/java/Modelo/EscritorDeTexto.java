/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.io.*;

/**
 *
 * @author steve
 */
public class EscritorDeTexto implements EscritorDeArchivos{
    @Override
    public void escribir(String nombreArchivo, boolean agregar, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("archivos/" + nombreArchivo, agregar))) {
            bw.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

