/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
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

        
        LetturadaFile rot = new LetturadaFile();
        rot.lettura();
        String[] rotori = rot.getRotori();

        String[] start = rot.getStartPosition().split(" ");
        String scambString = rot.getScambiatore();
        String riflString = rot.getRiflettore();

        Codificatore c = new Codificatore(change(rotori[0]), change(rotori[1]), change(rotori[2]), getScambiatoreInt(scambString), getRiflettoreInt(riflString), Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2]));
        
            boolean unknown;
            do {
                unknown = false;
                System.out.println("Scegliere tipo lettura:\nPremere 1 per lettura da messaggio.txt\nPremere 2 per lettura da tastiera.\nPremere 0 per terminare.");
                Scanner input = new Scanner(System.in);
                String s = input.nextLine();
               
                switch (s) {
                    case "1":
                        System.out.println("Messaggio originale: "+ rot.getMessaggio());
                        System.out.println("Messagio codificato: " + c.carica(rot.getMessaggio()));
                        c.reset(Integer.parseInt(start[0]), Integer.parseInt(start[0]), Integer.parseInt(start[0]));
                        unknown = true;
                        break;
                    
                    case "2":
                        System.out.println("\nInserire il messaggio\n");
                        Scanner inputMessage = new Scanner(System.in);
                        String sMessage = inputMessage.nextLine();
                        System.out.println("Messaggio originale: "+ sMessage);
                        System.out.println("Messagio codificato: " + c.carica(sMessage));
                        c.reset(Integer.parseInt(start[0]), Integer.parseInt(start[0]), Integer.parseInt(start[0]));
                        unknown = true;
                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("Comando non riconosciuto");
                        unknown = true;
                        break;
                }
            } while (unknown);


    }

    private static int[] getRiflettoreInt(String riflettore) {
        int[] rifl = new int[26];
        for (int i = 0; i < riflettore.length(); i = i + 2) {
            String c = riflettore.substring(i, i + 2);
            rifl[(int) (c.charAt(0) - 65)] = (int) (c.charAt(1) - 65);
            rifl[(int) (c.charAt(1) - 65)] = (int) (c.charAt(0) - 65);
        }
        return rifl;
    }

    private static int[] getScambiatoreInt(String scambiatore) {
        int[] scamb = new int[26];
        Arrays.fill(scamb, -1);

        for (int i = 0; i < scambiatore.length(); i = i + 2) {
            String c = scambiatore.substring(i, i + 2);
            scamb[(int) (c.charAt(0) - 65)] = (int) (c.charAt(1) - 65);
            scamb[(int) (c.charAt(1) - 65)] = (int) (c.charAt(0) - 65);
        }
        for (int x = 0; x < scamb.length; x++) {
            if (scamb[x] == -1) {
                scamb[x] = x;
            }
        }
        return scamb;
    }

    private static int[] change(String rotore) {
        int[] rotoreInt = new int[26];
        int i = 0;
        for (char c : rotore.toCharArray()) {
            rotoreInt[i] = (int) (c - 65);
            i++;
        }
        return rotoreInt;
    }

}
