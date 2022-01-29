/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author isaac
 */
public class Premio {

    private int id;
    private int lugar;
    private String descripcion;
    private int idConcurso;
    private Concurso concurso;

    public Premio(int id, int lugar, String descripcion, int idConcurso, Concurso concurso) {
        this.id = id;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }

    public Premio(int lugar, String descripcion, int idConcurso, Concurso concurso) {
        this.id = Util.nextID("premios.txt");
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }

    //getters
    public int getId() {
        return id;
    }

    public int getLugar() {
        return lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.lugar + "|" + this.descripcion + "|" + this.idConcurso + "|" + this.concurso.toString();
    }

    //ARCHIVOS ECRITURA
    public void saveFile(String archivo) {//modo append
        try (FileWriter write = new FileWriter(archivo, true); BufferedWriter bf = new BufferedWriter(write)) {
            bf.write(this.toString());
            bf.newLine();
        } catch (IOException e) {
            System.out.println("Error. No se encontro el archivo");
        }

    }

    //ARCHIVOS LECTURA
    public static ArrayList<Premio> readFile(String archivo) {
        ArrayList<Premio> premios = new ArrayList<>();
        try (FileReader read = new FileReader(archivo); BufferedReader bf = new BufferedReader(read)) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] datos = linea.split("\\|");
                Premio p = new Premio(Integer.parseInt(datos[0]),
                        Integer.parseInt(datos[1]),
                        datos[2],
                        Integer.parseInt(datos[3]),
                        new Concurso(Integer.parseInt(datos[4]), datos[5], datos[6], Double.parseDouble(datos[7]), LocalDate.parse(datos[8]), LocalDate.parse(datos[9]), LocalDate.parse(datos[10])));//se crea un objeto premio
                premios.add(p);
            }

        } catch (IOException ex) {
            System.out.println("ERROR al leer el archivo");
        }
        return premios;
    }

    public static void nextPremio(Concurso c, String descripcion, int lugar) {
        
        Premio p = new Premio((lugar + 1), descripcion, c.getId(), c);
        p.saveFile("premios.txt");
        
    }
}
