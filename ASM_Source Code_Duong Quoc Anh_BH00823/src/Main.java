import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();
        System.out.println("Automatically creating " + n + " random students...");

        String[] randomNames = {"Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "Elijah", "Sophia", "William", "Isabella"};

        for (int i = 0; i < n; i++) {
            String id = String.format("ID%04d", i + 1);
            String name = randomNames[random.nextInt(randomNames.length)];
            double marks = Math.round(random.nextDouble() * 100 * 100.0) / 100.0;
            management.addStudent(id, name, marks);
        }

        System.out.println("Successfully created " + n + " random students.");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add student");
            System.out.println("2. Edit student information");
            System.out.println("3. Delete student");
            System.out.println("4. Display all students");
            System.out.println("5. Sort students (Selection Sort)");
            System.out.println("6. Sort students (Merge Sort)");
            System.out.println("7. Compare Sorting Algorithms");
            System.out.println("8. Exit");
            System.out.print("Choose an operation (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student marks: ");
                    double marks = scanner.nextDouble();
                    management.addStudent(id, name, marks);
                    break;

                case 2:

                    System.out.print("Enter student ID to edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new marks: ");
                    double newMarks = scanner.nextDouble();
                    management.editStudent(editId, newName, newMarks);
                    break;

                case 3:

                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine();
                    management.deleteStudent();
                    break;

                case 4:

                    management.displayStudents();
                    break;

                case 5:

                    System.out.println("\n--- Selection Sort ---");
                    management.sortStudentsSelection();
                    break;

                case 6:
                    // Sort students using Merge Sort
                    System.out.println("\n--- Merge Sort ---");
                    management.mergeSortWrapper();
                    break;


                case 7:

                    System.out.println("\n--- Comparing Sorting Algorithms ---");


                    long selectionStart = System.nanoTime();
                    management.sortStudentsSelection();
                    long selectionEnd = System.nanoTime();
                    long selectionTime = selectionEnd - selectionStart;


                    long mergeStart = System.nanoTime();
                    management.mergeSortWrapper();
                    long mergeEnd = System.nanoTime();
                    long mergeTime = mergeEnd - mergeStart;

                    System.out.printf("Selection Sort completed in %d ns\n", selectionTime);
                    System.out.printf("Merge Sort completed in %d ns\n", mergeTime);


                    if (selectionTime < mergeTime) {
                        System.out.println("Selection Sort was faster.");
                    } else if (mergeTime < selectionTime) {
                        System.out.println("Merge Sort was faster.");
                    } else {
                        System.out.println("Both sorts took the same time.");
                    }
                    break;

                case 8:
                    // Exit
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
        scanner.close();
    }
}
