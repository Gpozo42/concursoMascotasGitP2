/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gerar
 */
public class MiembroJurado extends Persona{
    private String perfil;
    private ArrayList<Evaluacion> evaluaciones;

    public MiembroJurado(int id, String nombres, String apellidos, String telefono, String email, String perfil, ArrayList<Evaluacion> evaluaciones) {
        super(id, nombres, apellidos, telefono, email);
        this.perfil = perfil;
        this.evaluaciones = evaluaciones;
    }
    
    //Getters
    public String getPerfil() {
        return perfil;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nomFile), true))) { // Modo append
            bw.write(this.toString());
            bw.newLine();
            // Solo se guarda hasta perfil, en el momento de la 
        }
        catch (IOException e) {
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<MiembroJurado> readFile(String nomFile) {
        ArrayList<MiembroJurado> miembrosJurado = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFile))) {
            String linea;
            while ((linea = br.readLine()) != null) { // linea = id|nombres|apellidos|telefono|email|perfil
                String[] datos = linea.split("\\|"); //Eliminamos | y hacemos el split
                miembrosJurado.add( new MiembroJurado(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], new ArrayList<Evaluacion>()) ) ;
                /*
                Al momento de la lectura, solo se envía el ArrayList vacío para luego ser inicializado en el Main con el setter
                */
            }
        }
        catch (IOException e) {
            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return miembrosJurado;
    }
    
}
