package com.saruul;

import java.io.*;


public class FlashcardLoader {
    public static void loadFromFile(String filename, FlashcardManager manager) {
        try (InputStream inputStream = FlashcardLoader.class.getClassLoader().getResourceAsStream(filename)) {

            if (inputStream == null) {
                System.out.println(" Файл олдсонгүй: " + filename);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String question = parts[0].trim();
                    String answer = parts[1].trim();
                    manager.addFlashcard(question, answer);
                }
            }

            System.out.println(" Картууд амжилттай ачаалагдлаа!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
