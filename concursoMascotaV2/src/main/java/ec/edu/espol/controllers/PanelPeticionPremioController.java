/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.concursomascotav2.App;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.ConcursoNotIndexException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class PanelPeticionPremioController implements Initializable {

    @FXML
    private BorderPane ventana;
    @FXML
    private Button bRegresar;
    @FXML
    private Button bCrear;
    @FXML
    private TextField numeroDado;
    @FXML
    private TextField textoNombreC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Regresar(ActionEvent event) {
        Stage stg = (Stage) ventana.getScene().getWindow();
        stg.close();
    }

    @FXML
    private void Crear(ActionEvent event) {
        String concurso = textoNombreC.getText();
        int cantidad = Integer.parseInt(numeroDado.getText());

        try {
            Concurso c = Concurso.anexarNombre(concurso);
            for (int i = 0; i < cantidad; i++) {
                FXMLLoader loader = App.loadFXML("panelPremio");
                Scene sc = new Scene(loader.load(), 800, 500);
                Stage sg = new Stage();
                sg.setScene(sc);
                sg.show();
            }
            
        } catch (ConcursoNotIndexException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se ha encontrado un concurso con ese nombre.");
            a.show();
        }
        catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al abrir la ventana");
            a.show();
        }
    }

}
