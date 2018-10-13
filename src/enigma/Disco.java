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
public class Disco {
    
    int[] rotore_front = new int[26];
    
    
    public int getValueFront(int i) {
        return rotore_front[i];
        
    }
    
    public String toString(){
        String a = "";
        for (int i = 0; i<26;i++){
            a = a.concat(rotore_front[i] + " ");
        }
        return a;
    }
}
