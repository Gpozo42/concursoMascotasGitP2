/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gerar
 */
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private Inscripcion inscripcion;
    private int idMiembroJurado;
    private MiembroJurado miembroJurado;
    private double nota;
    private int idCriterio;
    private Criterio criterio;

    public Evaluacion(int id, int idInscripcion, Inscripcion inscripcion, int idMiembroJurado, MiembroJurado miembroJurado, double nota, int idCriterio, Criterio criterio) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        this.inscripcion = inscripcion;
        this.idMiembroJurado = idMiembroJurado;
        this.miembroJurado = miembroJurado;
        this.nota = nota;
        this.idCriterio = idCriterio;
        this.criterio = criterio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idIncripcion) {
        this.idInscripcion = idIncripcion;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getIdMiembroJurado() {
        return idMiembroJurado;
    }

    public void setIdMiembroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public MiembroJurado getMiembroJurado() {
        return miembroJurado;
    }

    public void setMiembroJurado(MiembroJurado miembroJurado) {
        this.miembroJurado = miembroJurado;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }
    

    //Lectura y guardado de archivos
    public void saveFile(String nomFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFile, true))) { // Modo append
            bw.write(this.toString());
            bw.newLine();
            // Solo se guardan los Ids, los objetos no son posibles de guardar
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
        }
        
    }
    
    public static ArrayList<Evaluacion> readFile(String nomFile) {
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFile))) {
            String linea;
            while ((linea = br.readLine()) != null) { // linea = id|idInscripcion|idMiembroJurado|nota|idCriterio
                String[] datos = linea.split("\\|"); //Eliminamos | y hacemos el split
                evaluaciones.add( new Evaluacion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), null, Integer.parseInt(datos[2]), null, Double.parseDouble(datos[3]), Integer.parseInt(datos[4]), null) );
                /*
                Los argumentos con null, serán reemplazados por los objetos dependiendo de su id. Esto se elabora en el Main con los setters
                */
            }
        }
        catch (IOException e) {
            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return evaluaciones;
    }
    
    @Override
    public String toString() {
        return this.id + "\\|" + this.idInscripcion + "\\|" + this.idMiembroJurado + "\\|" + this.nota + "\\|" + this.idCriterio;
    }
}
