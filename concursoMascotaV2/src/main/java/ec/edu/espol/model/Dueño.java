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
import java.util.ArrayList;

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
    
    // Guardado y lectura de archivos
    public void saveFile(String nomFile){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFile))) { // Modo append
            bw.write(this.toString()); //Solo se guarda hasta la direccion, no es posible guardar una lista de otros objetos
            bw.newLine();
        }
        catch (IOException e) {
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static ArrayList<Dueño> readFile(String nomFile) {
        ArrayList<Dueño> dueños = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nomFile))) {
            String linea;
            while ((linea = br.readLine()) != null) { // linea = id|nombres|apellidos|telefono|email|direccion
                String[] datos = linea.split("\\|"); //Eliminamos el punto y hacemos el split
                dueños.add( new Dueño(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5]) );
                /*
                Se agregan argumentos nulos y listas vacías, en el Main se generará el cambio por medio del uso de setters
                */
            }
        }
        catch (IOException e) {
            
        }
        catch (NumberFormatException e) {
            
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return dueños;
    }

}
