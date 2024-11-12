/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
//Guillermo
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import proyectopoo.heladeria.App;
import proyectopoo.heladeria.PagoController;
import proyectopoo.heladeria.VentanaBasesController;
import proyectopoo.heladeria.VentanaInicioController;
import proyectopoo.heladeria.VentanaToppingsController;

/**
 *
 * @author tomas
 */
public class Pedido implements Serializable, Pagable {
    private Base base1;
    private ArrayList<Sabor> listasabores;
    private ArrayList<Topping> listatopping;
    private int numPago = 9999;
    // Dependencias inyectadas a través de interfaces
    private ClienteInfo clienteInfo;
    private ControladorDePago controladorDePago;
    private NumeroDePedido numeroDePedido;
    
    public Pedido(Base base1, ArrayList<Sabor> listasabores, ArrayList<Topping> listatopping,
                  ClienteInfo clienteInfo, ControladorDePago controladorDePago, NumeroDePedido numeroDePedido) {
        this.base1 = base1;
        this.listasabores = listasabores;
        this.listatopping = listatopping;
        this.clienteInfo = clienteInfo;
        this.controladorDePago = controladorDePago;
        this.numeroDePedido = numeroDePedido;
    }

    @Override
        public void generarTransaccion() {
        Date fecha = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        double totalPago = controladorDePago.obtenerTotalPago();

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(ManejoArchivos.rutaArchivos + "pagos.txt", true))) {
            String line = numPago + "," + numeroDePedido.obtenerNumeroPedido() + "," +
                          clienteInfo.getUsuario() + "," + totalPago + "," +
                          sd.format(fecha) + "," + String.valueOf(controladorDePago.obtenerTipoPago());
            bf.write(line + "\n");
            numPago--;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    /**
     * 
     * @return Base del helado escogida por el cliente
     */
    public Base getBase1() {
        return base1;
    }
    /**
     * 
     * @param base1 Cambia la base por base1 del helado de ser necesario 
     */
    public void setBase1(Base base1) {
        this.base1 = base1;
    }
    /**
     * 
     * @return Lista de los sabores escogidos por el cliente 
     */
    public ArrayList<Sabor> getListasabores() {
        return listasabores;
    }
    /**
     * 
     * @param listasabores Cambia la lista de sabores del cliente por la listasabores de ser necesario
     */
    public void setListasabores(ArrayList<Sabor> listasabores) {
        this.listasabores = listasabores;
    }
    /**
     * 
     * @return Lista de los toppings escogidos por el cliente 
     */
    public ArrayList<Topping> getListatopping() {
        return listatopping;
    }
    /**
     * 
     * @param listatopping Cambia la lista de toppings del cliente por la listatopping de ser necesario
     */
    public void setListatopping(ArrayList<Topping> listatopping) {
        this.listatopping = listatopping;
    }
    /**
     * 
     * @return Codigo del pago generado
     */
    public int getNumPago() {
        return numPago;
    }
    /**
     * 
     * @param objeto Objeto a serializar
     * @param nombreArchivo Nombre que se le pondrá al objeto serializado
     */
    public static void serializarPedido(Pedido objeto, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ManejoArchivos.rutaArchivos + nombreArchivo))) {
            oos.writeObject(objeto);
            System.out.println("¡El objeto se serializó con éxito!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }// se cierra el metodo

}
