package ventanas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import analizador.Automata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Clase encargada de manejar los elementos dentro del JPanel del menu principal. */
public class PanelMenu extends JPanel {
    JTextArea textArea;
    public PanelMenu(){
        createPanel();

    }
    /*Este metodo sirve para crear los botones del menu principal */
    public JButton createButton(String name, GridBagLayout layout, GridBagConstraints c){
        JButton button = new JButton(name);
        layout.setConstraints(button, c);
        return button;
    }
    /*Metodo encargado de crear el panel dle menu principal*/
    public void createPanel() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout (layout); // Definimos el tipo de layout
        GridBagConstraints c = new GridBagConstraints();


        //JLabel de las lineas del texto
        JLabel label = new JLabel("*IZQ*");
        c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.gridheight = 3; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=1.0; c.weightx=0.0; c.fill = GridBagConstraints.VERTICAL; //el eje y debe estirarse, el eje x no, y solo se estira en vertial
        c.anchor = GridBagConstraints.FIRST_LINE_START; //
        this.add(label, c);
        c.weighty=0.0; c.weightx=0.0;








        //text area
        textArea = new JTextArea("Escribe algo");
        c.gridx = 1; c.gridy = 0; c.gridwidth = 2; c.gridheight = 3; 
        //el weighty y weightx es el valor de si estirar o no las filas y columnas, debemos de resetarla
        //para que no afecte a los demas componentes, al igual que el fill, es es el estitar en si el elemento
        c.weighty=1.0; c.weightx=1.0; c.fill = GridBagConstraints.BOTH; 
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(textArea, c);
        c.weighty=0.0; c.weightx=0.0; //reset
        

        


        //boton abrir
        c.gridx = 3; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.NORTHEAST;

        JButton a = createButton("Abrir", layout, c);
        c.weighty=0.0;  c.anchor = GridBagConstraints.CENTER; //reset

        a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy abrir");
                new archivos.FileOpen(textArea);
			}
            
        });

        this.add(a);






        //boton guardar
        c.gridx = 3; c.gridy = 1; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST;

        JButton g = createButton("Guardar", layout, c);
        c.weighty=0.0; c.anchor = GridBagConstraints.CENTER; //reset

        g.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Guardar");
                new archivos.FileSave(textArea);
			}
            
        });
        this.add(g);






        //boton evaluar

        c.gridx = 3; c.gridy = 2; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST; 

        JButton e = createButton("Evaluar", layout, c);
        c.weighty=0.0; //reset

        e.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Evaluar");
                new Automata(textArea);
			}
            
        });
        this.add(e);







         //boton reportes

         c.gridx = 3; c.gridy = 3; c.gridwidth = 1; c.gridheight = 1; 
         c.weighty=0.0; c.anchor = GridBagConstraints.NORTHEAST;
         JButton r = createButton("Reportes", layout, c);
         c.weighty=0.0; //reset
         r.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Hola soy Reportes");
             }
             
         });
         this.add(r);
         c.fill = GridBagConstraints.NONE;




         //JLabel de la parte inferior
        JLabel labelU = new JLabel("***************************************************************************");
        c.gridx = 1; c.gridy = 4; c.gridwidth = 2; c.gridheight = 1; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=0.0; c.weightx=1.0; c.fill = GridBagConstraints.HORIZONTAL; 
        this.add(labelU, c);
        c.weighty=0.0; c.weightx=0.0;

         


    }


}
