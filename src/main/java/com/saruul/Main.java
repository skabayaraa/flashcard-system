package com.saruul;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Flashcard app running!");
       

        FlashcardManager manager = new FlashcardManager();
        RecentMistakesFirstSorter sorter = new RecentMistakesFirstSorter();

        // 1. Файлын нэрийг асуух
        System.out.print(" WRITE THE cards.txt ");
        String filename = scanner.nextLine().trim();

        // 2. Картуудыг ачаалах
        FlashcardLoader.loadFromFile(filename, manager);

        // 3. Картуудыг буруу хариултын дагуу эрэмбэлэх
        List<Flashcard> cards = manager.getFlashcards();
        sorter.sortCards(cards);

        // 4. Картуудыг асуух
        for (Flashcard card : cards) {
            System.out.println(" Асуулт: " + card.getQuestion());
            System.out.print(" Хариулт: ");
            String userAnswer = scanner.nextLine().trim();

            if (userAnswer.equalsIgnoreCase(card.getAnswer())) {
                System.out.println(" Зөв!");
                card.incrementCorrectAnswers();
            } else {
                System.out.println(" Буруу! Зөв хариулт: " + card.getAnswer());
                card.incrementWrongAnswers();
                sorter.addWrongAnsweredCard(card); // буруу хариулсан карт бүртгэх
            }

            System.out.println();
        }

        // 5. Амжилтыг шалгах
        Achievements.checkAchievements(cards);

        System.out.println(" Тест дууслаа. Баяр хүргэе!");
        scanner.close();
    }
}
