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

    public void saveFile(String archivo) {//esta en modo a(para añadir)
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(archivo), true))) {
            pw.println(this.id + "|" + this.descripcion + "|" + this.idConcurso+"|" + this.concurso.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //ARCHIVOS LECTURA
    public static ArrayList<Criterio> readFile(String archivo) {
        ArrayList<Criterio> criterios = new ArrayList<>();//creo una lista de criterios
        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {//mientras exista la sguiente linea
                String linea = sc.nextLine();
                String[] datos = linea.split("\\|");
                Criterio cr = new Criterio(Integer.parseInt(datos[0]), datos[1], Integer.parseInt(datos[2]), new Concurso(Integer.parseInt(datos[3]), datos[4], datos[5], Double.parseDouble(datos[6]), LocalDate.parse(datos[7]), LocalDate.parse(datos[8]), LocalDate.parse(datos[9])));//se crea un objeto criterio
                criterios.add(cr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return criterios;
    }

    //FUNCIONES ESTATICAS
    public static void nextCriterio(Scanner sc) {
        System.out.println("Ingrese la cantidad de criterios para el concurso:");
        int cantidad = sc.nextInt();
        while (cantidad <= 0) {
            System.out.println("Ingrese un valor mayor a 0 para los criterios: ");
            cantidad = sc.nextInt();
        }
        int sumador = 0;//o contador
        String[] descripciones = new String[cantidad];
        while (sumador < cantidad) {
            System.out.println("Ingrese la descripcion del criterio " + (sumador + 1) + ":");
            String descrip = sc.next();
            descripciones[sumador] = descrip;
            sumador++;
        }
        System.out.println("Ingrese el nombre del concurso: ");
        String nombreConcurso = sc.next();
        Concurso valido = Concurso.anexarNombre(nombreConcurso);
        for (int i = 0; i < cantidad; i++) {
            Criterio p = new Criterio(descripciones[i], valido.getId(), valido);
            p.saveFile("criterios.txt");
        }
    }

}
