package src;

import java.util.HashMap;

public abstract class Nacitavac<E> {
    private HashMap<String, int[]> relativneElementy;

    public Nacitavac() {
        // zoznam elemenetov pre relativne pozicie
        // obsahuje nazov elementu ako key a jeho suradnice + zomery v jednom array ako value
        this.relativneElementy = new HashMap<>();
    }

    public abstract void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement);

    public void pridajRelativnyElement(String nazov, int x, int y, int sirka, int vyska) {
        this.relativneElementy.put(nazov, new int[]{x, y, sirka, vyska});
    }

    public int[] getRelativnyElement(String nazov) {
        return this.relativneElementy.get(nazov);
    }
}
