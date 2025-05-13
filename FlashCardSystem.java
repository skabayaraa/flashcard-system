public class FlashCardSystem {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("--help")) {
            showHelp();
            return;
        }

        String order = "random"; // default order
        int repetitions = 1; // default repetitions
        boolean invertCards = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--order":
                    order = args[++i];
                    break;
                case "--repetitions":
                    repetitions = Integer.parseInt(args[++i]);
                    break;
                case "--invertCards":
                    invertCards = true;
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
                    showHelp();
                    return;
            }
        }

        // Process the flashcards with the given options
        System.out.println("Order: " + order);
        System.out.println("Repetitions: " + repetitions);
        System.out.println("Invert Cards: " + invertCards);
    }

    private static void showHelp() {
        System.out.println("Usage: flashcard <cards-file> [options]");
        System.out.println("Options:");
        System.out.println("--help                   Show this help message");
        System.out.println("--order <order>          Order type (random, worst-first, recent-mistakes-first)");
        System.out.println("--repetitions <num>      Number of repetitions for each card");
        System.out.println("--invertCards            Swap question and answer cards");
    }
}
