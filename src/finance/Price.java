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
        System.out.println(cent + " Value of cent");
        float f = (float)(cent)/(float) 100;
        System.out.println(f + "value in float");
        return String.format("%.02f", f);
    }
    
    public static int toCent(String text){
        float f = Float.parseFloat(text);
        return (int)(f*100);
    }
}
