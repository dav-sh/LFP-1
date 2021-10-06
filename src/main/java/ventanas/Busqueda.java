package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import analizador.Buscador;

public class Busqueda extends JFrame{
    public Busqueda(String textoTA, String palabraB) {
        creaPanel(textoTA, palabraB);
    }

	private void creaPanel(String textoTA, String palabraB) {
        JPanel panel = new JPanel();
        JTextArea textA = new JTextArea(textoTA);
        new Buscador(textA, palabraB);
        this.add(panel);
        this.setSize(200, 200);
        this.setVisible(true);
        panel.add(textA);
    }
}
