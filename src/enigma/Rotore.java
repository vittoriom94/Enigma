/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

/**
 *
 * @author vimat
 */
public class Rotore extends Macchina{

    int[] rotore_back = new int[26];

    public Rotore(int[] values) {
        for(int i=0;i<values.length;i++){
            rotore_front[i]=values[i];
            rotore_back[values[i]] = i;
        }
    }
    
     public int getValueFront(int i,int shift){
        //int value = ((rotore_front[((i+shift)%26+26)%26]-shift)%26+26)%26;
        //System.out.println("Ingresso: "+ i + " Shift " + shift+ " Valore base " + rotore_front[((i+shift)%26+26)%26] + " Uscita rotore " + value);
        System.out.println(this.toString(shift) + " | " + (((rotore_front[((i-shift)%26+26)%26]+shift)%26+26)%26) + " " + (rotore_front[((i-shift)%26+26)%26]) + " "+ i + " " + shift);
       
        return ((rotore_front[((i-shift)%26+26)%26]+shift)%26+26)%26;
        // Versione precedente
        /*
        int value = (i-shift);
        while(value < 0){
            value = value+26;
        }
        value = value%26;
        System.out.println(this.toString() + " | " + i + " " + shift + " " + value + " " + rotore_front[value]);
        return rotore_front[value];
        */
        
    }
    public int getValueBack(int i,int shift){
        //int value = ((rotore_back[((i+shift)%26+26)%26]-shift)%26+26)%26;
        //System.out.println("Ingresso " + i + " " + "Uscita rotore back " + value);
        System.out.println((this.toStringr2(shift) + " | " + i + " " + shift ));
       
        return ((rotore_back[((i-shift)%26+26)%26]+shift)%26+26)%26;
        // Versione precedente
        
        
        //return (rotore_back[i] +shift)%26;*/
    }
    /*
    public int getValueFront(int i){
        
        System.out.println(this.toString());
        
        return rotore_front[i];
    }
    public int getValueBack(int i){
        
        System.out.println(this.toString2());
        
        return rotore_back[i];
    }*/
    public void shift() {
        int end = rotore_front[rotore_front.length - 1];
        for (int i = rotore_front.length-1; i > 0; i--) {
            int newVal = rotore_front[i - 1];
            rotore_front[i] = newVal;
            rotore_back[newVal] = i;
        }
        rotore_front[0] = end;
        rotore_back[end] = 0;
        
            System.out.println("Rotazione " + this.toString());
    }
    public String toStringr2(int shift){
        String a = "";
        for (int i = 0; i<26;i++){
            a =a.concat((((rotore_back[((i-shift)%26+26)%26]+shift)%26+26)%26)+ " ");
        }
        return a;
    }
    
    public String toString(int shift){
        String a = "";
        for (int i = 0; i<26;i++){
            a =a.concat((((rotore_front[((i+shift)%26+26)%26]-shift)%26+26)%26)+ " ");
        }
        return a;
    }
}
