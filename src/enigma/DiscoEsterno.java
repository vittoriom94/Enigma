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
public class DiscoEsterno extends Disco{
    
    public DiscoEsterno(int[] values) {
        for(int i=0;i<values.length;i++){
            rotore_front[i]=values[i];
        }
    }
}
