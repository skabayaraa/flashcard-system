package flashcard;

public class Flashcard {
    public String question;
    public String answer;
    public int correctCount;
    public int mistakes;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.correctCount = 0;
        this.mistakes = 0;
    }
}
