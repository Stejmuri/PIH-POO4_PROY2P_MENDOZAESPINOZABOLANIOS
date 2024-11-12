/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author steve
 */
public class ProcesadorDeDatos {
    
    public static ArrayList<Cliente> listaClientes() {
        ManejoArchivos manejoArchivos = new ManejoArchivos(new LectorDeTexto(), new EscritorDeTexto());
        ArrayList<String> clientestxt = manejoArchivos.leerArchivo("clientes.txt");
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (String cliente : clientestxt) {
            String[] c = cliente.split(",");
            clientes.add(new Cliente(c[0], c[1]));
        }
        return clientes;
    }

    /**
     * Método que crea una lista de bases a partir de los datos leídos de un archivo.
     * 
     * @return Una lista de objetos `Base`.
     */
    public static ArrayList<Base> listaBases() {
        ManejoArchivos manejoArchivos = new ManejoArchivos(new LectorDeTexto(), new EscritorDeTexto());
        ArrayList<String> basestxt = manejoArchivos.leerArchivo("bases.txt");
        ArrayList<Base> bases = new ArrayList<>();
        for (String base : basestxt) {
            String[] c = base.split(",");
            bases.add(new Base(c[0], Double.parseDouble(c[1])));
        }
        return bases;
    }

    /**
     * Método que crea una lista de sabores a partir de los datos leídos de un archivo.
     * 
     * @return Una lista de objetos `Sabor`.
     */
    public static ArrayList<Sabor> listaSabores() {
        ManejoArchivos manejoArchivos = new ManejoArchivos(new LectorDeTexto(), new EscritorDeTexto());
        ArrayList<String> saborestxt = manejoArchivos.leerArchivo("sabores.txt");
        ArrayList<Sabor> sabores = new ArrayList<>();
        for (String sabor : saborestxt) {
            String[] c = sabor.split(",");
            sabores.add(new Sabor(c[0], Double.parseDouble(c[1])));
        }
        return sabores;
    }

    /**
     * Método que crea una lista de toppings a partir de los datos leídos de un archivo.
     * 
     * @return Una lista de objetos `Topping`.
     */
    public static ArrayList<Topping> listaToppings() {
        ManejoArchivos manejoArchivos = new ManejoArchivos(new LectorDeTexto(), new EscritorDeTexto());
        ArrayList<String> toppingstxt = manejoArchivos.leerArchivo("toppings.txt");
        ArrayList<Topping> toppings = new ArrayList<>();
        for (String topping : toppingstxt) {
            String[] c = topping.split(",");
            toppings.add(new Topping(c[0], Double.parseDouble(c[1])));
        }
        return toppings;
    }
}
