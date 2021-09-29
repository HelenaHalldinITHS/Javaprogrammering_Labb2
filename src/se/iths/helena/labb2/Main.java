package se.iths.helena.labb2;

public class Main {
    public static void main(String[] args) {
        //Skapa kategorier
        CategorySimpler brod = new CategorySimpler("Bröd");
        CategorySimpler mejeri = new CategorySimpler("Mejeri");
        CategorySimpler fisk = new CategorySimpler("Fisk");

        CategorySimpler fruktOchGront = new CategorySimpler("Frukt och Grönt");
        CategorySimpler frukt = new CategorySimpler("Frukt", fruktOchGront);
        CategorySimpler meloner = new CategorySimpler("Meloner", frukt);
        CategorySimpler stenfrukt = new CategorySimpler("Stenfrukt", frukt);
        CategorySimpler bar = new CategorySimpler("Bär", frukt);



        //Skapa produkter
        Product milk = new Product("Arla 2%", 20, mejeri, "Arla", 12134);
        Product Yoggi = new Product("Yoggi", 25, mejeri, "Arla", 12111);
        Product lax = new Product("Lax", 79, fisk, "ICA", 99999);
        Product hallon = new Product("Hallon", 39, bar, "ICA", 333443);

        //Create "order list";
        Products products = new Products();
        products.addProduct(milk);
        products.addProduct(hallon);

     /*   //Lägg in i affären
        Store myStore = new Store();
        myStore.addItems(milk,4);
        myStore.addItems(Yoggi, 10);
        myStore.addItems(lax, 1);

        System.out.println(myStore.itemsInStore(lax) + ", should be 1");
        myStore.removeOneItemOfProduct(lax);
        System.out.println(myStore.itemsInStore(lax) + ", should be 0");


        myStore.itemsInStore(hallon);
        myStore.addItems(milk, 4);
        System.out.println(myStore.itemsInStore(milk) + ", should be 7");
        myStore.clearInventory(milk);
        System.out.println(myStore.itemsInStore(milk));
        System.out.println(myStore.itemsInStore(hallon));*/
    }


}
