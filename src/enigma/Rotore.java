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
public class Rotore {

    int[] rotore = new int[26];

    public Rotore(int[] values) {
        rotore = values.clone();
    }

    public int getValue(int i) {
        System.out.println(this.toString());
        return rotore[i];
        
    }

    public void shift() {
        int end = rotore[rotore.length - 1];
        for (int i = rotore.length-1; i > 0; i--) {
            rotore[i] = rotore[i - 1];
        }
        rotore[0] = end;
    }
    
    public String toString(){
        String a = "";
        for (int i = 0; i<26;i++){
            a = a.concat(rotore[i] + " ");
        }
        return a;
    }
}
