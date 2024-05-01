package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // poznamka: snehuliak a strom je trochu opity, nevedel som to opravit...

        JFrame okno = new JFrame("Vyber suboru");
        JButton button1 = new JButton("pozicie.txt");
        JButton button2 = new JButton("pozicie-trojuholniky.txt");
        JButton button3 = new JButton("snehuliak.txt");
        JButton button4 = new JButton("strom.txt");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nacitaj("src/pozicie.txt");
                } catch (FileNotFoundException f) {
                    System.out.println(f.getMessage());
                }
                okno.dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nacitaj("src/pozicie-trojuholniky.txt");
                } catch (FileNotFoundException f) {
                    System.out.println(f.getMessage());
                }
                okno.dispose();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nacitaj("src/snehuliak.txt");
                } catch (FileNotFoundException f) {
                    System.out.println(f.getMessage());
                }
                okno.dispose();
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nacitaj("src/strom.txt");
                } catch (FileNotFoundException f) {
                    System.out.println(f.getMessage());
                }
                okno.dispose();
            }
        });

        okno.setLayout(new BorderLayout());
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.add(new JLabel("Vyberte subor, z ktoreho chcete vykreslit: "), BorderLayout.NORTH);
        JPanel tlacitka = new JPanel();
        tlacitka.add(button1);
        tlacitka.add(button2);
        tlacitka.add(button3);
        tlacitka.add(button4);
        okno.add(tlacitka, BorderLayout.CENTER);
        okno.pack();
        okno.setVisible(true);

    }

    public static void nacitaj(String nazovSuboru) throws FileNotFoundException {
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
