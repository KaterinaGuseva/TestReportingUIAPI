package utils.commonutils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CreateString {

    public static String getString (String...args) {
        return String.join("", args);
    }
}
