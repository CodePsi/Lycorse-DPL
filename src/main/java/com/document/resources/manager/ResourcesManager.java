package com.document.resources.manager;

import com.document.Main;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class ResourcesManager {
    public static String getResourceFilepath(String filepath) {
        URL url = getResourceURL(filepath);
        String resourceFilepath = url.getFile();
        if (resourceFilepath.charAt(0) == '/') {
            return resourceFilepath.substring(1);
        }

        return resourceFilepath;
    }

    public static URL getResourceURL(String filepath) {
        return Objects.requireNonNull(Main.class.getClassLoader().getResource(filepath));
    }

    public static File getResourceFile(String filepath) {
        return new File(getResourceFilepath(filepath));
    }

    public static InputStream getResourceInputStream(String filepath) {
        return Main.class.getClassLoader().getResourceAsStream(filepath);
    }
}
