package se.iths.helena.labb2;

public class Controller {

    public static void main(String[] args) {
        printMenu();

    }

    public static void printMenu(){
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till/ändra kategorier");
        System.out.println("2. Lägg till/ändra produkter");
        System.out.println("3. Lägg till/ändra handla");
    }
}
