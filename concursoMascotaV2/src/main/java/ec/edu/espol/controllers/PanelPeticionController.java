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
public class PanelPeticionController implements Initializable {

    @FXML
    private BorderPane ventana;
    @FXML
    private Label titulo;
    @FXML
    private Button bRegresar;
    @FXML
    private Button bCrear;
    @FXML
    private Label lbClase;
    @FXML
    private TextField textoNombreC;
    @FXML
    private TextField numeroDado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbClase.getText();
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
        String cantidad = numeroDado.getText();
        String nameClass = lbClase.getText();
Alert k = new Alert(Alert.AlertType.ERROR, nameClass);
            k.show();
        try {
            if (concurso.equals("") || cantidad.equals("")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Exiten campos vacios. Por favor, rellenarlos");
                a.show();
            } else {
                int lugar=Integer.parseInt(cantidad);
                Concurso c = Concurso.anexarNombre(concurso);
                Stage stg = (Stage) ventana.getScene().getWindow();
                stg.close();
                if (nameClass.equals("premios")) {
                    for (int i = 0; i < lugar; i++) {
                        FXMLLoader loader = App.loadFXML("panelClase");
                        Scene sc = new Scene(loader.load(), 800, 500);
                        PanelClaseController pcc = loader.getController();
                        pcc.editarCampos(nameClass, i);
                        Stage sg = new Stage();
                        sg.setScene(sc);
                        sg.show();
                        String descripcion = "";
                        while (descripcion.equals("")) {
//                            pcc.pasarDrescripcion();
                       }
                        Alert a = new Alert(Alert.AlertType.ERROR, "1111111");
                        a.show();

                    }
                }
                if (nameClass.equals("criterios")) {

                }

            }

        } catch (ConcursoNotIndexException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se ha encontrado un concurso con ese nombre.");
            a.show();
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Se ha ingresado letras por numeros");
            a.show();
        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al abrir la ventana");
            a.show();
        }
    }

    public void llenarCamposPremio() {
        titulo.setText("PREMIOS");
        lbClase.setText("premios");
    }

    public void llenarCamposCriterio() {
        titulo.setText("CRITERIOS");
        lbClase.setText("criterios");
    }

}
