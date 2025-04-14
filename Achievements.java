package flashcard;

import java.util.*;

public class Achievements {
    // Картуудын амжилтыг шалгах
    public static void checkAchievements(List<Flashcard> cards) {
        int maxCorrect = 0;
        for (Flashcard card : cards) {
            if (card.correctCount > maxCorrect) {
                maxCorrect = card.correctCount;
            }
        }
        System.out.println("Дээд амжилт: " + maxCorrect + " зөв хариулт");
    }
}
