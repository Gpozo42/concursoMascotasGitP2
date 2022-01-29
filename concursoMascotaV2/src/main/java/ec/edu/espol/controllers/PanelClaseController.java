/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class PanelClaseController implements Initializable {

    @FXML
    private Label lbPuesto;
    @FXML
    private Label lbNombre;
    @FXML
    private TextField lbDecripcion;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Confirmar(ActionEvent event) {
        pasarDrescripcion();
        
    }
    
    public void editarCampos(String clase,int valor){
        lbNombre.setText(clase);
        lbPuesto.setText(""+valor);
    }
    
    public String pasarDrescripcion(){
        return lbDecripcion.getText();
    }
    
}