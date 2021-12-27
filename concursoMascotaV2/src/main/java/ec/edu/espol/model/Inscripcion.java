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
import java.util.Objects;
import java.util.Scanner;

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
    
    public static ArrayList<Inscripcion> nextInscripcion(Scanner sc) {
        int contador = 0;
        int numMascotas = 0;
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        
        System.out.println("Cuántas mascotas desea inscribir?");
        
        if (sc.hasNextInt()) {
            numMascotas = sc.nextInt();

            while (contador < numMascotas) {
                String nombreMascota;
                String nombreConcurso;
                Concurso concursoInscrito = null;
                Mascota mascotaInscrita = null;

                System.out.println("Ingrese el nombre de su mascota: ");
                nombreMascota = sc.next();
                System.out.println("Ingrese el nombre del concurso a inscribirse: ");
                nombreConcurso = sc.next();

                for (Concurso c : Concurso.readFile("concursos.txt")) if (Objects.equals(nombreConcurso, c.getNombre())) concursoInscrito = c;
                for (Mascota m : Mascota.readFile("mascotas.txt")) if (Objects.equals(nombreMascota, m.getNombre())) mascotaInscrita = m;

                if (concursoInscrito != null && mascotaInscrita != null) inscripciones.add(new Inscripcion(Util.nextID("inscripciones.txt"), mascotaInscrita.getId(), mascotaInscrita, concursoInscrito.getId(), concursoInscrito, concursoInscrito.getCosto(), 0, new ArrayList<>()));

                contador++;
            }
        }
        
        
        return inscripciones;
    }
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            pw.println(this.toString());
            // No se guardan los objetos Mascota y Concurso, solo sus Ids para encontrarlos al momento de la lectura
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<Inscripcion> readFile(String nomFile) {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine(); // linea = id|nombres|apellidos|telefono|email|perfil|evaluaciones
                String[] datos = linea.split("\\|"); //Eliminamos el punto y hacemos el split
                inscripciones.add( new Inscripcion(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), null, Integer.parseInt(datos[2]), null, Double.parseDouble(datos[3]), Double.parseDouble(datos[4]), new ArrayList<Evaluacion>()) ) ;
                /*
                Se agregan argumentos nulos y listas vacías, en el Main se generará el cambio por medio del uso de setters
                */
            }
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
