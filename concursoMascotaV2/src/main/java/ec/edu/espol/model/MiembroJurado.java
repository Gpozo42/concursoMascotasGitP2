/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
    
    // Comportamientos adicionales
    
    public static MiembroJurado nextMiembroJurado(Scanner sc) {
        String nombre;
        String apellido;
        String numTelefono;
        String email;
        String perfil;
        ArrayList<Evaluacion> evaluaciones = new ArrayList<>();
        
        System.out.println("Escriba su nombre: ");
        nombre = sc.nextLine();
        System.out.println("Escriba su apellido: ");
        apellido = sc.nextLine();
        System.out.println("Indique su número de celular: ");
        numTelefono = sc.nextLine();
        System.out.println("Indique su email: ");
        email = sc.nextLine();
        System.out.println("Indique su perfil profesional: ");
        perfil = sc.nextLine();
        
        return new MiembroJurado(Util.nextID("miembroJurados.txt"), nombre, apellido, numTelefono, email, perfil, evaluaciones);
    }
    
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            pw.println(super.toString() + "\\|" + this.perfil);
            // Solo se guarda hasta perfil, en el momento de la 
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<MiembroJurado> readFile(String nomFile) {
        ArrayList<MiembroJurado> miembrosJurado = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine(); // linea = id|nombres|apellidos|telefono|email|perfil
                String[] datos = linea.split("\\|"); //Eliminamos | y hacemos el split
                miembrosJurado.add( new MiembroJurado(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], new ArrayList<Evaluacion>()) ) ;
                /*
                Al momento de la lectura, solo se envía el ArrayList vacío para luego ser inicializado en el Main con el setter
                */
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return miembrosJurado;
    }
    
}
