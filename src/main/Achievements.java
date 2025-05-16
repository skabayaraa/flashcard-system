package com.saruul;
import java.util.List;
import java.util.ArrayList;

public class Achievements {
    public static void checkAchievements(List<Flashcard> cards) {
        int maxCorrect = 0;
        List<Flashcard> topCards = new ArrayList<>();

        for (Flashcard card : cards) {
            if (card.correctCount > maxCorrect) {
                maxCorrect = card.correctCount;
                topCards.clear();
                topCards.add(card);
            } else if (card.correctCount == maxCorrect) {
                topCards.add(card);
            }
        }

        System.out.println("Дээд амжилт: " + maxCorrect + " зөв хариулт");
        System.out.println("Хамгийн амжилттай карт(ууд):");
        for (Flashcard card : topCards) {
            System.out.println("- " + card.question); 
        }
    }
}
