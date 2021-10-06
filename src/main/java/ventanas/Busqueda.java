package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import analizador.Buscador;


public class Busqueda extends JFrame{
    public Busqueda(String textoTA, String palabraB) {
        creaPanel(textoTA, palabraB);
    }

	private void creaPanel(String textoTA, String palabraB) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        JTextArea textA = new JTextArea(textoTA);
        new Buscador(textA, palabraB);
        this.add(panel);
        this.setSize(400, 400);
        this.setVisible(true);
        panel.add(textA);
    }
}
