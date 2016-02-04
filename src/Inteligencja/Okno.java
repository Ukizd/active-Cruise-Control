package Inteligencja;

import javax.swing.*;
import javax.swing.plaf.metal.MetalSliderUI;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Kamil on 19.01.2016.
 */
public class Okno extends JFrame {

    public static final String TERM_A="file/TermA.txt";
    public static final String TERM_B="file/TermPrzyspieszenie.txt";
//-----------------------------------------------------------------------------//
   private Timer timer,timer2;
    //private TimerTask timerTask;
//-----------------------------------------------------------------------------//
    private JPanel jPanelCentralny, jPanelContentPane;
    private JSlider jSliderPredokosc;
    private JLabel jLabelPredkoscSamochoduB;
    private JLabel jLabelOdlegloscPomiedzySamochodami;
    private JLabel jLabelSilaHamowania;
    private JLabel jLabelSilaPrzyspiszenia;
    private JProgressBar jProgressBarHamowanie,jProgressBarStala,jProgressBarPrzyspieszenie;

//-----------------------------------------------------------------------------//

    private int predkoscSamochoduB;
    private int predkoscSamochoduA;
    private int staraPredkoscSamochoduB = 0;
    private int roznica = 0;
    private int odlegloscPomiedzySamochodami;
    LogikaRozmyta logikaRozmyta =new LogikaRozmyta(TERM_A,TERM_B);



    public Okno() {

        initGUI();



        odlegloscPomiedzySamochodami = 100;


    }





    public void initGUI() {
        ustawieniaOkna();
        addJSliderPredkosc();
        addjLabelPredkoscSamochoduB();
        addjLabelOdlegloscPomiedzySamochodami();

        addjProgressBar();

    }

    private void addjProgressBar() {

        jLabelSilaPrzyspiszenia = new JLabel("Siła przyspiszenia wynosi: ");
        jProgressBarPrzyspieszenie = new JProgressBar(SwingConstants.HORIZONTAL,0,100);
        //jProgressBarHamowanie.setForeground(Color.BLUE);
        //jProgressBarPrzyspieszenie.setValue(5);
        jLabelSilaPrzyspiszenia.setBounds(340,20,500,20);
        jProgressBarPrzyspieszenie.setBounds(20,20,300,20);

        jLabelSilaHamowania = new JLabel("Siła hamowania wynosi: ");
        jProgressBarHamowanie = new JProgressBar(SwingConstants.HORIZONTAL,0,100);
        jProgressBarHamowanie.setBounds(20,50,300,20);
        jLabelSilaHamowania.setBounds(340,50,500,20);

        jPanelCentralny.add(jProgressBarPrzyspieszenie);
        jPanelCentralny.add(jProgressBarHamowanie);
        jPanelCentralny.add(jLabelSilaPrzyspiszenia);
        jPanelCentralny.add(jLabelSilaHamowania);



    }


    private void addjLabelOdlegloscPomiedzySamochodami() {
        jLabelOdlegloscPomiedzySamochodami = new JLabel("Odległość pomiędzy samochodami");
        jLabelOdlegloscPomiedzySamochodami.setBounds(200,100,300,200);
        jPanelCentralny.add(jLabelOdlegloscPomiedzySamochodami);
    }





    private void addjLabelPredkoscSamochoduB() {
        jLabelPredkoscSamochoduB = new JLabel("Predkosc Samochodu B: ");
        jLabelPredkoscSamochoduB.setBounds(230, 280, 200, 200);
        jPanelCentralny.add(jLabelPredkoscSamochoduB);

    }

    public void ustawieniaOkna() {
        this.setTitle("Tempomat aktywny");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension frameSize = new Dimension(600, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(frameSize);
        setResizable(false);
        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        jPanelContentPane = new JPanel();
        jPanelContentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(jPanelContentPane);
        jPanelCentralny = new JPanel();
        jPanelContentPane.add(jPanelCentralny, BorderLayout.CENTER);
        jPanelCentralny.setLayout(null);

//        ImageIcon img = new ImageIcon("images/CarA.png");
//        JLabel lb=new JLabel(img);





//        for (int i = 0; i < 100; i++) {
//            lb.setBounds(i,0,200,200);
//
//        }
//
//        jPanelCentralny.add(lb);
    }



    public void addJSliderPredkosc() {
        jSliderPredokosc = new JSlider(0, 50, 0);
        jSliderPredokosc.setBounds(20, 400,560, 40);
        jSliderPredokosc.setMajorTickSpacing(5);
        jSliderPredokosc.setMinorTickSpacing(1);
        jSliderPredokosc.setPaintTicks(true);
        jSliderPredokosc.setPaintLabels(true);
        // jSliderPredokosc.addChangeListener(this);

        jPanelCentralny.add(jSliderPredokosc);
        jSliderPredokosc.setUI(new MetalSliderUI() {
            protected void scrollDueToClickInTrack(int direction) {
                // this is the default behaviour, let's comment that out
                //scrollByBlock(direction);

                int value = jSliderPredokosc.getValue();
                staraPredkoscSamochoduB = value;
                if (jSliderPredokosc.getOrientation() == JSlider.HORIZONTAL) {
                    value = this.valueForXPosition(jSliderPredokosc.getMousePosition().x);
                } else if (jSliderPredokosc.getOrientation() == JSlider.VERTICAL) {
                    value = this.valueForYPosition(jSliderPredokosc.getMousePosition().y);
                }
                jSliderPredokosc.setValue(value);
                predkoscSamochoduB = value;
                roznica = staraPredkoscSamochoduB - value;
                roznica = Math.abs(roznica);
                //System.out.println(roznica);
                zmianaPredkosciSamochoduB();
            }
        });

    }

    public void zmianaPredkosciSamochoduB() {
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, 1000);

    }
    public void zmianaOdleglosci(){
        timer2= new Timer();
        timer2.schedule(new ZmianaOdleglosci(),0,1000);
    }
    public void zmianaOdlegloscFunkcja(){


    }


    public static void main(String[] args) {
        JFrame okno = new Okno();
        okno.setVisible(true);

//


    }


    class RemindTask extends TimerTask {
        int koniecZmiany = roznica;


        @Override
        public void run() {
            if (staraPredkoscSamochoduB < predkoscSamochoduB) {


            if (koniecZmiany > 0) {
                staraPredkoscSamochoduB++;
               // System.out.println(staraPredkoscSamochoduB);
                jLabelPredkoscSamochoduB.setText("Predkosc Samochodu B: "+staraPredkoscSamochoduB);
                try {
                    Thread.sleep(1000);
                    zmianaOdleglosci();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                odlegloscPomiedzySamochodami+=20;
                jLabelOdlegloscPomiedzySamochodami.setText("Odległość pomiędzy samochodami "+odlegloscPomiedzySamochodami);


                koniecZmiany--;
            } else {
               // System.out.println("koniec");
                timer.cancel();

            }
        }
            else
            {
                if (koniecZmiany > 0) {
                    staraPredkoscSamochoduB--;
                    jLabelPredkoscSamochoduB.setText("Predkosc Samochodu B: "+staraPredkoscSamochoduB);
                    try {
                        Thread.sleep(1000);
                        zmianaOdleglosci();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    odlegloscPomiedzySamochodami-=20;
                    jLabelOdlegloscPomiedzySamochodami.setText("Odległość pomiędzy samochodami "+odlegloscPomiedzySamochodami);





                    koniecZmiany--;
                } else {
                    //System.out.println("koniec");
                    timer.cancel();

                }

            }
        }
    }


    public class ZmianaOdleglosci extends TimerTask {

        @Override
        public void run() {
            double k=0;
        int odleglosc = odlegloscPomiedzySamochodami;
            //System.out.println("Odleglosc pomiedzy samochodami przed ifami"+odleglosc);
            if(odleglosc>400 ) {
                jProgressBarHamowanie.setValue(0);
                jLabelSilaHamowania.setText("Siła Hamowania wynosi: 0");
                System.out.println("Jestem w Przyspieszeniu");
                k = logikaRozmyta.silnikLogikiRozmytej(odlegloscPomiedzySamochodami);
                System.out.println("Odleglosc pomiedzy samochodami wynosi " + odleglosc);
                System.out.println("Wartosc Przyspiszenia " + k);
                int s = (int) (k * 100);
                System.out.println(s);
                jProgressBarPrzyspieszenie.setValue(s);
                jLabelSilaPrzyspiszenia.setText("Siła przyspiszenia wynosi: "+s);
            }
            if(odleglosc>0 && odleglosc<400) {
                System.out.println("Jestem w Hamowaniu");
                k = logikaRozmyta.silnikLogikiRozmytej(odlegloscPomiedzySamochodami);
                System.out.println("Odleglosc pomiedzy samochodami wynosi " + odleglosc);
                System.out.println("Wartosc Hamowania " + k);
                int s = (int) (k * 100);
                s = Math.abs(s);
                System.out.println(s);
                jProgressBarHamowanie.setValue(s);
                jLabelSilaHamowania.setText("Siła Hamowania wynosi: " + s);
            }
            if(odleglosc==0){
                jProgressBarHamowanie.setValue(0);
                jLabelSilaHamowania.setText("Siła Hamowania wynosi: 0");

            }
            if(odleglosc==800){
                jProgressBarHamowanie.setValue(800);
            jLabelSilaHamowania.setText("Siła Hamowania wynosi: 800");

        }





            timer2.cancel();
        }
    }
}

