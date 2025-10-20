package week9Prac;

import java.io.*;
import java.util.Scanner;

public class ReadNumberFile_Claude {
  private static final int MAX_ARRAY_SIZE = 100;

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    ArrayResult result = readDataFromFile("numbers.txt");
    int[] numbers = result.getArray();
    int count = result.getCount();

    if (count == 0) {
      System.out.println("No data was read from the file. Program will exit.");
      return;
    }

    System.out.println("Successfully read " + count + " numbers from the file.");

    boolean exit = false;
    while (!exit) {
      displayMenu();
      int choice = getMenuChoice(input);

      switch (choice) {
      case 1:
        int sum = calculateSum(numbers, count);
        System.out.println("Sum of all values: " + sum);
        break;
      case 2:
        double average = calculateAverage(numbers, count);
        System.out.println("Average of all values: " + average);
        break;
      case 3:
        System.out.println("Array contents:");
        displayArrayToScreen(numbers, count);
        break;
      case 4:
        outputArrayToFile(numbers, count, "numbersoutput.txt");
        break;
      case 5:
        exit = true;
        System.out.println("Exiting program. Goodbye!");
        break;
      default:
        System.out.println("Invalid choice. Please try again.");
      }

      if (!exit) {
        System.out.println();
      }
    }

    input.close();
  }

  public static ArrayResult readDataFromFile(String filename) {
    int[] numbers = new int[MAX_ARRAY_SIZE];
    int count = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null && count < MAX_ARRAY_SIZE) {
        try {
          int number = Integer.parseInt(line.trim());
          numbers[count] = number;
          count++;
        } catch (NumberFormatException e) {
          System.out.println("Skipping invalid number: " + line);
        }
      }

      if (count == MAX_ARRAY_SIZE) {
        System.out.println("Warning: File contains more than " + MAX_ARRAY_SIZE + " numbers. Only first "
            + MAX_ARRAY_SIZE + " were read.");
      }

    } catch (FileNotFoundException e) {
      System.out.println("Error: File '" + filename + "' not found.");
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }

    return new ArrayResult(numbers, count);
  }

  public static int calculateSum(int[] numbers, int count) {
    int sum = 0;
    for (int i = 0; i < count; i++) {
      sum += numbers[i];
    }
    return sum;
  }

  public static double calculateAverage(int[] numbers, int count) {
    if (count == 0) {
      return 0.0;
    }
    return (double) calculateSum(numbers, count) / count;
  }

  public static void displayArrayToScreen(int[] numbers, int count) {
    for (int i = 0; i < count; i++) {
      System.out.println(numbers[i]);
    }
  }

  public static void outputArrayToFile(int[] numbers, int count, String filename) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
      for (int i = 0; i < count; i++) {
        writer.println(numbers[i]);
      }
      System.out.println("Array contents successfully written to " + filename);
    } catch (IOException e) {
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }

  private static void displayMenu() {
    System.out.println("Please choose an option:");
    System.out.println("1. Display sum of array values");
    System.out.println("2. Display average of array values");
    System.out.println("3. Display array contents");
    System.out.println("4. Create output file");
    System.out.println("5. Exit program");
    System.out.print("Enter your choice (1-5): ");
  }

  private static int getMenuChoice(Scanner input) {
    try {
      return Integer.parseInt(input.nextLine());
    } catch (NumberFormatException e) {
      return -1;
    }
  }
}