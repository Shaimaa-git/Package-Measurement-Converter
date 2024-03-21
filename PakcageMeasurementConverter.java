import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PackageMeasurementConverter1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input string: ");
        String input = scanner.nextLine();
        scanner.close();

        List<Integer> measurements = convertToMeasurement(input);
        System.out.println("Input: " + input + " => Measurement: " + measurements);
    }

    //Converts the input string into measurements.
    //return A list of measurements.
    
    public static List<Integer> convertToMeasurement(String input) {
        List<Integer> measurements = new ArrayList<>();
        int index = 0;
        while (index < input.length()) {
            char c = input.charAt(index);
            int value = 0;
            if (c >= 'a' && c <= 'z') {
                int count = c - 'a' + 1;
                index++;
                String sequence = "";
                boolean zEncountered = false; // Flag to track if 'z' is encountered within the sequence
                for (int i = 0; i < count && index < input.length(); i++) {
                    char nextChar = input.charAt(index++);
                    if (nextChar == 'z') {
                        sequence += nextChar;
                        // Count consecutive 'z' characters and the next character
                        while (index < input.length() && input.charAt(index) == 'z') {
                            sequence += input.charAt(index++);
                        }
                        if (index < input.length()) {
                            sequence += input.charAt(index++);
                        }
                    } else if ((nextChar >= 'a' && nextChar <= 'z') || isNonAlphabetical(nextChar)) {
                        sequence += nextChar;
                        zEncountered = false;
                    } else {
                        index--; // Move back one step to process the non-alphabetical character again
                        break;
                    }
                }
                // Calculate value for the sequence if 'z' is not encountered or if it's the last character of the sequence
                if (!zEncountered || sequence.charAt(sequence.length() - 1) == 'z') {
                    value = calculateValue(sequence);
                    measurements.add(value);
                }
            } else {
                index++;
            }
        }
        return measurements;
    }

