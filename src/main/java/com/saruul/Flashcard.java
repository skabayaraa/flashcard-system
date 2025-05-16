package com.saruul;


public class Flashcard {
    String question;
    private String answer;
    int correctCount = 0;
    private int correctAnswers;
    private int wrongAnswers;
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