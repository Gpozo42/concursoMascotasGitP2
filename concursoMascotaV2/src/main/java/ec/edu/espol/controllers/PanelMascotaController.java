/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Mascota;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class PanelMascotaController implements Initializable {

    private ArrayList<Dueño> duenios;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfRaza;
    @FXML
    private TextField tfFecha;
    @FXML
    private TextField tfTipo;
    @FXML
    private TextField tfCorreo;
    @FXML
    private GridPane ventana;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        duenios = Dueño.readFile("dueños.txt");
    }

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = tfNombre.getText();
        String raza = tfRaza.getText();
        String tipo = tfTipo.getText();
        String fechaNacimiento = tfFecha.getText();
        String correo = tfCorreo.getText();
        
        try {
            if (nombre.equals("") || raza.equals("") || tipo.equals("") || fechaNacimiento.equals("") || correo.equals("")){
                Alert a = new Alert(Alert.AlertType.ERROR, "Exiten campos vacios. Por favor, rellenarlos");
                a.show();
            }else{
                Dueño d= anexarEmail(correo, duenios);
                Mascota.nextMascota(nombre, tipo, raza, fechaNacimiento, d);
                Alert inf = new Alert(Alert.AlertType.INFORMATION, "Se ha guardado el concurso exitosamente");
                inf.show();
                
            }
            
        }catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Se ha ingresado letras por numeros");
            a.show();
        } catch (DateTimeParseException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "El formato escrito no es igual a (AAAA-MM-DD) o ha escrito \nmeses o dias fuera del rango ");
            a.show();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error en la lectura");
            a.show();
        }
        catch (NullPointerException e) {
            Alert a = new Alert(Alert.AlertType.WARNING, "El email ya existe en el sistema");
            a.show();
        }
        
        tfNombre.setText("");
        tfRaza.setText("");
        tfTipo.setText("");
        tfFecha.setText("");
        tfCorreo.setText("");

    }

    @FXML
    private void regresar(ActionEvent event) {
        Stage stg = (Stage) ventana.getScene().getWindow();
        stg.close();
    }
    
    public static Dueño anexarEmail(String correo, ArrayList<Dueño> duenios) throws NullPointerException {
        for (Dueño d : duenios) {
            if (Objects.equals(correo, d.getEmail())) return d;
        }
        throw new NullPointerException();
    }
}
