/**
 * Created by Kamil on 08.01.2016.
 */
public class OdczytTermówTest {

    public static final String TERM_A="file/TermA.txt";
    public static final String TERM_B="file/TermPrzyspieszenie.txt";
    public static void main(String[] args) {
        OdczytTermów odczytTermów =new OdczytTermów(TERM_A,TERM_B);
        odczytTermów.odczytTermuOdleglosc();
        odczytTermów.odczytTermuPrzyspieszenie();
        odczytTermów.odczytTermu();
    }
}
