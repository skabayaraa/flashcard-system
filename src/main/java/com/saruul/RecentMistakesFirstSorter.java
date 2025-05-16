package com.saruul;

import java.util.ArrayList;
import java.util.List;

public class RecentMistakesFirstSorter implements CardOrganizer {
    private List<Flashcard> wrongAnsweredCards;

    public RecentMistakesFirstSorter() {
        this.wrongAnsweredCards = new ArrayList<>();
    }

    public void sortCards(List<Flashcard> cards) {
        throw new UnsupportedOperationException("Unimplemented method 'sortCards'");
    }
    
    @Override
    public List<Flashcard> organizeCards(List<Flashcard> cards) {
        List<Flashcard> organizedCards = new ArrayList<>(cards);
        organizedCards.sort((card1, card2) -> {
            boolean card1Wrong = wrongAnsweredCards.contains(card1);
            boolean card2Wrong = wrongAnsweredCards.contains(card2);
            return card1Wrong == card2Wrong ? 0 : (card1Wrong ? -1 : 1);
        });
        return organizedCards;
    }

    public void addWrongAnsweredCard(Flashcard card) {
        wrongAnsweredCards.add(card);
    }
}
