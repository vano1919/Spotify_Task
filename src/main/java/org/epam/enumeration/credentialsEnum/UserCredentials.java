package org.epam.enumeration.credentialsEnum;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum UserCredentials {

    EMAIL_USERNAME(""),
    PASSWORD(""),
    NAME ("");
    private String value;

    UserCredentials(String value) {
        this.value = value;
    }

    static {
        Map<String, String> credentialsMap = loadCredentials();

        EMAIL_USERNAME.value = credentialsMap.getOrDefault("EMAIL_USERNAME", "DEFAULT_EMAIL");
        PASSWORD.value = credentialsMap.getOrDefault("PASSWORD", "DEFAULT_PASSWORD");
        NAME.value = credentialsMap.getOrDefault("NAME", "DEFAULT_USERNAME").toLowerCase ();

    }

    private static Map<String, String> loadCredentials() {

        Map<String, String> credentialsMap = new HashMap<>();
        File file = new File("src/main/resources/credentials.txt");

        if (!file.exists()) {
            System.err.println("credentials.txt not found!");
            return credentialsMap;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String val = parts[1].trim().replace("\"", "");
                    credentialsMap.put(key, val);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read credentials.txt due to an error:");
            e.printStackTrace();
        }

        return credentialsMap;
    }
}
