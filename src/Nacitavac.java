package src;

public abstract class Nacitavac<E> {
    public Nacitavac() {
    }

    public abstract void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement, Zrelativnovac zrelativnovac);

}
