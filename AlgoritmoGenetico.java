/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Vinnys
 */
public class AlgoritmoGenetico {
    public static int numeroIndividuos=15;
    public static int numeroInteraciones=10;
    public static int rDeMutacion=10;
    public static int rDeMantenimiento=50;
    //public ArrayList<Individuo>poblacion;
    public static List<Individuo> poblacion;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Inicio...");
        generarPoblacion();
        System.out.println("Iteracion a travez de generaciones ...");
        for (int i = 0; i < numeroInteraciones; i++) {
            System.out.println(i+"_");
            generarFitness();
            //organizar();
            informacion();
            cruzaMutacion();
        }
        System.out.println("Definicion del criterio de terminacion(10 interacciones/generaciones)");
        System.out.println("Fin...");
    }
    
    public static void generarPoblacion(){
        
        poblacion= new ArrayList<Individuo>();
        for (int i = 0; i < numeroIndividuos; i++) {
            
            Individuo individuo= new Individuo();
            individuo.genoma=( (int) (Math.random() * 25) + 0);
            poblacion.add(i, individuo);
            
        }
        /*System.out.println("______________");
        for (int i = 0; i < poblacion.size(); i++) {
            System.out.println("i: "+i+", "+poblacion.get(i).genoma+", "+poblacion.get(i).fitness);
        }*/
        System.out.println("Generacion de poblacion inicial...");
    }
    public static void generarFitness(){
        
        int tamaño = poblacion.size();
        //System.out.println("Fitness "+tamaño);
        for (int i = 0; i < tamaño; i++) {
            //System.out.println("Fitness: "+i);
            Individuo individuo=poblacion.get(i);
            int geno=individuo.genoma;
            individuo.fitness=1/miFuncion(geno/100f);
            poblacion.set(i, individuo);
        }
    }
    public static float miFuncion(float x){
        
        //return (float) ((9*(x+8))/(2*(x+8)+ Math.sin(x+8))); 
        return 5*x*x-20*x+3;
    }
    public static void organizar(){
        //System.out.println("Organizas");
        int tamaño=poblacion.size();
        boolean sw=true;
        while(sw){
            sw=false;
            for (int i = 0; i < tamaño-1;i++) {
                    if(poblacion.get(i).fitness > poblacion.get(i+1).fitness){
                        Individuo indi=poblacion.get(i+1);
                        poblacion.set(i, indi);
                        indi=poblacion.get(i);
                        poblacion.set(i+1,indi);
                        sw=true;
                    }
            }
        }
    }
    public static void informacion(){
        String s="";
        int tamaño = poblacion.size();
        for (int  i = 0;  i < tamaño;  i++) {
            s=s+" "+"("+i+")"+poblacion.get(i).genoma/100f+ " p:"+poblacion.get(i).fitness+"\n";
        }
        System.out.println(s);
    }
    public static void cruzaMutacion(){
        //System.out.println("Aplicación de los operadores genéticos (cruza y mutación).");
        List<Individuo> aptos = quitarNoAptos();
        int cantidad=aptos.size();
        for (int i = 0; i < (numeroIndividuos*(1f-rDeMantenimiento/100f)); i++) {
            int indice1 =  (int) (Math.random() * cantidad) + 0;
            int indice2 = (int) (Math.random() * cantidad) + 0;
            String gen1=aptos.get(indice1).genoma+"";
            String gen2=aptos.get(indice2).genoma+"";
            String genHijo="";
            for (int j = 0; j < gen1.length(); j++) {
                if(((int) (Math.random() * 100) + 0)< rDeMutacion){
                    if(j==0){
                        genHijo+=(((Math.random() * 1) + 0)==0)? "0": "1";
                    }else{
                        genHijo+=((Math.random() * 1) + 0);
                    }
                }else{
                    if(((Math.random() * 100) + 0) > 50){
                       genHijo+=gen1.charAt(j);
                    }else{
                        genHijo+=gen1.charAt(j);
                    }
                }
            }
            //System.out.println("Gh: "+genHijo);
            Individuo indi= new Individuo();
            double conversion=Double.parseDouble(genHijo);
            int valor =(int) (conversion);
            //System.out.println("Gh: "+valor);
            indi.BinToDec(valor+"");
            //indi.getGenoma();
            aptos.add(indi);
        }
        poblacion=new ArrayList<>();
        poblacion=aptos;
    }
    public static List<Individuo> quitarNoAptos(){
        List<Individuo> aptos = new ArrayList<Individuo>();
        for (int i = 0; i < numeroIndividuos*rDeMantenimiento/100f; i++) {
            aptos.add(poblacion.get(i));
        }
        return aptos;
    }
}
