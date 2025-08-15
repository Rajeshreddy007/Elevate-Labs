import java.util.*;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean askQuestion(Scanner scanner) {
        System.out.println("\n" + questionText);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.print("Your answer: ");
        int answer = scanner.nextInt();
        return answer == correctOption;
    }
}

public class Quiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Which language is used for Android development?", List.of("Python", "Java", "C#", "Swift"), 2));
        questions.add(new Question("Which data structure works on FIFO principle?", List.of("Stack", "Queue", "Tree", "Graph"), 2));
        questions.add(new Question("Who invented Java?", List.of("Bjarne Stroustrup", "James Gosling", "Guido van Rossum", "Dennis Ritchie"), 2));
        questions.add(new Question("What does HTML stand for?", List.of("Hyper Trainer Marking Language", "Hyper Text Markup Language", "High Text Machine Language", "Hyperlinking Text Management Language"), 2));
        questions.add(new Question("Which keyword is used to inherit a class in Java?", List.of("this", "implements", "extends", "super"), 3));
        questions.add(new Question("Which company developed Java?", List.of("Sun Microsystems", "Microsoft", "Google", "IBM"), 1));
        questions.add(new Question("Which loop is guaranteed to execute at least once?", List.of("for", "while", "do-while", "none"), 3));
        questions.add(new Question("Which symbol is used for single-line comments in Java?", List.of("//", "/* */", "#", "--"), 1));
        questions.add(new Question("Which of these is not a Java primitive type?", List.of("int", "float", "boolean", "string"), 4));
        questions.add(new Question("Which method is the entry point of a Java program?", List.of("start()", "main()", "run()", "init()"), 2));

        Collections.shuffle(questions); 

        int score = 0;
        System.out.println("===== Welcome to the Online Quiz =====");
        for (Question q : questions) {
            if (q.askQuestion(scanner)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
            }
        }

        System.out.println("\n===== Quiz Completed! =====");
        System.out.println("Your Score: " + score + " / " + questions.size());
        double percentage = (score * 100.0) / questions.size();
        System.out.printf("Percentage: %.2f%%\n", percentage);

        if (percentage == 100) {
            System.out.println("Excellent! Perfect score!");
        } else if (percentage >= 60) {
            System.out.println("Good effort!");
        } else {
            System.out.println("Keep practicing!");
        }

        scanner.close();
    }
}
