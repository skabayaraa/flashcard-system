package flashcard;

import java.util.*;

public class RecentMistakesFirstSorter {
    // Картуудыг хамгийн сүүлд буруу хариулсан картуудаар эхлүүлэх
    public void sortCards(List<Flashcard> cards) {
        cards.sort(Comparator.comparingInt(card -> -card.mistakes));
    }
}
