package Week9Prac;
import java.io.*;
import java.util.Scanner;

public class NumberArray {

	// set a constant for the maximum array size
    private static final int MAX_SIZE = 100;

    // Read numbers from file into Array
    public static ArrayResult readFileToArray(String fileName) {
        int[] numbers = new int[MAX_SIZE]; // to store numbers
        int count = 0;					   // how many numbers are read

        File file = new File(fileName);	   // create file object with given name
        Scanner fileScanner = null;

        // Stop if the file is not found
        if (!file.exists()) {
            System.out.println("File not found!");
            return new ArrayResult(numbers, 0);
        }
        
        // try to open the files
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file.");
            return new ArrayResult(numbers, 0);
        }
        
        // read each integer from the file until the end or the MAX size
        while (fileScanner.hasNextInt() && count < MAX_SIZE) {
            numbers[count] = fileScanner.nextInt();
            count++;
        }
        
        // close the files after reading
        fileScanner.close();
        // run both array and count
        return new ArrayResult(numbers, count);
    }

    // Sum of all numbers
    // adds all the numbers in the array up to the given count
    public static int calculateSum(int[] array, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += array[i];
        }
        return sum;
    }

    // Average of numbers
    // finds the average numbers in the array
    public static double calculateAverage(int[] array, int count) {
        if (count == 0) return 0; // avoid division by zero
        int sum = calculateSum(array , count); // reuse the sum methods
        return (double) calculateSum(array, count) / count; // cast to double for decimal result
    }

    // Display numbers
    // prints all the number that were stored in the array
    public static void displayArray(int[] array, int count) {
        if (count == 0) {
            System.out.println("No data.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(array[i]);
            }
        }
    }

    // Write array to file
    // saves the array contents into a new text file
    public static void writeArrayToFile(int[] array, int count, String outputFile) {
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            // write each number on a new line
            for (int i = 0; i < count; i++) {
                writer.println(array[i]);
            }
            writer.close(); // close the file after writing
            System.out.println("Saved to " + outputFile);
        } catch (IOException e) {
            System.out.println("Write error!");
        }
    }
    // main method
    // display menu and handles user choices
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // for user input
        int[] numberArray = new int[MAX_SIZE]; // array to store number
        int count = 0;						   // total numbers read
        boolean fileLoaded = false;			   // flag if files loaded
        int choice = 0;							// user menu choice

        // loop until user choices to exit
        while (choice != 6) {
        	// display menu
            System.out.println("\n1.Read file");
            System.out.println("2.Show sum");
            System.out.println("3.Show average");
            System.out.println("4.Show all numbers");
            System.out.println("5.Save to file");
            System.out.println("6.Exit");
            System.out.print("Choose: ");
            choice = input.nextInt();
            
            // option 1 read file
            if (choice == 1) {
                System.out.print("Enter filename: ");
                String fileName = input.next();
                ArrayResult result = readFileToArray(fileName);
                numberArray = result.getArray();
                count = result.getCount();
                
                // show message depending on the result
                if (count > 0) {
                    fileLoaded = true;
                    System.out.println(count + " numbers loaded.");
                } else {
                    System.out.println("No numbers loaded.");
                }
                // option 2 - display the total sum
            } else if (choice == 2) {
                if (fileLoaded) System.out.println("Sum = " + calculateSum(numberArray, count));
                else System.out.println("Load a file first.");
                
                // option 3 = display average
            } else if (choice == 3) {
                if (fileLoaded) System.out.println("Average = " + calculateAverage(numberArray, count));
                else System.out.println("Load a file first.");

                // option 4 = show all numbers
            } else if (choice == 4) {
                if (fileLoaded) displayArray(numberArray, count);
                else System.out.println("Load a file first.");
                
                // option 5 = save to file
            } else if (choice == 5) {
                if (fileLoaded) writeArrayToFile(numberArray, count, "numbersoutput.txt");
                else System.out.println("Load a file first.");
                
                // option 6 - exit progams
            } else if (choice == 6) {
                System.out.println("Goodbye!");
                
              // if invalid number entered
            } else {
                System.out.println("Invalid choice.");
            }
        }
        // close the input scanner
        input.close();
    }
}

// Helper to return array and count
class ArrayResult {
    private int[] array;
    private int count;
    
    // constructor
    public ArrayResult(int[] array, int count) {
        this.array = array;
        this.count = count;
    }
    
    // return array
    public int[] getArray() {
        return array;
    }
    
    // return number count
    public int getCount() {
        return count;
    }
}
