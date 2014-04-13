/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package finance;

/**
 *
 * @author Elitward
 */
public class Price {
    public static String toText(int cent){
        float f = (float)(cent/100);
        return String.format("%f", f);
    }
    
    public static int toCent(String text){
        float f = Float.parseFloat(text);
        return (int)(f*100);
    }
}
