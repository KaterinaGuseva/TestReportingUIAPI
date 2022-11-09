package utils.commonutils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@UtilityClass
public class Base64Converting {

    public static String encodeImageToBase64(String contentPath) {
        try {
            byte[] dataEncode = Files.readAllBytes(Paths.get(contentPath));
            return Base64.getEncoder().encodeToString(dataEncode);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
