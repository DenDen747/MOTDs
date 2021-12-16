package com.denesgarda.MOTDs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MOTDs {
    private final String path;

    public String[] MOTDs = null;

    public MOTDs(String path) {
        this.path = path;
    }

    public void reload() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null) {
            MOTDs = Arrays.copyOf(MOTDs, MOTDs.length + 1);
            MOTDs[MOTDs.length - 1] = line;
            line = reader.readLine();
        }
    }

    public static MOTDs returnObject(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return new MOTDs(path);
        } else {
            boolean successful = file.getParentFile().mkdirs() && file.createNewFile();
            if (successful) {
                return new MOTDs(path);
            } else {
                throw new RuntimeException("Failed to create file.");
            }
        }
    }
}
