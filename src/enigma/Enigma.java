/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.io.IOException;
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
            Codificatore c = new Codificatore();
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

}
