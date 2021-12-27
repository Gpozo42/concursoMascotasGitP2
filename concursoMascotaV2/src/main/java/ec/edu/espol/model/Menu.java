/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author gerar
 */
public class Menu {
    private Menu(){}
    
    public static void menuInicial(int opcion) {
    
        // Acciones del menú
        switch (opcion) { //Recibimos un int de la validacion anterior, un switch case es rapido y efectivo
            case 1: //Dueño
                Scanner sc1 = new Scanner(System.in);
                sc1.useDelimiter("\n");
                Dueño dueño = Dueño.nextDueño(sc1);
                dueño.saveFile("dueños.txt");
                sc1.close();
                break;
            case 2: //Mascota
                Scanner sc2=new Scanner(System.in);
                sc2.useDelimiter("\n");
                Mascota.nextMascota(sc2);
                sc2.close();
                break;
            case 3: //Concurso
                Scanner sc3=new Scanner(System.in);
                sc3.useDelimiter("\n");
                (Concurso.nextConcurso(sc3)).saveFile("concursos.txt");
                sc3.close();
                break;
            case 4: //Premio
                Scanner sc4=new Scanner(System.in);
                sc4.useDelimiter("\n");
                Premio.nextPremio(sc4);
                sc4.close();
                break;
            case 5: //Criterio
                Scanner sc5=new Scanner(System.in);
                sc5.useDelimiter("\n");
                Criterio.nextCriterio(sc5);
                sc5.close();
                break;
            case 6: //Incripción
                Scanner sc6 = new Scanner(System.in);
                sc6.useDelimiter("\n");
                ArrayList<Inscripcion> inscrip = Inscripcion.nextInscripcion(sc6);
                if (!(inscrip.isEmpty())) for (Inscripcion i : inscrip) i.saveFile("incripciones.txt");
                sc6.close();
                break;
            case 7: //MiembroJurado
                Scanner sc7 = new Scanner(System.in);
                sc7.useDelimiter("\n");
                MiembroJurado miembroJurado = MiembroJurado.nextMiembroJurado(sc7);
                miembroJurado.saveFile("miembroJurados.txt");
                sc7.close();
                break;
            default: //Evaluacion
                Scanner sc8 = new Scanner(System.in);
                sc8.useDelimiter("\n");
                Evaluacion evaluacionNueva = Evaluacion.nextEvaluacion(sc8);
                if (evaluacionNueva != null) evaluacionNueva.saveFile("evaluaciones.txt");
                sc8.close();
                break;
        }
        
    } 
}
