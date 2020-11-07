/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmo.genetico;

/**
 *
 * @author Vinnys
 */
public class Individuo {
    public int genoma;
    public float fitness;

    public Individuo() {
        this.genoma = genoma;
        this.fitness = fitness;
    }

    public String getGenoma() {
        return DecToBin(genoma);
    }
    /*public String setGenoma2(String valor) {
        genoma= BinToDec(Integer.parseInt(valor));
        return null;
    }*/
    

    public void setGenoma(int genoma) {
        this.genoma = genoma;
    }

    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
    public int BinToDec(String numero){
        int n=Integer.parseInt(numero);
        if(n==0){
            return 0;
        }
        float suma=0;
        int string=(n+"").length();
        for (int i = 0; i < string; i++) {
            int ultimoDigito=n%10;
            suma=(float) (suma+ultimoDigito*(Math.pow(2, i)));
            n=n/10;
        }
        if(n>10){
            return (int)suma;
        }else{
            return (int)suma;
        }
        
    }
    public static String DecToBin(int numero){
        int n=Math.abs(numero);
        
        System.out.println("abs= "+n);
        String binario="";
        if(n==0){
            return "00000000000";
        }
        while(n>0){
            if(n%2==0){
                binario="0"+binario;
            }else{
                binario="1"+binario;
            }
            n=(int)n/2;
        }
        
        while(binario.length()<10){//4 bits
         binario="0"+binario;
        }
        if(numero>0){
            return "0"+binario;
        }else{
            return "-"+binario;
        }
    }
    
}
