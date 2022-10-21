package nl.haaientanden.eindopdrachtbackendtandartspraktijk.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class UtilityMethodes {
    public static String getErrorMessage(BindingResult bindingResult) {

        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getField() + ": ");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append("\n");
        }
        return (stringBuilder.toString());
    }
}
