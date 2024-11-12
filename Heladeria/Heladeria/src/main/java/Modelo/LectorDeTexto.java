/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author steve
 */
public class LectorDeTexto implements LectorDeArchivos {
    @Override
    public ArrayList<String> leer(String nombreArchivo) {
        ArrayList<String> informacion = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File("archivos/" + nombreArchivo)))) {
            String datos = br.readLine();
            while (datos != null) {
                informacion.add(datos);
                datos = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return informacion;
    }
}
