package enigma;


import java.io.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import java.util.HashSet;
import java.lang.Object;

public class LetturadaFile {

    static String rot_path = "/rotori.txt";
    static String conf_path = "/configurazione.txt";
    static String msg_path = "/messaggio.txt";
    String messaggio;
    String filename;
    int i = 0;
    int p = 0;
    int rot = 0;
    int ROTORI = 26;
    static int n_par = 4;
    static int n_rot = 3;
    String[] rotori = new String[n_rot];
    String[] res = new String[n_par];
    boolean validator = false;

    public String getMessaggio(){
        return messaggio;
    }
    public String[] getRotori(){
        System.out.println("getRotori " + rotori[0]);
        return rotori;
    }
    public String getStartPosition(){
        return res[1];
    }
    public String getScambiatore(){
        return res[2];
    }
    public String getRiflettore(){
        return res[3];
    }
    //Legge i due file
    public String[] readFile(String path) {

        BufferedReader b_r = null;
        FileReader f_r = null;
        int[] conf = new int[n_rot];
        
        try {														//-------------------------------------------
            String absolutePath = new File("").getAbsolutePath();
            
            f_r = new FileReader(absolutePath.concat(path));
            b_r = new BufferedReader(f_r);
            //Legge e valida per linea il file 
            String line;
            //rotori.txt restituendo true se ben
            if (path == rot_path) {
                String prefix = "Rotore#";
                filename = "rotori.txt";										//formato, false altrimenti

                while ((line = b_r.readLine()) != null) {
                    if (validateRotLine(line, prefix) == true) {
                        System.out.println(line);
                        i++;
                    }

                }													//-------------------------------------------

                i = 0;

            } else if (path == conf_path) //-------------------------------------------
            {
                i = 0;
                filename = "configurazione.txt";

                while ((line = b_r.readLine()) != null) //Legge e valida per linea il file 
                {

                    if (!line.isEmpty()) //configurazione.txt restituendo i valori letti
                    {
                        res[i] = validateConfLine(line);
                        //se corretti, in un vettore di	stringhe,					
                        i++;
                    }
                    //altrimenti lanciando eccezione.
                }

                i = 0;

                //-------------------------------------------
            } else if (path.equals(msg_path)){
                String msg = "";
                while ((line = b_r.readLine()) != null) {
                    System.out.println("line " + line);
                    msg=msg+line;

                }	
                String[] msgReturn = new String[1];
                msgReturn[0] = msg;
                return msgReturn;
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (b_r != null) {
                    b_r.close();
                }

                if (f_r != null) {
                    f_r.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        i = 0;

        System.out.println("\n");

        return res;

    }

    //valida il file rotori.txt
    public boolean validateRotLine(String line, String prefix) {
        System.out.println("validateRotLine " + line);
        LinkedList<Character> vett = new LinkedList<Character>();
        int p = 0;
        int j = 0;

        if (i % 2 == 0 && line.startsWith(prefix) == false) {
            rot++;
            System.out.println("\nFile rotori.txt non validato!");
            System.out.println("\nLa stringa del rotore n " + rot + " è mal formata!");
            System.out.println("\nline" + line);

            return false;
        } else if (i != 0 && i % 2 != 0) {
            if (line.length() != ROTORI) {
                rot++;
                System.out.println("\nFile rotori.txt non validato!");
                System.out.println("\nIl rotore n " + rot + " contiene un num di caratteri più o meno di 26! " + line.length());
                return false;
            } else {
                vett.clear();
                rot++;
                for (p = 0; p < ROTORI; p++) {
                    if (line.charAt(p) < 64 || line.charAt(p) > 91) {
                        System.out.println("\nFile rotori.txt non validato!");
                        System.out.println("\nIl rotore n " + rot + " contiene caratteri non consentiti!");
                        return false;
                    } else if (vett.contains(line.charAt(p))) {
                        System.out.println("\nFile rotori.txt non validato!");
                        System.out.println("\nIl rotore n " + rot + " contiene caratteri duplicati!");
                        System.out.println(line);
                        return false;
                    } else {
                        vett.add(line.charAt(p));
                    }
                }
            }

        }
        return true;
    }

    //valida il file configurazioni.txt e restituisce i parametri per la configurazione
    public String validateConfLine(String line) {

        String vett = "";
        String s_rotori = "Rotori: ";
        String s_posizione = "Posizione iniziale: ";
        String s_scambiatore = "Scambiatore: ";
        String s_riflettore = "Riflettore: ";

        int[] rotori = new int[n_rot];
        String[] rotori_s;
        int p = 0;
        int i = 0;
        //riga 1 --------
        if (line.startsWith(s_rotori) == true) {
            p = 8;
            vett = line.substring(p);
            rotori_s = vett.split(", ");
            try {
                for (i = 0; i < n_rot; i++) {
                    rotori[i] = Integer.parseInt(rotori_s[i]);
                }

                vett = rotori_s[0].concat(" " + rotori_s[1]).concat(" " + rotori_s[2]);
                validator = true;
            } catch (NumberFormatException e) {
                System.out.println("Stringa dei rotori non contiene tutti numeri!");
                validator = false;
                return "File " + filename + " non validato!";
            }

        } //riga 2 --------
        else if (line.startsWith(s_posizione) == true) {
            p = 20;
            vett = line.substring(p);
            rotori_s = vett.split(", ");

            try {
                for (i = 0; i < n_rot; i++) {
                    rotori[i] = Integer.parseInt(rotori_s[i]);
                }

                vett = rotori_s[0].concat(" " + rotori_s[1]).concat(" " + rotori_s[2]);
                validator = true;

            } catch (NumberFormatException e) {
                System.out.println("Stringa delle posizioni non contiene tutti numeri!");
                validator = false;
                return "File " + filename + " non validato!";

            }

        } //riga 3 ---------
        else if (line.startsWith(s_scambiatore) == true) {
            p = 13;
            vett = line.substring(p);
            String temp = "";

            for (int j = 0; j < vett.length(); j++) {

                if (vett.charAt(j) != ' ') {
                    if (temp.contains(vett.substring(j, j + 1)) == false) {
                        temp = temp.concat(vett.substring(j, j + 1));
                    } else {
                        validator = false;

                        System.out.println("Lo scambiatore contiene caratteri duplicati!");
                        return "File " + filename + " non validato!";

                    }
                }

            }

            vett = temp;
            validator = true;

        } //riga 4 ---------
        else if (line.startsWith(s_riflettore) == true) {
            p = 12;
            vett = line.substring(p);
            String temp = "";
            HashSet h = new HashSet<Integer>();
            for (i = 0; i < 26; i++) {
                h.add(i + 65);
            }

            for (int j = 0; j < vett.length(); j++) {

                if (vett.charAt(j) != ' ') {
                    if (temp.contains(vett.substring(j, j + 1)) == false) {
                        temp = temp.concat(vett.substring(j, j + 1));
                    } else {
                        validator = false;
                        System.out.println("Il riflettore contiene caratteri duplicati!");
                        return "File " + filename + " non validato!";
                    }
                }

            }

            vett = temp;
            validator = true;

            for (int j = 0; j < vett.length(); j++) {

                h.remove((int) vett.charAt(j));

            }

            Object[] myArr = h.toArray();
            int value1 = Integer.parseInt(myArr[0].toString());
            int value2 = Integer.parseInt(myArr[1].toString());

            char val1 = (char) value1;
            char val2 = (char) value2;

            vett = vett + val1 + val2;

        }

        return vett;

    }

    public  String[] configRotors(String path, String param) {
        int i = 0;
        int j = 0;
        int k = 0;

        int dim = (param.length() + 1) / 2;
        int[] rots = new int[dim];
        String[] rotori = new String[dim];

        String temp;
        for (i = 0; i < dim; i++) {
            rots[i] = (int) param.charAt(i * 2) - 48;

        }

        BufferedReader br = null;
        FileReader fr = null;

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            String absolutePath = new File("").getAbsolutePath();
            
            fr = new FileReader(absolutePath.concat(path));
            br = new BufferedReader(fr);

            String line;

            j = 1;
            k = 0;
            while ((line = br.readLine()) != null) {//System.out.println(line);
                for (i = 0; i < dim; i++) {
                    if (j == rots[i] * 2) {
                        rotori[k] = rots[i] + line;
                        k++;
                    }
                }
                j++;
            }

            try {
                for (i = 0; i < dim; i++) {
                    for (j = 0; j < dim; j++) {
                        if (Integer.parseInt(rotori[j].substring(0, 1)) == rots[i]) {
                            temp = rotori[i];
                            rotori[i] = rotori[j];
                            rotori[j] = temp;

                        }
                    }
                }
                for (i = 0; i < dim; i++) {
                    rotori[i] = rotori[i].substring(1);
                }

            } catch (NullPointerException e) {

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null) {
                    br.close();
                }

                if (fr != null) {
                    fr.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        System.out.println("\n");

        return rotori;

    }
    public void lettura(){
        

        String[] param;

        
        this.readFile(rot_path);
        param = this.readFile(conf_path);
        for (int i = 0; i < n_par; i++) {
            System.out.println(param[i]);
        }

        rotori = this.configRotors(rot_path, param[0]);
        for (int i = 0; i < n_rot; i++) {
            System.out.println("Rotore n" + param[0].charAt(i * 2) + " " + rotori[i]);
        }
        messaggio = this.readFile(msg_path)[0];
        System.out.println("Letturadafile messaggio: " + messaggio);
    }
}
