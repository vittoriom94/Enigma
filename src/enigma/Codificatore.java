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
    Rotore primo;
    Rotore secondo;
    Rotore terzo;
    Riflettore riflettore;
    Scambiatore scambiatore;
    
    public Codificatore(int[] first, int[] second, int[] third, int[] scambiatore, int[] riflettore,int i,int j,int k){
        this.i = i;
        this.j = j;
        this.k=k;
        primo = new Rotore(first);
        secondo = new Rotore(second);
        terzo = new Rotore(third);
        this.scambiatore = new Scambiatore(scambiatore);
        this.riflettore = new Riflettore(riflettore);
        
        System.out.println(primo.toString()+"\n"+secondo.toString()+"\n"+terzo.toString());
        
    }
    
    public String carica(String messaggio){
        /*
        for (int z = 0;z<12;z++){
                
            primo.shift();
        }
        for (int z = 0;z<5;z++){
            secondo.shift();
        }
        for (int z = 0;z<24;z++){
            terzo.shift();
        }*/
        String codifica = "";
        for(char c : messaggio.toCharArray()){
            if (c>=65 && c<=90){
                int x = this.traduci((c-65));
                codifica = codifica + (char)(x+65);
            } else {
                codifica = codifica + (char)c;
            }
        }
        System.out.println("Messaggio iniziale: " + messaggio + "\n" + "Codifica: " + codifica);
        return codifica;
    }
    
    public  int traduci(int v){
     System.out.println("valore iniziale " + v);
     
     // SOMMARE A i LO SPOSTAMENTO INIZIALE
     controlla_rotazione();
     v = scambiatore.getValueFront(v);
     v = primo.getValueFront(v,i);
     v = secondo.getValueFront(v,j);
     v = terzo.getValueFront(v,k);
     v = riflettore.getValueFront(v);
     v = terzo.getValueBack(v,k);
     v = secondo.getValueBack(v,j);
     v = primo.getValueBack(v,i);
     v = scambiatore.getValueFront(v);
  
     /*
     
     v = scambiatore.getValueFront(v);
     v = primo.getValueFront(v);
     v = secondo.getValueFront(v);
     v = terzo.getValueFront(v);
     v = riflettore.getValueFront(v);
     v = terzo.getValueBack(v);
     v = secondo.getValueBack(v);
     v = primo.getValueBack(v);
     v = scambiatore.getValueFront(v);
     */
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
        
    }
    public void reset(int i, int j,int k){
        this.i = i;
        this.j = j;
        this.k = k;
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
