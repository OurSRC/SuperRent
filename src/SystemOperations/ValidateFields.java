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
        String regex = "[0-9]+";
        /*try
        {
            Double newDouble = Double.parseDouble(inputString);
            return true;

        }catch(Exception e)
        {
            return false;
        }*/
        return inputString.matches(regex);
    }
    
}
