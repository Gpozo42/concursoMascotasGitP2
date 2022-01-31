/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.MiembroJurado;
import ec.edu.espol.model.Util;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class VentanaMiembroJuradoFXMLController implements Initializable {

    private ArrayList<MiembroJurado> miembrosJurado;
    
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnHecho;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtPerfil;
    @FXML
    private BorderPane ventana;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        miembrosJurado = MiembroJurado.readFile("miembroJurados.txt");
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
        catch (NullPointerException ex) {
            Alert a = new Alert(AlertType.WARNING, "No se han ingresado todos los valores");
            a.show();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private void creacionObjeto() throws NullPointerException {
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String telefono = txtTelefono.getText();
        String mail = txtMail.getText();
        String perfil = txtPerfil.getText();
        
        if (nombres.equals("") || apellidos.equals("") || telefono.equals("") || telefono.equals("") || mail.equals("") || perfil.equals("")) {
            Alert a = new Alert(AlertType.WARNING, "Los campos se encuentran vacíos");
            a.show();
        }
        else {
            if (miembrosJurado.isEmpty() || verificacionMail(mail)) {
                MiembroJurado mj = new MiembroJurado(Util.nextID("miembroJurados.txt"), nombres, apellidos, telefono, mail, perfil, new ArrayList<Evaluacion>());
                mj.saveFile("miembroJurados.txt");
            }
        }
    }
    
    private boolean verificacionMail(String mail) {
        for (MiembroJurado mj : miembrosJurado) {
            if (Objects.equals(mail, mj.getEmail())) {
                Alert a = new Alert(AlertType.WARNING, "El mail ya existe en el sistema");
                a.show();
                txtMail.setText("");
                return false;
            }
        }
        return true;
    }
    
    private void limpiezaFields() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtMail.setText("");
        txtPerfil.setText("");
    }
    
}
