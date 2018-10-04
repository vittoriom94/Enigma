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
        
        System.out.println(this.toString2());
        
        int value = i<shift ? i+26-shift : i-shift;
        return rotore_front[value];
    }
    public int getValueBack(int i,int shift){
        
        System.out.println(this.toString2() + " " + i + " " + shift);
        
        int value = i+shift>=26 ? i-26+shift : i+shift;
        return rotore_back[value];
    }/*
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
    }
    public String toString2(){
        String a = "";
        for (int i = 0; i<26;i++){
            a = a.concat(rotore_back[i] + " ");
        }
        return a;
    }

}
