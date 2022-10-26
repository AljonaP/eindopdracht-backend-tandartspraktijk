package nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;




public class UtilityMethodes {

    public static int lengthDutchZipCode = 6;

    public static String getErrorMessage(BindingResult bindingResult) {

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getField() + ": ");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append("\n");
        }
        return (stringBuilder.toString());
    }

    public static boolean checkPostcode(String zipCode) {
        if (zipCode.length() != lengthDutchZipCode || zipCode.charAt(0) == '0') {
            return false;
        }
        if (zipCode.length() == lengthDutchZipCode) {
            for (int i = 0; i < zipCode.length(); i++) {
                if (i < 4 && Character.isLetter(zipCode.charAt(i))) {
                    return false;
                }
                if (i > 3 && Character.isDigit(zipCode.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
}


