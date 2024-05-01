package src;

import fri.shapesge.Kruh;

public class NacitavacKruhov extends Nacitavac<Kruh> {

    @Override
    public void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement) {
        Kruh k = new Kruh(x, y);
        k.zmenPriemer(sirka);
        k.zmenFarbu(farba);

        // zmena pozicie
        if (!pozicia.equals("")) {
            // hodnoty relativneho elementu
            int[] hodnoty = super.getRelativnyElement(relativnyElement);
            int relatX = hodnoty[0];
            int relatY = hodnoty[1];
            int relatSirka = hodnoty[2];
            int relatVyska = hodnoty[3];

            if (pozicia.equals("hore")) {
                x = relatX;
                y = relatY - vyska;
            } else if (pozicia.equals("dole")) {
                x = relatX;
                y = relatY + relatSirka;
            } else if (pozicia.equals("vlavo")) {
                x = relatX - sirka;
                y = relatY;
            } else if (pozicia.equals("vpravo")) {
                x = relatX + relatVyska;
                y = relatY;
            }
        }
        k.zmenPolohu(x, y);
        k.zobraz();

        if (!nazov.equals("")) {
            super.pridajRelativnyElement(nazov, x, y, sirka, vyska);
        }
    }
}
