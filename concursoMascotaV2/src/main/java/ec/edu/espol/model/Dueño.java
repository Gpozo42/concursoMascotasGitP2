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
 * @author Gonzàlez
 */
public class Dueño extends Persona
{
    private String direccion;
    private ArrayList<Mascota> mascotas;
    
    public Dueño(int id, String nombres,String apellidos, String telefono, String email,String direccion )
    {
        super(id,nombres,apellidos,telefono,email);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\\|" + this.direccion;
       
    }
    
    public static Dueño anexarEmail(String email) {//verifica si el email de la clase dueno es igual al enviado por teclado
        ArrayList<Dueño> lista = Dueño.readFile("Dueños.txt");
        while (true) {
            for (Dueño c : lista) {
                if (email.equals(c.email)) {
                    return c;
                }
            }
            System.out.println("Ingrese un email de dueño ya existente: ");
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            email = sc.next();
        }
    }
    
    public static Dueño nextDueño(Scanner sc) {
        String nombres;
        String apellidos;
        String telefono;
        String email;
        String direccion;
        
        System.out.println("Ingrese sus nombres: ");
        nombres = sc.next();
        System.out.println("Ingrese sus apellidos: ");
        apellidos = sc.next();
        System.out.println("Ingrese su número de teléfono: ");
        telefono = sc.next();
        System.out.println("Ingrese su dirección de correo electrónico: ");
        email = sc.next();
        System.out.println("Ingrese su dirección de hogar: ");
        direccion = sc.next();
        
        return new Dueño(Util.nextID("dueños.txt"), nombres, apellidos, telefono, email, direccion);
    }
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) { // Modo append
            pw.println(this.toString()); //Solo se guarda hasta la direccion, no es posible guardar una lista de otros objetos
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<Dueño> readFile(String nomFile) {
        ArrayList<Dueño> dueños = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine(); // linea = id|nombres|apellidos|telefono|email|direccion
                String[] datos = linea.split("\\|"); //Eliminamos el punto y hacemos el split
                dueños.add( new Dueño(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5]) );
                /*
                Se agregan argumentos nulos y listas vacías, en el Main se generará el cambio por medio del uso de setters
                */
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return dueños;
    }

}
