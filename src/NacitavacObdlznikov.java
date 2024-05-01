package src;

import fri.shapesge.Obdlznik;

public class NacitavacObdlznikov extends Nacitavac<Obdlznik> {

    @Override
    public void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement, Zrelativnovac zrelativnovac) {
        Obdlznik o = new Obdlznik();
        o.zmenStrany(sirka, vyska);
        o.zmenFarbu(farba);

        // zmena pozicie
        if (!pozicia.equals("")) {
            int[] noveHodnoty = zrelativnovac.upravSuradnice(relativnyElement, pozicia, x, y, sirka, vyska);
            x = noveHodnoty[0];
            y = noveHodnoty[1];
        }

        o.zmenPolohu(x, y);
        o.zobraz();

        zrelativnovac.pridajRelativnyElement(nazov, x, y, sirka, vyska);
    }
}
