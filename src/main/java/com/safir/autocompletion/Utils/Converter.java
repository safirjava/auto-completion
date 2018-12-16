package com.safir.autocompletion.Utils;

import java.util.List;

/**
 * Utility class for Converting One form of date to other form.
 */
public class Converter {

    /**
     * Convert List of String to Multi Line String.
     * @param list Take list of String as input.
     * @return Return multi line string, return null if list is empty or null.
     */
    public static String listToMultiLineString(List<String> list){
       if(list == null || list.size () < 1){
           return null;
       }
       StringBuffer convertedString = new StringBuffer ();
       list.forEach ((n) -> convertedString.append (n).append ("\n"));
       return convertedString.toString ();
    }
}
