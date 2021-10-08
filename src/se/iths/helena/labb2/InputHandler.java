package se.iths.helena.labb2;

import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntegerInput(List<Integer> validChoices) {
        int inputAsInt;
        while (true) {
            try {
                inputAsInt = Integer.parseInt(scanner.nextLine());
                if (!validChoices.contains(inputAsInt))
                    throw new IllegalArgumentException();
                return inputAsInt;
            } catch (IllegalArgumentException e) {
                System.out.println("Val ej giltigt, försök igen genom att skriva den siffra som motsvarar ditt val.");
            }
        }
    }

    public static int getIntegerInput(String text) {
        System.out.println(text);
        return getIntegerInput();
    }

    public static int getIntegerInput(int limitLower, int limitHigher) {
        return getIntegerInput(limitLower,limitHigher,"Input ej giltig, försök igen: ");
    }

    public static int getIntegerInput(int limitLower, int limitHigher, String errorMessage) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < limitLower || input > limitHigher) {
                    throw new IllegalArgumentException();
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static int getIntegerInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Input ej giltig, försök igen: ");
            }
        }
    }

    public static long getLongInput() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Input ej giltig, försök igen: ");
            }
        }
    }

    public static long getLongInput(String text) {
        System.out.println(text);
        return getLongInput();
    }

    public static String getInput(String text) {
        System.out.println(text);
        return getInput();
    }

    public static String getInput() {
        return scanner.nextLine();
    }
}
