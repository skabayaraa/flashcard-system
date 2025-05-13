const readline = require("readline");
const fs = require("fs");

// Flashcard класс
class Flashcard {
    constructor(question, answer) {
        this.question = question;
        this.answer = answer;
        this.correctCount = 0;  
        this.mistakes = 0;      
    }
}

// Flashcard менежер
class FlashcardManager {
    constructor() {
        this.cards = [];
    }

    addFlashcard(question, answer) {
        this.cards.push(new Flashcard(question, answer));
    }

    getFlashcards() {
        return this.cards;
    }
}

// Буруу хариулсан картуудыг эхэнд гаргах
class RecentMistakesFirstSorter {
    sortCards(cards) {
        cards.sort((a, b) => b.mistakes - a.mistakes);
    }
}

// Амжилтыг шалгах систем
class Achievements {
    static checkAchievements(cards) {
        let allCorrect = cards.every(card => card.correctCount > 0 && card.mistakes === 0);
        let repeatedCard = cards.some(card => card.mistakes > 5);
        let confident = cards.some(card => card.correctCount >= 3);

        if (allCorrect) console.log(" CORRECT - Бүх карт зөв хариулсан!");
        if (repeatedCard) console.log(" REPEAT - 5-аас олон удаа буруу хариулсан!");
        if (confident) console.log(" CONFIDENT - Нэг картад дор хаяж 3 удаа зөв хариулсан!");
    }
}

// CLI тохиргоо
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const manager = new FlashcardManager();
const sorter = new RecentMistakesFirstSorter();

// Файлаас карт ачаалах функц
function loadCardsFromFile(filename) {
    const data = fs.readFileSync(filename, "utf8").split("\n");
    data.forEach(line => {
        const [question, answer] = line.split(",");
        if (question && answer) {
            manager.addFlashcard(question.trim(), answer.trim());
        }
    });
}

// Flashcard тоглуулах функц
function startFlashcards() {
    let cards = manager.getFlashcards();
    sorter.sortCards(cards);

    function askQuestion(index) {
        if (index >= cards.length) {
            console.log(" Тест дууслаа!");
            Achievements.checkAchievements(cards);
            rl.close();
            return;
        }

        const card = cards[index];
        rl.question(`${card.question}: `, (userAnswer) => {
            if (userAnswer.toLowerCase() === card.answer.toLowerCase()) {
                console.log(" Зөв!");
                card.correctCount++;
            } else {
                console.log(` Буруу! Зөв хариулт: ${card.answer}`);
                card.mistakes++;
            }
            askQuestion(index + 1);
        });
    }

    askQuestion(0);
}

// CLI эхлүүлэх функц
function startCLI() {
    console.log("📖 Flashcard систем эхэллээ.");
    rl.question("📂 Картуудын файлын нэрийг оруулна уу (жишээ нь: cards.txt): ", (filename) => {
        loadCardsFromFile(filename);
        startFlashcards();
    });
}

// Программыг ажиллуулах
startCLI();
