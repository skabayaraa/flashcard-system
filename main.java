package flashcard;

import java.io.*;
import java.util.*;

import flashcard.FlashcardManager;
import flashcard.Flashcard;
import flashcard.RecentMistakesFirstSorter;

public class Main {
    private static FlashcardManager manager = new FlashcardManager();
    private static boolean invertCards = false;
    private static String order = "random";
    private static int repetitions = 1;

    public static void main(String[] args) {
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            printHelp();
            return;
        }

        String filename = args[0];
        parseOptions(Arrays.copyOfRange(args, 1, args.length));
        loadCardsFromFile(filename);
        startFlashcards();
    }

    private static void printHelp() {
        System.out.println("flashcard <cards-file> [options]");
        System.out.println("--help                      : Тусламжийн мэдээлэл харуулах");
        System.out.println("--order <order>             : Зохион байгуулалтын төрөл (random, worst-first, recent-mistakes-first)");
        System.out.println("--repetitions <num>         : Нэг картыг хэдэн удаа зөв хариулах шаардлагатай");
        System.out.println("--invertCards               : Картын асуулт, хариултыг солино");
    }

    private static void parseOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            switch (options[i]) {
                case "--order":
                    if (i + 1 < options.length) {
                        order = options[++i];
                        if (!order.equals("random") && !order.equals("worst-first") && !order.equals("recent-mistakes-first")) {
                            System.out.println(" Алдаа: Буруу order утга. Зөв утгууд: random, worst-first, recent-mistakes-first");
                            System.exit(1);
                        }
                    } else {
                        System.out.println(" Алдаа: --order дараа утга байх ёстой.");
                        System.exit(1);
                    }
                    break;
                case "--repetitions":
                    if (i + 1 < options.length) {
                        try {
                            repetitions = Integer.parseInt(options[++i]);
                        } catch (NumberFormatException e) {
                            System.out.println(" Алдаа: repetitions тоо байх ёстой.");
                            System.exit(1);
                        }
                    } else {
                        System.out.println(" Алдаа: --repetitions дараа тоо байх ёстой.");
                        System.exit(1);
                    }
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    System.out.println(" Танигдаагүй сонголт: " + options[i]);
                    System.exit(1);
            }
        }
    }

    private static void loadCardsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    manager.addFlashcard(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println(" Файл уншихад алдаа гарлаа: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void startFlashcards() {
        List<Flashcard> cards = manager.getFlashcards();

        switch (order) {
            case "random":
                Collections.shuffle(cards);
                break;
            case "worst-first":
                cards.sort(Comparator.comparingInt(card -> -card.mistakes));
                break;
            case "recent-mistakes-first":
                new RecentMistakesFirstSorter().sortCards(cards);
                break;
        }

        Scanner scanner = new Scanner(System.in);
        for (Flashcard card : cards) {
            int correctStreak = 0;
            while (correctStreak < repetitions) {
                String question = invertCards ? card.answer : card.question;
                String expected = invertCards ? card.question : card.answer;

                System.out.print(question + ": ");
                String userAnswer = scanner.nextLine();

                if (userAnswer.equalsIgnoreCase(expected)) {
                    System.out.println(" Зөв!");
                    card.correctCount++;
                    correctStreak++;
                } else {
                    System.out.println(" Буруу! Зөв хариулт: " + expected);
                    card.mistakes++;
                    correctStreak = 0;
                }
            }
        }

        System.out.println(" Бүх карт амжилттай хариуллаа!");
        Achievements.checkAchievements(cards);
    }
}
