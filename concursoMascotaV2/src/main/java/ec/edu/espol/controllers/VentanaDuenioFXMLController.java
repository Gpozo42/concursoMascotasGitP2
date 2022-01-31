/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Dueño;
import ec.edu.espol.model.Util;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class VentanaDuenioFXMLController implements Initializable {

    private ArrayList<Dueño> duenios;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtDireccion;
    @FXML
    private BorderPane ventana;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        duenios = Dueño.readFile("dueños.txt");
    }    

    @FXML
    private void regresar(MouseEvent event) {
        Stage stg = (Stage) ventana.getScene().getWindow();
        stg.close();
    }

    @FXML
    private void hecho(MouseEvent event) {
        try {
            creacionObjeto();
            limpiezaFields();
        }
        catch (Exception e) {
            Alert a = new Alert(AlertType.WARNING, e.getMessage());
            a.show();
        }
    }
    
    private void creacionObjeto() {
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String telefono = txtTelefono.getText();
        String email = txtMail.getText();
        String direccion = txtDireccion.getText();
        
        if (nombres.equals("") || apellidos.equals("") || telefono.equals("") || email.equals("") || direccion.equals("")) {
            Alert a = new Alert(AlertType.WARNING, "Se encuentran campos vacíos");
            a.show();
        }
        else {
            if (duenios.isEmpty() || verificacionMail(email)) {
                Dueño d = new Dueño(Util.nextID("dueños.txt"), nombres, apellidos, telefono, email, direccion);
                d.saveFile("dueños.txt");
            }
        }
    }
    
    private boolean verificacionMail(String mail) {
        for (Dueño d : duenios) {
            if (Objects.equals(mail, d.getEmail())) {
                Alert a = new Alert(AlertType.WARNING, "El mail ingresado ya existe");
                a.show();
                txtMail.setText("");
                return false;
            }
        }
        return true;
    }
    
    public void limpiezaFields() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtMail.setText("");
        txtDireccion.setText("");
    }
}
