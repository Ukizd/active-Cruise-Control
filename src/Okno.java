import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 19.01.2016.
 */
public class Okno extends JFrame {

    private JPanel jPanelCentralny,jPanelContentPane;
    private JSlider jSliderPredokosc;
    private JLabel jLabelPredkoscSamochoduB;
    public Okno(){
        initGUI();
    }

    public void initGUI() {
        ustawieniaOkna();
        addJSliderPredkosc();

    }

    private void ustawieniaOkna() {
        this.setTitle("Tempomat aktywny");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension frameSize = new Dimension(1000,600);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(frameSize);
        setResizable(false);
        this.setLocation((screenSize.width-frameSize.width)/2,
                (screenSize.height-frameSize.height)/2);
        jPanelContentPane = new JPanel();
        jPanelContentPane.setLayout(new BorderLayout(0,0));
        setContentPane(jPanelContentPane);
        jPanelCentralny = new JPanel();
        jPanelContentPane.add(jPanelCentralny,BorderLayout.CENTER);
        jPanelCentralny.setLayout(null);
    }

    public void addJSliderPredkosc() {
        jSliderPredokosc = new JSlider(0, 50, 0);
        jSliderPredokosc.setBounds(200,400,600,40);
        jSliderPredokosc.setMajorTickSpacing(5);
        jSliderPredokosc.setMinorTickSpacing(1);
        jSliderPredokosc.setPaintTicks(true);
        jSliderPredokosc.setPaintLabels(true);
        jPanelCentralny.add(jSliderPredokosc);
    }

    public static void main(String[] args) {
        JFrame okno = new Okno();
        okno.setVisible(true);
    }
}
