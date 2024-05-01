package src;

import fri.shapesge.Kruh;

public class NacitavacKruhov extends Nacitavac<Kruh> {

    @Override
    public void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement, Zrelativnovac zrelativnovac) {
        Kruh k = new Kruh();
        sirka *= 2;  // robime priemer z polomeru
        vyska = sirka;
        k.zmenPriemer(sirka);
        k.zmenFarbu(farba);

        // zmena pozicie
        if (!pozicia.equals("")) {
            int[] noveHodnoty = zrelativnovac.upravSuradnice(relativnyElement, pozicia, x, y, sirka, vyska);
            x = noveHodnoty[0];
            y = noveHodnoty[1];
        }

        k.zmenPolohu(x, y);
        k.zobraz();

        zrelativnovac.pridajRelativnyElement(nazov, x, y, sirka, vyska);
    }
}
