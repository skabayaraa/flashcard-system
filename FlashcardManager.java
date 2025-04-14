package flashcard;

import java.util.*;

public class FlashcardManager {
    private List<Flashcard> flashcards = new ArrayList<>();

    // Карт нэмэх
    public void addFlashcard(String question, String answer) {
        flashcards.add(new Flashcard(question, answer));
    }

    // Бүх картуудыг авах
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }
}
