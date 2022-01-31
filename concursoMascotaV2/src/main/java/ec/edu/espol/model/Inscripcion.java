/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

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
public class Inscripcion {
    private int id;
    private int idMascota;
    private Mascota mascota;
    private int idConcurso;
    private Concurso concurso;
    private double valor;
    private double descuento;
    private ArrayList<Evaluacion> evaluaciones;

    public Inscripcion(int id, int idMascota, Mascota mascota, int idConcurso, Concurso concurso, double valor, double descuento, ArrayList<Evaluacion> evaluaciones) {
        this.id = id;
        this.idMascota = idMascota;
        this.mascota = mascota;
        this.idConcurso = idConcurso;
        this.concurso = concurso;
        this.valor = valor;
        this.descuento = descuento;
        this.evaluaciones = evaluaciones;
    }

    public int getId() {
        return id;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public double getValor() {
        return valor;
    }

    public double getDescuento() {
        return descuento;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }
    
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFile, true))) { // Modo append
            bw.write(this.toString());
            bw.newLine();
            // No se guardan los objetos Mascota y Concurso, solo sus Ids para encontrarlos al momento de la lectura
        }
        catch (IOException e) {
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    public static void saveFile(String nomFile, ArrayList<Inscripcion> inscripciones) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFile, true))) { // Modo append
            for (Inscripcion i : inscripciones) {
                bw.write(i.toString());
                bw.newLine();
            }
            // No se guardan los objetos Mascota y Concurso, solo sus Ids para encontrarlos al momento de la lectura
        }
        catch (IOException e) {
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Inscripcion> readFile(String nomFile) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFile))) {
            String linea;
            while ((linea = br.readLine()) != null) { // linea = id|nombres|apellidos|telefono|email|perfil|evaluaciones
                String[] datos = linea.split("\\|"); //Eliminamos el punto y hacemos el split
                inscripciones.add( new Inscripcion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), null, Integer.parseInt(datos[2]), null, Double.parseDouble(datos[3]), Double.parseDouble(datos[4]), new ArrayList<Evaluacion>()) ) ;
                /*
                Se agregan argumentos nulos y listas vacías, en el Main se generará el cambio por medio del uso de setters
                */
            }
        }
        catch (IOException e) {
            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return inscripciones;
    }
    
    @Override
    public String toString() {
        return this.id + "\\|" + this.idMascota + "\\|" + this.idConcurso + "\\|" + this.valor + "\\|" + this.descuento;
    }
}
