/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.io.IOException;
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
            
            int[] v = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
            
            int[] v2 = {25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
            Codificatore c = new Codificatore(v,v,v,v,v2);
            c.carica("ZABCDEFGHIJKLMNOPQRSTUVWXY PZNPJFHHD QN IINIF");
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
