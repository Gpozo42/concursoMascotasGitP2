/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.concursomascotav2.App;
import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Mascota;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
public class VentanaInscripcionesFXMLController implements Initializable {

    private ArrayList<Concurso> concursos;
    private ArrayList<Mascota> mascotas;
    
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnHecho;
    @FXML
    private BorderPane ventana;
    @FXML
    private TextField txtConcursoInscrip;
    @FXML
    private TextField txtNumMascotas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        concursos = Concurso.readFile("concursos.txt");
        mascotas = Mascota.readFile("mascotas.txt");
    }    

    @FXML
    private void regresar(MouseEvent event) {
        Stage stg = (Stage) ventana.getScene().getWindow();
        stg.close();
    }

    @FXML
    private void hecho(MouseEvent event) {
        String concursoInscrip = txtConcursoInscrip.getText();
        String numMascotaStr = txtNumMascotas.getText();
        
        if (concursoInscrip.equals("") || numMascotaStr.equals("")) {
            Alert a = new Alert(AlertType.WARNING, "Los campos se encuentran vacíos");
            a.show();
        }
        else if (!(existeConcurso(concursoInscrip))) {
            Alert a = new Alert(AlertType.WARNING, "El concurso no existe");
            a.show();
            txtConcursoInscrip.setText("");
        }
        else {
            try {
                int numMascota = Integer.parseInt(numMascotaStr);
                if (numMascota > 0) {
                    Concurso concurso = null;
                    for (Concurso c : concursos) if (c.getNombre().equals(concursoInscrip)) concurso = c;
                    
                    Stage stg = (Stage) ventana.getScene().getWindow();
                    stg.close();
                    FXMLLoader loader = App.loadFXML("ventanaInscripcionMascotasFXML");
                    Scene sc = new Scene(loader.load(), 600, 400);
                    VentanaInscripcionMascotasFXMLController vimf = loader.getController();

                    vimf.setNumMascotas(numMascota);
                    vimf.setConcurso(concurso);
                    
                    Stage sg = new Stage();
                    sg.setScene(sc);
                    sg.show();
                }
                else {
                    Alert a = new Alert(AlertType.WARNING,"Debe ingresar un número mayor a 0");
                    a.show();
                    txtNumMascotas.setText("");
                }
            }
            catch (NumberFormatException e) {
                Alert a = new Alert(AlertType.WARNING, "Ha ingresado letras en lugar de números");
                a.show();
                txtNumMascotas.setText("");
            }
            catch (IOException e) {
                Alert a = new Alert(AlertType.WARNING, e.getMessage());
            }
        }
    }
    
    private boolean existeConcurso(String concursoInscrip) {
        for (Concurso c : concursos) {
            if (Objects.equals(concursoInscrip, c.getNombre())) return true;
        }
        return false;
    }
    
}
