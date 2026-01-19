package org.example.passwordmanager;

import java.io.*;

public class Safe_storage {

    public static File getFile() {
        if (!Session.isLoggedIn()) {

            return null;
        }

        User user = Session.getUser();
        String username = user.get_username();

        // Create data directory if it doesn't exist
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            boolean created = dataDir.mkdir();

        }

        File file = new File("data/" + username + "_safe.txt");
        return file;
    }

    public static void loadData(Safe_items vault) {
        File file = getFile();
        if (file == null) {

            return;
        }



        if (!file.exists()) {

            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 3);
                if (parts.length == 3) {
                    vault.add(parts[0], parts[1], parts[2]);
                    count++;

                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void save_data(Safe_items vault) {
        File file = getFile();
        if (file == null) {

            return;
        }



        // Create parent directory if it doesn't exist
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();

        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            int count = 0;
            for (Items item : vault.getItems()) {
                String line = item.getWebsite() + ":" + item.getUsername() + ":" + item.getPassword();
                writer.write(line);
                writer.newLine();
                count++;

            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}