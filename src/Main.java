package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // TODO zmenit premennu pre vykreslenie ineho suboru
        //String nazovSuboru = "src/pozicie.txt";
        //String nazovSuboru = "src/pozicie-trojuholniky.txt";
        //String nazovSuboru = "src/snehuliak.txt"; // taky opity snehuliak
        String nazovSuboru = "src/strom.txt";

        File subor = new File(nazovSuboru);
        Scanner scannerRiadky = new Scanner(subor);

        HashMap<String, Nacitavac<?>> hashMap = new HashMap<>();
        hashMap.put("Obdlznik", new NacitavacObdlznikov());
        hashMap.put("Kruh", new NacitavacKruhov());
        hashMap.put("Trojuholnik", new NacitavacTrojuholnikov());

        Zrelativnovac zrelativnovac = new Zrelativnovac();

        ArrayList<ArrayList<String>> tvary = new ArrayList<>();
        ArrayList<String> tvaryRiadky = new ArrayList<>();

        // davanie suboru do arraylistu arraylistov stringov
        while (scannerRiadky.hasNextLine()) {
            String riadok = scannerRiadky.nextLine();

            if (riadok.equals("")) {
                tvary.add(tvaryRiadky);
                tvaryRiadky = new ArrayList<>();
            } else {
                tvaryRiadky.add(riadok);
            }
        }
        // pridanie posledneho tvaru, kedze posledny tvar nema za sebou prazdny riadok, trebalo ho pridat dodatocne
        tvary.add(tvaryRiadky);

        for (ArrayList<String> tvar : tvary) {
            // v podstate zistovanie, akeho typu je dany tvar
            Nacitavac<?> nacitavac = hashMap.get(tvar.get(0).split(" ")[0]);

            // potrebne udaje pre tvary
            int sirka = 0;
            int vyska = 0;
            int x = 0;
            int y = 0;
            String farba = "";
            String nazov = "";
            String pozicia = "";
            String relativnyElement = "";

            for (String riadkyTvaru : tvar) {
                if (riadkyTvaru.contains("rozmery")) {
                    sirka = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                    vyska = Integer.parseInt(riadkyTvaru.split(" ")[2]);
                } else if (riadkyTvaru.contains("sirka")) {
                    sirka = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                } else if (riadkyTvaru.contains("vyska")) {
                    vyska = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                } else if (riadkyTvaru.contains("polomer")) {
                    sirka = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                    vyska = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                } else if (riadkyTvaru.contains("x")) {
                    x = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                } else if (riadkyTvaru.contains("y")) {
                    y = Integer.parseInt(riadkyTvaru.split(" ")[1]);
                } else if (riadkyTvaru.contains("farba")) {
                    farba = riadkyTvaru.split(" ")[1];
                } else if (riadkyTvaru.contains("nazov")) {
                    nazov = riadkyTvaru.split(" ")[1];
                } else if (riadkyTvaru.contains("pozicia")) {
                    pozicia = riadkyTvaru.split(" ")[1];
                    relativnyElement = riadkyTvaru.split(" ")[2];
                } else {
                    // ak sa nezhoduje s nicim, tak je to prvy riadok -> ziskavam nazov elementu
                    try {
                        nazov = riadkyTvaru.split(" ")[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        nazov = "";
                    }
                }
            }

            nacitavac.nacitaj(sirka, vyska, x, y, farba, nazov, pozicia, relativnyElement, zrelativnovac);
        }

        scannerRiadky.close();
    }
}
