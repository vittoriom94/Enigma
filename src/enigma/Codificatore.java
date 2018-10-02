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
    Rotore primo = new Rotore(v);
    Rotore secondo = new Rotore(v);
    Rotore terzo = new Rotore(v);
    Rotore riflettore = new Rotore(v);
    Rotore scambiatore = new Rotore(v);
    
    public Codificatore(){
        i = j = k = 0;
        
    }
    public  int traduci(int v){
     v = scambiatore.getValue(v);
     v = primo.getValue(v);
     v = secondo.getValue(v);
     v = terzo.getValue(v);
     v = riflettore.getValue(v);
     v = terzo.getValue(v);
     v = secondo.getValue(v);
     v = primo.getValue(v);
     v = scambiatore.getValue(v);
     controlla_rotazione();
     return v;
    }
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
            }
        }
        
    }
    
}
