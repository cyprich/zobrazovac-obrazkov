package src;

import fri.shapesge.Trojuholnik;

public class NacitavacTrojuholnikov extends Nacitavac<Trojuholnik>{

    @Override
    public void nacitaj(int sirka, int vyska, int x, int y, String farba, String nazov, String pozicia, String relativnyElement) {
        Trojuholnik t = new Trojuholnik(x, y);
        t.zmenRozmery(vyska, sirka);
        t.zmenFarbu(farba);

        // zmena pozicie
        if (!pozicia.equals("")) {
            // hodnoty relativneho elementu
            int[] hodnoty = super.getRelativnyElement(relativnyElement);
            int relatX = hodnoty[0];
            int relatY = hodnoty[1];
            int relatSirka = hodnoty[2];
            int relatVyska = hodnoty[3];

            // uprava relatX, pretoze trojuholnik sa zadava od vrcholu namiesto stredu
            relatX -= relatSirka/ 4;

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
        t.zmenPolohu(x, y);
        t.zobraz();

        if (!nazov.equals("")) {
            super.pridajRelativnyElement(nazov, x, y, sirka, vyska);
        }
    }
}
