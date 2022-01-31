/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class VentanaInscripcionMascotasFXMLController implements Initializable {

    private ArrayList<Mascota> mascotas;
    private int numMascotas;
    private Concurso concurso;
    private TextField[] campos;
    private String mascotaActual;
    
    @FXML
    private VBox vbxContenedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generarCampos(numMascotas);
        mascotas = Mascota.readFile("mascotas.txt");
    }    

    @FXML
    private void guardar(MouseEvent event) {
        try {
            ArrayList<Inscripcion> inscripciones = new ArrayList<>();
            double costoTotal = 0;
            for (int i = 0; i < numMascotas; i++) {
                costoTotal += concurso.getCosto();
                mascotaActual = campos[i].getText();
                Mascota m = existenciaMascota(mascotaActual);
                Inscripcion ins = new Inscripcion(Util.nextID("inscripciones.txt"), m.getId(), m, concurso.getId(), concurso, concurso.getCosto(), 0, new ArrayList<Evaluacion>());
                inscripciones.add(ins);
            }
            Inscripcion.saveFile("inscripciones.txt", inscripciones);
            Alert inf = new Alert(AlertType.WARNING, "Ingreso exitoso!" + "\n" + "Costo total: " + costoTotal);
            Stage stg = (Stage) vbxContenedor.getScene().getWindow();
            stg.close();
        }
        catch (NullPointerException e) {
            Alert a = new Alert(AlertType.WARNING, "La mascota " + mascotaActual + " no existe");
            a.show();
        }
    }
    
    public void generarCampos(int numMascotas) {
        campos = new TextField[numMascotas];
        for (int i = 0; i < numMascotas; i++) {
            campos[i] = new TextField();
        }
        vbxContenedor.getChildren().addAll(campos);
    }
    
    public void setNumMascotas(int numero){
        this.numMascotas = numero;
    }
    
    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    
    public Mascota existenciaMascota(String mascota) throws NullPointerException{
        for (Mascota m : mascotas) {
            if (Objects.equals(mascota, m.getNombre())) return m;
        }
        throw new NullPointerException();
    }
}
