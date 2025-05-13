public class flashcard {
    private String question;
    private String answer;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;

    public FlashCard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void incrementCorrectAnswers() {
        correctAnswers++;
    }

    public void incrementWrongAnswers() {
        wrongAnswers++;
    }

    public boolean isCorrect() {
        return correctAnswers > 0;
    }

    public boolean isConfident() {
        return correctAnswers >= 3;
    }

    public boolean isRepeat() {
        return correctAnswers > 5;
    }
}
