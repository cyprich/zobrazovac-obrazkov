package src;

import fri.shapesge.Trojuholnik;

public class NacitavacTrojuholnikov extends Nacitavac<Trojuholnik> {

    @Override
    public void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement, Zrelativnovac zrelativnovac) {
        Trojuholnik t = new Trojuholnik(x, y);
        t.zmenRozmery(vyska, sirka);  // z nejakeho dovodu ma trojuholnik najprv vysku, az potom zakladnu(sirku)
        t.zmenFarbu(farba);

        // zmena pozicie
        if (!pozicia.equals("")) {
            int[] noveHodnoty = zrelativnovac.upravSuradnice(relativnyElement, pozicia, x-sirka/4, y, sirka, vyska);
            x = noveHodnoty[0];
            y = noveHodnoty[1];
        }

        t.zmenPolohu(x, y);
        t.zobraz();

        zrelativnovac.pridajRelativnyElement(nazov, x, y, sirka, vyska);
    }
}
