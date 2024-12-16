import java.util.Scanner;
import java.util.Random;

public class StudentManagement {
    private StudentStack studentStack;

    public StudentManagement() {
        studentStack = new StudentStack();
    }

    public void addStudent(String id, String name, double marks) {
        if (id == null || id.isEmpty() || name == null || name.isEmpty() || marks < 0) {
            System.out.println("Error: Invalid student details. Please check ID, name, and marks.");
            return;
        }

        // Check for duplicate ID
        StudentStack tempStack = new StudentStack();
        boolean exists = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                exists = true;
            }
            tempStack.push(student);
        }
        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (exists) {
            System.out.println("Error: Duplicate student ID.");
            return;
        }

        Student student = new Student(id, name, marks);
        studentStack.push(student);
        System.out.println("Student added successfully.");
    }

    public void deleteStudent() {
        if (studentStack.isEmpty()) {
            System.out.println("Error: Cannot delete. The stack is empty.");
            return;
        }
        try {
            studentStack.pop();
            System.out.println("Student removed from the stack.");
        } catch (Exception e) {
            System.out.println("Unexpected error during deletion: " + e.getMessage());
        }
    }

    public void editStudent(String id, String newName, double newMarks) {
        StudentStack tempStack = new StudentStack();
        boolean found = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                found = true;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        if (found) {
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void displayStudents() {
        System.out.println("Students in the stack:");
        studentStack.displayStack();
    }

    // Selection Sort
    public void sortStudentsSelection() {
        Student[] studentsArray = toArray();

        long startTime = System.nanoTime();
        int n = studentsArray.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (studentsArray[j].getMarks() > studentsArray[maxIndex].getMarks()) {
                    maxIndex = j;
                }
            }
            Student temp = studentsArray[maxIndex];
            studentsArray[maxIndex] = studentsArray[i];
            studentsArray[i] = temp;
        }
        long endTime = System.nanoTime();

        rebuildStack(studentsArray);
    }

    // Merge Sort
    public void mergeSortWrapper() {
        Student[] studentsArray = toArray();

        long startTime = System.nanoTime();
        mergeSort(studentsArray, 0, studentsArray.length - 1);
        long endTime = System.nanoTime();

        rebuildStack(studentsArray);
    }

    private void mergeSort(Student[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(Student[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] leftArray = new Student[n1];
        Student[] rightArray = new Student[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getMarks() >= rightArray[j].getMarks()) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private Student[] toArray() {
        StudentStack tempStack = new StudentStack();
        int count = 0;

        while (!studentStack.isEmpty()) {
            tempStack.push(studentStack.pop());
            count++;
        }

        Student[] studentsArray = new Student[count];
        for (int i = 0; i < count; i++) {
            studentsArray[i] = tempStack.pop();
            studentStack.push(studentsArray[i]);
        }

        return studentsArray;
    }

    private void rebuildStack(Student[] studentsArray) {
        studentStack = new StudentStack();
        for (Student student : studentsArray) {
            studentStack.push(student);
        }
    }

    public void searchStudent(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("Error: Invalid student ID.");
            return;
        }

        try {
            StudentStack tempStack = new StudentStack();
            boolean found = false;

            while (!studentStack.isEmpty()) {
                Student student = studentStack.pop();
                if (student.getId().equals(id)) {
                    System.out.println("Student found: " + student);
                    found = true;
                }
                tempStack.push(student);
            }

            while (!tempStack.isEmpty()) {
                studentStack.push(tempStack.pop());
            }

            if (!found) {
                System.out.println("Error: Student not found.");
            }
        } catch (Exception e) {
            System.out.println("Unexpected error during search: " + e.getMessage());
        }
    }
}






