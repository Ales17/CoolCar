package cz.ales17.auto.storage;

import java.util.Map;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getExtension;

public class FileUtil {

    public static final String ROOT_LOCATION = "/files/";

    public static final Map<String, String> allowedFileTypes = Map.of("jpg", "image/jpeg", "jpeg", "image/jpeg", "png", "image/png");

    public static String newFileName(String originalName) {
        String newBaseName = UUID.randomUUID().toString();
        String extension = getExtension(originalName);
        return String.format("%s.%s", newBaseName, extension);
    }
}
