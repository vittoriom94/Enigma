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
    int i_start,j_start,k_start;
    int i,j,k;
    Rotore primo;
    Rotore secondo;
    Rotore terzo;
    DiscoEsterno riflettore;
    DiscoEsterno scambiatore;
    
    public Codificatore(int[] first, int[] second, int[] third, int[] scambiatore, int[] riflettore,int i,int j,int k){
        this.i_start = i;
        this.j_start = j;
        this.k_start=k;
        this.i=0;
        this.j=0;
        this.k=0;
        primo = new Rotore(first);
        secondo = new Rotore(second);
        terzo = new Rotore(third);
        this.scambiatore = new DiscoEsterno(scambiatore);
        this.riflettore = new DiscoEsterno(riflettore);
        

        
    }
    
    public String carica(String messaggio){
        
        String codifica = "";
        for(char c : messaggio.toCharArray()){
            if (c>=65 && c<=90){
                int x = this.traduci((c-65));
                codifica = codifica + (char)(x+65);
            } else {
                codifica = codifica + (char)c;
            }
        }
        return codifica;
    }
    
    public  int traduci(int v){
     
     // SOMMARE A i LO SPOSTAMENTO INIZIALE
     controlla_rotazione();
     v = scambiatore.getValueFront(v);
     v = primo.getValueFront(v,i+i_start);
     v = secondo.getValueFront(v,j+j_start);
     v = terzo.getValueFront(v,k+k_start);
     v = riflettore.getValueFront(v);
     v = terzo.getValueBack(v,k+k_start);
     v = secondo.getValueBack(v,j+j_start);
     v = primo.getValueBack(v,i+i_start);
     v = scambiatore.getValueFront(v);
 
    
     return v;
    }
    
    public void controlla_rotazione(){
        i++;

        if ( i == 26 ){
            i = 0;
            j++;
            if(j == 26){
                j = 0;
                k++;
                if(k==26){
                    k=0;
                }
            }
        }
        
    }
    public void reset(int i, int j,int k){
        this.i = 0;
        this.j = 0;
        this.k = 0;
    }
}
