/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SystemOperations;

import java.util.ArrayList;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class ValidateFields {
    
    public static boolean CheckForNumbersOnly(String inputString)
    {
        
        try
        {
            Double newDouble = Double.parseDouble(inputString);
            return true;

        }catch(Exception e)
        {
            return false;
        }
        
    }
    public static boolean CheckIntegerNumbersOnly(String inputString)
    {
        String regex = "[0-9]+";
        
        return inputString.matches(regex);
    }
    public static boolean ValidatePhoneNumber(String inputPhoneString) {
        return CheckIntegerNumbersOnly(inputPhoneString) && (inputPhoneString.length() <= 10);
    }
    
    public static boolean CheckLettersOnly(String inputString)
    {
        String regex;
        regex = "^[a-zA-Z\\s]+";
        
        return inputString.matches(regex);
    }
    
}
