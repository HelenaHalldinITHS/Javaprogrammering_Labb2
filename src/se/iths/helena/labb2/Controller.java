package se.iths.helena.labb2;

import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        while (true){
            printMenu();
            int input = getInput();
            if (input == 0)
                break;
            runChoice(input);

        }


    }

    private static void runChoice(int input) {
        switch (input) {
            case 1 -> CategoryFactory.run();
            case 2 -> ProductFactory.run();
            case 3 -> Store.run();
        }
    }

    private static int getInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static void printMenu(){
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till kategorier");
        System.out.println("2. Lägg till/ändra produkter");
        System.out.println("3. Handla");
        System.out.println("0. Avsluta");
    }
}
