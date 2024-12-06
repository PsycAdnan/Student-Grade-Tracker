import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentGradesTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> grades = new HashMap<>();
        double assignmentTotal = 0;
        int assignmentCount = 0;

        System.out.println("Welcome to the Student Grades Tracker by ScriptedByAdnan!");

        // Prompt user for the number of subjects
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects;
        while (true) {
            try {
                numberOfSubjects = Integer.parseInt(scanner.nextLine());
                if (numberOfSubjects > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Input subject names, grades, and assignment marks
        for (int i = 1; i <= numberOfSubjects; i++) {
            System.out.println("\n--- Subject " + i + " ---");

            System.out.print("Enter the name of subject " + i + ": ");
            String subject = scanner.nextLine();

            System.out.print("Enter grade for " + subject + " (0-100): ");
            double grade;
            while (true) {
                try {
                    grade = Double.parseDouble(scanner.nextLine());
                    if (grade >= 0 && grade <= 100) {
                        grades.put(subject, grade);
                        break;
                    } else {
                        System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value for the grade.");
                }
            }

            System.out.print("Enter assignment marks for " + subject + " (out of 10): ");
            double assignmentMark;
            while (true) {
                try {
                    assignmentMark = Double.parseDouble(scanner.nextLine());
                    if (assignmentMark >= 0 && assignmentMark <= 10) {
                        assignmentTotal += assignmentMark;
                        assignmentCount++;
                        break;
                    } else {
                        System.out.println("Invalid mark. Please enter a value between 0 and 10.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value for the assignment mark.");
                }
            }

            System.out.println(); // Break line for better readability
        }

        // Calculate and display overall grade
        if (grades.isEmpty() && assignmentCount == 0) {
            System.out.println("No grades or assignment marks available to calculate.");
        } else {
            double total = assignmentTotal * 10 / assignmentCount; // Scale assignment marks to 100
            for (double g : grades.values()) {
                total += g;
            }

            int totalItems = grades.size() + (assignmentCount > 0 ? 1 : 0);
            double average = total / totalItems;
            String letterGrade = calculateLetterGrade(average);
            double gpa = calculateGPA(average);
            String comment = getPerformanceComment(letterGrade);

            System.out.println("\n--- Overall Grade ---");
            System.out.printf("Average Grade: %.2f\n", average);
            System.out.println("Letter Grade: " + letterGrade);
            System.out.printf("GPA: %.2f\n", gpa);
            System.out.println("Performance Comment: " + comment);
        }

        System.out.println("Thank you for using the Student Grades Tracker. Goodbye!");

        scanner.close();
    }

    // Method to calculate the letter grade based on the average
    private static String calculateLetterGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // Method to calculate GPA based on the average
    private static double calculateGPA(double average) {
        if (average >= 90) {
            return 4.0;
        } else if (average >= 80) {
            return 3.0;
        } else if (average >= 70) {
            return 2.0;
        } else if (average >= 60) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    // Method to provide comments on student performance based on letter grade
    private static String getPerformanceComment(String letterGrade) {
        switch (letterGrade) {
            case "A":
                return "Excellent performance. Keep up the great work!";
            case "B":
                return "Good job! You can aim for an A next time.";
            case "C":
                return "Fair performance. Focus on improvement.";
            case "D":
                return "Needs improvement. Work harder to pass.";
            case "F":
                return "Failed. Seek help and dedicate more effort.";
            default:
                return "No comment available.";
        }
    }
}