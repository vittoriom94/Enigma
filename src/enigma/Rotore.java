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
public class Rotore extends Disco{

    int[] rotore_back = new int[26];

    public Rotore(int[] values) {
        for(int i=0;i<values.length;i++){
            rotore_front[i]=values[i];
            rotore_back[values[i]] = i;
        }
    }
    
     public int getValueFront(int i,int shift){
       int index=((i-shift)%26+26)%26;
       int value=rotore_front[index];
        return ((value+shift)%26+26)%26;
       
        
    }
    public int getValueBack(int i,int shift){
       
       
        int index=((i-shift)%26+26)%26;
        int value=rotore_back[index];
         return ((value+shift)%26+26)%26;
        
       
    }
   
   
    public String toStringrback(int shift){
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
