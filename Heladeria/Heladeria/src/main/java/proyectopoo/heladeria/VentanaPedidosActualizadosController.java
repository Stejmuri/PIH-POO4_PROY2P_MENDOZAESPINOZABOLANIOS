/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.EscritorDeArchivos;
import Modelo.LectorDeTexto;
import Modelo.ManejoArchivos;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author tomas
 */
public class VentanaPedidosActualizadosController implements Initializable {

    @FXML
    private HBox nodoHbox3;
    @FXML
    private ListView<String> nodoListView;

    private volatile boolean running = true;
    
    /**
     * Inicializa el controlador de la clase
     * @param url Localizacion del FXML
     * @param rb Recursos utilizados en el controlador
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LectorDeTexto lectorDeTexto = new LectorDeTexto();
        EscritorDeArchivos escritorDeTexto = null; // No se necesita para esta clase
        ManejoArchivos manejoArchivos = new ManejoArchivos(lectorDeTexto, escritorDeTexto);
        
        Task<Void> actualizarTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (running) {
                    ArrayList<String> pedidos = manejoArchivos.leerArchivo("pedido.txt");
                    Collections.reverse( pedidos );
                    ObservableList<String> items = FXCollections.observableArrayList(pedidos);
                    
                    // Actualizar el ListView en el hilo de la interfaz de usuario
                    javafx.application.Platform.runLater(() -> nodoListView.setItems(items));
                    
                    Thread.sleep(5000); // Esperar 5 segundos
                }
                return null;
            }
        };

        // Crear un nuevo hilo para la tarea de actualización
        Thread actualizarThread = new Thread(actualizarTask);
        actualizarThread.setDaemon(true); 
        actualizarThread.start();
    }

    
    /**
     * Cierra el ObservableView
     */
    public void shutdown() {
        running = false;
    }
    


    
}