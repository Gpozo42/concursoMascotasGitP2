/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author isaac
 */
public class Criterio {

    private int id;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private Concurso concurso;

    public Criterio(int id, String descripcion, int idConcurso, Concurso concurso) {
        this.id = id;
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }

    public Criterio(String descripcion, int idConcurso, Concurso concurso) {
        this.id = Util.nextID("criterios.txt");
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    public String toString(){
        return this.id + "|" + this.descripcion + "|" + this.idConcurso + "|" + this.concurso.toString();
    }

    public void saveFile(String archivo) throws IOException{
        try (FileWriter write = new FileWriter(archivo,true); BufferedWriter bf = new BufferedWriter(write)) {
            bf.write(this.toString());
            bf.newLine();
        }
    }

    //ARCHIVOS LECTURA
    public static ArrayList<Criterio> readFile(String archivo) throws IOException {
        ArrayList<Criterio> criterios = new ArrayList<>();
        try (FileReader read = new FileReader(archivo); BufferedReader bf = new BufferedReader(read)) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] datos=linea.split("\\|");
                Criterio cr = new Criterio(Integer.parseInt(datos[0]),
                        datos[1],
                        Integer.parseInt(datos[2]),
                        new Concurso(Integer.parseInt(datos[3]), datos[4], datos[5], Double.parseDouble(datos[6]), LocalDate.parse(datos[7]), LocalDate.parse(datos[8]), LocalDate.parse(datos[9])));//se crea un objeto criterio
                criterios.add(cr);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return criterios;
    }

    //FUNCIONES ESTATICAS 
    public static void nextCriterio(Concurso c, String descripcion) throws IOException {
        Criterio cr=new Criterio(descripcion,c.getId(),c);
        cr.saveFile("criterios.txt");
    }
}
