package se.iths.helena.labb2;

public final record Product(String name, int price, Category category, String brand, long id) {

    public void showInfo() {
        System.out.println();
        System.out.println("Namn: " + this.name());
        System.out.println("Id: " + this.id());
        System.out.println("Kategori: " + this.category().getName());
        System.out.println("Pris: " + this.price());
        System.out.println("Märke: " + this.brand());
    }
}
