/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author isaac
 */
public class Concurso {

    private int id;
    private String nombre;
    private LocalDate fecha;//fecha del concurso
    private LocalDate fechaIncripcion;
    private LocalDate fechaCierreInscripcion;
    private String tematica;
    private double costo;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Premio> premios;
    private ArrayList<Criterio> criterios;

    public Concurso(String nombre, String tematica, double costo, LocalDate fecha, LocalDate fechaInscripcion, LocalDate fechaCierreInscripcion) {
        this.id = Util.nextID("concursos.txt");
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaIncripcion = fechaInscripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;
        this.costo = costo;
        this.inscripciones = new ArrayList<>();
        this.premios = new ArrayList<>();
        this.criterios = new ArrayList<>();
    }

    public Concurso(int id, String nombre, String tematica, double costo, LocalDate fecha, LocalDate fechaIncripcion, LocalDate fechaCierreInscripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.fechaIncripcion = fechaIncripcion;
        this.fechaCierreInscripcion = fechaCierreInscripcion;
        this.tematica = tematica;
        this.costo = costo;
        this.inscripciones = new ArrayList<>();
        this.premios = new ArrayList<>();
        this.criterios = new ArrayList<>();
    }

    //getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalDate getFechaIncripcion() {
        return fechaIncripcion;
    }

    public LocalDate getFechaCierreInscripcion() {
        return fechaCierreInscripcion;
    }

    public String getTematica() {
        return tematica;
    }

    public double getCosto() {
        return costo;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFechaIncripcion(LocalDate fechaIncripcion) {
        this.fechaIncripcion = fechaIncripcion;
    }

    public void setFechaCierreInscripcion(LocalDate fechaCierreInscripcion) {
        this.fechaCierreInscripcion = fechaCierreInscripcion;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    //FUNCIONES
    @Override
    public String toString() {
        return this.id + "|" + this.nombre + "|" + this.tematica + "|" + this.costo + "|" + this.fecha + "|" + this.fechaIncripcion + "|" + this.fechaCierreInscripcion;
    }

    //ARCHIVOS ESCRITURA
    public void saveFile(String archivo) {//esta en append 
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(archivo), true))) {
            pw.println(this.id + "|" + this.nombre + "|" + this.tematica + "|" + this.costo + "|" + this.fecha + "|" + this.fechaIncripcion + "|" + this.fechaCierreInscripcion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //ARCHIVOS LECTURA
    public static ArrayList<Concurso> readFile(String archivo) {
        ArrayList<Concurso> listaConcurso = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {//mientras exista la sguiente linea
                String linea = sc.nextLine();
                String[] datos = linea.split("\\|");
                Concurso c = new Concurso(Integer.parseInt(datos[0]), datos[1], datos[2], Double.parseDouble(datos[3]), LocalDate.parse(datos[4]), LocalDate.parse(datos[5]), LocalDate.parse(datos[6]));//se crea un objeto concurso
                listaConcurso.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaConcurso;
    }

    //FUNCIONES ESTATICAS
    public static Concurso nextConcurso(Scanner sc) {//Opcion 3
        System.out.println("Ingrese el nombre del concurso:");
        String nombre = sc.next();
        System.out.println("Ingrese el nombre de la tematica:");
        String tematica = sc.next();
        System.out.println("Ingrese el costo por participar:");
        double coste = sc.nextDouble();
        System.out.println("Ingrese la fecha del concurso:");
        String fecha = sc.next();
        System.out.println("Ingrese la fecha inicial de incripcion:");
        String fechaI = sc.next();
        System.out.println("Ingrese la fecha final de inscripcion:");
        String fechaF = sc.next();
        Concurso c = new Concurso(nombre, tematica, coste, LocalDate.parse(fecha), LocalDate.parse(fechaI), LocalDate.parse(fechaF));
        return c;
    }

    public static Concurso anexarNombre(String nombre) {//verifica si el inombre de la clase concurso es igual al enviado por teclado
        ArrayList<Concurso> lista = Concurso.readFile("concursos.txt");
        while (true) {
            for (Concurso c : lista) {
                if (nombre.equals(c.nombre)) {
                    return c;
                }
            }
            System.out.println("Ingrese un nombre de concurso ya existente: ");
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            nombre = sc.next();
        }
    }

}
