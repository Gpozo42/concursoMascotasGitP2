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


/**
 *
 * @author isaac
 */
public class Mascota {
        
    private int id;
    private String nombre;
    private String tipo;
    private String raza;
    private LocalDate fechaNacimiento;
    private int idDueno;
    private Dueño dueno;
    private ArrayList<Inscripcion> inscripciones;

    public Mascota(int id, String nombre, String tipo, String raza, LocalDate fechaNacimiento, int idDueno, Dueño dueno) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.idDueno = idDueno;
        this.dueno = dueno;
        this.inscripciones = new ArrayList<>();
    }

    public Mascota(String nombre, String tipo, String raza, LocalDate fechaNacimiento, int idDueno, Dueño dueno) {
        this.id = Util.nextID("mascotas.txt");
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.idDueno = idDueno;
        this.dueno = dueno;
        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public String getRaza() {
        return raza;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public int getIdDueno() {
        return idDueno;
    }
    public Dueño getDueno() {
        return dueno;
    }
    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }
    public void setDueno(Dueño dueno) {
        this.dueno = dueno;
    }
    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    public String toString(){
        return this.id + "|" + this.nombre + "|" + this.tipo + "|" + this.raza + "|" + this.fechaNacimiento + "|" + this.idDueno + "|"+this.dueno.toString();
    }

    
    //ARCHIVOS ECRITURA
        public void saveFile(String archivo) throws IOException {//esta en append 
        try (FileWriter write = new FileWriter(archivo,true); BufferedWriter bf = new BufferedWriter(write)) {
            bf.write(this.toString());
            bf.newLine();
        } 
    }

    //ARCHIVOS LECTURA
        public static ArrayList<Mascota> readFile(String archivo) {
        ArrayList<Mascota> mascota = new ArrayList<>();
        try (FileReader read = new FileReader(archivo); BufferedReader bf = new BufferedReader(read)) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String[] datos = linea.split("\\|");
                Mascota m = new Mascota(Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        datos[3],
                        LocalDate.parse(datos[4]),
                        Integer.parseInt(datos[5]),
                        new Dueño(Integer.parseInt(datos[6]), datos[7], datos[8], datos[9], datos[10], datos[11]));//se crea un objeto mascota
                mascota.add(m);
            }
        } catch (IOException ex) {
            System.out.println("ERROR al leer el archivo");
        }
        return mascota;
    }

    //funciones estaticas
    public static void nextMascota(String nombre, String tipo, String raza, String fecha, Dueño dueno) throws IOException { 
        Mascota p = new Mascota(nombre, tipo, raza, LocalDate.parse(fecha), dueno.getId(), dueno);
        p.saveFile("mascotas.txt");
    }

}
