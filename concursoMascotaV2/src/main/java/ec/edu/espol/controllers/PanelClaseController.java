/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.ConcursoNotIndexException;
import ec.edu.espol.model.Premio;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class PanelClaseController implements Initializable {

    @FXML
    private VBox vb;

    private TextField[] campos;

    private String nameClass;
    private String nameConcurso;
    private int numero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Confirmar(ActionEvent event) {

        try {
            for (int i = 0; i < numero; i++) {
                if (nameClass.equals("premios")) {
                    Concurso c = Concurso.anexarNombre(nameConcurso);
                    Premio.nextPremio(c, campos[i].getText(), i);
                }
                if (nameClass.equals("concursos")) {

                }
            }
            Stage stg = (Stage) vb.getScene().getWindow();
            stg.close();
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Se han guardado los premios satisfactoriamente");
            a.show();
        } catch (ConcursoNotIndexException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se ha encontrado un concurso con ese nombre.");
            a.show();
        }
    }

    public void generarCampos(int i) {
        campos = new TextField[i];
        for (int j = 0; j < i; j++) {
            campos[j] = new TextField();
        }
        vb.getChildren().addAll(campos);
    }

    public void nombreClase(String nombre) {
        this.nameClass = nombre;
    }

    public void nombreConcurso(String nombre) {
        this.nameConcurso = nombre;
    }

    public void Cantidad(String nombre) {
        this.numero = Integer.parseInt(nombre);
    }

}
