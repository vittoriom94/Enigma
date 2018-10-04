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
public class Codificatore {
    int i,j,k;
    int[] v = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
    Rotore primo;
    Rotore secondo;
    Rotore terzo;
    Riflettore riflettore;
    Scambiatore scambiatore;
    
    public Codificatore(int[] first, int[] second, int[] third, int[] scambiatore, int[] riflettore){
        i = j = k = 0;
        primo = new Rotore(first);
        secondo = new Rotore(second);
        terzo = new Rotore(third);
        this.scambiatore = new Scambiatore(scambiatore);
        this.riflettore = new Riflettore(riflettore);
        
    }
    
    public void carica(String messaggio){
        String codifica = "";
        for(char c : messaggio.toCharArray()){
            if (c != 32){
                int x = this.traduci((c-65));
                codifica = codifica + (char)(x+65);
            } else {
                codifica = codifica + " ";
            }
        }
        System.out.println(codifica);
    }
    
    public  int traduci(int v){
     System.out.println("valore iniziale " + v);
     v = scambiatore.getValueFront(v);
     v = primo.getValueFront(v,i);
     v = secondo.getValueFront(v,j);
     v = terzo.getValueFront(v,k);
     v = riflettore.getValueFront(v);
     v = terzo.getValueBack(v,k);
     v = secondo.getValueBack(v,j);
     v = primo.getValueBack(v,i);
     v = scambiatore.getValueFront(v);
     controlla_rotazione();
     
     System.out.println("valore finale " + v);
     return v;
    }
    public void controlla_rotazione(){
        i++;
        //primo.shift();
        if ( i == 26 ){
            i = 0;
            j++;
            //secondo.shift();
            if(j == 26){
                j = 0;
                //terzo.shift();
                k++;
                if(k==26){
                    k=0;
                }
            }
        }
        
    }/*
    public void controlla_rotazione(){
        i++;
        primo.shift();
        if ( i == 26 ){
            i = 0;
            j++;
            secondo.shift();
            if(j == 26){
                j = 0;
                terzo.shift();
                k++;
                if(k==26){
                    k=0;
                }
            }
        }
        
    }*/
}
