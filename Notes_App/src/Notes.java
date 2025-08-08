import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Notes{

    private static final String NOTES_FILE_PATH = "notes.txt"; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File notesFile = new File(NOTES_FILE_PATH);
        while (true) {
            System.out.println("\n=== Notes App ===");
            System.out.println("1. Write a Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    writeNote(scanner, notesFile);
                    break;
                case 2:
                    readNotes(notesFile);
                    break;
                case 3:
                    System.out.println("Exiting Notes App.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void writeNote(Scanner scanner, File file) {
        try {
            System.out.print("Enter your note: ");
            String note = scanner.nextLine();

            FileWriter writer = new FileWriter(file, true); // append mode
            writer.write(note + System.lineSeparator());
            writer.close();

            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note.");
            e.printStackTrace();
        }
    }

    private static void readNotes(File file) {
        if (!file.exists()) {
            System.out.println("No notes found.");
            return;
        }

        System.out.println("\n=== Your Notes ===");
        try {
            FileReader reader = new FileReader(file);
            int ch;
            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the notes.");
            e.printStackTrace();
        }
    }
}
