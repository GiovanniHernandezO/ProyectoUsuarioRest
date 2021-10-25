package com.ejemplo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static boolean validator(String strValidar, String expresion) {
        if (strValidar != null && !"".equalsIgnoreCase(strValidar)) {
            final Pattern REGEX = Pattern.compile(expresion, Pattern.CASE_INSENSITIVE);
            Matcher matcher = REGEX.matcher(strValidar);
            return matcher.find();
        } else {
            return false;
        }
    }
}
