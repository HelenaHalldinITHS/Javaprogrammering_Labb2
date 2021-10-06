package se.iths.helena.labb2;

import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInput(List<Integer> validChoices) {
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
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Input ej giltig, försök igen: ");
            }
        }
    }


    public static String getInput(String text) {
        System.out.println(text);
        return scanner.nextLine();
    }

    public static String getInput() {
        return scanner.nextLine();
    }
}
