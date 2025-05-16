package com.saruul;
public class RecentMistakesFirstSorter implements CardOrganizer {
    private List<Flashcard> wrongAnsweredCards;

    public RecentMistakesFirstSorter() {
        this.wrongAnsweredCards = new ArrayList<>();
    }

    @Override
    public List<FlashCard> organizeCards(List<FlashCard> cards) {
        List<FlashCard> organizedCards = new ArrayList<>(cards);
        organizedCards.sort((card1, card2) -> {
            boolean card1Wrong = wrongAnsweredCards.contains(card1);
            boolean card2Wrong = wrongAnsweredCards.contains(card2);
            return card1Wrong == card2Wrong ? 0 : (card1Wrong ? -1 : 1);
        });
        return organizedCards;
    }

    public void addWrongAnsweredCard(FlashCard card) {
        wrongAnsweredCards.add(card);
    }
}
