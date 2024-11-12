/*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
    */
package proyectopoo.heladeria;

import Modelo.Base;
import Modelo.Cliente;
import Modelo.IncompleteStageException;
import Modelo.ManejoArchivos;
import Modelo.ProcesadorDeDatos;
import Modelo.Sabor;
import Modelo.Topping;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author tomas
 */
public class VentanaInicioController implements Initializable {
    
    Boolean acceso=false;
    /**
     * Usuario del cliente
     */
    public static String usuarioVentana1 ;
    private ArrayList<Cliente> clientesV1;
    /**
     * Lista de bases
     */
    public ArrayList<Base> bases;
    /**
     * Lista sabores
     */
    public ArrayList<Sabor> sabores;
    /**
     * Lista toppings
     */
    public ArrayList<Topping> toppings;
    /**
     * Objeto Cliente duenio del pedido actual
     */
    public static Cliente clienteActual;
    @FXML
    private StackPane nodoStackPane;
    @FXML
    private ImageView nodoImageView;
    @FXML
    private HBox nodoHbox;
    @FXML
    private VBox nodoVbox;
    @FXML
    private Label nombreHeladeria;
    @FXML
    private Label iceCream;
    @FXML
    private TextField usuarioTextField;
    @FXML
    private TextField contraseñaTextField;
    @FXML
    private Button iniciarSesionBoton;
        

   /**
    * Inicializa el controlador de la clase
    * @param url Localizacion del FXML
    * @param rb Recursos utilizados en el controlador
    */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         nombreHeladeria.setStyle("-fx-font-size: 30px; -fx-text-fill: black; -fx-font-family: 'Georgia';");
        iceCream.setStyle("-fx-font-size: 22px; -fx-text-fill: black; -fx-font-family: 'Georgia';");
        try(FileInputStream fis = new FileInputStream (ManejoArchivos.rutaImagenes+"heladosVentanaInicio.png") ) {
            Image i = new Image(fis);
            nodoImageView.setImage(i);
            
            // esta tarea se ejecuta al momento de iniciar la aplicacion, para que el programa
            //sea sostenible y no lea el archivo de clientes apenas el usuario quiera iniciar sesion,
            //sino que ya este cargado, pues es un proceso que puede demorar
            
            Thread cargaArchivoThread = new Thread(() -> {
        try {
            clientesV1 = ProcesadorDeDatos.listaClientes();
            System.out.println("clientes cargados");
            bases = ProcesadorDeDatos.listaBases();
            System.out.println("bases cargadas");
            sabores = ProcesadorDeDatos.listaSabores();
            System.out.println("sabores cargados");
            toppings = ProcesadorDeDatos.listaToppings();
            System.out.println("toppings cargados");
        } catch (Exception e) {
            System.out.println("Proceso fallido");
        }
    });

    cargaArchivoThread.setDaemon(true); // Si deseas que el hilo se detenga cuando se cierre la aplicación
    cargaArchivoThread.start();

        }

        catch(Exception e){
            System.out.println("Proceso fallido");
           }
        

       }    
    
    /**
     * Metodo que valida el usuario para conceder acceso a la siguiente ventana
     * @param event Evento al accionar boton
     * @throws IOException Excepcion a manejar
     */
    @FXML
    private void validarUsuario(ActionEvent event) throws IOException {
    String us = usuarioTextField.getText();
    String con = contraseñaTextField.getText();
    clienteActual=new Cliente(us,con);
         if (us.isEmpty() || con.isEmpty()) {
            try {
                throw new IncompleteStageException("Por favor, complete todos los campos.");
            } catch (IncompleteStageException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Campos vacios");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
            return; // Sale del método si los campos están vacíos
        }     

        else if(clientesV1 == null) {
        // El archivo aún se está cargando en segundo plano
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de carga");
            alert.setHeaderText(null);
            alert.setContentText("El archivo aún se está cargando en segundo plano. Por favor, espere.");
            alert.showAndWait();
            return;
        }
                 
                 
   // la validacion no demora mucho incluso si el cliente esta en las ultimas lineas del txt
   // Charlie,10000000 es el ultimo                 
    for (Cliente cs : clientesV1) {
        if (cs.equals(new Cliente(us, con))) {
            usuarioVentana1=us;
            acceso = true;           
            break;
        }    
    }

    if (acceso) {   
        // actualiza el nombredeusuario de la ventanaBienvenida, para imprimir en el label
        // bienvenida nombre usuario
        VentanaBienvenidaController.usuarioVentana2 = usuarioVentana1 ;
        
        try {
            App.setRoot("VentanaBienvenida");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaPedidosActualizados.fxml"));
            Parent root = loader.load();
            VentanaPedidosActualizadosController controller = loader.getController();
            Stage stage = new Stage();
            
        
            stage.setScene(new Scene(root));
            stage.setTitle("Pedidos Recientes");
            stage.show();

            // Llamar al método shutdown en VentanaPedidosActualizadosController cuando la ventana se cierre
            stage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    controller.shutdown();
                }
            });
 
        } 
        catch (IOException ioe) {
            System.out.println("Algo salió mal al cambiar de ventana");
        }    
                     
    } else {
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error de autenticación");
        alert.setHeaderText(null);
        alert.setContentText("El usuario o contraseña ingresado son incorrectos, vuelva a intentarlo.");
        alert.showAndWait();
    }
}
    
 
        
    
    

}