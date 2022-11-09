package utils.commonutils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Parsing {

    public static int getValue(String s) {
        return Integer.parseInt(s.replaceAll("[^0-9]", ""));
    }
}
