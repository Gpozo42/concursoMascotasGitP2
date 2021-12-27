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
 * @author gerar
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

    //ARCHIVOS ECRITURA
    public void saveFile(String archivo) {//esta en append 
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(archivo), true))) {
            pw.println(this.id + "|" + this.lugar + "|" + this.descripcion + "|" + this.idConcurso + "|" + this.concurso.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //ARCHIVOS LECTURA
    public static ArrayList<Premio> readFile(String archivo) {
        ArrayList<Premio> listaPremio = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(archivo))) {
            while (sc.hasNextLine()) {//mientras exista la sguiente linea
                String linea = sc.nextLine();
                String[] datos = linea.split("\\|");
                Premio p = new Premio(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), datos[2], Integer.parseInt(datos[3]), new Concurso(Integer.parseInt(datos[4]), datos[5], datos[6], Double.parseDouble(datos[7]), LocalDate.parse(datos[8]), LocalDate.parse(datos[9]), LocalDate.parse(datos[10])));//se crea un objeto premio
                listaPremio.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listaPremio;
    }

    public static void nextPremio(Scanner sc) {
        System.out.println("Ingrese la cantidad de premios para el concurso:");
        int cantidad = sc.nextInt();
        while (cantidad <= 0) {
            System.out.println("Ingrese un valor mayor a 0 para los premios: ");
            cantidad = sc.nextInt();
        }
        int sumador = 0;//o contador
        String[] descripciones = new String[cantidad];
        while (sumador < cantidad) {
            System.out.println("Ingrese la descripcion del premio " + (sumador + 1) + ":");
            String descrip = sc.next();
            descripciones[sumador] = descrip;
            sumador++;
        }
        System.out.println("Ingrese el nombre del concurso: ");
        String nombreConcurso = sc.next();
        Concurso valido = Concurso.anexarNombre(nombreConcurso);
        for (int i = 0; i < cantidad; i++) {
            Premio p = new Premio((i + 1), descripciones[i], valido.getId(), valido);
            p.saveFile("premios.txt");
        }
    }

}
