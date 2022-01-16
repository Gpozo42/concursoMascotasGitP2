/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import java.net.URL;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class PanelConcursoController implements Initializable {

    @FXML
    private TextField exNom;
    @FXML
    private TextField exTem;
    @FXML
    private TextField exCos;
    @FXML
    private TextField exFc;
    @FXML
    private TextField exFi;
    @FXML
    private TextField exFf;
    @FXML
    private BorderPane ventana;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void regresar(MouseEvent event) {
        Stage stg = (Stage) ventana.getScene().getWindow();
        stg.close();
    }

    @FXML
    private void guardar(MouseEvent event) {
        String nombre = exNom.getText();
        String tematica = exTem.getText();
        String costo = exCos.getText();
        String fecha = exFc.getText();
        String fechaInicio = exFi.getText();
        String fechaFinal = exFf.getText();

        try {
            Concurso c = Concurso.nextConcurso(nombre, tematica, costo, fecha, fechaInicio, fechaFinal);
            c.saveFile("concursos.txt");
            Alert inf = new Alert(Alert.AlertType.INFORMATION, "Se ha guardado el concurso exitosamente");
            inf.show();
        } catch (NumberFormatException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Se ha ingresado letras por numeros");
            a.show();
        } catch (DateTimeParseException e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "El formato escrito no es igual a (AAAA-MM-DD) o ha escrito \nmeses o dias fuera del rango ");
            a.show();
        }

        exNom.setText("");
        exTem.setText("");
        exCos.setText("");
        exFc.setText("");
        exFi.setText("");
        exFf.setText("");
    }

}
