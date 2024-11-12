/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;



public class GestorDeBases {
    public static ArrayList<Base> bases = new ArrayList<>();

    public static void agregarBase(Base base) {
        bases.add(base);
    }

    public static boolean eliminarBase(Base base) {
        return bases.remove(base);
    }

    public static Base buscarBasePorNombre(String nombre) {
        for (Base base : bases) {
            if (base.getNombreBase().equalsIgnoreCase(nombre)) {
                return base;
            }
        }
        return null;
    }

    public static ArrayList<Base> obtenerTodasLasBases() {
        return new ArrayList<>(bases); // Devolver una copia para evitar modificaciones externas
    }
}
