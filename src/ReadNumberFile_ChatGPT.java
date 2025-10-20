package week9Prac;

import java.io.*;
import java.util.Scanner;

public class ReadNumberFile_ChatGPT {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    NumberData data = readNumbersFromFile("numbers.txt");

    if (data == null || data.count == 0) {
      System.out.println("No numbers were loaded. Exiting program.");
      return;
    }

    boolean running = true;
    while (running) {
      System.out.println("\nMenu:");
      System.out.println("1. Display sum of numbers");
      System.out.println("2. Display average of numbers");
      System.out.println("3. Display all numbers");
      System.out.println("4. Write numbers to numbersoutput.txt");
      System.out.println("5. Exit");
      System.out.print("Enter choice: ");

      int choice = input.nextInt();

      switch (choice) {
      case 1:
        int sum = calculateSum(data.numbers, data.count);
        System.out.println("Sum = " + sum);
        break;
      case 2:
        double avg = calculateAverage(data.numbers, data.count);
        System.out.println("Average = " + avg);
        break;
      case 3:
        displayArray(data.numbers, data.count);
        break;
      case 4:
        writeArrayToFile(data.numbers, data.count, "numbersoutput.txt");
        System.out.println("Numbers written to numbersoutput.txt");
        break;
      case 5:
        running = false;
        break;
      default:
        System.out.println("Invalid choice. Try again.");
      }
    }

    input.close();
    System.out.println("Program ended.");
  }

  public static NumberData readNumbersFromFile(String filename) {
    int[] numbers = new int[100];
    int count = 0;

    try (Scanner fileScanner = new Scanner(new File(filename))) {
      while (fileScanner.hasNextInt() && count < numbers.length) {
        numbers[count] = fileScanner.nextInt();
        count++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found -> " + filename);
      return null;
    }

    return new NumberData(numbers, count);
  }

  public static int calculateSum(int[] arr, int count) {
    int sum = 0;
    for (int i = 0; i < count; i++) {
      sum += arr[i];
    }
    return sum;
  }

  public static double calculateAverage(int[] arr, int count) {
    if (count == 0)
      return 0;
    return (double) calculateSum(arr, count) / count;
  }

  public static void displayArray(int[] arr, int count) {
    if (count == 0) {
      System.out.println("Array is empty.");
    } else {
      for (int i = 0; i < count; i++) {
        System.out.println(arr[i]);
      }
    }
  }

  public static void writeArrayToFile(int[] arr, int count, String filename) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
      for (int i = 0; i < count; i++) {
        writer.println(arr[i]);
      }
    } catch (IOException e) {
      System.out.println("Error writing to file: " + filename);
    }
  }
}
