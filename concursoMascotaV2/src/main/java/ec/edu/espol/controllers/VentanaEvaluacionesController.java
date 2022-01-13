/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.Evaluacion;
import ec.edu.espol.model.Inscripcion;
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

/**
 * FXML Controller class
 *
 * @author gerar
 */
public class VentanaEvaluacionesController implements Initializable {
    
    private ArrayList<Evaluacion> evaluaciones;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<MiembroJurado> miembrosJurado;
    private ArrayList<Criterio> criterios;

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
        try {
            this.evaluaciones = Evaluacion.readFile("evaluaciones.txt");
            this.inscripciones = Inscripcion.readFile("inscripciones.txt");
            this.miembrosJurado = MiembroJurado.readFile("miembrosJurado.txt");
            this.criterios = Criterio.readFile("criterios.txt");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }    

    @FXML
    private void hecho(MouseEvent event) {
        try {
            creacionObjecto();
            limpiezaFields();
            } 
        catch (NullPointerException e) {
            Alert a = new Alert(AlertType.WARNING, "Falta de ingresar datos");
            a.show();
        }
    }

    @FXML
    private void cancelar(MouseEvent event) {
        limpiezaFields();
        //Añadir cambio de escena
    }
    
    private void creacionObjecto() throws NullPointerException {
        int idInscripcion = Integer.parseInt(txtIdInscripcion.getText());
        int idCriterio = Integer.parseInt(txtIdCriterio.getText());
        String emailJurado = txtEmailJurado.getText();
        double notaEvaluacion = Double.parseDouble(txtNotaEvaluacion.getText());
        
        Inscripcion inscripcion = null;
        MiembroJurado miembroJurado = null;
        Criterio criterio = null;
        for(Inscripcion i : this.inscripciones) if(i.getId() == idInscripcion) inscripcion = i;
        for(MiembroJurado mj: this.miembrosJurado) if (Objects.equals(emailJurado, mj.getEmail())) miembroJurado = mj;
        for (Criterio c : criterios) if(c.getId() == idCriterio) criterio = c;
        int idMiembroJurado = miembroJurado.getId();
            
        Evaluacion e = new Evaluacion(Util.nextID("evaluaciones.txt"), idInscripcion, inscripcion, idMiembroJurado, miembroJurado, notaEvaluacion, idCriterio, criterio);
        evaluaciones.add(e);
    }
    
    private void limpiezaFields() {
        txtIdInscripcion.setText("");
        txtIdCriterio.setText("");
        txtEmailJurado.setText("");
        txtNotaEvaluacion.setText("");
        
    }
}
