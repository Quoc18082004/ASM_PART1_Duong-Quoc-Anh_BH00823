import java.util.*;

public class StudentManagement {
    private Stack<Student> students = new Stack<>(); // Stack stores the list of students

    // Add a student to the Stack
    public void addStudent(String id, String name, double marks) {
        students.push(new Student(id, name, marks)); // Add to the top of the Stack
        System.out.println("Student added successfully!");
    }

    // Edit a student's information based on ID
    public void editStudent(String id, String newName, double newMarks) {
        Stack<Student> tempStack = new Stack<>();
        boolean found = false;

        // Search and edit in the Stack
        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                found = true;
            }
            tempStack.push(student);
        }

        // Restore the original Stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (found) {
            System.out.println("Student edited successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Delete a student from the Stack based on ID
    public void deleteStudent(String id) {
        Stack<Student> tempStack = new Stack<>();
        boolean found = false;

        // Find and delete the student
        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                System.out.println("Deleted student: " + student);
                found = true;
            } else {
                tempStack.push(student);
            }
        }

        // Restore the original Stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        if (!found) {
            System.out.println("Student not found!");
        }
    }

    // Display the list of students in the Stack
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        Stack<Student> tempStack = new Stack<>();
        System.out.println("Displaying all students:");

        // Iterate through the Stack
        while (!students.isEmpty()) {
            Student student = students.pop();
            System.out.println(student);
            tempStack.push(student);
        }

        // Restore the original Stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }
    }

    // Sort students by marks using Bubble Sort
    public void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        Stack<Student> tempStack = new Stack<>();
        boolean swapped;

        // Convert the Stack to a list for easier processing with Bubble Sort
        List<Student> studentList = new ArrayList<>();
        while (!students.isEmpty()) {
            studentList.add(students.pop());
        }

        // Perform Bubble Sort on the list
        int n = studentList.size();
        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (studentList.get(i).getMarks() < studentList.get(i + 1).getMarks()) {
                    // Swap if order is wrong
                    Student temp = studentList.get(i);
                    studentList.set(i, studentList.get(i + 1));
                    studentList.set(i + 1, temp);
                    swapped = true;
                }
            }
            n--; // Reduce the loop size after each pass
        } while (swapped);

        // Push the sorted list back to the Stack
        for (int i = studentList.size() - 1; i >= 0; i--) {
            students.push(studentList.get(i));
        }

        System.out.println("Students sorted successfully");
    }

    // Search for a student by ID
    public Student searchStudent(String id) {
        Stack<Student> tempStack = new Stack<>();
        Student foundStudent = null;

        // Search for the student in the Stack
        while (!students.isEmpty()) {
            Student student = students.pop();
            if (student.getId().equals(id)) {
                foundStudent = student;
            }
            tempStack.push(student);  // Save the student in the temporary Stack
        }

        // Restore the original Stack
        while (!tempStack.isEmpty()) {
            students.push(tempStack.pop());
        }

        return foundStudent;  // Return the student if found, null if not
    }
}




