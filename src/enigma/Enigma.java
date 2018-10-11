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
    //esempio gif usa refl B, rotore 1,
    public static void main(String[] args) {

        /*
            String s1 = "EJ OY IV AQ KW FX MT PS LU BD"; //plug
            //String s1 = "DO";
            String s3 = "AA";
           String s2 = "IU AS DV GL FT OX EZ CH MR KN BQ PW JY";
           String stampa = "";
           String s = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
           for(char c : s.toCharArray()){
               stampa = stampa + (int)(c-65) + ",";
           }
           int[] scamb = new int[26];
           int[] rifl = new int[26];
           Arrays.fill(scamb, -1);
           for(String c: s3.split(" ")){
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
            int[] Irot = {4,10,12,5,11,6,3,16,21,25,13,19,14,22,24,7,23,20,18,15,0,8,1,17,2,9};
            int[] IIrot = {0,9,3,10,18,8,17,20,23,1,11,7,22,19,12,2,16,6,25,13,15,24,5,21,14,4};
            int[] IVrot = {4,18,14,21,15,25,9,0,24,16,20,8,17,7,23,11,13,5,19,6,10,3,2,12,22,1};
            int[] brefl = {24,17,20,7,16,18,11,3,15,23,13,6,14,10,12,8,4,1,5,25,2,22,21,9,0,19};*/
        LetturadaFile rot = new LetturadaFile();
        rot.lettura();
        String[] rotori = rot.getRotori();

        String[] start = rot.getStartPosition().split(" ");
        String scambString = rot.getScambiatore();
        String riflString = rot.getRiflettore();

        Codificatore c = new Codificatore(change(rotori[0]), change(rotori[1]), change(rotori[2]), getScambiatoreInt(scambString), getRiflettoreInt(riflString), Integer.parseInt(start[0]), Integer.parseInt(start[0]), Integer.parseInt(start[0]));
        //c.carica("VYCHUNQLFW RYEXHPXJSS XHHZLPHPAH KITSEOTGWM ZHDYHTSIER WBYKRXTTJP QMOOMHJCTV BUARKARNEQ LSATIUBMBJ");
        
            boolean unknown;
            do {
                unknown = false;
                System.out.println("\nScegliere tipo lettura:\nPremere 1 per lettura da messaggio.txt\nPremere 2 per lettura da tastiera.\nPremere 0 per terminare.");
                Scanner input = new Scanner(System.in);
                String s = input.nextLine();
               
                switch (s) {
                    case "1":
                        System.out.println("Messaggio originale: "+ rot.getMessaggio());
                        System.out.println("Messagio codificato: " + c.carica(rot.getMessaggio()));
                        break;
                    //if(x != 10){
                    //    System.out.println("Read: " + c.traduci((x-65)) + "\n");
                    //}
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
                        System.out.println("esci");
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
            System.out.println("scamb " + c);
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
        System.out.println("change " + rotore);
        for (char c : rotore.toCharArray()) {
            rotoreInt[i] = (int) (c - 65);
            i++;
        }
        return rotoreInt;
    }

}
