/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.concursomascotav2.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class VentanaPrincipalFXMLController implements Initializable {

    @FXML
    private Button btnDuenio;
    @FXML
    private Button btnMascota;
    @FXML
    private Button btnConcurso;
    @FXML
    private Button btnPremio;
    @FXML
    private Button btnCriterio;
    @FXML
    private Button btnInscripcion;
    @FXML
    private Button btnMiembroJurado;
    @FXML
    private Button btnEvaluacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void escenaDuenio(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("ventanaDuenioFXML");
            Scene sc = new Scene(loader.load(), 800, 500);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "No se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaMascota(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("panelMascota");
            Scene sc = new Scene(loader.load(), 800, 500);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "no se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaConcurso(MouseEvent event) {

        try {
            FXMLLoader loader = App.loadFXML("panelConcurso");
            Scene sc = new Scene(loader.load(), 800, 500);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "no se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaPremio(MouseEvent event) {

        try {
            FXMLLoader loader = App.loadFXML("panelPeticion");
            Scene sc = new Scene(loader.load(), 800, 400);
            PanelPeticionController pp = loader.getController();
            pp.llenarCamposPremio();
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "no se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaCriterio(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("panelPeticion");
            Scene sc = new Scene(loader.load(), 800, 400);
            PanelPeticionController pp = loader.getController();
            pp.llenarCamposCriterio();
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "no se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaInscripcion(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("ventanaInscripcionesFXML");
            Scene sc = new Scene(loader.load(), 800, 500);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "No se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaMiembroJurado(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("ventanaMiembroJuradoFXML");
            Scene sc = new Scene(loader.load(), 800, 500);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "No se pudo abrir la ventana");
        }
    }

    @FXML
    private void escenaEvaluacion(MouseEvent event) {
        try {
            FXMLLoader loader = App.loadFXML("ventanaEvaluaciones");
            Scene sc = new Scene(loader.load(), 800, 500);
            Stage sg = new Stage();
            sg.setScene(sc);
            sg.show();
        } catch (IOException e) {
            Alert a = new Alert(AlertType.ERROR, "No se pudo abrir la ventana");
        }
    }

}
