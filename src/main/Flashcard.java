package com.saruul.flashcard;


public class Flashcard {
    private String question;
    private String answer;
    private int correctCount = 0;
    private int mistakes = 0;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void incrementCorrectAnswers() {
        correctAnswers++;
    }

    public void incrementWrongAnswers() {
        wrongAnswers++;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }
}