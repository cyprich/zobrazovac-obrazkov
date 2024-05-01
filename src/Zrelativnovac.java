package src;

import java.util.HashMap;

// Zrelatívňovač
// riesi relativne suradnice pri tvaroch
public class Zrelativnovac {
    private HashMap<String, int[]> relativneElementy;

    public Zrelativnovac() {
        this.relativneElementy = new HashMap<>();
    }

    public void pridajRelativnyElement(String nazov, int x, int y, int sirka, int vyska) {
        this.relativneElementy.put(nazov, new int[]{x, y, sirka, vyska});
    }

    public int[] upravSuradnice(String nazov, String pozicia, int x, int y, int sirka, int vyska) {
        int[] hodnotyRelativnehoElementu = this.relativneElementy.get(nazov);
        int relatX = hodnotyRelativnehoElementu[0];
        int relatY = hodnotyRelativnehoElementu[1];
        int relatSirka = hodnotyRelativnehoElementu[2];
        int relatVyska = hodnotyRelativnehoElementu[3];


        if (pozicia.equals("hore")) {
            x = relatX;
            y = relatY - vyska;
        } else if (pozicia.equals("dole")) {
            x = relatX;
            y = relatY + relatVyska;
        } else if (pozicia.equals("vlavo")) {
            x = relatX - sirka;
            y = relatY;
        } else if (pozicia.equals("vpravo")) {
            x = relatX + relatSirka;
            y = relatY;
        }

        return new int[]{x, y};
    }
}
