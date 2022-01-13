/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Util;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class VentanaEvaluacionesController implements Initializable {
    
    private ArrayList<Evaluacion> evaluaciones;

    @FXML
    private Button btnHecho;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtEmailJurado;
    @FXML
    private TextField txtIdInscripcion;
    @FXML
    private TextField txtIdCriterio;
    @FXML
    private TextField txtNotaEvaluacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.evaluaciones = new ArrayList<>();
    }    

    @FXML
    private void hecho(MouseEvent event) {
        try {
            int idInscripcion = Integer.parseInt(txtIdInscripcion.getText());
            Evaluacion e = new Evaluacion(Util.nextID("evaluaciones.txt"), idInscripcion);
        } 
        catch (NullPointerException e) {
            Alert a = new Alert(AlertType.WARNING, "Falta de ingresar datos");
        }
    }

    @FXML
    private void cancelar(MouseEvent event) {
    }
    
}
