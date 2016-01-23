import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kamil on 08.01.2016.
 */
public class OdczytTermów {
    public double[][] termOdleglosc;
    public double[][] termPrzyspieszenie;
    public double[][] zbiorWyjsciowy;
    private String lokalizacjaTermuOdleglosc;
    private String lokalizacjaTermuPrzyspieszenie;
    private double uClose;
    private double uNormal;
    private double uLong;


    public OdczytTermów(String lokalizacjaTermuOdleglosc, String lokalizacjaTermuPrzyspieszenie) {
        this.lokalizacjaTermuOdleglosc = lokalizacjaTermuOdleglosc;
        this.lokalizacjaTermuPrzyspieszenie = lokalizacjaTermuPrzyspieszenie;
        zbiorWyjsciowy = new double[200][5];
    }

    public void odczytTermuOdleglosc() {

        Path sciezka = Paths.get(lokalizacjaTermuOdleglosc);
        ArrayList<String> odczyt = new ArrayList<String>();

        try {
            odczyt = (ArrayList) Files.readAllLines(sciezka);
        } catch (IOException e) {
            e.printStackTrace();
        }
        termOdleglosc = new double[odczyt.size()][];
        int nrLinii = 0;
                for (String linia : odczyt) {
                    String[] liniaDaneString = linia.split(";");
                    double[] liniaDouble = new double[liniaDaneString.length];
                    for (int i = 0; i < liniaDouble.length; i++) {
                        liniaDouble[i] = Double.parseDouble(liniaDaneString[i]);

            }
            termOdleglosc[nrLinii] = liniaDouble;
            nrLinii++;

        }

    }

    public void odczytTermuPrzyspieszenie() {
        Path sciezka = Paths.get(lokalizacjaTermuPrzyspieszenie);
        ArrayList<String> odczyt = new ArrayList<String>();

        try {
            odczyt = (ArrayList) Files.readAllLines(sciezka);
        } catch (IOException e) {
            e.printStackTrace();
        }
        termPrzyspieszenie = new double[odczyt.size()][];
        int nrLinii = 0;
        for (String linia : odczyt) {
            String[] liniaDaneString = linia.split(";");
            double[] liniaDouble = new double[liniaDaneString.length];
            for (int i = 0; i < liniaDouble.length; i++) {
                liniaDouble[i] = Double.parseDouble(liniaDaneString[i]);

            }
            termPrzyspieszenie[nrLinii] = liniaDouble;
            nrLinii++;

        }


    }

    public void odczytTermu() {
        Scanner sc = new Scanner(System.in);


            double liczba = sc.nextDouble();
//        for (int i = 0; i < 801; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.println(termOdleglosc[i][j]);
//            }
//        }
        // Rozmycie wartosci
            for (int i = 0; i < 4; i++) {
//                System.out.println(termOdleglosc[(int) liczba][i]);
                if (i == 1) {
                    uClose = (termOdleglosc[(int) liczba][i]);
                }
                if(i==2){
                    uNormal = (termOdleglosc[(int) liczba][i]);
                }
                if(i==3){
                    uLong = (termOdleglosc[(int) liczba][i]);
                }
            }
//            for (int i = 0; i < 4; i++) {
//                System.out.println(termPrzyspieszenie[(int) liczba][i]);
//
//            }

        //System.out.println(uClose);
//        System.out.println(uNormal);
//        System.out.println(uLong);
        reguly();
    }
    public void reguly() {
        //R1: Jeżeli odległość = Close TO przyspiszenie = Deceleration
        if (uClose > 0) {
            for (int i = 0; i < 200; i++) {
                zbiorWyjsciowy[i][0]=i;
                zbiorWyjsciowy[i][1] = Math.min(termPrzyspieszenie[i][1], uClose);

            }
        }
        if (uNormal > 0) {
            for (int i = 0; i < 200; i++) {
                zbiorWyjsciowy[i][0]=i;
                zbiorWyjsciowy[i][2] = Math.min(termPrzyspieszenie[i][2], uNormal);

            }
        }
        if (uLong > 0) {
            for (int i = 0; i < 200; i++) {
                zbiorWyjsciowy[i][0]=i;
                zbiorWyjsciowy[i][3] = Math.min(termPrzyspieszenie[i][3], uLong);

            }
        }

//        for (int i = 1; i < 200; i++) {
//            for (int j = 1; j < 4; j++) {
//                if(j==1)
//                System.out.print("Hamowanie"+zbiorWyjsciowy[i][j]+" ");
//                if(j==2)
//                    System.out.print("Stala"+zbiorWyjsciowy[i][j]+" ");
//                if(j==3)
//                    System.out.print("Przyspieszenie"+zbiorWyjsciowy[i][j]+" ");
//                System.out.println();
//            }
////        }
//        }
        agregacja();
    }

    public void agregacja() {
        for (int i = 0; i < 200; i++) {
            zbiorWyjsciowy[i][4]=Math.max(zbiorWyjsciowy[i][1],Math.max(zbiorWyjsciowy[i][2],zbiorWyjsciowy[i][3]));
        }
        for (int i = 0; i <200 ; i++) {
            System.out.println(zbiorWyjsciowy[i][4]);

        }
        wyznaczenieSrodkaCiezkosci();
    }

    public void wyznaczenieSrodkaCiezkosci() {
//        g = 0;    //licznik
//        d = 0;    //mianownik
//
//        for i=1:101
//        g = g + zbiorWYJ(5,i) * i;
//        d = d + zbiorWYJ(5,i);
//
//
//                y = g / d;
//
//        disp(y,'y = ');
        double licznik=0;
        double mianownik = 0;
        for (int i = 0; i < 200; i++) {
            licznik=licznik+zbiorWyjsciowy[i][4]*i;
            mianownik=mianownik+zbiorWyjsciowy[i][4];
        }
        double wynik = licznik/mianownik;
        System.out.println("Wynik "+ wynik);
        System.out.println(termPrzyspieszenie[(int) wynik][0]);
    }
}
