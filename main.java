package flashcard;

import java.io.*;
import java.util.*;

public class Main {
    private static FlashcardManager manager = new FlashcardManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("📖 Flashcard систем эхэллээ.");
        System.out.print("📂 Картуудын файлын нэрийг оруулна уу (жишээ: cards.txt): ");
        String filename = scanner.nextLine();
        loadCardsFromFile(filename);
        startFlashcards();
    }

    private static void loadCardsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    manager.addFlashcard(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Файл уншихад алдаа гарлаа: " + e.getMessage());
        }
    }

    private static void startFlashcards() {
        List<Flashcard> cards = manager.getFlashcards();
        new RecentMistakesFirstSorter().sortCards(cards);

        for (Flashcard card : cards) {
            System.out.print(card.question + ": ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(card.answer)) {
                System.out.println("✅ Зөв!");
                card.correctCount++;
            } else {
                System.out.println("❌ Буруу! Зөв хариулт: " + card.answer);
                card.mistakes++;
            }
        }

        System.out.println("✅ Тест дууслаа!");
        Achievements.checkAchievements(cards);
    }
}
