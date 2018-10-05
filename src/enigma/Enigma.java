/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vimat
 */
public class Enigma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            String s1 = "EJ OY IV AQ KW FX MT PS LU BD";
           String s2 = "IU AS DV GL FT OX EZ CH MR KN BQ PW JY";
           String stampa = "";
           /*for(char c : s.toCharArray()){
               stampa = stampa + (int)(c-65) + ",";
           }*/
           int[] scamb = new int[26];
           int[] rifl = new int[26];
           Arrays.fill(scamb, -1);
           for(String c: s1.split(" ")){
               scamb[(int)(c.charAt(0)-65)] = (int)(c.charAt(1)-65);
               scamb[(int)(c.charAt(1)-65)] = (int)(c.charAt(0)-65);        
           }
           for(int x = 0; x<scamb.length;x++){
               if (scamb[x] == -1){
                   scamb[x] = x;
               }
           }
           for(String c: s2.split(" ")){
               rifl[(int)(c.charAt(0)-65)] = (int)(c.charAt(1)-65);
               rifl[(int)(c.charAt(1)-65)] = (int)(c.charAt(0)-65);        
           }
           System.out.println(stampa);  
            int[] primo = {1,3,5,7,9,11,2,15,17,19,23,21,25,13,24,4,8,22,6,0,10,12,20,18,16,14};
            int[] secondo = {4,18,14,21,15,25,9,0,24,16,20,8,17,7,23,11,13,5,19,6,10,3,2,12,22,1};
            int[] terzo = {4,10,12,5,11,6,3,16,21,25,13,19,14,22,24,7,23,20,18,15,0,8,1,17,2,9};
            Codificatore c = new Codificatore(primo,secondo,terzo,scamb,rifl);
            c.carica("MESSAGGIO");
            int x;
            do {
                x = System.in.read();
                if(x != 10){
                    System.out.println("Read: " + c.traduci((x-65)) + "\n");
                }
            } while (x != 0);
        } catch (IOException ex) {
            Logger.getLogger(Enigma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int[] change(String rotore){
        return null;
    }

}
